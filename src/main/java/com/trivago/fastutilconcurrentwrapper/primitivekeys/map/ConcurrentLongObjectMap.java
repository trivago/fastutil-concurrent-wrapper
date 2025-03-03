package com.trivago.fastutilconcurrentwrapper.primitivekeys.map;

import com.trivago.fastutilconcurrentwrapper.primitivekeys.LongObjectMap;
import com.trivago.fastutilconcurrentwrapper.primitivekeys.wrapper.PrimitiveFastutilLongObjectWrapper;
import it.unimi.dsi.fastutil.longs.Long2ObjectFunction;

import java.util.concurrent.locks.Lock;
import java.util.function.BiFunction;

public class ConcurrentLongObjectMap<V> extends PrimitiveConcurrentMap implements LongObjectMap<V> {

    private final LongObjectMap<V>[] maps;
    private final V defaultValue;

    public ConcurrentLongObjectMap(int numBuckets, int initialCapacity, float loadFactor, V defaultValue) {
        super(numBuckets);

        //noinspection unchecked
        this.maps = new LongObjectMap[numBuckets];
        this.defaultValue = defaultValue;

        for (int i = 0; i < numBuckets; i++) {
            maps[i] = new PrimitiveFastutilLongObjectWrapper<>(initialCapacity, loadFactor, defaultValue);
        }
    }

    @Override
    public int size() {
        return super.size(maps);
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty(maps);
    }

    @Override
    public boolean containsKey(long key) {
        int bucket = getBucket(key);

        Lock readLock = locks[bucket].readLock();
        readLock.lock();
        try {
            return maps[bucket].containsKey(key);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public V get(long l) {
        int bucket = getBucket(l);

        V result;

        Lock readLock = locks[bucket].readLock();
        readLock.lock();
        try {
            result = maps[bucket].get(l);
        } finally {
            readLock.unlock();
        }

        return result;
    }

    @Override
    public V put(long key, V value) {
        int bucket = getBucket(key);

        V result;

        Lock writeLock = locks[bucket].writeLock();
        writeLock.lock();
        try {
            result = maps[bucket].put(key, value);
        } finally {
            writeLock.unlock();
        }

        return result;
    }

    @Override
    public V getDefaultValue() {
        return defaultValue;
    }

    @Override
    public V remove(long key) {
        int bucket = getBucket(key);

        Lock writeLock = locks[bucket].writeLock();
        writeLock.lock();
        try {
            return maps[bucket].remove(key);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public boolean remove(long key, V value) {
        int bucket = getBucket(key);

        Lock writeLock = locks[bucket].writeLock();
        writeLock.lock();
        try {
            return maps[bucket].remove(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public V computeIfAbsent(long key, Long2ObjectFunction<V> mappingFunction) {
        int bucket = getBucket(key);

        Lock writeLock = locks[bucket].writeLock();
        writeLock.lock();
        try {
            return maps[bucket].computeIfAbsent(key, mappingFunction);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public V computeIfPresent(long key, BiFunction<Long, V, V> mappingFunction) {
        int bucket = getBucket(key);

        Lock writeLock = locks[bucket].writeLock();
        writeLock.lock();
        try {
            return maps[bucket].computeIfPresent(key, mappingFunction);
        } finally {
            writeLock.unlock();
        }
    }
}
