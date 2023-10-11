package com.trivago.fastutilconcurrentwrapper;

import it.unimi.dsi.fastutil.ints.Int2LongFunction;
import it.unimi.dsi.fastutil.longs.Long2IntFunction;

import java.util.function.BiFunction;

public interface LongIntMap extends PrimitiveLongKeyMap {

    int DEFAULT_VALUE = 0;

    /**
     * @param key key to get
     * @return 0 if the key is not present
     */
    int get(long key);

    int put(long key, int value);

    int getDefaultValue();

    int remove(long key);

    boolean remove(long key, int value);

    int computeIfAbsent(long key, Long2IntFunction mappingFunction);

    int computeIfPresent(long key, BiFunction<Long, Integer, Integer> mappingFunction);
}
