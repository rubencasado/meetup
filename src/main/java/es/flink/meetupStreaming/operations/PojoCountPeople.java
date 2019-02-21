package es.flink.meetupStreaming.operations;

import es.flink.meetupStreaming.jsonparser.MeetupRSVGevent;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import java.util.Iterator;

/**
 * Created by bigdata on 10/05/16.
 */
public class PojoCountPeople implements org.apache.flink.streaming.api.functions.windowing.WindowFunction<MeetupRSVGevent, Object, Tuple, TimeWindow> {
    @Override
    public void apply(Tuple tuple, TimeWindow timeWindow, Iterable<MeetupRSVGevent> iterable, Collector<Object> collector) throws Exception {
        Tuple2<String, Integer> out = new Tuple2<String, Integer>();

        int cont= 0;
        Iterator<MeetupRSVGevent> itr = iterable.iterator();

        //simplemente contar el nº de apariciones
        while (itr.hasNext()){
            cont++;
            itr.next();
        }

        out.f0=tuple.toString();
        out.f1= new Integer(cont);

        collector.collect(out);
    }
}
