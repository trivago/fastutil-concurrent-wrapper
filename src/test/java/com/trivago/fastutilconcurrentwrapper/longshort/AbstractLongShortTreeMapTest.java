package com.trivago.fastutilconcurrentwrapper.longshort;

import com.trivago.fastutilconcurrentwrapper.AbstractMapTest;
import com.trivago.fastutilconcurrentwrapper.LongShortTreeMap;
import it.unimi.dsi.fastutil.longs.Long2ShortMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

abstract class AbstractLongShortTreeMapTest extends AbstractMapTest {
    private static final short FASTUTIL_DEFAULT_VALUE = 0;
    protected short defaultValue;
    private LongShortTreeMap map;

    abstract LongShortTreeMap createMap();

    @BeforeEach
    void initializeMap() {
        defaultValue = nextShort();
        map = createMap();
    }

    @Test
    protected void containsKeyReturnsFalseIfMapIsEmpty() {
        final long key = nextLong();
        assertFalse(map.containsKey(key));
    }

    @Test
    protected void containsKeyReturnsTrueIfKeyExists() {
        long key = nextLong();
        short value = nextShort();
        map.put(key, value);
        assertTrue(map.containsKey(key));
    }

    @Test
    protected void containsKeyReturnsFalseIfKeyWasRemoved() {
        long key = nextLong();
        short value = nextShort();
        map.put(key, value);
        map.remove(key);
        assertFalse(map.containsKey(key));
    }

    @Test
    protected void mapIsEmptyWhenNothingWasInserted() {
        assertTrue(map.isEmpty());
    }

    @Test
    protected void mapIsEmptyWhenAllKeysAreDeleted() {
        int entryCount = (Math.abs(nextInt()) % 100) + 1;
        short value = nextShort();
        for (long key = 1; key <= entryCount; key++) {
            map.put(key, value);
        }
        for (long key = 1; key <= entryCount; key++) {
            map.remove(key);
        }
        assertTrue(map.isEmpty());
    }

    @Test
    protected void sizeIsCorrect() {
        int entries = (Math.abs(nextInt()) % 50) + 1;
        short value = nextShort();
        for (long key = 1; key <= entries; key++) {
            map.put(key, value);
        }
        assertEquals(entries, map.size());
    }

    @Test
    protected void gettingExistingValueReturnsCorrectValue() {
        long key = nextLong();
        short value = nextShort();
        map.put(key, value);
        assertEquals(value, map.get(key));
    }

    @Test
    protected void gettingNonExistingValueReturnsCorrectValue() {
        long key = nextLong();
        assertEquals(defaultValue, map.get(key));
    }

    @Test
    protected void removingNonExistingKeyReturnsDefaultValue() {
        long key = nextLong();
        assertEquals(defaultValue, map.remove(key));
    }

    @Test
    protected void removingExistingKeyReturnsPreviousValue() {
        long key = nextLong();
        short value = nextShort();
        map.put(key, value);
        assertEquals(value, map.remove(key));
    }

    @Test
    protected void removingWithValueWhenKeyDoesNotExistReturnsFalse() {
        long key = nextLong();
        short value = nextShort();
        assertFalse(map.remove(key, value));
    }

    @Test
    protected void removingWithValueWhenValueIsDifferentReturnsFalse() {
        long key = nextLong();
        short value = nextShort();
        map.put(key, value);
        assertFalse(map.remove(key, (short) (value - 1)));
    }

    @Test
    protected void removingWithValueWhenValueIsSameReturnsTrue() {
        long key = nextLong();
        short value = nextShort();
        map.put(key, value);
        assertTrue(map.remove(key, value));
    }

    @Test
    protected void puttingValueIfAbsentReturnsSameValue() {
        long key = nextLong();
        short value = nextShort();
        map.computeIfAbsent(key, l -> value);
        assertEquals(value, map.get(key));
    }

    @Test
    protected void checkingValueIfNotAbsentReturnsSameValue() {
        long key = nextLong();
        short value = nextShort();
        map.put(key, value);
        short returned = map.computeIfAbsent(key, l -> value);
        assertEquals(value, map.get(key));
        assertEquals(value, returned);
    }

    @Test
    protected void replacingValueIfPresentReturnsNewValue() {
        long key = nextLong();
        short value = nextShort();
        map.put(key, value);
        map.computeIfPresent(key, (aLongKey, aShortValue) -> (short) (2 * aShortValue));
        assertEquals((short) (2 * value), map.get(key));
    }

    @Test
    protected void checkingValueIfNotPresentReturnsDefaultValue() {
        long key = nextLong();
        map.computeIfPresent(key, (aLongKey, aShortValue) -> (short) (2 * aShortValue));
        assertEquals(map.getDefaultValue(), map.get(key));
    }

    // --- floorEntry tests ---
    @Test
    protected void floorEntryEmptyMapReturnsNull() {
        assertNull(map.floorEntry(100L));
    }

    @Test
    protected void floorEntryExactMatchReturnsExactEntry() {
        map.put(10L, (short) 1);
        map.put(20L, (short) 2);
        map.put(30L, (short) 3);
        Long2ShortMap.Entry result = map.floorEntry(20L);
        assertNotNull(result);
        assertEquals(20L, result.getLongKey());
        assertEquals((short) 2, result.getShortValue());
    }

    @Test
    protected void floorEntryKeyBetweenEntriesReturnsPredecessor() {
        map.put(10L, (short) 1);
        map.put(20L, (short) 2);
        map.put(30L, (short) 3);
        Long2ShortMap.Entry result = map.floorEntry(25L);
        assertNotNull(result);
        assertEquals(20L, result.getLongKey());
        assertEquals((short) 2, result.getShortValue());
    }

    @Test
    protected void floorEntryKeyBelowMinimumReturnsNull() {
        map.put(10L, (short) 1);
        map.put(20L, (short) 2);
        assertNull(map.floorEntry(5L));
    }

    @Test
    protected void floorEntryKeyAboveMaximumReturnsLastEntry() {
        map.put(10L, (short) 1);
        map.put(20L, (short) 2);
        Long2ShortMap.Entry result = map.floorEntry(100L);
        assertNotNull(result);
        assertEquals(20L, result.getLongKey());
        assertEquals((short) 2, result.getShortValue());
    }
}
