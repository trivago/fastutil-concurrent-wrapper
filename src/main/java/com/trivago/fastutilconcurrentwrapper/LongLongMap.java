package com.trivago.fastutilconcurrentwrapper;

public interface LongLongMap extends PrimitiveLongKeyMap {

    long DEFAULT_VALUE = 0;

    /**
     * @param key
     * @return 0 if the key is not present
     */
    long get(long key);

    long put(long key, long value);

    long remove(long key);
}
