package com.trivago.fastutilconcurrentwrapper.longshort;

import com.trivago.fastutilconcurrentwrapper.AbstractMapTest;
import com.trivago.fastutilconcurrentwrapper.LongShortMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

abstract class AbstractLongShortMapTest extends AbstractMapTest {
    // Some methods return the default value of the underlying Fastutil implementation.
    private static final short FASTUTIL_DEFAULT_VALUE = 0;

    protected short defaultValue;
    private LongShortMap map;

    abstract LongShortMap createMap();

    @BeforeEach
    void initializeMap() {
        defaultValue = nextShort();
        map = createMap();
    }

    @Test
    protected void containsKeyReturnsFalseIfMapIsEmpty() {
        final long key = nextLong();

        final boolean contains = map.containsKey(key);

        assertFalse(contains);
    }

    @Test
    protected void containsKeyReturnsTrueIfKeyExists() {
        long key = nextLong();
        short value = nextShort();
        map.put(key, value);

        final boolean contains = map.containsKey(key);

        assertTrue(contains);
    }

    @Test
    protected void containsKeyReturnsFalseIfKeyWasRemoved() {
        long key = nextLong();
        short value = nextShort();
        map.put(key, value);
        map.remove(key);

        final boolean contains = map.containsKey(key);

        assertFalse(contains);
    }

    @Test
    protected void mapIsEmptyWhenNothingWasInserted() {
        final boolean empty = map.isEmpty();

        assertTrue(empty);
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

        final boolean empty = map.isEmpty();

        assertTrue(empty);
    }

    @Test
    protected void sizeIsCorrect() {
        int entries = (Math.abs(nextInt()) % 50) + 1;
        short value = nextShort();

        for (long key = 1; key <= entries; key++) {
            map.put(key, value);
        }

        final int size = map.size();

        assertEquals(entries, size);
    }

    @Test
    protected void gettingExistingValueReturnsCorrectValue() {
        long key = nextLong();
        short value = nextShort();
        map.put(key, value);
        final short returnedValue = map.get(key);

        assertEquals(value, returnedValue);
    }

    @Test
    protected void gettingNonExistingValueReturnsCorrectValue() {
        long key = nextLong();
        final short returnedValue = map.get(key);

        assertEquals(defaultValue, returnedValue);
    }

    @Test
    protected void removingNonExistingKeyReturnsDefaultValue() {
        long key = nextLong();
        final short removedValue = map.remove(key);

        assertEquals(FASTUTIL_DEFAULT_VALUE, removedValue);
    }

    @Test
    protected void removingExistingKeyReturnsPreviousValue() {
        long key = nextLong();
        short value = nextShort();
        map.put(key, value);
        final short removedValue = map.remove(key);

        assertEquals(value, removedValue);
    }

    @Test
    protected void removingWithValueWhenKeyDoesNotExistReturnsFalse() {
        long key = nextLong();
        short value = nextShort();
        final boolean result = map.remove(key, value);

        assertFalse(result);
    }

    @Test
    protected void removingWithValueWhenValueIsDifferentReturnsFalse() {
        long key = nextLong();
        short value = nextShort();
        map.put(key, value);
        final boolean result = map.remove(key, (short) (value - 1));

        assertFalse(result);
    }

    @Test
    protected void removingWithValueWhenValueIsSameReturnsTrue() {
        long key = nextLong();
        short value = nextShort();
        map.put(key, value);
        final boolean result = map.remove(key, value);

        assertTrue(result);
    }

    @Test
    protected void puttingValueIfAbsentReturnsSameValue() {
        long key = nextLong();
        short value = nextShort();
        map.computeIfAbsent(key, l -> value);

        short result = map.get(key);

        assertEquals(result, value);
    }

    @Test
    protected void checkingValueIfNotAbsentReturnsSameValue() {
        long key = nextLong();
        short value = nextShort();
        map.put(key, value);
        short returned = map.computeIfAbsent(key, l -> value);

        short result = map.get(key);

        assertEquals(result, value);
        assertEquals(value, returned);
    }

    @Test
    protected void replacingValueIfPresentReturnsNewValue() {
        long key = nextLong();
        short value = nextShort();
        map.put(key, value);

        map.computeIfPresent(key, (aLongKey, aShortValue) -> (short) (2 * aShortValue));

        short result = map.get(key);

        assertEquals(result, (short) (2 * value));
    }

    @Test
    protected void checkingValueIfNotPresentReturnsDefaultValue() {
        long key = nextLong();
        map.computeIfPresent(key, (aLongKey, aShortValue) -> (short) (2 * aShortValue));

        short result = map.get(key);

        assertEquals(result, map.getDefaultValue());
    }
}

