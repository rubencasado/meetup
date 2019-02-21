package es.flink.meetupStreaming.operations;

import org.apache.flink.api.java.tuple.Tuple1;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.Window;
import org.apache.flink.util.Collector;

import java.util.Iterator;

/**
 * Created by @ruben_casado on 10/05/16.
 */
public class CountPeople<IN> extends Window implements WindowFunction<IN, Tuple2<String, Integer>, Tuple1, Window> {


    @Override
    public void apply(Tuple1 s, Window window, Iterable<IN> iterable, Collector<Tuple2<String, Integer>> collector) throws Exception {

        Tuple2<String, Integer> out = new Tuple2<String, Integer>();

        int cont= 0;
        Iterator<IN> itr = iterable.iterator();

        //simplemente contar el nº de apariciones
        while (itr.hasNext()){
            cont++;
            itr.next();
        }

        out.f0=s.f0.toString();
        out.f1= new Integer(cont);

        collector.collect(out);
    }

    @Override
    public long maxTimestamp() {
        return 0;
    }
}

