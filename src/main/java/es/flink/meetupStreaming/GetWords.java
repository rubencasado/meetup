package es.flink.meetupStreaming;

import org.apache.flink.util.Collector;

/**
 * Created by ruben.casado.tejedor on 24/2/18.
 */
public class GetWords implements org.apache.flink.api.common.functions.FlatMapFunction<String, String> {
    @Override
    public void flatMap(String s, Collector<String> collector) throws Exception {
        for (String word: s.split((" "))) {
            collector.collect(word);
        }
    }
}
