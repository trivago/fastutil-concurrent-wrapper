package com.trivago.fastutilconcurrentwrapper.primitivekeys;

import com.trivago.fastutilconcurrentwrapper.ConcurrentMapBuilder;
import com.trivago.fastutilconcurrentwrapper.primitivekeys.map.ConcurrentBusyWaitingIntIntMap;
import com.trivago.fastutilconcurrentwrapper.primitivekeys.map.ConcurrentIntIntMap;

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
