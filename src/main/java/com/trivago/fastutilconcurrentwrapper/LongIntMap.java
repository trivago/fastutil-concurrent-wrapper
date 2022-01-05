package com.trivago.fastutilconcurrentwrapper;

public interface LongIntMap extends PrimitiveLongKeyMap {

    int DEFAULT_VALUE = 0;

    /**
     * @param key key
     * @return {@code DEFAULT_VALUE} if the key is not present
     */
    int get(long key);

    int put(long key, int value);

    int getDefaultValue();

    int remove(long key);

    /**
     * Remove this key only if it has the given value.
     * @param key
     * @param value
     * @return
     */
    boolean remove(long key, int value);
}
