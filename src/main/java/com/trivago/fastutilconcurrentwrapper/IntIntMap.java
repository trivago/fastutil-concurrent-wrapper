package com.trivago.fastutilconcurrentwrapper;

import it.unimi.dsi.fastutil.ints.Int2IntFunction;

import java.util.function.BiFunction;

public interface IntIntMap extends PrimitiveIntKeyMap {

    int DEFAULT_VALUE = 0;

    /**
     * @param key
     * @return 0 if the key is not present
     */
    int get(int key);

    int put(int key, int value);

    int getDefaultValue();

    int remove(int key);

    boolean remove(int key, int value);

    int computeIfAbsent(int key, Int2IntFunction mappingFunction);

    int computeIfPresent(int key, BiFunction<Integer, Integer, Integer> mappingFunction);
}
