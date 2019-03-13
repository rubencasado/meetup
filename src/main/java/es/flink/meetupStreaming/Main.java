package es.flink.meetupStreaming;

import es.flink.meetupStreaming.jsonparser.*;
import es.flink.meetupStreaming.meetupWebSocketSource.MeetupStreamingSource;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;


/**
 *  Entry point.
 */
public class Main {

    public static void main(String[] args) throws Exception {


        // set up the execution environment
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        //add the event source
        String url = "wss://stream.meetup.com/2/rsvps";
        DataStream<MeetupRSVGevent> events = env.addSource(new MeetupStreamingSource(url));

        /********
         *
         * implemente the business logic
         *
          */



        /* add the sink */
        events.print();


        env.execute("Meetup Flink DataStream");
    }
}
