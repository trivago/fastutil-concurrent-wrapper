package com.trivago.fastutilconcurrentwrapper.wrapper;

import com.trivago.fastutilconcurrentwrapper.LongFloatMap;
import it.unimi.dsi.fastutil.longs.Long2FloatFunction;
import it.unimi.dsi.fastutil.longs.Long2FloatOpenHashMap;

import java.util.function.BiFunction;

public class PrimitiveFastutilLongFloatWrapper implements LongFloatMap {
    private final float defaultValue;
    private final Long2FloatOpenHashMap map;

    public PrimitiveFastutilLongFloatWrapper(int initialCapacity, float loadFactor, float defaultValue) {
        this.map = new Long2FloatOpenHashMap(initialCapacity, loadFactor);
        this.defaultValue = defaultValue;
    }

    @Override
    public float getDefaultValue() {
        return defaultValue;
    }

    @Override
    public float get(long key) {
        return map.getOrDefault(key, defaultValue);
    }

    @Override
    public float put(long key, float value) {
        return map.put(key, value);
    }

    @Override
    public float remove(long key) {
        return map.remove(key);
    }

    @Override
    public boolean remove(long key, float value) {
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
    public float computeIfAbsent(long key, Long2FloatFunction mappingFunction) {
        return map.computeIfAbsent(key, mappingFunction);
    }

    @Override
    public float computeIfPresent(int key, BiFunction<Long, Float, Float> mappingFunction) {
        return map.computeIfPresent(key, mappingFunction);
    }
}
