package com.trivago.fastutilconcurrentwrapper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConcurrentIntIntMapBuilderTest {
    @Test
    public void simpleBuilderTest() {
        ConcurrentIntIntMapBuilder b = ConcurrentIntIntMapBuilder.newBuilder()
                .withBuckets(2)
                .withDefaultValue(0)
                .withInitialCapacity(100)
                .withMode(ConcurrentIntIntMapBuilder.MapMode.BUSY_WAITING)
                .withLoadFactor(0.8f);

        IntIntMap map = b.build();

        map.put(1, 10);
        float v = map.get(1);
        assertEquals(10, v);
    }

    @Test
    public void simpleBuilderTest0() {
        ConcurrentIntIntMapBuilder b = ConcurrentIntIntMapBuilder.newBuilder()
                .withBuckets(2)
                .withDefaultValue(0)
                .withInitialCapacity(100)
                .withMode(ConcurrentIntIntMapBuilder.MapMode.BLOCKING)
                .withLoadFactor(0.8f);

        IntIntMap map = b.build();

        map.put(1, 10);
        float v = map.get(1);
        assertEquals(10, v);
    }
}