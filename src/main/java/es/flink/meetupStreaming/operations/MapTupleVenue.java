package es.flink.meetupStreaming.operations;

import es.flink.meetupStreaming.jsonparser.MeetupRSVGevent;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;

/**
 * Created by @ruben_casado on 10/05/16.
 */
public class MapTupleVenue implements MapFunction <MeetupRSVGevent, Tuple2<String, MeetupRSVGevent>>{
    @Override
    public Tuple2<String, MeetupRSVGevent> map(MeetupRSVGevent event) throws Exception {

        try {
            String venue = event.getVenue().getVenue_name();
            return new Tuple2<String, MeetupRSVGevent>(venue, event);
        }
        catch (Exception e)
        {
            return new Tuple2<String, MeetupRSVGevent> ();
        }
    }
}
