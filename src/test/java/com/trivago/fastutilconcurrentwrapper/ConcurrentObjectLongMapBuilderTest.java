package com.trivago.fastutilconcurrentwrapper;

import com.trivago.fastutilconcurrentwrapper.objectkeys.ConcurrentObjectLongMapBuilder;
import com.trivago.fastutilconcurrentwrapper.objectkeys.ObjectLongMap;
import com.trivago.fastutilconcurrentwrapper.objectkeys.map.ConcurrentBusyWaitingObjectLongMap;
import com.trivago.fastutilconcurrentwrapper.objectkeys.map.ConcurrentObjectLongMap;
import org.junit.jupiter.api.Test;

import static com.trivago.fastutilconcurrentwrapper.AbstractMapTest.nextObject;
import static org.junit.jupiter.api.Assertions.*;

public class ConcurrentObjectLongMapBuilderTest {
    private final long DEFAULT_VALUE = -1L;

    @Test
    public void simpleBuilderTest() {
        ConcurrentObjectLongMapBuilder<AbstractMapTest.ObjectKey> b = ConcurrentObjectLongMapBuilder.<AbstractMapTest.ObjectKey>newBuilder()
                .withBuckets(2)
                .withDefaultValue(DEFAULT_VALUE)
                .withInitialCapacity(100)
                .withMode(ConcurrentMapBuilder.MapMode.BUSY_WAITING)
                .withLoadFactor(0.9f);

        ObjectLongMap<AbstractMapTest.ObjectKey> map = b.build();

        AbstractMapTest.ObjectKey key = nextObject();
        map.put(key, 10L);
        long v = map.get(key);

        assertInstanceOf(ConcurrentBusyWaitingObjectLongMap.class, map);
        assertEquals(10L, v);
        assertEquals(map.get(nextObject()), map.getDefaultValue());
    }

    @Test
    public void buildsBlockingMap() {
        ConcurrentObjectLongMapBuilder<AbstractMapTest.ObjectKey> b = ConcurrentObjectLongMapBuilder.<AbstractMapTest.ObjectKey>newBuilder()
                .withBuckets(2)
                .withDefaultValue(DEFAULT_VALUE)
                .withInitialCapacity(100)
                .withMode(ConcurrentMapBuilder.MapMode.BLOCKING)
                .withLoadFactor(0.9f);

        ObjectLongMap<AbstractMapTest.ObjectKey> map = b.build();

        AbstractMapTest.ObjectKey key = nextObject();
        map.put(key, 10L);
        long v = map.get(key);

        assertInstanceOf(ConcurrentObjectLongMap.class, map);
        assertEquals(10L, v);
        assertEquals(map.get(nextObject()), map.getDefaultValue());
    }
}
