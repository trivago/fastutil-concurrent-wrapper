package com.trivago.fastutilconcurrentwrapper.map;

import com.trivago.fastutilconcurrentwrapper.LongShortTreeMap;
import com.trivago.fastutilconcurrentwrapper.wrapper.PrimitiveFastutilLongShortTreeWrapper;
import it.unimi.dsi.fastutil.longs.Long2ShortFunction;
import it.unimi.dsi.fastutil.longs.Long2ShortMap;

import java.util.concurrent.locks.Lock;
import java.util.function.BiFunction;

public class ConcurrentBusyWaitingLongShortTreeMap extends PrimitiveConcurrentMap implements LongShortTreeMap {

    private final LongShortTreeMap[] maps;
    private final short defaultValue;

    public ConcurrentBusyWaitingLongShortTreeMap(int numBuckets,
                                             short defaultValue) {
        super(numBuckets);

        this.maps = new LongShortTreeMap[numBuckets];
        this.defaultValue = defaultValue;

        for (int i = 0; i < numBuckets; i++) {
            maps[i] = new PrimitiveFastutilLongShortTreeWrapper(defaultValue);
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
    public Long2ShortMap.Entry floorEntry(long key) {
        int bucket = getBucket(key);
        Lock readLock = locks[bucket].readLock();
        while (true) {
            if (readLock.tryLock()) {
                try {
                    return maps[bucket].floorEntry(key);
                } finally {
                    readLock.unlock();
                }
            }
        }
    }

    @Override
    public short get(long key) {
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
    public short put(long key, short value) {
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
    public short getDefaultValue() {
        return defaultValue;
    }

    @Override
    public short remove(long key) {
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

    @Override
    public boolean remove(long key, short value) {
        int bucket = getBucket(key);

        Lock writeLock = locks[bucket].writeLock();

        while (true) {
            if (writeLock.tryLock()) {
                try {
                    return maps[bucket].remove(key, value);
                } finally {
                    writeLock.unlock();
                }
            }
        }
    }

    @Override
    public short computeIfAbsent(long key, Long2ShortFunction mappingFunction) {
        int bucket = getBucket(key);

        Lock writeLock = locks[bucket].writeLock();

        while (true) {
            if (writeLock.tryLock()) {
                try {
                    return maps[bucket].computeIfAbsent(key, mappingFunction);
                } finally {
                    writeLock.unlock();
                }
            }
        }
    }

    @Override
    public short computeIfPresent(long key, BiFunction<Long, Short, Short> mappingFunction) {
        int bucket = getBucket(key);

        Lock writeLock = locks[bucket].writeLock();

        while (true) {
            if (writeLock.tryLock()) {
                try {
                    return maps[bucket].computeIfPresent(key, mappingFunction);
                } finally {
                    writeLock.unlock();
                }
            }
        }
    }
}



