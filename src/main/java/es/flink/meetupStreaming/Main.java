package es.flink.meetupStreaming;

import es.flink.meetupStreaming.jsonparser.MeetupRSVGevent;
import es.flink.meetupStreaming.meetupWebSocketSource.MeetupStreamingSource;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;

/**
 * Created by @ruben_casado on 10/05/16.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        // set up the execution environment
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        String url = "wss://stream.meetup.com/2/rsvps";

        DataStream<MeetupRSVGevent> events = env.addSource(new MeetupStreamingSource(url));


        DataStream<Tuple2<String, Integer>> cuenta= events
                .keyBy("group.group_name")
                .timeWindow(Time.seconds(5))
                .apply(new ContarVentanaGroup());

        cuenta.print();

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
