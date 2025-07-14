package com.trivago.fastutilconcurrentwrapper;

import com.trivago.fastutilconcurrentwrapper.map.ConcurrentBusyWaitingIntLongMap;
import com.trivago.fastutilconcurrentwrapper.map.ConcurrentIntLongMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ConcurrentIntLongMapBuilderTest {
    private final long DEFAULT_VALUE = -1L;

    @Test
    public void simpleBuilderTest() {
        ConcurrentIntLongMapBuilder b = ConcurrentIntLongMapBuilder.newBuilder()
                .withBuckets(2)
                .withDefaultValue(DEFAULT_VALUE)
                .withInitialCapacity(100)
                .withMode(ConcurrentIntLongMapBuilder.MapMode.BUSY_WAITING)
                .withLoadFactor(0.9f);

        IntLongMap map = b.build();

        map.put(1, 10L);
        long v = map.get(1);

        assertInstanceOf(ConcurrentBusyWaitingIntLongMap.class, map);
        assertEquals(10L, v);
        assertEquals(map.get(2), map.getDefaultValue());
    }

    @Test
    public void buildsBlockingMap() {
        ConcurrentIntLongMapBuilder b = ConcurrentIntLongMapBuilder.newBuilder()
                .withBuckets(2)
                .withDefaultValue(DEFAULT_VALUE)
                .withInitialCapacity(100)
                .withMode(ConcurrentIntLongMapBuilder.MapMode.BLOCKING)
                .withLoadFactor(0.9f);

        IntLongMap map = b.build();

        map.put(1, 10L);
        long v = map.get(1);

        assertInstanceOf(ConcurrentIntLongMap.class, map);
        assertEquals(10L, v);
        assertEquals(map.get(2), map.getDefaultValue());
    }
}
