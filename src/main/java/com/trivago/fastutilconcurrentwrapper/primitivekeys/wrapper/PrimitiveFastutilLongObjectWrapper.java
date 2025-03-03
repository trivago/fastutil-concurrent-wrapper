package com.trivago.fastutilconcurrentwrapper.primitivekeys.wrapper;

import com.trivago.fastutilconcurrentwrapper.primitivekeys.LongObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectFunction;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;

import java.util.function.BiFunction;

public class PrimitiveFastutilLongObjectWrapper<V> implements LongObjectMap<V> {
    private final Long2ObjectOpenHashMap<V> map;
    private final V defaultValue;

    public PrimitiveFastutilLongObjectWrapper(int initialCapacity, float loadFactor) {
        this(initialCapacity, loadFactor, null);
    }

    public PrimitiveFastutilLongObjectWrapper(int initialCapacity, float loadFactor, V defaultValue) {
        this.defaultValue = defaultValue;
        this.map = new Long2ObjectOpenHashMap<>(initialCapacity, loadFactor);
    }

    @Override
    public V get(long key) {
        return map.getOrDefault(key, defaultValue);
    }

    @Override
    public V put(long key, V value) {
        return map.put(key, value);
    }

    @Override
    public V getDefaultValue() {
        return defaultValue;
    }

    @Override
    public V remove(long key) {
        return map.remove(key);
    }

    @Override
    public boolean remove(long key, V value) {
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
    public V computeIfAbsent(long key, Long2ObjectFunction<V> mappingFunction) {
        return map.computeIfAbsent(key, mappingFunction);
    }

    @Override
    public V computeIfPresent(long key, BiFunction<Long, V, V> mappingFunction) {
        return map.computeIfPresent(key, mappingFunction);
    }
}
