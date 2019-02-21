package es.flink.meetupStreaming.meetupWebSocketSource;


/**
 * Created by @ruben_casado and @0xNacho on 9/05/16.
 */

import es.flink.meetupStreaming.jsonparser.MeetupRSVGevent;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;


import java.io.IOException;
import java.net.URI;


//wss://stream.meetup.com/2/rsvps

public class MeetupStreamingSource implements SourceFunction<MeetupRSVGevent> {

    protected String uri;
    public  MeetupStreamingSource(String uri){
        this.uri=uri;
    }


    @Override
    public void run(final SourceContext<MeetupRSVGevent> sourceContext) throws Exception {

        final MeetupEndpoint clientEndPoint = new MeetupEndpoint(
                new URI(uri));
        clientEndPoint.addMessageHandler(new MeetupEndpoint.MessageHandler() {
            public void handleMessage(String message) {
                ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
                mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                MeetupRSVGevent event = null;
                try {
                    event = mapper.readValue(message, MeetupRSVGevent.class);
                    sourceContext.collect(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
        while(true){
            Thread.sleep(2000);
        }
    }

    @Override
    public void cancel() {
        try {
            System.out.println("cancel");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
