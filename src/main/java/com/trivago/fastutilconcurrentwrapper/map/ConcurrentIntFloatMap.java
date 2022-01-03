package com.trivago.fastutilconcurrentwrapper.map;

import com.trivago.fastutilconcurrentwrapper.IntFloatMap;
import com.trivago.fastutilconcurrentwrapper.wrapper.PrimitiveFastutilIntFloatWrapper;

import java.util.concurrent.locks.Lock;

public class ConcurrentIntFloatMap extends PrimitiveConcurrentMap implements IntFloatMap {

    private final IntFloatMap[] maps;

    public ConcurrentIntFloatMap(int numBuckets,
                                 int initialCapacity,
                                 float loadFactor) {
        super(numBuckets);
        this.maps = new IntFloatMap[numBuckets];
        for (int i = 0; i < numBuckets; i++) {
            maps[i] = new PrimitiveFastutilIntFloatWrapper(initialCapacity, loadFactor, IntFloatMap.DEFAULT_VALUE);
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
    public boolean containsKey(int key) {
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
    public float get(int l) {
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
    public float put(int key, float value) {
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
    public float remove(int key) {
        int bucket = getBucket(key);

        Lock writeLock = locks[bucket].writeLock();
        writeLock.lock();
        try {
            return maps[bucket].remove(key);
        } finally {
            writeLock.unlock();
        }
    }

}
