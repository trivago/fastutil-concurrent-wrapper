package com.trivago.fastutilconcurrentwrapper;

import it.unimi.dsi.fastutil.longs.Long2ShortFunction;

import java.util.function.BiFunction;

public interface LongShortMap extends PrimitiveLongKeyMap {

    short DEFAULT_VALUE = 0;

    /**
     * @param key key to get
     * @return configured LongShortMap.getDefaultValue(), if the key is not present
     */
    short get(long key);

    short put(long key, short value);

    short getDefaultValue();

    short remove(long key);

    boolean remove(long key, short value);

    short computeIfAbsent(long key, Long2ShortFunction mappingFunction);

    short computeIfPresent(long key, BiFunction<Long, Short, Short> mappingFunction);
}

