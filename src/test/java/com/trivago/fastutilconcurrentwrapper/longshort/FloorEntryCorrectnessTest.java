package com.trivago.fastutilconcurrentwrapper.longshort;

import it.unimi.dsi.fastutil.longs.Long2ShortMap;
import com.trivago.fastutilconcurrentwrapper.impl.longs.FloorableLongShortAVLTreeMap; 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Verifies correctness of {@code floorEntry()} in:
 * <ul>
 *   <li>{@link FloorableLongShortAVLTreeMap} — headMap()-based</li>
 * </ul>
 *
 * <p>Uses a given-when-then structure and exercises the implementation via
 * {@link FloorEntryContract}.
 */
class FloorEntryCorrectnessTest {

    /**
     * Shared contract tests for the {@code floorEntry()} behaviour exercised by
     * the nested test class.
     */
    interface FloorEntryContract {

        Long2ShortMap.Entry floorEntry(long key);

        void put(long key, short value);

        int size();

        // --- Empty map ---

        @Test
        default void floorEntryEmptyMapReturnsNull() {
            // given: empty map (no puts)
            // then
            assertNull(floorEntry(100L));
        }

        // --- Exact match ---

        @Test
        default void floorEntryExactMatchReturnsExactEntry() {
            // given
            put(10L, (short) 1);
            put(20L, (short) 2);
            put(30L, (short) 3);

            // when
            Long2ShortMap.Entry result = floorEntry(20L);

            // then
            assertNotNull(result);
            assertEquals(20L, result.getLongKey());
            assertEquals((short) 2, result.getShortValue());
        }

        // --- Key between entries (near miss) ---

        @Test
        default void floorEntryKeyBetweenEntriesReturnsPredecessor() {
            // given
            put(10L, (short) 1);
            put(20L, (short) 2);
            put(30L, (short) 3);

            // when
            Long2ShortMap.Entry result = floorEntry(25L);

            // then
            assertNotNull(result);
            assertEquals(20L, result.getLongKey());
            assertEquals((short) 2, result.getShortValue());
        }

        // --- Key below minimum ---

        @Test
        default void floorEntryKeyBelowMinimumReturnsNull() {
            // given
            put(10L, (short) 1);
            put(20L, (short) 2);

            // then
            assertNull(floorEntry(5L));
        }

        // --- Key above maximum ---

        @Test
        default void floorEntryKeyAboveMaximumReturnsLastEntry() {
            // given
            put(10L, (short) 1);
            put(20L, (short) 2);

            // when
            Long2ShortMap.Entry result = floorEntry(100L);

            // then
            assertNotNull(result);
            assertEquals(20L, result.getLongKey());
            assertEquals((short) 2, result.getShortValue());
        }

        // --- Single element ---

        @Test
        default void floorEntrySingleElementExactMatch() {
            // given
            put(42L, (short) 99);

            // when
            Long2ShortMap.Entry result = floorEntry(42L);

            // then
            assertNotNull(result);
            assertEquals(42L, result.getLongKey());
            assertEquals((short) 99, result.getShortValue());
        }

        @Test
        default void floorEntrySingleElementAboveKeyReturnsEntry() {
            // given
            put(42L, (short) 99);

            // when
            Long2ShortMap.Entry result = floorEntry(100L);

            // then
            assertNotNull(result);
            assertEquals(42L, result.getLongKey());
        }

        @Test
        default void floorEntrySingleElementBelowKeyReturnsNull() {
            // given
            put(42L, (short) 99);

            // then
            assertNull(floorEntry(41L));
        }

        // --- Key equals first entry ---

        @Test
        default void floorEntryEqualsFirstKeyReturnsFirstEntry() {
            // given
            put(10L, (short) 1);
            put(20L, (short) 2);
            put(30L, (short) 3);

            // when
            Long2ShortMap.Entry result = floorEntry(10L);

            // then
            assertNotNull(result);
            assertEquals(10L, result.getLongKey());
            assertEquals((short) 1, result.getShortValue());
        }

        // --- Key equals last entry ---

        @Test
        default void floorEntryEqualsLastKeyReturnsLastEntry() {
            // given
            put(10L, (short) 1);
            put(20L, (short) 2);
            put(30L, (short) 3);

            // when
            Long2ShortMap.Entry result = floorEntry(30L);

            // then
            assertNotNull(result);
            assertEquals(30L, result.getLongKey());
            assertEquals((short) 3, result.getShortValue());
        }

        // --- Consecutive keys ---

        @Test
        default void floorEntryConsecutiveKeysExactMatch() {
            // given
            for (long k = 1L; k <= 5L; k++) {
                put(k, (short) k);
            }

            // when/then
            for (long k = 1L; k <= 5L; k++) {
                Long2ShortMap.Entry result = floorEntry(k);
                assertNotNull(result, "Expected non-null for key=" + k);
                assertEquals(k, result.getLongKey(), "Key mismatch for query=" + k);
                assertEquals((short) k, result.getShortValue(), "Value mismatch for query=" + k);
            }
        }

        // --- Large sparse keys (mimics production i2pid encoding) ---

        @Test
        default void floorEntryLargeSparseKeysReturnsCorrectFloor() {
            // given: keys spaced far apart, like encoded i2pid values
            put(1_000_000_000L, (short) 10);
            put(5_000_000_000L, (short) 50);
            put(10_000_000_000L, (short) 100);

            // when: query between first two
            Long2ShortMap.Entry result = floorEntry(3_000_000_000L);

            // then
            assertNotNull(result);
            assertEquals(1_000_000_000L, result.getLongKey());
            assertEquals((short) 10, result.getShortValue());
        }

        // --- Negative keys ---

        @Test
        default void floorEntryNegativeKeysReturnsCorrectFloor() {
            // given
            put(-30L, (short) 1);
            put(-20L, (short) 2);
            put(-10L, (short) 3);

            // when
            Long2ShortMap.Entry result = floorEntry(-15L);

            // then
            assertNotNull(result);
            assertEquals(-20L, result.getLongKey());
            assertEquals((short) 2, result.getShortValue());
        }
    }

    // =========================================================================
    // headMap() implementation tests
    // =========================================================================

    @Nested
    class HeadMapFloorEntryTest implements FloorEntryContract {
        private FloorableLongShortAVLTreeMap map;

        @BeforeEach
        void setUp() {
            map = new FloorableLongShortAVLTreeMap();
            map.defaultReturnValue(Short.MIN_VALUE);
        }

        @Override
        public Long2ShortMap.Entry floorEntry(long key) {
            return map.floorEntry(key);
        }

        @Override
        public void put(long key, short value) {
            map.put(key, value);
        }

        @Override
        public int size() {
            return map.size();
        }
    }
}
