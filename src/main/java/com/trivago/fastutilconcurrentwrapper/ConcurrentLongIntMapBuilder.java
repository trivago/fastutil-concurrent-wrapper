package com.trivago.fastutilconcurrentwrapper;

import com.trivago.fastutilconcurrentwrapper.map.ConcurrentBusyWaitingLongIntMap;
import com.trivago.fastutilconcurrentwrapper.map.ConcurrentLongIntMap;

public final class ConcurrentLongIntMapBuilder {
    private MapMode mapMode = MapMode.BLOCKING;
    private int buckets = 8;
    private int initialCapacity = 100_000;
    private float loadFactor = 0.8f;
    private int defaultValue = LongIntMap.DEFAULT_VALUE;

    private ConcurrentLongIntMapBuilder() {

    }

    public static ConcurrentLongIntMapBuilder newBuilder() {
        return new ConcurrentLongIntMapBuilder();
    }

    public ConcurrentLongIntMapBuilder withBuckets(int buckets) {
        this.buckets = buckets;
        return this;
    }

    public ConcurrentLongIntMapBuilder withInitialCapacity(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        return this;
    }

    public ConcurrentLongIntMapBuilder withLoadFactor(float loadFactor) {
        this.loadFactor = loadFactor;
        return this;
    }

    public ConcurrentLongIntMapBuilder withMode(MapMode mapMode) {
        this.mapMode = mapMode;
        return this;
    }

    public ConcurrentLongIntMapBuilder withDefaultValue(int defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public LongIntMap build() {
        return mapMode.createMap(this);
    }

    public enum MapMode {
        BUSY_WAITING {
            @Override
            LongIntMap createMap(ConcurrentLongIntMapBuilder builder) {
                return new ConcurrentBusyWaitingLongIntMap(
                        builder.buckets,
                        builder.initialCapacity,
                        builder.loadFactor,
                        builder.defaultValue);
            }
        },
        BLOCKING {
            @Override
            LongIntMap createMap(ConcurrentLongIntMapBuilder builder) {
                return new ConcurrentLongIntMap(
                        builder.buckets,
                        builder.initialCapacity,
                        builder.loadFactor,
                        builder.defaultValue);
            }
        };

        abstract LongIntMap createMap(ConcurrentLongIntMapBuilder builder);
    }
}
