package com.trivago.fastutilconcurrentwrapper.wrapper;

import com.trivago.fastutilconcurrentwrapper.LongShortMap;
import it.unimi.dsi.fastutil.longs.Long2ShortFunction;
import it.unimi.dsi.fastutil.longs.Long2ShortOpenHashMap;

import java.util.function.BiFunction;

public class PrimitiveFastutilLongShortWrapper implements LongShortMap {
    private final Long2ShortOpenHashMap map;
    private final short defaultValue;

    public PrimitiveFastutilLongShortWrapper(int initialCapacity, float loadFactor) {
        this(initialCapacity, loadFactor, LongShortMap.DEFAULT_VALUE);
    }

    public PrimitiveFastutilLongShortWrapper(int initialCapacity, float loadFactor, short defaultValue) {
        this.defaultValue = defaultValue;
        this.map = new Long2ShortOpenHashMap(initialCapacity, loadFactor);
    }

    @Override
    public short get(long key) {
        return map.getOrDefault(key, defaultValue);
    }

    @Override
    public short put(long key, short value) {
        return map.put(key, value);
    }

    @Override
    public short getDefaultValue() {
        return defaultValue;
    }

    @Override
    public short remove(long key) {
        return map.remove(key);
    }

    @Override
    public boolean remove(long key, short value) {
        return map.remove(key, value);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean containsKey(long key) {
        return map.containsKey(key);
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public short computeIfAbsent(long key, Long2ShortFunction mappingFunction) {
        return map.computeIfAbsent(key, mappingFunction);
    }

    @Override
    public short computeIfPresent(long key, BiFunction<Long, Short, Short> mappingFunction) {
        return map.computeIfPresent(key, mappingFunction);
    }
}

