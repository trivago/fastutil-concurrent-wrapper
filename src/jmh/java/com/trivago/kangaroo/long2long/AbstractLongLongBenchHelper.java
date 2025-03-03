package com.trivago.kangaroo.long2long;

import com.trivago.fastutilconcurrentwrapper.ConcurrentLongLongMapBuilder;
import com.trivago.fastutilconcurrentwrapper.ConcurrentMapBuilder;
import com.trivago.fastutilconcurrentwrapper.LongLongMap;
import com.trivago.kangaroo.AbstractCommonBenchHelper;

import java.util.concurrent.ThreadLocalRandom;

public abstract class AbstractLongLongBenchHelper extends AbstractCommonBenchHelper {

    protected static final int NUM_VALUES = 1_000_000;

    protected LongLongMap map;

    public void initAndLoadData(ConcurrentMapBuilder.MapMode mode) {
        map = ConcurrentLongLongMapBuilder.newBuilder()
                .withBuckets(16)
                .withInitialCapacity(NUM_VALUES)
                .withMode(mode)
                .withLoadFactor(0.8f)
                .build();

        for (int i = 0; i < NUM_VALUES; i++) {
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
