package es.flink.meetupStreaming.operations;

import es.flink.meetupStreaming.jsonparser.MeetupRSVGevent;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;

/**
 * Created by @ruben_casado on 10/05/16.
 */
public class MapTupleCity implements MapFunction <MeetupRSVGevent, Tuple2<String, MeetupRSVGevent>>{
    @Override
    public Tuple2<String, MeetupRSVGevent> map(MeetupRSVGevent event) throws Exception {

        try {
            String city = event.getGroup().getGroup_city();
            return new Tuple2<String, MeetupRSVGevent>(city, event);
        }
        catch (Exception e)
        {
            return new Tuple2<String, MeetupRSVGevent> ();
        }
    }
}
