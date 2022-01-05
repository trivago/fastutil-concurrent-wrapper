package com.trivago.fastutilconcurrentwrapper.map;

import com.trivago.fastutilconcurrentwrapper.LongFloatMap;
import com.trivago.fastutilconcurrentwrapper.wrapper.PrimitiveFastutilLongFloatWrapper;
import java.util.concurrent.locks.Lock;

public class ConcurrentLongFloatMap extends PrimitiveConcurrentMap implements LongFloatMap {

    private final LongFloatMap[] maps;
    private final float defaultValue;

    public ConcurrentLongFloatMap(int numBuckets,
                           int initialCapacity,
                           float loadFactor,
                           float defaultValue) {
        super(numBuckets);

        this.maps = new LongFloatMap[numBuckets];
        this.defaultValue = defaultValue;

        for (int i = 0; i < numBuckets; i++) {
            maps[i] = new PrimitiveFastutilLongFloatWrapper(initialCapacity, loadFactor, defaultValue);
        }
    }

    @Override
    public float getDefaultValue() {
        return defaultValue;
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
    public float get(long l) {
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
    public float put(long key, float value) {
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
    public float remove(long key) {
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
    public boolean remove(long key, float value) {
        int bucket = getBucket(key);

        Lock writeLock = locks[bucket].writeLock();
        writeLock.lock();
        try {
            return maps[bucket].remove(key, value);
        } finally {
            writeLock.unlock();
        }
    }

}
