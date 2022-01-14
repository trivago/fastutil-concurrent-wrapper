package com.trivago.kangaroo;

import it.unimi.dsi.fastutil.longs.Long2LongOpenHashMap;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@State(Scope.Benchmark)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 3, time = 2)
public class JavaUtilWrapperBenchmark extends AbstractCommonBenchHelper {

    Map<Long, Long> map;

    @Setup(Level.Trial)
    public void loadData() {
        Long2LongOpenHashMap m = new Long2LongOpenHashMap(AbstractBenchHelper.NUM_VALUES, 0.8f);
        for (int i = 0; i < AbstractBenchHelper.NUM_VALUES; i++) {
            long key = ThreadLocalRandom.current().nextLong();
            long value = ThreadLocalRandom.current().nextLong();
            m.put(key, value);
        }
        map = Collections.synchronizedMap(m);
    }

    public void testGet() {
        long key = ThreadLocalRandom.current().nextLong();
        map.get(key);
    }

    public void testPut() {
        long key = ThreadLocalRandom.current().nextLong();
        long value = ThreadLocalRandom.current().nextLong();
        map.put(key, value);
    }

    public void testAllOps() {
        int op = ThreadLocalRandom.current().nextInt(3);
        long key = ThreadLocalRandom.current().nextLong();
        switch (op) {
            case 1:
                long value = ThreadLocalRandom.current().nextLong();
                map.put(key, value);
                break;
            case 2:
                map.remove(key);
                break;
            default:
                map.get(key);
                break;
        }
    }
}
