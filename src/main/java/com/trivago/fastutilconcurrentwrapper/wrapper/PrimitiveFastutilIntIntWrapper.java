package com.trivago.fastutilconcurrentwrapper.wrapper;

import com.trivago.fastutilconcurrentwrapper.IntIntMap;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;

import java.util.function.BiFunction;

public class PrimitiveFastutilIntIntWrapper implements IntIntMap {
    private final int defaultValue;
    private final Int2IntOpenHashMap map;

    public PrimitiveFastutilIntIntWrapper(int initialCapacity, float loadFactor, int defaultValue) {
        this.defaultValue = defaultValue;
        this.map = new Int2IntOpenHashMap(initialCapacity, loadFactor);
    }

    @Override
    public int get(int key) {
        return map.getOrDefault(key, defaultValue);
    }

    @Override
    public int put(int key, int value) {
        return map.put(key, value);
    }

    @Override
    public int getDefaultValue() {
        return defaultValue;
    }

    @Override
    public int remove(int key) {
        return map.remove(key);
    }

    @Override
    public boolean remove(int key, int value) {
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
    public int computeIfAbsent(int key, Int2IntFunction mappingFunction) {
        return map.computeIfAbsent(key, mappingFunction);
    }

    @Override
    public int computeIfPresent(int key, BiFunction<Integer, Integer, Integer> mappingFunction) {
        return map.computeIfPresent(key, mappingFunction);
    }
}
