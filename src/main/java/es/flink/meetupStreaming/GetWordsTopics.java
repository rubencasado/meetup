package es.flink.meetupStreaming;

/**
 * Created by ruben.casado.tejedor on 24/2/18.
 */

import es.flink.meetupStreaming.jsonparser.Group_topics;
import es.flink.meetupStreaming.jsonparser.MeetupRSVGevent;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

import java.util.ArrayList;

public class GetWordsTopics implements FlatMapFunction <MeetupRSVGevent, Tuple2<String, Integer>> {
    @Override
    public void flatMap(MeetupRSVGevent event, Collector<Tuple2<String, Integer>> collector) throws Exception {

        ArrayList<Group_topics> topics= event.getGroup().getGroup_topics();

        for (int i=0; i< topics.size(); i++) {
            Group_topics topic = topics.get(i);
            String [] words = topic.getTopic_name().split(" ");
            for (int j=0; j < words.length; j++){
                collector.collect(new Tuple2<String, Integer> (words[j], 1));
            }
        }
    }
}
