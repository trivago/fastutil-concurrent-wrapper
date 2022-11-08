package com.trivago.fastutilconcurrentwrapper.map;

import com.trivago.fastutilconcurrentwrapper.LongLongMap;
import com.trivago.fastutilconcurrentwrapper.wrapper.PrimitiveFastutilLongLongWrapper;
import it.unimi.dsi.fastutil.longs.Long2LongFunction;

import java.util.concurrent.locks.Lock;
import java.util.function.BiFunction;

public class ConcurrentLongLongMap extends PrimitiveConcurrentMap implements LongLongMap {

    private final LongLongMap[] maps;
    private final long defaultValue;

    public ConcurrentLongLongMap(int numBuckets,
                                 int initialCapacity,
                                 float loadFactor,
                                 long defaultValue) {
        super(numBuckets);

        this.maps = new LongLongMap[numBuckets];
        this.defaultValue = defaultValue;

        for (int i = 0; i < numBuckets; i++) {
            maps[i] = new PrimitiveFastutilLongLongWrapper(initialCapacity, loadFactor, defaultValue);
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
    public long get(long l) {
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
    public long put(long key, long value) {
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
    public long remove(long key) {
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
    public boolean remove(long key, long value) {
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
    public long computeIfAbsent(long key, Long2LongFunction mappingFunction) {
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
    public long computeIfPresent(long key, BiFunction<Long, Long, Long> mappingFunction) {
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
