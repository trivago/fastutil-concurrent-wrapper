package com.trivago.fastutilconcurrentwrapper.primitivekeys;

import it.unimi.dsi.fastutil.ints.Int2ObjectFunction;

import java.util.function.BiFunction;

public interface IntObjectMap<V> extends PrimitiveIntKeyMap {

    V get(int key);

    V put(int key, V value);

    V getDefaultValue();

    V remove(int key);

    boolean remove(int key, V value);

    V computeIfAbsent(int key, Int2ObjectFunction<V> mappingFunction);

    V computeIfPresent(int key, BiFunction<Integer, V, V> mappingFunction);
}
