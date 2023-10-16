package com.trivago.fastutilconcurrentwrapper;

import com.trivago.fastutilconcurrentwrapper.map.ConcurrentBusyWaitingLongIntMap;
import com.trivago.fastutilconcurrentwrapper.map.ConcurrentLongIntMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConcurrentLongIntMapBuilderTest {
    private final int DEFAULT_VALUE = -1;

    @Test
    public void buildsBusyWaitingMap() {
        ConcurrentLongIntMapBuilder b = ConcurrentLongIntMapBuilder.newBuilder()
                .withBuckets(2)
                .withDefaultValue(DEFAULT_VALUE)
                .withInitialCapacity(100)
                .withMode(ConcurrentLongIntMapBuilder.MapMode.BUSY_WAITING)
                .withLoadFactor(0.9f);

        LongIntMap map = b.build();

        map.put(1L, 10);
        int v = map.get(1L);

        assertTrue(map instanceof ConcurrentBusyWaitingLongIntMap);
        assertEquals(10, v);
        assertEquals(map.get(2L), map.getDefaultValue());
    }

    @Test
    public void buildsBlockingMap() {
        ConcurrentLongIntMapBuilder b = ConcurrentLongIntMapBuilder.newBuilder()
                .withBuckets(2)
                .withDefaultValue(DEFAULT_VALUE)
                .withInitialCapacity(100)
                .withMode(ConcurrentLongIntMapBuilder.MapMode.BLOCKING)
                .withLoadFactor(0.9f);

        LongIntMap map = b.build();

        map.put(1L, 10);
        long v = map.get(1L);

        assertTrue(map instanceof ConcurrentLongIntMap);
        assertEquals(10, v);
        assertEquals(map.get(2L), map.getDefaultValue());
    }
}
