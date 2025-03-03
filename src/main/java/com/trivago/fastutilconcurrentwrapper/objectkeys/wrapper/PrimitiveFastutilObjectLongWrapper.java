package com.trivago.fastutilconcurrentwrapper.objectkeys.wrapper;

import com.trivago.fastutilconcurrentwrapper.objectkeys.ObjectLongMap;
import it.unimi.dsi.fastutil.objects.Object2LongFunction;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;

import java.util.function.BiFunction;

public class PrimitiveFastutilObjectLongWrapper<K> implements ObjectLongMap<K> {
    private final Object2LongOpenHashMap<K> map;
    private final long defaultValue;

    public PrimitiveFastutilObjectLongWrapper(int initialCapacity, float loadFactor) {
        this(initialCapacity, loadFactor, ObjectLongMap.DEFAULT_VALUE);
    }

    public PrimitiveFastutilObjectLongWrapper(int initialCapacity, float loadFactor, long defaultValue) {
        this.defaultValue = defaultValue;
        this.map = new Object2LongOpenHashMap<>(initialCapacity, loadFactor);
    }

    @Override
    public long get(K key) {
        return map.getOrDefault(key, defaultValue);
    }

    @Override
    public long put(K key, long value) {
        return map.put(key, value);
    }

    @Override
    public long getDefaultValue() {
        return defaultValue;
    }

    @Override
    public long remove(K key) {
        return map.removeLong(key);
    }

    @Override
    public boolean remove(K key, long value) {
        return map.remove(key, value);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public long computeIfAbsent(K key, Object2LongFunction<K> mappingFunction) {
        return map.computeIfAbsent(key, mappingFunction);
    }

    @Override
    public long computeIfPresent(K key, BiFunction<K, Long, Long> mappingFunction) {
        return map.computeLongIfPresent(key, mappingFunction);
    }
}
