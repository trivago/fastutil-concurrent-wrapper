package com.trivago.fastutilconcurrentwrapper.longobject;

import com.trivago.fastutilconcurrentwrapper.AbstractMapTest;
import com.trivago.fastutilconcurrentwrapper.primitivekeys.LongIntMap;
import com.trivago.fastutilconcurrentwrapper.primitivekeys.LongObjectMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

abstract class AbstractLongObjectMapTest extends AbstractMapTest {

    protected ObjectKey defaultValue;
    private LongObjectMap<ObjectKey> map;
    // Keep the default value to easily verify that this value is returned.

    abstract LongObjectMap<ObjectKey> createMap();

    @BeforeEach
    void initializeMap() {
        defaultValue = nextObject();
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
        ObjectKey value = nextObject();
        map.put(key, value);

        final boolean contains = map.containsKey(key);

        assertTrue(contains);
    }

    @Test
    protected void containsKeyReturnsFalseIfKeyWasRemoved() {
        int key = nextInt();
        ObjectKey value = nextObject();
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
        ObjectKey value = nextObject();

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
        ObjectKey value = nextObject();

        for (int key = 1; key <= entries; key++) {
            map.put(key, value);
        }

        final int size = map.size();

        assertEquals(entries, size);
    }

    @Test
    protected void gettingExistingValueReturnsCorrectValue() {
        int key = nextInt();
        ObjectKey value = nextObject();
        map.put(key, value);
        final ObjectKey returnedValue = map.get(key);

        assertEquals(value, returnedValue);
    }

    @Test
    protected void gettingNonExistingValueReturnsCorrectValue() {
        int key = nextInt();
        final ObjectKey returnedValue = map.get(key);

        assertEquals(defaultValue, returnedValue);
    }

    @Test
    protected void removingNonExistingKeyReturnsDefaultValue() {
        int key = nextInt();
        final ObjectKey removedValue = map.remove(key);

        assertEquals(null, removedValue);
    }

    @Test
    protected void removingExistingKeyReturnsPreviousValue() {
        int key = nextInt();
        ObjectKey value = nextObject();
        map.put(key, value);
        final ObjectKey removedValue = map.remove(key);

        assertEquals(value, removedValue);
    }

    @Test
    protected void removingWithValueWhenKeyDoesNotExistReturnsFalse() {
        int key = nextInt();
        ObjectKey value = nextObject();
        final boolean result = map.remove(key, value);

        assertFalse(result);
    }

    @Test
    protected void removingWithValueWhenValueIsDifferentReturnsFalse() {
        int key = nextInt();
        ObjectKey value = nextObject();
        map.put(key, value);
        final boolean result = map.remove(key, nextObject());

        assertFalse(result);
    }

    @Test
    protected void removingWithValueWhenValueIsSameReturnsTrue() {
        int key = nextInt();
        ObjectKey value = nextObject();
        map.put(key, value);
        final boolean result = map.remove(key, value);

        assertTrue(result);
    }

    @Test
    protected void puttingValueIfAbsentReturnsSameValue() {
        int key = nextInt();
        ObjectKey value = nextObject();
        map.computeIfAbsent(key, l -> value);

        ObjectKey result = map.get(key);

        assertEquals(result, value);
    }

    @Test
    protected void checkingValueIfNotAbsentReturnsSameValue() {
        int key = nextInt();
        ObjectKey value = nextObject();
        map.put(key, value);
        ObjectKey returned = map.computeIfAbsent(key, l -> value);

        ObjectKey result = map.get(key);

        assertEquals(result, value);
        assertEquals(value, returned);
    }

    @Test
    protected void replacingValueIfPresentReturnsNewValue() {
        int key = nextInt();
        ObjectKey value = nextObject();
        map.put(key, value);

        ObjectKey newValue = nextObject();

        map.computeIfPresent(key, (aLongKey, anIntValue) -> newValue); // key + old value

        ObjectKey result = map.get(key);

        assertEquals(result, newValue);
    }

    @Test
    protected void checkingValueIfNotPresentReturnsDefaultValue() {
        int key = nextInt();
        ObjectKey newValue = nextObject();
        map.computeIfPresent(key, (aLongKey, anIntValue) -> newValue); // key + old value

        ObjectKey result = map.get(key);

        assertEquals(result, map.getDefaultValue());
    }
}
