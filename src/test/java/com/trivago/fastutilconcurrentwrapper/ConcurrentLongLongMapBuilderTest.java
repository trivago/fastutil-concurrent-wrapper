package com.trivago.fastutilconcurrentwrapper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConcurrentLongLongMapBuilderTest {

    @Test
    public void simpleBuilderTest() {
        ConcurrentLongLongMapBuilder b = ConcurrentLongLongMapBuilder.newBuilder()
                .withBuckets(2)
                .withDefaultValue(0)
                .withInitialCapacity(100)
                .withMode(ConcurrentLongLongMapBuilder.MapMode.BUSY_WAITING)
                .withLoadFactor(0.9f);

        LongLongMap map = b.build();

        map.put(1L, 10L);
        long v = map.get(1L);
        assertEquals(10L, v);
    }
}
