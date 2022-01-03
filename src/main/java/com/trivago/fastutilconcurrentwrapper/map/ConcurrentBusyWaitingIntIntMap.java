package com.trivago.fastutilconcurrentwrapper.map;

import com.trivago.fastutilconcurrentwrapper.IntIntMap;
import com.trivago.fastutilconcurrentwrapper.wrapper.PrimitiveFastutilIntIntWrapper;

import java.util.concurrent.locks.Lock;

public class ConcurrentBusyWaitingIntIntMap extends PrimitiveConcurrentMap implements IntIntMap {

    private final IntIntMap[] maps;

    ConcurrentBusyWaitingIntIntMap(int numBuckets,
                                   int initialCapacity,
                                   float loadFactor,
                                   int defaultValue) {
        super(numBuckets);
        this.maps = new IntIntMap[numBuckets];
        for (int i = 0; i < numBuckets; i++) {
            maps[i] = new PrimitiveFastutilIntIntWrapper(initialCapacity, loadFactor, defaultValue);
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
    public int get(int key) {
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
    public int put(int key, int value) {
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
    public int remove(int key) {
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
