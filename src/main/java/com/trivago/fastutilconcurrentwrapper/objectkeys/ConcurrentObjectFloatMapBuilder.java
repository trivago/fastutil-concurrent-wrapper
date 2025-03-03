package com.trivago.fastutilconcurrentwrapper.objectkeys;

import com.trivago.fastutilconcurrentwrapper.ConcurrentMapBuilder;
import com.trivago.fastutilconcurrentwrapper.objectkeys.map.ConcurrentBusyWaitingObjectFloatMap;
import com.trivago.fastutilconcurrentwrapper.objectkeys.map.ConcurrentObjectFloatMap;

public final class ConcurrentObjectFloatMapBuilder<K> extends ConcurrentMapBuilder<
        ConcurrentObjectFloatMapBuilder<K>, ObjectFloatMap<K>> {

    private float defaultValue = ObjectFloatMap.DEFAULT_VALUE;

    private ConcurrentObjectFloatMapBuilder() {
    }

    public static <K> ConcurrentObjectFloatMapBuilder<K> newBuilder() {
        return new ConcurrentObjectFloatMapBuilder<>();
    }

    public ConcurrentObjectFloatMapBuilder<K> withDefaultValue(float defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    @Override
    public ObjectFloatMap<K> build() {
        return switch (mapMode) {
            case BUSY_WAITING -> new ConcurrentBusyWaitingObjectFloatMap<>(
                    this.buckets,
                    this.initialCapacity,
                    this.loadFactor,
                    this.defaultValue
            );
            case BLOCKING -> new ConcurrentObjectFloatMap<>(
                    this.buckets,
                    this.initialCapacity,
                    this.loadFactor,
                    this.defaultValue
            );
        };
    }
}
