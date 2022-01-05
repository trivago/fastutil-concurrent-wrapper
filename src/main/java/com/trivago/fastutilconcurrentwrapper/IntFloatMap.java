package com.trivago.fastutilconcurrentwrapper;

public interface IntFloatMap extends PrimitiveIntKeyMap {

    float DEFAULT_VALUE = 0.0f;

    float get(int key);

    float put(int key, float value);

    float getDefaultValue();

    float remove(int key);

    boolean remove(int key, float value);
}
