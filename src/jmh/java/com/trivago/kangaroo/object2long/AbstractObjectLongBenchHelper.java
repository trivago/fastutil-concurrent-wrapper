package com.trivago.kangaroo.object2long;

import com.trivago.fastutilconcurrentwrapper.ConcurrentMapBuilder;
import com.trivago.fastutilconcurrentwrapper.objectkeys.ConcurrentObjectLongMapBuilder;
import com.trivago.fastutilconcurrentwrapper.objectkeys.ObjectLongMap;
import com.trivago.kangaroo.AbstractCommonBenchHelper;

import java.util.concurrent.ThreadLocalRandom;

public abstract class AbstractObjectLongBenchHelper extends AbstractCommonBenchHelper {

    protected static final int NUM_VALUES = 1_000_000;

    protected ObjectLongMap<TestObjectKey> map;

    public void initAndLoadData(ConcurrentMapBuilder.MapMode mode) {
        map = ConcurrentObjectLongMapBuilder.<TestObjectKey>newBuilder()
                .withBuckets(16)
                .withInitialCapacity(NUM_VALUES)
                .withMode(mode)
                .withLoadFactor(0.8f)
                .build();

        for (int i = 0; i < NUM_VALUES; i++) {
            TestObjectKey key = new TestObjectKey();
            long value = ThreadLocalRandom.current().nextLong();
            map.put(key, value);
        }
    }

    public void testGet() {
        TestObjectKey key = new TestObjectKey();
        map.get(key);
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
