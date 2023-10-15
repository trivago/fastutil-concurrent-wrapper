package com.trivago.fastutilconcurrentwrapper;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

/*
 *  Since there is no common Map interface for the typed maps, in fastutilsconcurrentwrapper,
 *  test cases have to be repeated for each typed map interface.
 */
public abstract class AbstractMapTest {
    @Test
    protected abstract void containsKeyReturnsFalseIfMapIsEmpty();

    @Test
    protected abstract void containsKeyReturnsTrueIfKeyExists();

    @Test
    protected abstract void containsKeyReturnsFalseIfKeyWasRemoved();

    @Test
    protected abstract void mapIsEmptyWhenNothingWasInserted();

    @Test
    protected abstract void mapIsEmptyWhenAllKeysAreDeleted();

    @Test
    protected abstract void sizeIsCorrect();

    @Test
    protected abstract void gettingExistingValueReturnsCorrectValue();

    @Test
    protected abstract void gettingNonExistingValueReturnsCorrectValue();

    @Test
    protected abstract void removingNonExistingKeyReturnsDefaultValue();

    @Test
    protected abstract void removingExistingKeyReturnsPreviousValue();

    @Test
    protected abstract void removingWithValueWhenKeyDoesNotExistReturnsFalse();

    @Test
    protected abstract void removingWithValueWhenValueIsDifferentReturnsFalse();

    @Test
    protected abstract void removingWithValueWhenValueIsSameReturnsTrue();

    @Test
    protected abstract void puttingValueIfAbsentReturnsSameValue();

    @Test
    protected abstract void checkingValueIfNotAbsentReturnsSameValue();

    @Test
    protected abstract void replacingValueIfPresentReturnsNewValue();

    @Test
    protected abstract void checkingValueIfNotPresentReturnsDefaultValue();

    protected static long nextLong() {
        return ThreadLocalRandom.current().nextLong();
    }

    protected static int nextInt() {
        return ThreadLocalRandom.current().nextInt();
    }
}
