package com.trivago.fastutilconcurrentwrapper;

import it.unimi.dsi.fastutil.longs.Long2ShortMap;

/**
 * A sorted {@link LongShortMap} that additionally supports a {@code floorEntry()} operation.
 */
public interface LongShortTreeMap extends LongShortMap {

    // floorEntry() requires all keys to reside in a single bucket to return
    // correct results, so the bucket count is fixed at 1.
    // ToDo: Identify a bucketing methodology that works for TreeMap
    public static final int BUCKETS = 1;

    /**
     * Returns the entry with the greatest key less than or equal to the given key,
     * or {@code null} if no such key exists.
     *
     * @param key the key to search for
     * @return an entry with the greatest key ≤ {@code key}, or {@code null}
     */
    Long2ShortMap.Entry floorEntry(long key);
}
