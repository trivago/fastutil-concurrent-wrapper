package com.trivago.fastutilconcurrentwrapper.objectkeys;

import com.trivago.fastutilconcurrentwrapper.ConcurrentMapBuilder;
import com.trivago.fastutilconcurrentwrapper.objectkeys.map.ConcurrentBusyWaitingObjectIntMap;
import com.trivago.fastutilconcurrentwrapper.objectkeys.map.ConcurrentObjectIntMap;

public final class ConcurrentObjectIntMapBuilder<K> extends ConcurrentMapBuilder<
        ConcurrentObjectIntMapBuilder<K>, ObjectIntMap<K>> {

    private int defaultValue = ObjectIntMap.DEFAULT_VALUE;

    private ConcurrentObjectIntMapBuilder() {
    }

    public static <K> ConcurrentObjectIntMapBuilder<K> newBuilder() {
        return new ConcurrentObjectIntMapBuilder<>();
    }

    public ConcurrentObjectIntMapBuilder<K> withDefaultValue(int defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public ObjectIntMap<K> build() {
        return switch (mapMode) {
            case BUSY_WAITING -> new ConcurrentBusyWaitingObjectIntMap<>(
                    this.buckets,
                    this.initialCapacity,
                    this.loadFactor,
                    this.defaultValue
            );
            case BLOCKING -> new ConcurrentObjectIntMap<>(
                    this.buckets,
                    this.initialCapacity,
                    this.loadFactor,
                    this.defaultValue
            );
        };
    }
}
