package com.trivago.fastutilconcurrentwrapper.objectint;

import com.trivago.fastutilconcurrentwrapper.AbstractMapTest;
import com.trivago.fastutilconcurrentwrapper.objectkeys.ObjectIntMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

abstract class AbstractObjectIntMapTest extends AbstractMapTest {
    // Some methods return the default value of the underlying Fastutil implementation.
    private static final int FASTUTIL_DEFAULT_VALUE = 0;

    protected int defaultValue;
    private ObjectIntMap<ObjectKey> map;
    // Keep the default value to easily verify that this value is returned.

    abstract ObjectIntMap<ObjectKey> createMap();

    @BeforeEach
    void initializeMap() {
        defaultValue = nextInt();
        map = createMap();
    }

    @Test
    protected void containsKeyReturnsFalseIfMapIsEmpty() {
        final ObjectKey key = nextObject();

        final boolean contains = map.containsKey(key);

        assertFalse(contains);
    }

    @Test
    protected void containsKeyReturnsTrueIfKeyExists() {
        ObjectKey key = nextObject();
        int value = nextInt();
        map.put(key, value);

        final boolean contains = map.containsKey(key);

        assertTrue(contains);
    }

    @Test
    protected void containsKeyReturnsFalseIfKeyWasRemoved() {
        ObjectKey key = nextObject();
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

        List<ObjectKey> keys = new ArrayList<>(entryCount);
        for (int key = 1; key <= entryCount; key++) {
            ObjectKey objectKey = nextObject();
            keys.add(objectKey);
            map.put(objectKey, value);
        }
        for (ObjectKey objectKey : keys) {
            map.remove(objectKey);
        }

        final boolean empty = map.isEmpty();

        assertTrue(empty);
    }

    @Test
    protected void sizeIsCorrect() {
        int entries = (Math.abs(nextInt()) % 50) + 1;
        int value = nextInt();

        for (int key = 1; key <= entries; key++) {
            map.put(nextObject(), value);
        }

        final int size = map.size();

        assertEquals(entries, size);
    }

    @Test
    protected void gettingExistingValueReturnsCorrectValue() {
        ObjectKey key = nextObject();
        int value = nextInt();
        map.put(key, value);
        final int returnedValue = map.get(key);

        assertEquals(value, returnedValue);
    }

    @Test
    protected void gettingNonExistingValueReturnsCorrectValue() {
        ObjectKey key = nextObject();
        final int returnedValue = map.get(key);

        assertEquals(defaultValue, returnedValue);
    }

    @Test
    protected void removingNonExistingKeyReturnsDefaultValue() {
        ObjectKey key = nextObject();
        final int removedValue = map.remove(key);

        assertEquals(FASTUTIL_DEFAULT_VALUE, removedValue);
    }

    @Test
    protected void removingExistingKeyReturnsPreviousValue() {
        ObjectKey key = nextObject();
        int value = nextInt();
        map.put(key, value);
        final int removedValue = map.remove(key);

        assertEquals(value, removedValue);
    }

    @Test
    protected void removingWithValueWhenKeyDoesNotExistReturnsFalse() {
        ObjectKey key = nextObject();
        int value = nextInt();
        final boolean result = map.remove(key, value);

        assertFalse(result);
    }

    @Test
    protected void removingWithValueWhenValueIsDifferentReturnsFalse() {
        ObjectKey key = nextObject();
        int value = nextInt();
        map.put(key, value);
        final boolean result = map.remove(key, value - 1);

        assertFalse(result);
    }

    @Test
    protected void removingWithValueWhenValueIsSameReturnsTrue() {
        ObjectKey key = nextObject();
        int value = nextInt();
        map.put(key, value);
        final boolean result = map.remove(key, value);

        assertTrue(result);
    }

    @Test
    protected void puttingValueIfAbsentReturnsSameValue() {
        ObjectKey key = nextObject();
        int value = nextInt();
        map.computeIfAbsent(key, l -> value);

        int result = map.get(key);

        assertEquals(result, value);
    }

    @Test
    protected void checkingValueIfNotAbsentReturnsSameValue() {
        ObjectKey key = nextObject();
        int value = nextInt();
        map.put(key, value);
        int returned = map.computeIfAbsent(key, l -> value);

        int result = map.get(key);

        assertEquals(result, value);
        assertEquals(value, returned);
    }

    @Test
    protected void replacingValueIfPresentReturnsNewValue() {
        ObjectKey key = nextObject();
        int value = nextInt();
        map.put(key, value);

        map.computeIfPresent(key, (aLongKey, anIntValue) -> 2 * anIntValue); // key + old value

        int result = map.get(key);

        assertEquals(result, 2 * value);
    }

    @Test
    protected void checkingValueIfNotPresentReturnsDefaultValue() {
        ObjectKey key = nextObject();
        map.computeIfPresent(key, (aLongKey, anIntValue) -> 2 * anIntValue); // key + old value

        int result = map.get(key);

        assertEquals(result, map.getDefaultValue());
    }
}
