package com.trivago.fastutilconcurrentwrapper.primitivekeys.wrapper;

import com.trivago.fastutilconcurrentwrapper.primitivekeys.IntObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectFunction;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;

import java.util.function.BiFunction;

public class PrimitiveFastutilIntObjectWrapper<V> implements IntObjectMap<V> {
    private final V defaultValue;
    private final Int2ObjectOpenHashMap<V> map;

    public PrimitiveFastutilIntObjectWrapper(int initialCapacity, float loadFactor, V defaultValue) {
        this.defaultValue = defaultValue;
        this.map = new Int2ObjectOpenHashMap<>(initialCapacity, loadFactor);
    }

    @Override
    public V get(int key) {
        return map.getOrDefault(key, defaultValue);
    }

    @Override
    public V put(int key, V value) {
        return map.put(key, value);
    }

    @Override
    public V getDefaultValue() {
        return defaultValue;
    }

    @Override
    public V remove(int key) {
        return map.remove(key);
    }

    @Override
    public boolean remove(int key, V value) {
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
    public V computeIfAbsent(int key, Int2ObjectFunction<V> mappingFunction) {
        return map.computeIfAbsent(key, mappingFunction);
    }

    @Override
    public V computeIfPresent(int key, BiFunction<Integer, V, V> mappingFunction) {
        return map.computeIfPresent(key, mappingFunction);
    }
}
