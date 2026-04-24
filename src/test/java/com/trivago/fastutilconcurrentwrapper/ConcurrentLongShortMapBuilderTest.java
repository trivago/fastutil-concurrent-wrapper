package com.trivago.fastutilconcurrentwrapper;

import com.trivago.fastutilconcurrentwrapper.map.ConcurrentBusyWaitingLongShortMap;
import com.trivago.fastutilconcurrentwrapper.map.ConcurrentLongShortMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConcurrentLongShortMapBuilderTest {
    private final short DEFAULT_VALUE = -1;

    @Test
    public void buildsBusyWaitingMap() {
        ConcurrentLongShortMapBuilder b = ConcurrentLongShortMapBuilder.newBuilder()
                .withBuckets(2)
                .withDefaultValue(DEFAULT_VALUE)
                .withInitialCapacity(100)
                .withMode(ConcurrentLongShortMapBuilder.MapMode.BUSY_WAITING)
                .withLoadFactor(0.9f);

        LongShortMap map = b.build();

        map.put(1L, (short) 10);
        short v = map.get(1L);

        assertTrue(map instanceof ConcurrentBusyWaitingLongShortMap);
        assertEquals(10, v);
        assertEquals(map.get(2L), map.getDefaultValue());
    }

    @Test
    public void buildsBlockingMap() {
        ConcurrentLongShortMapBuilder b = ConcurrentLongShortMapBuilder.newBuilder()
                .withBuckets(2)
                .withDefaultValue(DEFAULT_VALUE)
                .withInitialCapacity(100)
                .withMode(ConcurrentLongShortMapBuilder.MapMode.BLOCKING)
                .withLoadFactor(0.9f);

        LongShortMap map = b.build();

        map.put(1L, (short) 10);
        short v = map.get(1L);

        assertTrue(map instanceof ConcurrentLongShortMap);
        assertEquals(10, v);
        assertEquals(map.get(2L), map.getDefaultValue());
    }
}

