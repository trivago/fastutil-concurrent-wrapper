package com.trivago.fastutilconcurrentwrapper;

import com.trivago.fastutilconcurrentwrapper.map.ConcurrentBusyWaitingIntIntMap;
import com.trivago.fastutilconcurrentwrapper.map.ConcurrentIntIntMap;

public final class ConcurrentIntIntMapBuilder {

    private MapMode mapMode = MapMode.BLOCKING;
    private int buckets = 12;
    private int defaultValue = -1;
    private int initialCapacity = 300_000;
    private float loadFactor = 0.8f;

    private ConcurrentIntIntMapBuilder() {
    }

    public static ConcurrentIntIntMapBuilder newBuilder() {
        return new ConcurrentIntIntMapBuilder();
    }

    public ConcurrentIntIntMapBuilder withBuckets(int buckets) {
        this.buckets = buckets;
        return this;
    }

    public ConcurrentIntIntMapBuilder withDefaultValue(int defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public ConcurrentIntIntMapBuilder withInitialCapacity(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        return this;
    }

    public ConcurrentIntIntMapBuilder withLoadFactor(float loadFactor) {
        this.loadFactor = loadFactor;
        return this;
    }

    public ConcurrentIntIntMapBuilder withMode(MapMode mapMode) {
        this.mapMode = mapMode;
        return this;
    }

    public IntIntMap build() {
        return mapMode.createMap(this);
    }

    public enum MapMode {
        BLOCKING {
            @Override
            IntIntMap createMap(ConcurrentIntIntMapBuilder builder) {
                return new ConcurrentIntIntMap(
                        builder.buckets,
                        builder.initialCapacity,
                        builder.loadFactor,
                        builder.defaultValue);
            }
        },
        BUSY_WAITING {
            @Override
            IntIntMap createMap(ConcurrentIntIntMapBuilder builder) {
                return new ConcurrentBusyWaitingIntIntMap(
                        builder.buckets,
                        builder.initialCapacity,
                        builder.loadFactor,
                        builder.defaultValue);
            }
        };

        abstract IntIntMap createMap(ConcurrentIntIntMapBuilder builder);
    }
}
