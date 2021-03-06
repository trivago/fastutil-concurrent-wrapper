package com.trivago.fastutilconcurrentwrapper;

import com.trivago.fastutilconcurrentwrapper.map.ConcurrentBusyWaitingLongFloatMap;
import com.trivago.fastutilconcurrentwrapper.map.ConcurrentLongFloatMap;

public final class ConcurrentLongFloatMapBuilder {

    private MapMode mapMode = MapMode.BLOCKING;
    private int buckets = 8;
    private int initialCapacity = 100_000;
    private float loadFactor = 0.8f;
    private float defaultValue = LongFloatMap.DEFAULT_VALUE;

    private ConcurrentLongFloatMapBuilder() {
    }

    public static ConcurrentLongFloatMapBuilder newBuilder() {
        return new ConcurrentLongFloatMapBuilder();
    }

    public ConcurrentLongFloatMapBuilder withBuckets(int buckets) {
        this.buckets = buckets;
        return this;
    }

    public ConcurrentLongFloatMapBuilder withInitialCapacity(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        return this;
    }

    public ConcurrentLongFloatMapBuilder withLoadFactor(float loadFactor) {
        this.loadFactor = loadFactor;
        return this;
    }

    public ConcurrentLongFloatMapBuilder withDefaultValue(float defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public ConcurrentLongFloatMapBuilder withMode(MapMode mapMode) {
        this.mapMode = mapMode;
        return this;
    }

    public LongFloatMap build() {
        return mapMode.createMap(this);
    }

    public enum MapMode {
        BLOCKING {
            @Override
            LongFloatMap createMap(ConcurrentLongFloatMapBuilder builder) {
                return new ConcurrentLongFloatMap(
                        builder.buckets,
                        builder.initialCapacity,
                        builder.loadFactor,
                        builder.defaultValue);
            }
        },
        BUSY_WAITING {
            @Override
            LongFloatMap createMap(ConcurrentLongFloatMapBuilder builder) {
                return new ConcurrentBusyWaitingLongFloatMap(
                        builder.buckets,
                        builder.initialCapacity,
                        builder.loadFactor,
                        builder.defaultValue);
            }
        };

        abstract LongFloatMap createMap(ConcurrentLongFloatMapBuilder builder);
    }
}
