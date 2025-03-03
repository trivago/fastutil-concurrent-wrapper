package com.trivago.fastutilconcurrentwrapper.objectkeys.wrapper;

import com.trivago.fastutilconcurrentwrapper.objectkeys.ObjectIntMap;
import it.unimi.dsi.fastutil.objects.Object2IntFunction;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;

import java.util.function.BiFunction;

public class PrimitiveFastutilObjectIntWrapper<K> implements ObjectIntMap<K> {
    private final Object2IntOpenHashMap<K> map;
    private final int defaultValue;

    public PrimitiveFastutilObjectIntWrapper(int initialCapacity, float loadFactor) {
        this(initialCapacity, loadFactor, ObjectIntMap.DEFAULT_VALUE);
    }

    public PrimitiveFastutilObjectIntWrapper(int initialCapacity, float loadFactor, int defaultValue) {
        this.defaultValue = defaultValue;
        this.map = new Object2IntOpenHashMap<>(initialCapacity, loadFactor);
    }

    @Override
    public int get(K key) {
        return map.getOrDefault(key, defaultValue);
    }

    @Override
    public int put(K key, int value) {
        return map.put(key, value);
    }

    @Override
    public int getDefaultValue() {
        return defaultValue;
    }

    @Override
    public int remove(K key) {
        return map.removeInt(key);
    }

    @Override
    public boolean remove(K key, int value) {
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
    public int computeIfAbsent(K key, Object2IntFunction<K> mappingFunction) {
        return map.computeIfAbsent(key, mappingFunction);
    }

    @Override
    public int computeIfPresent(K key, BiFunction<K, Integer, Integer> mappingFunction) {
        return map.computeIntIfPresent(key, mappingFunction);
    }
}
