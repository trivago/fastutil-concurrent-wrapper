package com.trivago.fastutilconcurrentwrapper;

public interface IntFloatMap extends PrimitiveIntKeyMap {

    float DEFAULT_VALUE = 0.0f;

    float get(int key);

    float put(int key, float value);

    float remove(int key);
}
