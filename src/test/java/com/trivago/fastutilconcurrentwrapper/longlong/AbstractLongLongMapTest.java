package com.trivago.fastutilconcurrentwrapper.longlong;

import com.trivago.fastutilconcurrentwrapper.LongLongMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

abstract class AbstractLongLongMapTest {

  private final Random random = ThreadLocalRandom.current();

  private LongLongMap map;
  // Keep the default value to easily verify that this value is returned.
  protected long defaultValue;
  // Some methods return the default value of the underlying Fastutil implementation.
  private static final long FASTUTIL_DEFAULT_VALUE = 0L;

  abstract LongLongMap createMap();

  @BeforeEach
  void initializeMap() {
    defaultValue = random.nextLong();
    map = createMap();
  }

  @Test
  void containsKeyReturnsFalseIfMapIsEmpty() {
    final long key = random.nextLong();

    final boolean contains = map.containsKey(key);

    assertFalse(contains);
  }

  @Test
  void containsKeyReturnsTrueIfKeyExists() {
    long key = random.nextLong();
    long value = random.nextLong();
    map.put(key, value);

    final boolean contains = map.containsKey(key);

    assertTrue(contains);
  }

  @Test
  void containsKeyReturnsFalseIfKeyWasRemoved() {
    long key = random.nextLong();
    long value = random.nextLong();
    map.put(key, value);
    map.remove(key);

    final boolean contains = map.containsKey(key);

    assertFalse(contains);
  }

  @Test
  void mapIsEmptyWhenNothingWasInserted() {
    final boolean empty = map.isEmpty();

    assertTrue(empty);
  }

  @Test
  void mapIsEmptyWhenAllKeysAreDeleted() {
    int entryCount = (Math.abs(random.nextInt()) % 100) + 1;
    long value = random.nextLong();

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
  void sizeIsCorrect() {
    int entries = (Math.abs(random.nextInt()) % 50) + 1;
    long value = random.nextLong();

    for (long key = 1; key <= entries; key++) {
      map.put(key, value);
    }

    final int size = map.size();

    assertEquals(entries, size);
  }

  @Test
  void gettingExistingValueReturnsCorrectValue() {
    long key = random.nextLong();
    long value = random.nextLong();
    map.put(key, value);
    final long returnedValue = map.get(key);

    assertEquals(value, returnedValue);
  }

  @Test
  void gettingNonExistingValueReturnsCorrectValue() {
    long key = random.nextLong();
    final long returnedValue = map.get(key);

    assertEquals(defaultValue, returnedValue);
  }

  @Test
  void removingNonExistingKeyReturnsDefaultValue() {
    long key = random.nextLong();
    final long removedValue = map.remove(key);

    assertEquals(FASTUTIL_DEFAULT_VALUE, removedValue);
  }

  @Test
  void removingExistingKeyReturnsPreviousValue() {
    long key = random.nextLong();
    long value = random.nextLong();
    map.put(key, value);
    final long removedValue = map.remove(key);

    assertEquals(value, removedValue);
  }

  @Test
  void removingWithValueWhenKeyDoesNotExistReturnsFalse() {
    long key = random.nextLong();
    long value = random.nextLong();
    final boolean result = map.remove(key, value);

    assertFalse(result);
  }

  @Test
  void removingWithValueWhenValueIsDifferentReturnsFalse() {
    long key = random.nextLong();
    long value = random.nextLong();
    map.put(key, value);
    final boolean result = map.remove(key, value - 1);

    assertFalse(result);
  }

  @Test
  void removingWithValueWhenValueIsSameReturnsTrue() {
    long key = random.nextLong();
    long value = random.nextLong();
    map.put(key, value);
    final boolean result = map.remove(key, value);

    assertTrue(result);
  }

  @Test
  void puttingValueIfNotExistsReturnsSameValue() {
    long key = random.nextLong();
    long value = random.nextLong();
    map.computeIfAbsent(key, l -> value);

    long result = map.get(key);

    assertEquals(result, value);
  }

  @Test
  void replacingValueIfExistsReturnsNewValue() {
    long key = random.nextLong();
    long value = random.nextLong();
    map.put(key, value);

    map.computeIfPresent(key, Long::sum); // key + old value

    long result = map.get(key);

    assertEquals(result, key + value);
  }
}
