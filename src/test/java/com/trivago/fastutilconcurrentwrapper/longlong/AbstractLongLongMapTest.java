package com.trivago.fastutilconcurrentwrapper.longlong;

import com.trivago.fastutilconcurrentwrapper.AbstractMapTest;
import com.trivago.fastutilconcurrentwrapper.LongLongMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

abstract class AbstractLongLongMapTest extends AbstractMapTest {
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
  protected void containsKeyReturnsFalseIfMapIsEmpty() {
    final long key = random.nextLong();

    final boolean contains = map.containsKey(key);

    assertFalse(contains);
  }

  @Test
  protected void containsKeyReturnsTrueIfKeyExists() {
    long key = random.nextLong();
    long value = random.nextLong();
    map.put(key, value);

    final boolean contains = map.containsKey(key);

    assertTrue(contains);
  }

  @Test
  protected void containsKeyReturnsFalseIfKeyWasRemoved() {
    long key = random.nextLong();
    long value = random.nextLong();
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
  protected void sizeIsCorrect() {
    int entries = (Math.abs(random.nextInt()) % 50) + 1;
    long value = random.nextLong();

    for (long key = 1; key <= entries; key++) {
      map.put(key, value);
    }

    final int size = map.size();

    assertEquals(entries, size);
  }

  @Test
  protected void gettingExistingValueReturnsCorrectValue() {
    long key = random.nextLong();
    long value = random.nextLong();
    map.put(key, value);
    final long returnedValue = map.get(key);

    assertEquals(value, returnedValue);
  }

  @Test
  protected void gettingNonExistingValueReturnsCorrectValue() {
    long key = random.nextLong();
    final long returnedValue = map.get(key);

    assertEquals(defaultValue, returnedValue);
  }

  @Test
  protected void removingNonExistingKeyReturnsDefaultValue() {
    long key = random.nextLong();
    final long removedValue = map.remove(key);

    assertEquals(FASTUTIL_DEFAULT_VALUE, removedValue);
  }

  @Test
  protected void removingExistingKeyReturnsPreviousValue() {
    long key = random.nextLong();
    long value = random.nextLong();
    map.put(key, value);
    final long removedValue = map.remove(key);

    assertEquals(value, removedValue);
  }

  @Test
  protected void removingWithValueWhenKeyDoesNotExistReturnsFalse() {
    long key = random.nextLong();
    long value = random.nextLong();
    final boolean result = map.remove(key, value);

    assertFalse(result);
  }

  @Test
  protected void removingWithValueWhenValueIsDifferentReturnsFalse() {
    long key = random.nextLong();
    long value = random.nextLong();
    map.put(key, value);
    final boolean result = map.remove(key, value - 1);

    assertFalse(result);
  }

  @Test
  protected void removingWithValueWhenValueIsSameReturnsTrue() {
    long key = random.nextLong();
    long value = random.nextLong();
    map.put(key, value);
    final boolean result = map.remove(key, value);

    assertTrue(result);
  }

  @Test
  protected void puttingValueIfAbsentReturnsSameValue() {
    long key = random.nextLong();
    long value = random.nextLong();
    map.computeIfAbsent(key, l -> value);

    long result = map.get(key);

    assertEquals(result, value);
  }

  @Test
  protected void checkingValueIfNotAbsentReturnsSameValue() {
    long key = random.nextLong();
    long value = random.nextLong();
    map.put(key, value);
    long returned = map.computeIfAbsent(key, l -> value);

    long result = map.get(key);

    assertEquals(result, value);
    assertEquals(value, returned);
  }

  @Test
  protected void replacingValueIfPresentReturnsNewValue() {
    long key = random.nextLong();
    long value = random.nextLong();
    map.put(key, value);

    map.computeIfPresent(key, Long::sum); // key + old value

    long result = map.get(key);

    assertEquals(result, key + value);
  }

  @Test
  protected void checkingValueIfNotPresentReturnsDefaultValue() {
    long key = random.nextLong();
    map.computeIfPresent(key, Long::sum);

    long result = map.get(key);

    assertEquals(result, map.getDefaultValue());
  }
}
