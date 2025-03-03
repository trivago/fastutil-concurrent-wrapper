package com.trivago.fastutilconcurrentwrapper.objectkeys;

import it.unimi.dsi.fastutil.objects.Object2FloatFunction;

import java.util.function.BiFunction;

public interface ObjectFloatMap<K> extends ObjectKeyMap<K> {

    float DEFAULT_VALUE = 0F;

    /**
     * @param key
     * @return 0 if the key is not present
     */
    float get(K key);

    float put(K key, float value);

    float getDefaultValue();

    float remove(K key);

    boolean remove(K key, float value);

    float computeIfAbsent(K key, Object2FloatFunction<K> mappingFunction);

    float computeIfPresent(K key, BiFunction<K, Float, Float> mappingFunction);
}
