package com.trivago.fastutilconcurrentwrapper;

import it.unimi.dsi.fastutil.longs.Long2LongFunction;

import java.util.function.BiFunction;

public interface LongLongMap extends PrimitiveLongKeyMap {

    long DEFAULT_VALUE = 0;

    /**
     * @param key
     * @return 0 if the key is not present
     */
    long get(long key);

    long put(long key, long value);

    long getDefaultValue();

    long remove(long key);

    boolean remove(long key, long value);

    long computeIfAbsent(long key, Long2LongFunction mappingFunction);

    long computeIfPresent(long key, BiFunction<Long, Long, Long> mappingFunction);
}
