package com.trivago.fastutilconcurrentwrapper;

import com.trivago.fastutilconcurrentwrapper.map.ConcurrentBusyWaitingIntFloatMap;
import com.trivago.fastutilconcurrentwrapper.map.ConcurrentIntFloatMap;

public final class ConcurrentIntFloatMapBuilder {

    private MapMode mapMode = MapMode.BUSY_WAITING;
    private int buckets = 12;
    private float defaultValue = 0.0f;
    private int initialCapacity = 300_000;
    private float loadFactor = 0.8f;

    private ConcurrentIntFloatMapBuilder() {
    }

    public static ConcurrentIntFloatMapBuilder newBuilder() {
        return new ConcurrentIntFloatMapBuilder();
    }

    public ConcurrentIntFloatMapBuilder withBuckets(int buckets) {
        this.buckets = buckets;
        return this;
    }

    public ConcurrentIntFloatMapBuilder withDefaultValue(float defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public ConcurrentIntFloatMapBuilder withInitialCapacity(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        return this;
    }

    public ConcurrentIntFloatMapBuilder withLoadFactor(float loadFactor) {
        this.loadFactor = loadFactor;
        return this;
    }

    public ConcurrentIntFloatMapBuilder withMode(MapMode mapMode) {
        this.mapMode = mapMode;
        return this;
    }

    public IntFloatMap build() {
        return mapMode.createMap(this);
    }

    public enum MapMode {
        BUSY_WAITING {
            @Override
            IntFloatMap createMap(ConcurrentIntFloatMapBuilder builder) {
                return new ConcurrentBusyWaitingIntFloatMap(
                        builder.buckets,
                        builder.initialCapacity,
                        builder.loadFactor);
            }
        },
        BLOCKING {
            @Override
            IntFloatMap createMap(ConcurrentIntFloatMapBuilder builder) {
                return new ConcurrentIntFloatMap(
                        builder.buckets,
                        builder.initialCapacity,
                        builder.loadFactor);
            }
        };

        abstract IntFloatMap createMap(ConcurrentIntFloatMapBuilder builder);
    }
}
