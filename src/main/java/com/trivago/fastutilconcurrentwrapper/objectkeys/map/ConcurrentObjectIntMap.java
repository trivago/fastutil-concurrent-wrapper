package com.trivago.fastutilconcurrentwrapper.objectkeys.map;

import com.trivago.fastutilconcurrentwrapper.objectkeys.ObjectIntMap;
import com.trivago.fastutilconcurrentwrapper.objectkeys.ObjectLongMap;
import com.trivago.fastutilconcurrentwrapper.objectkeys.wrapper.PrimitiveFastutilObjectIntWrapper;
import com.trivago.fastutilconcurrentwrapper.objectkeys.wrapper.PrimitiveFastutilObjectLongWrapper;
import it.unimi.dsi.fastutil.objects.Object2IntFunction;
import it.unimi.dsi.fastutil.objects.Object2LongFunction;

import java.util.concurrent.locks.Lock;
import java.util.function.BiFunction;

public class ConcurrentObjectIntMap<K> extends ObjectConcurrentMap<K> implements ObjectIntMap<K> {

    private final ObjectIntMap<K>[] maps;
    private final int defaultValue;

    public ConcurrentObjectIntMap(int numBuckets,
                                  int initialCapacity,
                                  float loadFactor,
                                  int defaultValue) {
        super(numBuckets);

        //noinspection unchecked
        this.maps = new ObjectIntMap[numBuckets];
        this.defaultValue = defaultValue;

        for (int i = 0; i < numBuckets; i++) {
            maps[i] = new PrimitiveFastutilObjectIntWrapper<>(initialCapacity, loadFactor, defaultValue);
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
    public int get(K l) {
        int bucket = getBucket(l);

        int result;

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
    public int put(K key, int value) {
        int bucket = getBucket(key);

        int result;

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
    public int getDefaultValue() {
        return defaultValue;
    }

    @Override
    public int remove(K key) {
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
    public boolean remove(K key, int value) {
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
    public int computeIfAbsent(K key, Object2IntFunction<K> mappingFunction) {
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
    public int computeIfPresent(K key, BiFunction<K, Integer, Integer> mappingFunction) {
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
