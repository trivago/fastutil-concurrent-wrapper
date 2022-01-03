package com.trivago.fastutilconcurrentwrapper;

public interface LongFloatMap extends PrimitiveLongKeyMap {

    float DEFAULT_VALUE = 0.0f;

    /**
     * @param key key
     * @return 0.0 if the key is not present
     */
    float get(long key);

    float put(long key, float value);

    float getDefaultValue();

    float remove(long key);
}
