package com.trivago.fastutilconcurrentwrapper;

import com.trivago.fastutilconcurrentwrapper.map.ConcurrentBusyWaitingLongShortMap;
import com.trivago.fastutilconcurrentwrapper.map.ConcurrentLongShortMap;

public final class ConcurrentLongShortMapBuilder {
    private MapMode mapMode = MapMode.BLOCKING;
    private int buckets = 8;
    private int initialCapacity = 100_000;
    private float loadFactor = 0.8f;
    private short defaultValue = LongShortMap.DEFAULT_VALUE;

    private ConcurrentLongShortMapBuilder() {

    }

    public static ConcurrentLongShortMapBuilder newBuilder() {
        return new ConcurrentLongShortMapBuilder();
    }

    public ConcurrentLongShortMapBuilder withBuckets(int buckets) {
        this.buckets = buckets;
        return this;
    }

    public ConcurrentLongShortMapBuilder withInitialCapacity(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        return this;
    }

    public ConcurrentLongShortMapBuilder withLoadFactor(float loadFactor) {
        this.loadFactor = loadFactor;
        return this;
    }

    public ConcurrentLongShortMapBuilder withMode(MapMode mapMode) {
        this.mapMode = mapMode;
        return this;
    }

    public ConcurrentLongShortMapBuilder withDefaultValue(short defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public LongShortMap build() {
        return mapMode.createMap(this);
    }

    public enum MapMode {
        BUSY_WAITING {
            @Override
            LongShortMap createMap(ConcurrentLongShortMapBuilder builder) {
                return new ConcurrentBusyWaitingLongShortMap(
                        builder.buckets,
                        builder.initialCapacity,
                        builder.loadFactor,
                        builder.defaultValue);
            }
        },
        BLOCKING {
            @Override
            LongShortMap createMap(ConcurrentLongShortMapBuilder builder) {
                return new ConcurrentLongShortMap(
                        builder.buckets,
                        builder.initialCapacity,
                        builder.loadFactor,
                        builder.defaultValue);
            }
        };

        abstract LongShortMap createMap(ConcurrentLongShortMapBuilder builder);
    }
}

