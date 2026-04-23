package com.trivago.fastutilconcurrentwrapper;
import com.trivago.fastutilconcurrentwrapper.map.ConcurrentBusyWaitingLongShortTreeMap;
import com.trivago.fastutilconcurrentwrapper.map.ConcurrentLongShortTreeMap;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class ConcurrentLongShortTreeMapBuilderTest {
    private final short DEFAULT_VALUE = -1;
    @Test
    public void buildsBusyWaitingMap() {
        ConcurrentLongShortTreeMapBuilder b = ConcurrentLongShortTreeMapBuilder.newBuilder()
                .withBuckets(2)
                .withDefaultValue(DEFAULT_VALUE)
                .withMode(ConcurrentLongShortTreeMapBuilder.MapMode.BUSY_WAITING);
        LongShortTreeMap map = b.build();
        map.put(1L, (short) 10);
        short v = map.get(1L);
        assertTrue(map instanceof ConcurrentBusyWaitingLongShortTreeMap);
        assertEquals(10, v);
        assertEquals(map.get(2L), map.getDefaultValue());
    }
    @Test
    public void buildsBlockingMap() {
        ConcurrentLongShortTreeMapBuilder b = ConcurrentLongShortTreeMapBuilder.newBuilder()
                .withBuckets(2)
                .withDefaultValue(DEFAULT_VALUE)
                .withMode(ConcurrentLongShortTreeMapBuilder.MapMode.BLOCKING);
        LongShortTreeMap map = b.build();
        map.put(1L, (short) 10);
        short v = map.get(1L);
        assertTrue(map instanceof ConcurrentLongShortTreeMap);
        assertEquals(10, v);
        assertEquals(map.get(2L), map.getDefaultValue());
    }
}
