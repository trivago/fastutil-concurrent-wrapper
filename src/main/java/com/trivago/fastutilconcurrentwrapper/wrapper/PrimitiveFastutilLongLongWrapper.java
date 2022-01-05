package com.trivago.fastutilconcurrentwrapper.wrapper;

import com.trivago.fastutilconcurrentwrapper.LongLongMap;
import it.unimi.dsi.fastutil.longs.Long2LongOpenHashMap;

public class PrimitiveFastutilLongLongWrapper implements LongLongMap {
    private final Long2LongOpenHashMap map;
    private final long defaultValue;

    public PrimitiveFastutilLongLongWrapper(int initialCapacity, float loadFactor) {
        this(initialCapacity, loadFactor, LongLongMap.DEFAULT_VALUE);
    }

    public PrimitiveFastutilLongLongWrapper(int initialCapacity, float loadFactor, long defaultValue) {
        this.defaultValue = defaultValue;
        this.map = new Long2LongOpenHashMap(initialCapacity, loadFactor);
    }

    @Override
    public long get(long key) {
        return map.getOrDefault(key, defaultValue);
    }

    @Override
    public long put(long key, long value) {
        return map.put(key, value);
    }

    @Override
    public long getDefaultValue() {
        return defaultValue;
    }

    @Override
    public long remove(long key) {
        return map.remove(key);
    }

    @Override
    public boolean remove(long key, long value) {
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
