package com.trivago.fastutilconcurrentwrapper.wrapper;

import com.trivago.fastutilconcurrentwrapper.IntFloatMap;
import it.unimi.dsi.fastutil.ints.Int2FloatOpenHashMap;

public class PrimitiveFastutilIntFloatWrapper implements IntFloatMap {
    private final float defaultValue;
    private final Int2FloatOpenHashMap int2FloatOpenHashMap;

    public PrimitiveFastutilIntFloatWrapper(int initialCapacity, float loadFactor, float defaultValue) {
        this.defaultValue = defaultValue;
        this.int2FloatOpenHashMap = new Int2FloatOpenHashMap(initialCapacity, loadFactor);

    }

    @Override
    public float get(int key) {
        return int2FloatOpenHashMap.getOrDefault(key, defaultValue);
    }

    @Override
    public float put(int key, float value) {
        return int2FloatOpenHashMap.put(key, value);
    }

    @Override
    public float getDefaultValue() {
        return defaultValue;
    }

    @Override
    public float remove(int key) {
        return int2FloatOpenHashMap.remove(key);
    }

    @Override
    public boolean remove(int key, float value) {
        return int2FloatOpenHashMap.remove(key, value);
    }

    @Override
    public boolean containsKey(int key) {
        return int2FloatOpenHashMap.containsKey(key);
    }

    @Override
    public int size() {
        return int2FloatOpenHashMap.size();
    }

    @Override
    public boolean isEmpty() {
        return int2FloatOpenHashMap.isEmpty();
    }
}
