package com.trivago.fastutilconcurrentwrapper;

import it.unimi.dsi.fastutil.ints.Int2LongFunction;

import java.util.function.BiFunction;

public interface IntLongMap extends PrimitiveIntKeyMap {

    long DEFAULT_VALUE = 0;

    /**
     * @param key
     * @return 0 if the key is not present
     */
    long get(int key);

    long put(int key, long value);

    long getDefaultValue();

    long remove(int key);

    boolean remove(int key, long value);

    long computeIfAbsent(int key, Int2LongFunction mappingFunction);

    long computeIfPresent(int key, BiFunction<Integer, Long, Long> mappingFunction);
}
