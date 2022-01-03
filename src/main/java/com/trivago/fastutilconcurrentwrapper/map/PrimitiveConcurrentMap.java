package com.trivago.fastutilconcurrentwrapper.map;

import com.trivago.fastutilconcurrentwrapper.PrimitiveKeyMap;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class PrimitiveConcurrentMap {

    protected final int numBuckets;
    protected final ReadWriteLock[] locks;

    protected PrimitiveConcurrentMap(int numBuckets) {
        this.numBuckets = numBuckets;
        this.locks = new ReadWriteLock[numBuckets];
        for (int i = 0; i < numBuckets; i++) {
            locks[i] = new ReentrantReadWriteLock();
        }
    }

    protected int size(PrimitiveKeyMap[] maps) {
        int sum = 0;
        for (int i = 0; i < numBuckets; i++) {
            sum = sum + sizeOfMap(i, maps);
        }
        return sum;
    }

    private int sizeOfMap(int index, PrimitiveKeyMap[] maps) {
        Lock readLock = locks[index].readLock();
        readLock.lock();
        try {
            return maps[index].size();
        } finally {
            readLock.unlock();
        }
    }

    protected boolean isEmpty(PrimitiveKeyMap[] maps) {
        for (int i = 0; i < numBuckets; i++) {
            if (!isMapEmpty(i, maps)) {
                return false;
            }
        }
        return true;
    }

    private boolean isMapEmpty(int index, PrimitiveKeyMap[] maps) {
        Lock readLock = locks[index].readLock();
        readLock.lock();
        try {
            return maps[index].isEmpty();
        } finally {
            readLock.unlock();
        }
    }

    protected int getBucket(long key) {
        int hash = Long.hashCode(key);
        return getBucketCheckMinValue(hash);
    }

    protected int getBucket(int key) {
        int hash = Integer.hashCode(key);
        return getBucketCheckMinValue(hash);
    }

    private int getBucketCheckMinValue(int hash) {
        return Math.abs(hash == Integer.MIN_VALUE ? 0 : hash) % numBuckets;
    }
}
