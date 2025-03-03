package com.trivago.fastutilconcurrentwrapper.objectkeys;

import it.unimi.dsi.fastutil.objects.Object2IntFunction;

import java.util.function.BiFunction;

public interface ObjectIntMap<K> extends ObjectKeyMap<K> {

    int DEFAULT_VALUE = 0;

    /**
     * @param key
     * @return 0 if the key is not present
     */
    int get(K key);

    int put(K key, int value);

    int getDefaultValue();

    int remove(K key);

    boolean remove(K key, int value);

    int computeIfAbsent(K key, Object2IntFunction<K> mappingFunction);

    int computeIfPresent(K key, BiFunction<K, Integer, Integer> mappingFunction);
}
