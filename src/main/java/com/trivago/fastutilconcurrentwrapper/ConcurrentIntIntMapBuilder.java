package com.trivago.fastutilconcurrentwrapper;

import com.trivago.fastutilconcurrentwrapper.map.ConcurrentBusyWaitingIntIntMap;
import com.trivago.fastutilconcurrentwrapper.map.ConcurrentIntIntMap;

public final class ConcurrentIntIntMapBuilder extends ConcurrentMapBuilder<ConcurrentIntIntMapBuilder, IntIntMap> {

    private int defaultValue = 0;

    private ConcurrentIntIntMapBuilder() {
    }

    public static ConcurrentIntIntMapBuilder newBuilder() {
        return new ConcurrentIntIntMapBuilder();
    }

    public ConcurrentIntIntMapBuilder withDefaultValue(int defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    @Override
    public IntIntMap build() {
        return switch (mapMode) {
            case BUSY_WAITING -> new ConcurrentBusyWaitingIntIntMap(
                    this.buckets,
                    this.initialCapacity,
                    this.loadFactor,
                    this.defaultValue
            );
            case BLOCKING -> new ConcurrentIntIntMap(
                    this.buckets,
                    this.initialCapacity,
                    this.loadFactor,
                    this.defaultValue
            );
        };
    }
}
