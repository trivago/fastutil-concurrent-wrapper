package com.trivago.fastutilconcurrentwrapper;

import it.unimi.dsi.fastutil.longs.Long2ShortMap;

/**
 * A sorted {@link LongShortMap} that additionally supports a {@code floorEntry()} operation.
 */
public interface LongShortTreeMap extends LongShortMap {
    /**
     * Returns the entry with the greatest key less than or equal to the given key,
     * or {@code null} if no such key exists.
     *
     * @param key the key to search for
     * @return an entry with the greatest key ≤ {@code key}, or {@code null}
     */
    Long2ShortMap.Entry floorEntry(long key);
}
