package com.trivago.fastutilconcurrentwrapper.objectkeys.map;

import com.trivago.fastutilconcurrentwrapper.objectkeys.ObjectFloatMap;
import com.trivago.fastutilconcurrentwrapper.objectkeys.ObjectIntMap;
import com.trivago.fastutilconcurrentwrapper.objectkeys.wrapper.PrimitiveFastutilObjectFloatWrapper;
import com.trivago.fastutilconcurrentwrapper.objectkeys.wrapper.PrimitiveFastutilObjectIntWrapper;
import it.unimi.dsi.fastutil.objects.Object2FloatFunction;
import it.unimi.dsi.fastutil.objects.Object2IntFunction;

import java.util.concurrent.locks.Lock;
import java.util.function.BiFunction;

public class ConcurrentObjectFloatMap<K> extends ObjectConcurrentMap<K> implements ObjectFloatMap<K> {

    private final ObjectFloatMap<K>[] maps;
    private final float defaultValue;

    public ConcurrentObjectFloatMap(int numBuckets,
                                    int initialCapacity,
                                    float loadFactor,
                                    float defaultValue) {
        super(numBuckets);

        //noinspection unchecked
        this.maps = new ObjectFloatMap[numBuckets];
        this.defaultValue = defaultValue;

        for (int i = 0; i < numBuckets; i++) {
            maps[i] = new PrimitiveFastutilObjectFloatWrapper<>(initialCapacity, loadFactor, defaultValue);
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
    public float get(K l) {
        int bucket = getBucket(l);

        float result;

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
    public float put(K key, float value) {
        int bucket = getBucket(key);

        float result;

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
    public float getDefaultValue() {
        return defaultValue;
    }

    @Override
    public float remove(K key) {
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
    public boolean remove(K key, float value) {
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
    public float computeIfAbsent(K key, Object2FloatFunction<K> mappingFunction) {
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
    public float computeIfPresent(K key, BiFunction<K, Float, Float> mappingFunction) {
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
