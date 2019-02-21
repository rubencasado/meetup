package es.flink.meetupStreaming;

/**
 * Created by ruben.casado.tejedor on 24/2/18.
 */

import  es.flink.meetupStreaming.jsonparser.MeetupRSVGevent;
import org.apache.flink.api.common.functions.FilterFunction;

public class FiltrarNulos implements FilterFunction<MeetupRSVGevent> {
    @Override
    public boolean filter(MeetupRSVGevent event) throws Exception {
        return event.getGroup()!=null &&
                event.getResponse()!=null &&
                event.getEvent()!=null &&
                event.getGuests()!=null &&
                event.getVenue()!=null &&
                event.getGroup()!=null;
    }
}
