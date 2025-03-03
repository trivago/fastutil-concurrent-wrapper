package com.trivago.fastutilconcurrentwrapper.objectkeys;

import it.unimi.dsi.fastutil.objects.Object2LongFunction;

import java.util.function.BiFunction;

public interface ObjectLongMap<K> extends ObjectKeyMap<K> {

    long DEFAULT_VALUE = 0L;

    /**
     * @param key
     * @return 0 if the key is not present
     */
    long get(K key);

    long put(K key, long value);

    long getDefaultValue();

    long remove(K key);

    boolean remove(K key, long value);

    long computeIfAbsent(K key, Object2LongFunction<K> mappingFunction);

    long computeIfPresent(K key, BiFunction<K, Long, Long> mappingFunction);
}
