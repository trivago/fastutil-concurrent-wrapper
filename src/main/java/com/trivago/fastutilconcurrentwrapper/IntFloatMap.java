package com.trivago.fastutilconcurrentwrapper;

import it.unimi.dsi.fastutil.ints.Int2FloatFunction;

import java.util.function.BiFunction;

public interface IntFloatMap extends PrimitiveIntKeyMap {

    float DEFAULT_VALUE = 0.0f;

    float get(int key);

    float put(int key, float value);

    float getDefaultValue();

    float remove(int key);

    boolean remove(int key, float value);

    float computeIfAbsent(int key, Int2FloatFunction mappingFunction);

    float computeIfPresent(int key, BiFunction<Integer, Float, Float> mappingFunction);
}
