package com.trivago.kangaroo.object2long;

import com.trivago.kangaroo.AbstractCommonBenchHelper;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
import org.openjdk.jmh.annotations.*;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@State(Scope.Benchmark)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 3, time = 2)
public class JavaUtilWrapperObjectLongBenchmark extends AbstractCommonBenchHelper {

    Map<TestObjectKey, Long> map;

    @Setup(Level.Trial)
    public void loadData() {
        Object2LongOpenHashMap<TestObjectKey> m = new Object2LongOpenHashMap<>(AbstractObjectLongBenchHelper.NUM_VALUES, 0.8f);
        for (int i = 0; i < AbstractObjectLongBenchHelper.NUM_VALUES; i++) {
            TestObjectKey key = new TestObjectKey();
            long value = ThreadLocalRandom.current().nextLong();
            m.put(key, value);
        }
        map = Collections.synchronizedMap(m);
    }

    public void testGet() {
        map.get(new TestObjectKey());
    }

    public void testPut() {
        TestObjectKey key = new TestObjectKey();
        long value = ThreadLocalRandom.current().nextLong();
        map.put(key, value);
    }

    public void testAllOps() {
        int op = ThreadLocalRandom.current().nextInt(3);
        TestObjectKey key = new TestObjectKey();
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
