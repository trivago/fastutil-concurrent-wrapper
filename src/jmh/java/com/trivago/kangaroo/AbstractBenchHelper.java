package com.trivago.kangaroo;

import com.trivago.fastutilconcurrentwrapper.ConcurrentLongLongMapBuilder;
import com.trivago.fastutilconcurrentwrapper.LongLongMap;

import java.util.concurrent.ThreadLocalRandom;

public abstract class AbstractBenchHelper extends AbstractCommonBenchHelper {

    protected static final int NUM_VALUES = 1_000_000;

    protected LongLongMap map;

    public void initAndLoadData(ConcurrentLongLongMapBuilder.MapMode mode) {
        if (mode.equals(ConcurrentLongLongMapBuilder.MapMode.BUSY_WAITING)) {
            map = ConcurrentLongLongMapBuilder.newBuilder()
                    .withBuckets(16)
                    .withInitialCapacity(5_000_000)
                    .withMode(ConcurrentLongLongMapBuilder.MapMode.BUSY_WAITING)
                    .withLoadFactor(0.8f)
                    .build();
        } else {
            map = ConcurrentLongLongMapBuilder.newBuilder()
                    .withBuckets(16)
                    .withInitialCapacity(5_000_000)
                    .withLoadFactor(0.8f)
                    .build();
        }

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
