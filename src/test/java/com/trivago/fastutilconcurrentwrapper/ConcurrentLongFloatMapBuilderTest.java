package com.trivago.fastutilconcurrentwrapper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConcurrentLongFloatMapBuilderTest {
    @Test
    public void simpleBuilderTest() {
        ConcurrentLongFloatMapBuilder b = ConcurrentLongFloatMapBuilder.newBuilder()
                .withBuckets(2)
                .withDefaultValue(0)
                .withInitialCapacity(100)
                .withMode(ConcurrentLongFloatMapBuilder.MapMode.BUSY_WAITING)
                .withLoadFactor(0.8f);

        LongFloatMap map = b.build();

        map.put(1L, 10.1f);
        float v = map.get(1L);
        assertEquals(10.1f, v);
    }

    @Test
    public void simpleBuilderTest0() {
        ConcurrentLongFloatMapBuilder b = ConcurrentLongFloatMapBuilder.newBuilder()
                .withBuckets(2)
                .withDefaultValue(0)
                .withInitialCapacity(100)
                .withMode(ConcurrentLongFloatMapBuilder.MapMode.BLOCKING)
                .withLoadFactor(0.8f);

        LongFloatMap map = b.build();

        map.put(1L, 10.1f);
        float v = map.get(1L);
        assertEquals(10.1f, v);
    }

    @Test
    public void defaultValueTest() {
        ConcurrentLongFloatMapBuilder b = ConcurrentLongFloatMapBuilder.newBuilder()
                .withBuckets(2)
                .withDefaultValue(22.22f)
                .withInitialCapacity(100)
                .withMode(ConcurrentLongFloatMapBuilder.MapMode.BLOCKING)
                .withLoadFactor(0.8f);

        LongFloatMap map = b.build();

        float v = map.get(1L);
        assertEquals(22.22f, v);
    }
}