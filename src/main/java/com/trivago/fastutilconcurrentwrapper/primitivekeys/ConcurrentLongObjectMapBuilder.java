package com.trivago.fastutilconcurrentwrapper.primitivekeys;

import com.trivago.fastutilconcurrentwrapper.ConcurrentMapBuilder;
import com.trivago.fastutilconcurrentwrapper.primitivekeys.map.ConcurrentBusyWaitingLongObjectMap;
import com.trivago.fastutilconcurrentwrapper.primitivekeys.map.ConcurrentLongObjectMap;

public final class ConcurrentLongObjectMapBuilder<V>
        extends ConcurrentMapBuilder<ConcurrentLongObjectMapBuilder<V>, LongObjectMap<V>> {

    private V defaultValue = null;

    private ConcurrentLongObjectMapBuilder() {
    }

    public static <V> ConcurrentLongObjectMapBuilder<V> newBuilder() {
        return new ConcurrentLongObjectMapBuilder<>();
    }

    public ConcurrentLongObjectMapBuilder<V> withDefaultValue(V defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    @Override
    public LongObjectMap<V> build() {
        return switch (mapMode) {
            case BUSY_WAITING -> new ConcurrentBusyWaitingLongObjectMap<>(
                    this.buckets,
                    this.initialCapacity,
                    this.loadFactor,
                    this.defaultValue
            );
            case BLOCKING -> new ConcurrentLongObjectMap<>(
                    this.buckets,
                    this.initialCapacity,
                    this.loadFactor,
                    this.defaultValue
            );
        };
    }
}
