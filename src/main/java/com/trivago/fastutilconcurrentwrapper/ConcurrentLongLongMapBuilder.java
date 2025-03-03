package com.trivago.fastutilconcurrentwrapper;

import com.trivago.fastutilconcurrentwrapper.map.ConcurrentBusyWaitingLongLongMap;
import com.trivago.fastutilconcurrentwrapper.map.ConcurrentLongLongMap;

public final class ConcurrentLongLongMapBuilder
        extends ConcurrentMapBuilder<ConcurrentLongLongMapBuilder, LongLongMap> {

    private long defaultValue = LongLongMap.DEFAULT_VALUE;

    private ConcurrentLongLongMapBuilder() {
    }

    public static ConcurrentLongLongMapBuilder newBuilder() {
        return new ConcurrentLongLongMapBuilder();
    }

    public ConcurrentLongLongMapBuilder withDefaultValue(long defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    @Override
    public LongLongMap build() {
        return switch (mapMode) {
            case BUSY_WAITING -> new ConcurrentBusyWaitingLongLongMap(
                    this.buckets,
                    this.initialCapacity,
                    this.loadFactor,
                    this.defaultValue
            );
            case BLOCKING -> new ConcurrentLongLongMap(
                    this.buckets,
                    this.initialCapacity,
                    this.loadFactor,
                    this.defaultValue
            );
        };
    }
}
