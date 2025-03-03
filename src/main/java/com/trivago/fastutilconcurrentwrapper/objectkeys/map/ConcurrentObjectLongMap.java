package com.trivago.fastutilconcurrentwrapper.objectkeys.map;

import com.trivago.fastutilconcurrentwrapper.objectkeys.ObjectLongMap;
import com.trivago.fastutilconcurrentwrapper.objectkeys.wrapper.PrimitiveFastutilObjectLongWrapper;
import it.unimi.dsi.fastutil.objects.Object2LongFunction;

import java.util.concurrent.locks.Lock;
import java.util.function.BiFunction;

public class ConcurrentObjectLongMap<K> extends ObjectConcurrentMap<K> implements ObjectLongMap<K> {

    private final ObjectLongMap<K>[] maps;
    private final long defaultValue;

    public ConcurrentObjectLongMap(int numBuckets,
                                   int initialCapacity,
                                   float loadFactor,
                                   long defaultValue) {
        super(numBuckets);

        //noinspection unchecked
        this.maps = new ObjectLongMap[numBuckets];
        this.defaultValue = defaultValue;

        for (int i = 0; i < numBuckets; i++) {
            maps[i] = new PrimitiveFastutilObjectLongWrapper<>(initialCapacity, loadFactor, defaultValue);
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
    public boolean containsKey(K key) {
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
    public long get(K l) {
        int bucket = getBucket(l);

        long result;

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
    public long put(K key, long value) {
        int bucket = getBucket(key);

        long result;

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
    public long getDefaultValue() {
        return defaultValue;
    }

    @Override
    public long remove(K key) {
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
    public boolean remove(K key, long value) {
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
    public long computeIfAbsent(K key, Object2LongFunction<K> mappingFunction) {
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
    public long computeIfPresent(K key, BiFunction<K, Long, Long> mappingFunction) {
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
