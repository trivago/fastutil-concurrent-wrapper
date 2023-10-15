package com.trivago.fastutilconcurrentwrapper.longint;

import com.trivago.fastutilconcurrentwrapper.AbstractMapTest;
import com.trivago.fastutilconcurrentwrapper.LongIntMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

abstract class AbstractLongIntMapTest extends AbstractMapTest {
    // Some methods return the default value of the underlying Fastutil implementation.
    private static final int FASTUTIL_DEFAULT_VALUE = 0;

    protected int defaultValue;
    private LongIntMap map;
    // Keep the default value to easily verify that this value is returned.

    abstract LongIntMap createMap();

    @BeforeEach
    void initializeMap() {
        defaultValue = nextInt();
        map = createMap();
    }

    @Test
    protected void containsKeyReturnsFalseIfMapIsEmpty() {
        final int key = nextInt();

        final boolean contains = map.containsKey(key);

        assertFalse(contains);
    }

    @Test
    protected void containsKeyReturnsTrueIfKeyExists() {
        int key = nextInt();
        int value = nextInt();
        map.put(key, value);

        final boolean contains = map.containsKey(key);

        assertTrue(contains);
    }

    @Test
    protected void containsKeyReturnsFalseIfKeyWasRemoved() {
        int key = nextInt();
        int value = nextInt();
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
        int value = nextInt();

        for (int key = 1; key <= entryCount; key++) {
            map.put(key, value);
        }
        for (int key = 1; key <= entryCount; key++) {
            map.remove(key);
        }

        final boolean empty = map.isEmpty();

        assertTrue(empty);
    }

    @Test
    protected void sizeIsCorrect() {
        int entries = (Math.abs(nextInt()) % 50) + 1;
        int value = nextInt();

        for (int key = 1; key <= entries; key++) {
            map.put(key, value);
        }

        final int size = map.size();

        assertEquals(entries, size);
    }

    @Test
    protected void gettingExistingValueReturnsCorrectValue() {
        int key = nextInt();
        int value = nextInt();
        map.put(key, value);
        final int returnedValue = map.get(key);

        assertEquals(value, returnedValue);
    }

    @Test
    protected void gettingNonExistingValueReturnsCorrectValue() {
        int key = nextInt();
        final int returnedValue = map.get(key);

        assertEquals(defaultValue, returnedValue);
    }

    @Test
    protected void removingNonExistingKeyReturnsDefaultValue() {
        int key = nextInt();
        final int removedValue = map.remove(key);

        assertEquals(FASTUTIL_DEFAULT_VALUE, removedValue);
    }

    @Test
    protected void removingExistingKeyReturnsPreviousValue() {
        int key = nextInt();
        int value = nextInt();
        map.put(key, value);
        final int removedValue = map.remove(key);

        assertEquals(value, removedValue);
    }

    @Test
    protected void removingWithValueWhenKeyDoesNotExistReturnsFalse() {
        int key = nextInt();
        int value = nextInt();
        final boolean result = map.remove(key, value);

        assertFalse(result);
    }

    @Test
    protected void removingWithValueWhenValueIsDifferentReturnsFalse() {
        int key = nextInt();
        int value = nextInt();
        map.put(key, value);
        final boolean result = map.remove(key, value - 1);

        assertFalse(result);
    }

    @Test
    protected void removingWithValueWhenValueIsSameReturnsTrue() {
        int key = nextInt();
        int value = nextInt();
        map.put(key, value);
        final boolean result = map.remove(key, value);

        assertTrue(result);
    }

    @Test
    protected void puttingValueIfAbsentReturnsSameValue() {
        int key = nextInt();
        int value = nextInt();
        map.computeIfAbsent(key, l -> value);

        int result = map.get(key);

        assertEquals(result, value);
    }

    @Test
    protected void checkingValueIfNotAbsentReturnsSameValue() {
        int key = nextInt();
        int value = nextInt();
        map.put(key, value);
        int returned = map.computeIfAbsent(key, l -> value);

        int result = map.get(key);

        assertEquals(result, value);
        assertEquals(value, returned);
    }

    @Test
    protected void replacingValueIfPresentReturnsNewValue() {
        int key = nextInt();
        int value = nextInt();
        map.put(key, value);

        map.computeIfPresent(key, (aLongKey, anIntValue) -> 2 * anIntValue); // key + old value

        int result = map.get(key);

        assertEquals(result, 2 * value);
    }

    @Test
    protected void checkingValueIfNotPresentReturnsDefaultValue() {
        int key = nextInt();
        map.computeIfPresent(key, (aLongKey, anIntValue) -> 2 * anIntValue); // key + old value

        int result = map.get(key);

        assertEquals(result, map.getDefaultValue());
    }
}
