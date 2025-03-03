package com.trivago.fastutilconcurrentwrapper.objectkeys.map;

import com.trivago.fastutilconcurrentwrapper.KeyMap;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class ObjectConcurrentMap<K> {

    protected final int numBuckets;
    protected final ReadWriteLock[] locks;

    protected ObjectConcurrentMap(int numBuckets) {
        this.numBuckets = numBuckets;
        this.locks = new ReadWriteLock[numBuckets];
        for (int i = 0; i < numBuckets; i++) {
            locks[i] = new ReentrantReadWriteLock();
        }
    }

    protected int size(KeyMap[] maps) {
        int sum = 0;
        for (int i = 0; i < numBuckets; i++) {
            sum = sum + sizeOfMap(i, maps);
        }
        return sum;
    }

    private int sizeOfMap(int index, KeyMap[] maps) {
        Lock readLock = locks[index].readLock();
        readLock.lock();
        try {
            return maps[index].size();
        } finally {
            readLock.unlock();
        }
    }

    protected boolean isEmpty(KeyMap[] maps) {
        for (int i = 0; i < numBuckets; i++) {
            if (!isMapEmpty(i, maps)) {
                return false;
            }
        }
        return true;
    }

    private boolean isMapEmpty(int index, KeyMap[] maps) {
        Lock readLock = locks[index].readLock();
        readLock.lock();
        try {
            return maps[index].isEmpty();
        } finally {
            readLock.unlock();
        }
    }

    protected int getBucket(K key) {
        return getBucketCheckMinValue(key.hashCode());
    }

    private int getBucketCheckMinValue(int hash) {
        return Math.abs(hash == Integer.MIN_VALUE ? 0 : hash) % numBuckets;
    }
}
