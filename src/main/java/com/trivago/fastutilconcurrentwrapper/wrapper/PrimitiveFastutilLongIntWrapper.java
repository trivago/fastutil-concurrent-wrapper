package com.trivago.fastutilconcurrentwrapper.wrapper;

import com.trivago.fastutilconcurrentwrapper.LongIntMap;
import it.unimi.dsi.fastutil.longs.Long2IntOpenHashMap;

public class PrimitiveFastutilLongIntWrapper implements LongIntMap {
    private final int defaultValue;
    private final Long2IntOpenHashMap map;

    public PrimitiveFastutilLongIntWrapper(int initialCapacity, float loadFactor, int defaultValue) {
        this.map = new Long2IntOpenHashMap(initialCapacity, loadFactor);
        this.defaultValue = defaultValue;
    }

    @Override
    public int getDefaultValue() {
        return defaultValue;
    }

    @Override
    public int get(long key) {
        return map.getOrDefault(key, defaultValue);
    }

    @Override
    public int put(long key, int value) {
        return map.put(key, value);
    }

    @Override
    public int remove(long key) {
        return map.remove(key);
    }

    @Override
    public boolean remove(long key, int value) {
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
}
