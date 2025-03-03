package com.trivago.fastutilconcurrentwrapper.primitivekeys;

import it.unimi.dsi.fastutil.longs.Long2ObjectFunction;

import java.util.function.BiFunction;

public interface LongObjectMap<V> extends PrimitiveLongKeyMap {

    V get(long key);

    V put(long key, V value);

    V getDefaultValue();

    V remove(long key);

    boolean remove(long key, V value);

    V computeIfAbsent(long key, Long2ObjectFunction<V> mappingFunction);

    V computeIfPresent(long key, BiFunction<Long, V, V> mappingFunction);
}
