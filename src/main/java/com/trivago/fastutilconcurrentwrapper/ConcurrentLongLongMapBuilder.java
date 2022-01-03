package com.trivago.fastutilconcurrentwrapper;

import com.trivago.fastutilconcurrentwrapper.map.ConcurrentBusyWaitingLongLongMap;
import com.trivago.fastutilconcurrentwrapper.map.ConcurrentLongLongMap;

public final class ConcurrentLongLongMapBuilder {
    private MapMode mapMode = MapMode.BLOCKING;
    private int buckets = 12;
    private int initialCapacity = 300_000;
    private float loadFactor = 0.8f;
    private long defaultValue = LongLongMap.DEFAULT_VALUE;

    private ConcurrentLongLongMapBuilder() {

    }

    public static ConcurrentLongLongMapBuilder newBuilder() {
        return new ConcurrentLongLongMapBuilder();
    }

    public ConcurrentLongLongMapBuilder withBuckets(int buckets) {
        this.buckets = buckets;
        return this;
    }

    public ConcurrentLongLongMapBuilder withInitialCapacity(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        return this;
    }

    public ConcurrentLongLongMapBuilder withLoadFactor(float loadFactor) {
        this.loadFactor = loadFactor;
        return this;
    }

    public ConcurrentLongLongMapBuilder withMode(MapMode mapMode) {
        this.mapMode = mapMode;
        return this;
    }

    public ConcurrentLongLongMapBuilder withDefaultValue(long defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public LongLongMap build() {
        return mapMode.createMap(this);
    }

    public enum MapMode {
        BUSY_WAITING {
            @Override
            LongLongMap createMap(ConcurrentLongLongMapBuilder builder) {
                return new ConcurrentBusyWaitingLongLongMap(
                        builder.buckets,
                        builder.initialCapacity,
                        builder.loadFactor,
                        builder.defaultValue);
            }
        },
        BLOCKING {
            @Override
            LongLongMap createMap(ConcurrentLongLongMapBuilder builder) {
                return new ConcurrentLongLongMap(
                        builder.buckets,
                        builder.initialCapacity,
                        builder.loadFactor,
                        builder.defaultValue);
            }
        };

        abstract LongLongMap createMap(ConcurrentLongLongMapBuilder builder);
    }
}
