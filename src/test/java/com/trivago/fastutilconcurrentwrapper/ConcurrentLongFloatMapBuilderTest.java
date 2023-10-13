package com.trivago.fastutilconcurrentwrapper;

import com.trivago.fastutilconcurrentwrapper.map.ConcurrentBusyWaitingLongFloatMap;
import com.trivago.fastutilconcurrentwrapper.map.ConcurrentBusyWaitingLongIntMap;
import com.trivago.fastutilconcurrentwrapper.map.ConcurrentLongFloatMap;
import com.trivago.fastutilconcurrentwrapper.map.ConcurrentLongIntMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConcurrentLongFloatMapBuilderTest {
    private final float DEFAULT_VALUE = -1f;

    @Test
    public void buildsBusyWaitingMap() {
        ConcurrentLongFloatMapBuilder b = ConcurrentLongFloatMapBuilder.newBuilder()
                .withBuckets(2)
                .withDefaultValue(DEFAULT_VALUE)
                .withInitialCapacity(100)
                .withMode(ConcurrentLongFloatMapBuilder.MapMode.BUSY_WAITING)
                .withLoadFactor(0.8f);

        LongFloatMap map = b.build();

        map.put(1L, 10.1f);
        float v = map.get(1L);

        assertTrue(map instanceof ConcurrentBusyWaitingLongFloatMap);
        assertEquals(10.1f, v);
        assertEquals(map.get(2L), map.getDefaultValue());
    }

    @Test
    public void buildsBlockingMap() {
        ConcurrentLongFloatMapBuilder b = ConcurrentLongFloatMapBuilder.newBuilder()
                .withBuckets(2)
                .withDefaultValue(DEFAULT_VALUE)
                .withInitialCapacity(100)
                .withMode(ConcurrentLongFloatMapBuilder.MapMode.BLOCKING)
                .withLoadFactor(0.8f);

        LongFloatMap map = b.build();

        map.put(1L, 10.1f);
        float v = map.get(1L);

        assertTrue(map instanceof ConcurrentLongFloatMap);
        assertEquals(10.1f, v);
        assertEquals(map.get(2L), map.getDefaultValue());
    }
}