package es.flink.meetupStreaming.sinks;

import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.connectors.elasticsearch.ElasticsearchSinkFunction;
import org.apache.flink.streaming.connectors.elasticsearch.RequestIndexer;
import org.apache.flink.streaming.connectors.elasticsearch6.ElasticsearchSink;
import org.apache.flink.types.Row;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Requests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElasticSearchSinkRow {

     public static void addSink(DataStream<Row> stream, String index, String type, String hostname, int port, String schema){

         List<HttpHost> httpHosts = new ArrayList<>();
         httpHosts.add(new HttpHost(hostname, port, schema));


         ElasticsearchSink.Builder<Row> myEsSinkBuilder = new ElasticsearchSink.Builder<>(httpHosts,
                 new ElasticsearchSinkFunction<Row>() {
                     public IndexRequest createIndexRequest(Row element) {
                         Map<String, String> json = new HashMap<>();
                         for (int i=0; i< element.getArity(); i++)
                             json.put("field"+i,element.getField(i).toString());
                         return Requests.indexRequest()
                                 .index(index)
                                 .type(type)
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
         stream.addSink(myEsSinkBuilder.build());

     }
}
