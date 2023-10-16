package com.trivago.fastutilconcurrentwrapper;

import com.trivago.fastutilconcurrentwrapper.map.ConcurrentBusyWaitingIntFloatMap;
import com.trivago.fastutilconcurrentwrapper.map.ConcurrentIntFloatMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConcurrentIntFloatMapBuilderTest {
    private final float DEFAULT_VALUE = -1f;

    @Test
    public void buildsBusyWaitingMap() {
        ConcurrentIntFloatMapBuilder b = ConcurrentIntFloatMapBuilder.newBuilder()
                .withBuckets(2)
                .withDefaultValue(DEFAULT_VALUE)
                .withInitialCapacity(100)
                .withMode(ConcurrentIntFloatMapBuilder.MapMode.BUSY_WAITING)
                .withLoadFactor(0.8f);

        IntFloatMap map = b.build();

        map.put(1, 10.1f);
        float v = map.get(1);

        assertTrue(map instanceof ConcurrentBusyWaitingIntFloatMap);
        assertEquals(10.1f, v);
        assertEquals(map.get(2), map.getDefaultValue());
    }

    @Test
    public void buildsBlockingMap() {
        ConcurrentIntFloatMapBuilder b = ConcurrentIntFloatMapBuilder.newBuilder()
                .withBuckets(2)
                .withDefaultValue(DEFAULT_VALUE)
                .withInitialCapacity(100)
                .withMode(ConcurrentIntFloatMapBuilder.MapMode.BLOCKING)
                .withLoadFactor(0.8f);

        IntFloatMap map = b.build();

        map.put(1, 10.1f);
        float v = map.get(1);

        assertTrue(map instanceof ConcurrentIntFloatMap);
        assertEquals(10.1f, v);
        assertEquals(map.get(2), map.getDefaultValue());
    }
}