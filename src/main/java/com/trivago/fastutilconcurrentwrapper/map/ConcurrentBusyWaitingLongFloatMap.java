package com.trivago.fastutilconcurrentwrapper.map;

import com.trivago.fastutilconcurrentwrapper.LongFloatMap;
import com.trivago.fastutilconcurrentwrapper.wrapper.PrimitiveFastutilLongFloatWrapper;

import java.util.concurrent.locks.Lock;

public class ConcurrentBusyWaitingLongFloatMap extends PrimitiveConcurrentMap implements LongFloatMap {

    private final LongFloatMap[] maps;
    private final float defaultValue;

    ConcurrentBusyWaitingLongFloatMap(int numBuckets,
                                      int initialCapacity,
                                      float loadFactor,
                                      float defaultValue) {
        super(numBuckets);
        this.defaultValue = defaultValue;
        this.maps = new LongFloatMap[numBuckets];
        for (int i = 0; i < numBuckets; i++) {
            maps[i] = new PrimitiveFastutilLongFloatWrapper(initialCapacity, loadFactor, defaultValue);
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

        while (true) {
            if (readLock.tryLock()) {
                try {
                    return maps[bucket].containsKey(key);
                } finally {
                    readLock.unlock();
                }
            }
        }
    }

    @Override
    public float get(long key) {
        int bucket = getBucket(key);

        Lock readLock = locks[bucket].readLock();

        while (true) {
            if (readLock.tryLock()) {
                try {
                    return maps[bucket].get(key);
                } finally {
                    readLock.unlock();
                }
            }
        }
    }

    @Override
    public float put(long key, float value) {
        int bucket = getBucket(key);

        Lock writeLock = locks[bucket].writeLock();

        while (true) {
            if (writeLock.tryLock()) {
                try {
                    return maps[bucket].put(key, value);
                } finally {
                    writeLock.unlock();
                }
            }
        }
    }

    @Override
    public float getDefaultValue() {
        return defaultValue;
    }

    @Override
    public float remove(long key) {
        int bucket = getBucket(key);

        Lock writeLock = locks[bucket].writeLock();

        while (true) {
            if (writeLock.tryLock()) {
                try {
                    return maps[bucket].remove(key);
                } finally {
                    writeLock.unlock();
                }
            }
        }
    }
}
