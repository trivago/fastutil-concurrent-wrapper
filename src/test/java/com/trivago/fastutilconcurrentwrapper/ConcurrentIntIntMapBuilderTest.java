package com.trivago.fastutilconcurrentwrapper;

import com.trivago.fastutilconcurrentwrapper.map.ConcurrentBusyWaitingIntIntMap;
import com.trivago.fastutilconcurrentwrapper.map.ConcurrentIntIntMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConcurrentIntIntMapBuilderTest {
    private final int DEFAULT_VALUE = -1;

    @Test
    public void buildsBusyWaitingMap() {
        ConcurrentIntIntMapBuilder b = ConcurrentIntIntMapBuilder.newBuilder()
                .withBuckets(2)
                .withDefaultValue(DEFAULT_VALUE)
                .withInitialCapacity(100)
                .withMode(ConcurrentIntIntMapBuilder.MapMode.BUSY_WAITING)
                .withLoadFactor(0.8f);

        IntIntMap map = b.build();

        map.put(1, 10);
        float v = map.get(1);

        assertTrue(map instanceof ConcurrentBusyWaitingIntIntMap);
        assertEquals(10, v);
        assertEquals(map.get(2), map.getDefaultValue());
    }

    @Test
    public void buildsBlockingMap() {
        ConcurrentIntIntMapBuilder b = ConcurrentIntIntMapBuilder.newBuilder()
                .withBuckets(2)
                .withDefaultValue(DEFAULT_VALUE)
                .withInitialCapacity(100)
                .withMode(ConcurrentIntIntMapBuilder.MapMode.BLOCKING)
                .withLoadFactor(0.8f);

        IntIntMap map = b.build();

        map.put(1, 10);
        float v = map.get(1);

        assertTrue(map instanceof ConcurrentIntIntMap);
        assertEquals(10, v);
        assertEquals(map.get(2), map.getDefaultValue());
    }
}