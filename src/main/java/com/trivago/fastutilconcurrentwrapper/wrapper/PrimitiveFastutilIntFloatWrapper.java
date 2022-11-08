package com.trivago.fastutilconcurrentwrapper.wrapper;

import com.trivago.fastutilconcurrentwrapper.IntFloatMap;
import it.unimi.dsi.fastutil.ints.Int2FloatFunction;
import it.unimi.dsi.fastutil.ints.Int2FloatOpenHashMap;

import java.util.function.BiFunction;

public class PrimitiveFastutilIntFloatWrapper implements IntFloatMap {
    private final float defaultValue;
    private final Int2FloatOpenHashMap map;

    public PrimitiveFastutilIntFloatWrapper(int initialCapacity, float loadFactor, float defaultValue) {
        this.defaultValue = defaultValue;
        this.map = new Int2FloatOpenHashMap(initialCapacity, loadFactor);

    }

    @Override
    public float get(int key) {
        return map.getOrDefault(key, defaultValue);
    }

    @Override
    public float put(int key, float value) {
        return map.put(key, value);
    }

    @Override
    public float getDefaultValue() {
        return defaultValue;
    }

    @Override
    public float remove(int key) {
        return map.remove(key);
    }

    @Override
    public boolean remove(int key, float value) {
        return map.remove(key, value);
    }

    @Override
    public boolean containsKey(int key) {
        return map.containsKey(key);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public float computeIfAbsent(int key, Int2FloatFunction mappingFunction) {
        return map.computeIfAbsent(key, mappingFunction);
    }

    @Override
    public float computeIfPresent(int key, BiFunction<Integer, Float, Float> mappingFunction) {
        return map.computeIfPresent(key, mappingFunction);
    }
}
