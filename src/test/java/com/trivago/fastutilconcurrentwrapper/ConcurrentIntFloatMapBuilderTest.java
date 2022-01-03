package com.trivago.fastutilconcurrentwrapper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConcurrentIntFloatMapBuilderTest {
    @Test
    public void simpleBuilderTest() {
        ConcurrentIntFloatMapBuilder b = ConcurrentIntFloatMapBuilder.newBuilder()
                .withBuckets(2)
                .withDefaultValue(0)
                .withInitialCapacity(100)
                .withMode(ConcurrentIntFloatMapBuilder.MapMode.BUSY_WAITING)
                .withLoadFactor(0.8f);

        IntFloatMap map = b.build();

        map.put(1, 10.1f);
        float v = map.get(1);
        assertEquals(10.1f, v);
    }

    @Test
    public void simpleBuilderTest0() {
        ConcurrentIntFloatMapBuilder b = ConcurrentIntFloatMapBuilder.newBuilder()
                .withBuckets(2)
                .withDefaultValue(0)
                .withInitialCapacity(100)
                .withMode(ConcurrentIntFloatMapBuilder.MapMode.BLOCKING)
                .withLoadFactor(0.8f);

        IntFloatMap map = b.build();

        map.put(1, 10.1f);
        float v = map.get(1);
        assertEquals(10.1f, v);
    }
}