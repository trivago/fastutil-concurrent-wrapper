package com.trivago.fastutilconcurrentwrapper;

import com.trivago.fastutilconcurrentwrapper.map.ConcurrentBusyWaitingLongIntMap;
import com.trivago.fastutilconcurrentwrapper.map.ConcurrentLongIntMap;

public final class ConcurrentLongIntMapBuilder extends ConcurrentMapBuilder<ConcurrentLongIntMapBuilder, LongIntMap> {

    private int defaultValue = LongIntMap.DEFAULT_VALUE;

    private ConcurrentLongIntMapBuilder() {
    }

    public static ConcurrentLongIntMapBuilder newBuilder() {
        return new ConcurrentLongIntMapBuilder();
    }

    public ConcurrentLongIntMapBuilder withDefaultValue(int defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    @Override
    public LongIntMap build() {
        return switch (mapMode) {
            case BUSY_WAITING -> new ConcurrentBusyWaitingLongIntMap(
                    this.buckets,
                    this.initialCapacity,
                    this.loadFactor,
                    this.defaultValue
            );
            case BLOCKING -> new ConcurrentLongIntMap(
                    this.buckets,
                    this.initialCapacity,
                    this.loadFactor,
                    this.defaultValue
            );
        };
    }
}
