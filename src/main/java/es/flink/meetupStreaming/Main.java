package es.flink.meetupStreaming;

import es.flink.meetupStreaming.jsonparser.MeetupRSVGevent;
import es.flink.meetupStreaming.jsonparser.Venue;
import es.flink.meetupStreaming.meetupWebSocketSource.MeetupStreamingSource;
import es.flink.meetupStreaming.sinks.ElasticSearchSinkRow;
import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.elasticsearch.ElasticsearchSinkFunction;
import org.apache.flink.streaming.connectors.elasticsearch.RequestIndexer;
import org.apache.flink.streaming.connectors.elasticsearch6.ElasticsearchSink;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.api.java.StreamTableEnvironment;
import org.apache.flink.types.Row;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Requests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by @ruben_casado on 10/05/16.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        // set up the execution environment
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        StreamTableEnvironment tableEnv = TableEnvironment.getTableEnvironment(env);

       String url = "wss://stream.meetup.com/2/rsvps";
        DataStream<MeetupRSVGevent> events = env.addSource(new MeetupStreamingSource(url));


        tableEnv.registerDataStream("Events", events, "event, response, visibility, mtime, guests, rsvp_id, group, member, venue, UserActionTime.proctime");

        String query = "SELECT event.event_name, count (event.event_name) FROM Events GROUP BY TUMBLE (UserActionTime, INTERVAL '5' SECOND), event.event_name";
        Table table = tableEnv.sqlQuery(query);
        DataStream<Row> sql_result = tableEnv.toAppendStream(table, Row.class);
        sql_result.print();

       // ElasticSearchSinkRow.addSink(sql_result, "my-index", "my-type");

        DataStream<String> input = env.fromElements("hola", "adios");

        List<HttpHost> httpHosts = new ArrayList<>();
        httpHosts.add(new HttpHost("127.0.0.1", 9200, "http"));


        ElasticsearchSink.Builder<Row> myEsSinkBuilder = new ElasticsearchSink.Builder<>(httpHosts,
                new ElasticsearchSinkFunction<Row>() {
                    public IndexRequest createIndexRequest(Row element) {
                        Map<String, String> json = new HashMap<>();
                        json.put("name", element.getField(0).toString());
                        json.put("count", element.getField(1).toString());

                        return Requests.indexRequest()
                                .index("my-index")
                                .type("my-type")
                                .source(json);
                    }

                    @Override
                    public void process(Row element, RuntimeContext ctx, RequestIndexer indexer) {
                        indexer.add(createIndexRequest(element));
                    }
                }
        );


        // configuration for the bulk requests; this instructs the sink to emit after every element, otherwise they would be buffered
        myEsSinkBuilder.setBulkFlushMaxActions(1);
        // finally, build and add the sink to the job's pipeline
        sql_result.addSink(myEsSinkBuilder.build());



    /*
        DataStream<Tuple2<String, Integer>> cuenta= events
                .keyBy("group.group_name")
                .timeWindow(Time.seconds(5))
                .apply(new ContarVentanaGroup());

        cuenta.print();
    */


    /*

        events.filter(new FiltrarNulos())
                .setParallelism(2)
                .flatMap(new GetWordsTopics())
                .keyBy(0)
                .timeWindow(Time.seconds(60), Time.seconds(10))
                .sum(1)
                .print();


        events.filter(new FilterNullsEvents())
                .keyBy("venue")
                .timeWindow(Time.seconds(5))
                .apply(new PojoCountPeople()).print();




        events.map(new MapTupleCity())                              // mapea a Tuple2 (ciudad, registro)
                .filter(new FilterNulls())                          // eliminar tuplas mal formadas
                .keyBy(0)                                           // agrupar por ciudad
                .timeWindow(Time.seconds(15), Time.seconds(2))      // ventanas deslizante de 15sg actualizado cada 2
                .apply(new CountPeople())                           // contar registros en cada ventana
                .print();

        */

        env.execute("Meetup Flink DataStream");
    }
}
