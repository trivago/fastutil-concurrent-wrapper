package com.trivago.fastutilconcurrentwrapper;

import com.trivago.fastutilconcurrentwrapper.primitivekeys.ConcurrentLongLongMapBuilder;
import com.trivago.fastutilconcurrentwrapper.primitivekeys.LongLongMap;
import com.trivago.fastutilconcurrentwrapper.primitivekeys.map.ConcurrentBusyWaitingLongLongMap;
import com.trivago.fastutilconcurrentwrapper.primitivekeys.map.ConcurrentLongLongMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConcurrentLongLongMapBuilderTest {
    private final long DEFAULT_VALUE = -1L;

    @Test
    public void simpleBuilderTest() {
        ConcurrentLongLongMapBuilder b = ConcurrentLongLongMapBuilder.newBuilder()
                .withBuckets(2)
                .withDefaultValue(DEFAULT_VALUE)
                .withInitialCapacity(100)
                .withMode(ConcurrentLongLongMapBuilder.MapMode.BUSY_WAITING)
                .withLoadFactor(0.9f);

        LongLongMap map = b.build();

        map.put(1L, 10L);
        long v = map.get(1L);

        assertTrue(map instanceof ConcurrentBusyWaitingLongLongMap);
        assertEquals(10L, v);
        assertEquals(map.get(2L), map.getDefaultValue());
    }

    @Test
    public void buildsBlockingMap() {
        ConcurrentLongLongMapBuilder b = ConcurrentLongLongMapBuilder.newBuilder()
                .withBuckets(2)
                .withDefaultValue(DEFAULT_VALUE)
                .withInitialCapacity(100)
                .withMode(ConcurrentLongLongMapBuilder.MapMode.BLOCKING)
                .withLoadFactor(0.9f);

        LongLongMap map = b.build();

        map.put(1L, 10L);
        long v = map.get(1L);

        assertTrue(map instanceof ConcurrentLongLongMap);
        assertEquals(10L, v);
        assertEquals(map.get(2L), map.getDefaultValue());
    }
}
