package com.trivago.kangaroo.long2long;

import com.trivago.kangaroo.AbstractCommonBenchHelper;
import org.openjdk.jmh.annotations.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

@State(Scope.Benchmark)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 3, time = 2)
public class JavaConcurrentLongLongBenchmark extends AbstractCommonBenchHelper {

    Map<Long, Long> map;

    @Setup(Level.Trial)
    public void loadData() {
        map = new ConcurrentHashMap<>(AbstractLongLongBenchHelper.NUM_VALUES, 0.8f);
        for (int i = 0; i < AbstractLongLongBenchHelper.NUM_VALUES; i++) {
            long key = ThreadLocalRandom.current().nextLong();
            long value = ThreadLocalRandom.current().nextLong();
            map.put(key, value);
        }
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
