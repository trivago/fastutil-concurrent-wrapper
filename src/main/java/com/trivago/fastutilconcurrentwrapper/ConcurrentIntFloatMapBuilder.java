package com.trivago.fastutilconcurrentwrapper;

import com.trivago.fastutilconcurrentwrapper.map.ConcurrentBusyWaitingIntFloatMap;
import com.trivago.fastutilconcurrentwrapper.map.ConcurrentIntFloatMap;

public final class ConcurrentIntFloatMapBuilder
        extends ConcurrentMapBuilder<ConcurrentIntFloatMapBuilder, IntFloatMap> {

    private float defaultValue = 0.0f;

    private ConcurrentIntFloatMapBuilder() {
    }

    public static ConcurrentIntFloatMapBuilder newBuilder() {
        return new ConcurrentIntFloatMapBuilder();
    }

    public ConcurrentIntFloatMapBuilder withDefaultValue(float defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    @Override
    public IntFloatMap build() {
        return switch (mapMode) {
            case BUSY_WAITING -> new ConcurrentBusyWaitingIntFloatMap(
                    this.buckets,
                    this.initialCapacity,
                    this.loadFactor,
                    this.defaultValue
            );
            case BLOCKING -> new ConcurrentIntFloatMap(
                    this.buckets,
                    this.initialCapacity,
                    this.loadFactor,
                    this.defaultValue
            );
        };
    }
}
