package com.trivago.fastutilconcurrentwrapper.objectkeys.wrapper;

import com.trivago.fastutilconcurrentwrapper.objectkeys.ObjectFloatMap;
import it.unimi.dsi.fastutil.objects.Object2FloatFunction;
import it.unimi.dsi.fastutil.objects.Object2FloatOpenHashMap;

import java.util.function.BiFunction;

public class PrimitiveFastutilObjectFloatWrapper<K> implements ObjectFloatMap<K> {
    private final Object2FloatOpenHashMap<K> map;
    private final float defaultValue;

    public PrimitiveFastutilObjectFloatWrapper(int initialCapacity, float loadFactor) {
        this(initialCapacity, loadFactor, ObjectFloatMap.DEFAULT_VALUE);
    }

    public PrimitiveFastutilObjectFloatWrapper(int initialCapacity, float loadFactor, float defaultValue) {
        this.defaultValue = defaultValue;
        this.map = new Object2FloatOpenHashMap<>(initialCapacity, loadFactor);
    }

    @Override
    public float get(K key) {
        return map.getOrDefault(key, defaultValue);
    }

    @Override
    public float put(K key, float value) {
        return map.put(key, value);
    }

    @Override
    public float getDefaultValue() {
        return defaultValue;
    }

    @Override
    public float remove(K key) {
        return map.removeFloat(key);
    }

    @Override
    public boolean remove(K key, float value) {
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
    public float computeIfAbsent(K key, Object2FloatFunction<K> mappingFunction) {
        return map.computeIfAbsent(key, mappingFunction);
    }

    @Override
    public float computeIfPresent(K key, BiFunction<K, Float, Float> mappingFunction) {
        return map.computeFloatIfPresent(key, mappingFunction);
    }
}
