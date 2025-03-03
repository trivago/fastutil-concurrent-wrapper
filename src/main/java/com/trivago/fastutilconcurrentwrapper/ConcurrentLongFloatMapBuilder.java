package com.trivago.fastutilconcurrentwrapper;

import com.trivago.fastutilconcurrentwrapper.map.ConcurrentBusyWaitingLongFloatMap;
import com.trivago.fastutilconcurrentwrapper.map.ConcurrentLongFloatMap;

public final class ConcurrentLongFloatMapBuilder
        extends ConcurrentMapBuilder<ConcurrentLongFloatMapBuilder, LongFloatMap> {

    private float defaultValue = LongFloatMap.DEFAULT_VALUE;

    private ConcurrentLongFloatMapBuilder() {
    }

    public static ConcurrentLongFloatMapBuilder newBuilder() {
        return new ConcurrentLongFloatMapBuilder();
    }

    public ConcurrentLongFloatMapBuilder withDefaultValue(float defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    @Override
    public LongFloatMap build() {
        return switch (mapMode) {
            case BUSY_WAITING -> new ConcurrentBusyWaitingLongFloatMap(
                    this.buckets,
                    this.initialCapacity,
                    this.loadFactor,
                    this.defaultValue
            );
            case BLOCKING -> new ConcurrentLongFloatMap(
                    this.buckets,
                    this.initialCapacity,
                    this.loadFactor,
                    this.defaultValue
            );
        };
    }
}
