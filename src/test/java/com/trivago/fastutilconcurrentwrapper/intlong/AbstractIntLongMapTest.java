package com.trivago.fastutilconcurrentwrapper.intlong;

import com.trivago.fastutilconcurrentwrapper.AbstractMapTest;
import com.trivago.fastutilconcurrentwrapper.IntLongMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

abstract class AbstractIntLongMapTest extends AbstractMapTest {
  private IntLongMap map;
  // Keep the default value to easily verify that this value is returned.
  protected long defaultValue;
  // Some methods return the default value of the underlying Fastutil implementation.
  private static final long FASTUTIL_DEFAULT_VALUE = 0L;

  abstract IntLongMap createMap();

  @BeforeEach
  void initializeMap() {
    defaultValue = nextLong();
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
    long value = nextLong();
    map.put(key, value);

    final boolean contains = map.containsKey(key);

    assertTrue(contains);
  }

  @Test
  protected void containsKeyReturnsFalseIfKeyWasRemoved() {
    int key = nextInt();
    long value = nextLong();
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
    long value = nextLong();

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
    long value = nextLong();

    for (int key = 1; key <= entries; key++) {
      map.put(key, value);
    }

    final int size = map.size();

    assertEquals(entries, size);
  }

  @Test
  protected void gettingExistingValueReturnsCorrectValue() {
    int key = nextInt();
    long value = nextLong();
    map.put(key, value);
    final long returnedValue = map.get(key);

    assertEquals(value, returnedValue);
  }

  @Test
  protected void gettingNonExistingValueReturnsCorrectValue() {
    int key = nextInt();
    final long returnedValue = map.get(key);

    assertEquals(defaultValue, returnedValue);
  }

  @Test
  protected void removingNonExistingKeyReturnsDefaultValue() {
    int key = nextInt();
    final long removedValue = map.remove(key);

    assertEquals(FASTUTIL_DEFAULT_VALUE, removedValue);
  }

  @Test
  protected void removingExistingKeyReturnsPreviousValue() {
    int key = nextInt();
    long value = nextLong();
    map.put(key, value);
    final long removedValue = map.remove(key);

    assertEquals(value, removedValue);
  }

  @Test
  protected void removingWithValueWhenKeyDoesNotExistReturnsFalse() {
    int key = nextInt();
    long value = nextLong();
    final boolean result = map.remove(key, value);

    assertFalse(result);
  }

  @Test
  protected void removingWithValueWhenValueIsDifferentReturnsFalse() {
    int key = nextInt();
    long value = nextLong();
    map.put(key, value);
    final boolean result = map.remove(key, value - 1);

    assertFalse(result);
  }

  @Test
  protected void removingWithValueWhenValueIsSameReturnsTrue() {
    int key = nextInt();
    long value = nextLong();
    map.put(key, value);
    final boolean result = map.remove(key, value);

    assertTrue(result);
  }

  @Test
  protected void puttingValueIfAbsentReturnsSameValue() {
    int key = nextInt();
    long value = nextLong();
    map.computeIfAbsent(key, l -> value);

    long result = map.get(key);

    assertEquals(result, value);
  }

  @Test
  protected void checkingValueIfNotAbsentReturnsSameValue() {
    int key = nextInt();
    long value = nextLong();
    map.put(key, value);
    long returned = map.computeIfAbsent(key, l -> value);

    long result = map.get(key);

    assertEquals(result, value);
    assertEquals(value, returned);
  }

  @Test
  protected void replacingValueIfPresentReturnsNewValue() {
    int key = nextInt();
    long value = nextLong();
    map.put(key, value);

    map.computeIfPresent(key, Long::sum); // key + old value

    long result = map.get(key);

    assertEquals(result, key + value);
  }

  @Test
  protected void checkingValueIfNotPresentReturnsDefaultValue() {
    int key = nextInt();
    map.computeIfPresent(key, Long::sum);

    long result = map.get(key);

    assertEquals(result, map.getDefaultValue());
  }
}
