package es.flink.meetupStreaming;

/**
 * Created by ruben.casado.tejedor on 24/2/18.
 */

import es.flink.meetupStreaming.jsonparser.Group_topics;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;

import java.util.ArrayList;
import java.util.Iterator;

public class GetTopic implements FlatMapFunction<java.util.ArrayList<Group_topics>, String> {
    @Override
    public void flatMap(ArrayList<Group_topics> topicss, Collector<String> collector) throws Exception {

        Iterator<Group_topics> itr = topicss.iterator();

        while(itr.hasNext()) {
            collector.collect(itr.next().getTopic_name());
        }
    }
}
