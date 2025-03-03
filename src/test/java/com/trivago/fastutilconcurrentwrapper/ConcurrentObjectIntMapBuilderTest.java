package com.trivago.fastutilconcurrentwrapper;

import com.trivago.fastutilconcurrentwrapper.objectkeys.ConcurrentObjectIntMapBuilder;
import com.trivago.fastutilconcurrentwrapper.objectkeys.ObjectIntMap;
import com.trivago.fastutilconcurrentwrapper.objectkeys.map.ConcurrentBusyWaitingObjectIntMap;
import com.trivago.fastutilconcurrentwrapper.objectkeys.map.ConcurrentObjectIntMap;
import org.junit.jupiter.api.Test;

import static com.trivago.fastutilconcurrentwrapper.AbstractMapTest.nextObject;
import static org.junit.jupiter.api.Assertions.*;

public class ConcurrentObjectIntMapBuilderTest {
    private final int DEFAULT_VALUE = -1;

    @Test
    public void simpleBuilderTest() {
        ConcurrentObjectIntMapBuilder<AbstractMapTest.ObjectKey> b = ConcurrentObjectIntMapBuilder.<AbstractMapTest.ObjectKey>newBuilder()
                .withBuckets(2)
                .withDefaultValue(DEFAULT_VALUE)
                .withInitialCapacity(100)
                .withMode(ConcurrentMapBuilder.MapMode.BUSY_WAITING)
                .withLoadFactor(0.9f);

        ObjectIntMap<AbstractMapTest.ObjectKey> map = b.build();

        AbstractMapTest.ObjectKey key = nextObject();
        map.put(key, 10);
        int v = map.get(key);

        assertInstanceOf(ConcurrentBusyWaitingObjectIntMap.class, map);
        assertEquals(10, v);
        assertEquals(map.get(nextObject()), map.getDefaultValue());
    }

    @Test
    public void buildsBlockingMap() {
        ConcurrentObjectIntMapBuilder<AbstractMapTest.ObjectKey> b = ConcurrentObjectIntMapBuilder.<AbstractMapTest.ObjectKey>newBuilder()
                .withBuckets(2)
                .withDefaultValue(DEFAULT_VALUE)
                .withInitialCapacity(100)
                .withMode(ConcurrentMapBuilder.MapMode.BLOCKING)
                .withLoadFactor(0.9f);

        ObjectIntMap<AbstractMapTest.ObjectKey> map = b.build();

        AbstractMapTest.ObjectKey key = nextObject();
        map.put(key, 10);
        int v = map.get(key);

        assertInstanceOf(ConcurrentObjectIntMap.class, map);
        assertEquals(10, v);
        assertEquals(map.get(nextObject()), map.getDefaultValue());
    }
}
