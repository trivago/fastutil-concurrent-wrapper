package com.trivago.fastutilconcurrentwrapper.wrapper;

import com.trivago.fastutilconcurrentwrapper.LongFloatMap;
import it.unimi.dsi.fastutil.longs.Long2FloatOpenHashMap;

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
}
