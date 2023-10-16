package com.trivago.fastutilconcurrentwrapper.map;

import com.trivago.fastutilconcurrentwrapper.IntIntMap;
import com.trivago.fastutilconcurrentwrapper.LongIntMap;
import com.trivago.fastutilconcurrentwrapper.wrapper.PrimitiveFastutilIntIntWrapper;
import com.trivago.fastutilconcurrentwrapper.wrapper.PrimitiveFastutilLongIntWrapper;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import it.unimi.dsi.fastutil.longs.Long2IntFunction;

import java.util.concurrent.locks.Lock;
import java.util.function.BiFunction;

public class ConcurrentLongIntMap extends PrimitiveConcurrentMap implements LongIntMap {

    private final LongIntMap[] maps;
    private final int defaultValue;

    public ConcurrentLongIntMap(
            int numBuckets,
            int initialCapacity,
            float loadFactor,
            int defaultValue) {

        super(numBuckets);

        this.maps = new LongIntMap[numBuckets];
        this.defaultValue = defaultValue;

        for (int i = 0; i < numBuckets; i++) {
            maps[i] = new PrimitiveFastutilLongIntWrapper(initialCapacity, loadFactor, defaultValue);
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
    public int get(long l) {
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
    public int put(long key, int value) {
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
    public int remove(long key) {
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
    public boolean remove(long key, int value) {
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
    public int computeIfAbsent(long key, Long2IntFunction mappingFunction) {
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
    public int computeIfPresent(long key, BiFunction<Long, Integer, Integer> mappingFunction) {
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
