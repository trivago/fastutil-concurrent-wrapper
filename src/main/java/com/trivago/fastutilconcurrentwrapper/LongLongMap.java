package com.trivago.fastutilconcurrentwrapper;

public interface LongLongMap extends PrimitiveLongKeyMap {

    long DEFAULT_VALUE = 0;

    /**
     * @param key
     * @return 0 if the key is not present
     */
    long get(long key);

    long put(long key, long value);

    long getDefaultValue();

    long remove(long key);

    boolean remove(long key, long value);
}
