package com.trivago.fastutilconcurrentwrapper;

import com.trivago.fastutilconcurrentwrapper.objectkeys.ConcurrentObjectFloatMapBuilder;
import com.trivago.fastutilconcurrentwrapper.objectkeys.ObjectFloatMap;
import com.trivago.fastutilconcurrentwrapper.objectkeys.map.ConcurrentBusyWaitingObjectFloatMap;
import com.trivago.fastutilconcurrentwrapper.objectkeys.map.ConcurrentObjectFloatMap;
import org.junit.jupiter.api.Test;

import static com.trivago.fastutilconcurrentwrapper.AbstractMapTest.nextObject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class ConcurrentObjectFloatMapBuilderTest {
    private final float DEFAULT_VALUE = -1;

    @Test
    public void simpleBuilderTest() {
        ConcurrentObjectFloatMapBuilder<AbstractMapTest.ObjectKey> b = ConcurrentObjectFloatMapBuilder.<AbstractMapTest.ObjectKey>newBuilder()
                .withBuckets(2)
                .withDefaultValue(DEFAULT_VALUE)
                .withInitialCapacity(100)
                .withMode(ConcurrentMapBuilder.MapMode.BUSY_WAITING)
                .withLoadFactor(0.9f);

        ObjectFloatMap<AbstractMapTest.ObjectKey> map = b.build();

        AbstractMapTest.ObjectKey key = nextObject();
        map.put(key, 10F);
        float v = map.get(key);

        assertInstanceOf(ConcurrentBusyWaitingObjectFloatMap.class, map);
        assertEquals(10F, v);
        assertEquals(map.get(nextObject()), map.getDefaultValue());
    }

    @Test
    public void buildsBlockingMap() {
        ConcurrentObjectFloatMapBuilder<AbstractMapTest.ObjectKey> b = ConcurrentObjectFloatMapBuilder.<AbstractMapTest.ObjectKey>newBuilder()
                .withBuckets(2)
                .withDefaultValue(DEFAULT_VALUE)
                .withInitialCapacity(100)
                .withMode(ConcurrentMapBuilder.MapMode.BLOCKING)
                .withLoadFactor(0.9f);

        ObjectFloatMap<AbstractMapTest.ObjectKey> map = b.build();

        AbstractMapTest.ObjectKey key = nextObject();
        map.put(key, 10F);
        float v = map.get(key);

        assertInstanceOf(ConcurrentObjectFloatMap.class, map);
        assertEquals(10F, v);
        assertEquals(map.get(nextObject()), map.getDefaultValue());
    }
}
