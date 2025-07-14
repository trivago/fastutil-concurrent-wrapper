package com.trivago.fastutilconcurrentwrapper;

import com.trivago.fastutilconcurrentwrapper.map.ConcurrentBusyWaitingIntLongMap;
import com.trivago.fastutilconcurrentwrapper.map.ConcurrentIntLongMap;

public final class ConcurrentIntLongMapBuilder {
    private MapMode mapMode = MapMode.BLOCKING;
    private int buckets = 8;
    private int initialCapacity = 100_000;
    private float loadFactor = 0.8f;
    private long defaultValue = IntLongMap.DEFAULT_VALUE;

    private ConcurrentIntLongMapBuilder() {

    }

    public static ConcurrentIntLongMapBuilder newBuilder() {
        return new ConcurrentIntLongMapBuilder();
    }

    public ConcurrentIntLongMapBuilder withBuckets(int buckets) {
        this.buckets = buckets;
        return this;
    }

    public ConcurrentIntLongMapBuilder withInitialCapacity(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        return this;
    }

    public ConcurrentIntLongMapBuilder withLoadFactor(float loadFactor) {
        this.loadFactor = loadFactor;
        return this;
    }

    public ConcurrentIntLongMapBuilder withMode(MapMode mapMode) {
        this.mapMode = mapMode;
        return this;
    }

    public ConcurrentIntLongMapBuilder withDefaultValue(long defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public IntLongMap build() {
        return mapMode.createMap(this);
    }

    public enum MapMode {
        BUSY_WAITING {
            @Override
            IntLongMap createMap(ConcurrentIntLongMapBuilder builder) {
                return new ConcurrentBusyWaitingIntLongMap(
                        builder.buckets,
                        builder.initialCapacity,
                        builder.loadFactor,
                        builder.defaultValue);
            }
        },
        BLOCKING {
            @Override
            IntLongMap createMap(ConcurrentIntLongMapBuilder builder) {
                return new ConcurrentIntLongMap(
                        builder.buckets,
                        builder.initialCapacity,
                        builder.loadFactor,
                        builder.defaultValue);
            }
        };

        abstract IntLongMap createMap(ConcurrentIntLongMapBuilder builder);
    }
}
