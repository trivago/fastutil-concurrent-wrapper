package com.trivago.fastutilconcurrentwrapper.wrapper;

import com.trivago.fastutilconcurrentwrapper.LongIntMap;
import com.trivago.fastutilconcurrentwrapper.LongLongMap;
import it.unimi.dsi.fastutil.longs.Long2IntFunction;
import it.unimi.dsi.fastutil.longs.Long2IntOpenHashMap;
import it.unimi.dsi.fastutil.longs.Long2LongFunction;
import it.unimi.dsi.fastutil.longs.Long2LongOpenHashMap;

import java.util.function.BiFunction;

public class PrimitiveFastutilLongIntWrapper implements LongIntMap {
    private final Long2IntOpenHashMap map;
    private final int defaultValue;

    public PrimitiveFastutilLongIntWrapper(int initialCapacity, float loadFactor) {
        this(initialCapacity, loadFactor, LongIntMap.DEFAULT_VALUE);
    }

    public PrimitiveFastutilLongIntWrapper(int initialCapacity, float loadFactor, int defaultValue) {
        this.defaultValue = defaultValue;
        this.map = new Long2IntOpenHashMap(initialCapacity, loadFactor);
    }

    @Override
    public int get(long key) {
        return map.getOrDefault(key, defaultValue);
    }

    @Override
    public int put(long key, int value) {
        return map.put(key, value);
    }

    @Override
    public int getDefaultValue() {
        return defaultValue;
    }

    @Override
    public int remove(long key) {
        return map.remove(key);
    }

    @Override
    public boolean remove(long key, int value) {
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
    public int computeIfAbsent(long key, Long2IntFunction mappingFunction) {
        return map.computeIfAbsent(key, mappingFunction);
    }

    @Override
    public int computeIfPresent(long key, BiFunction<Long, Integer, Integer> mappingFunction) {
        return map.computeIfPresent(key, mappingFunction);
    }
}
