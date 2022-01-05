package com.trivago.fastutilconcurrentwrapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ConcurrentLongIntMapBuilderTest {
    @Test
    public void simpleBuilderTest() {
        ConcurrentLongIntMapBuilder b = ConcurrentLongIntMapBuilder.newBuilder()
                .withBuckets(2)
                .withDefaultValue(0)
                .withInitialCapacity(100)
                .withMode(ConcurrentLongIntMapBuilder.MapMode.BUSY_WAITING)
                .withLoadFactor(0.8f);

        LongIntMap map = b.build();

        map.put(1L, 10);
        float v = map.get(1L);
        assertEquals(10, v);
    }

    @Test
    public void simpleBuilderTest0() {
        ConcurrentLongIntMapBuilder b = ConcurrentLongIntMapBuilder.newBuilder()
                .withBuckets(2)
                .withDefaultValue(0)
                .withInitialCapacity(100)
                .withMode(ConcurrentLongIntMapBuilder.MapMode.BLOCKING)
                .withLoadFactor(0.8f);

        LongIntMap map = b.build();

        map.put(1L, 10);
        float v = map.get(1L);
        assertEquals(10, v);
    }

    @Test
    public void defaultValueTest() {
        ConcurrentLongIntMapBuilder b = ConcurrentLongIntMapBuilder.newBuilder()
                .withBuckets(2)
                .withDefaultValue(22)
                .withInitialCapacity(100)
                .withMode(ConcurrentLongIntMapBuilder.MapMode.BLOCKING)
                .withLoadFactor(0.8f);

        LongIntMap map = b.build();

        float v = map.get(1L);
        assertEquals(22, v);
    }
}