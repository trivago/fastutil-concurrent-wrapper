package com.trivago.fastutilconcurrentwrapper.wrapper;

import com.trivago.fastutilconcurrentwrapper.IntLongMap;
import it.unimi.dsi.fastutil.ints.Int2LongFunction;
import it.unimi.dsi.fastutil.ints.Int2LongOpenHashMap;

import java.util.function.BiFunction;

public class PrimitiveFastutilIntLongWrapper implements IntLongMap {
    private final Int2LongOpenHashMap map;
    private final long defaultValue;

    public PrimitiveFastutilIntLongWrapper(int initialCapacity, float loadFactor) {
        this(initialCapacity, loadFactor, IntLongMap.DEFAULT_VALUE);
    }

    public PrimitiveFastutilIntLongWrapper(int initialCapacity, float loadFactor, long defaultValue) {
        this.defaultValue = defaultValue;
        this.map = new Int2LongOpenHashMap(initialCapacity, loadFactor);
    }

    @Override
    public long get(int key) {
        return map.getOrDefault(key, defaultValue);
    }

    @Override
    public long put(int key, long value) {
        return map.put(key, value);
    }

    @Override
    public long getDefaultValue() {
        return defaultValue;
    }

    @Override
    public long remove(int key) {
        return map.remove(key);
    }

    @Override
    public boolean remove(int key, long value) {
        return map.remove(key, value);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean containsKey(int key) {
        return map.containsKey(key);
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public long computeIfAbsent(int key, Int2LongFunction mappingFunction) {
        return map.computeIfAbsent(key, mappingFunction);
    }

    @Override
    public long computeIfPresent(int key, BiFunction<Integer, Long, Long> mappingFunction) {
        return map.computeIfPresent(key, mappingFunction);
    }
}
