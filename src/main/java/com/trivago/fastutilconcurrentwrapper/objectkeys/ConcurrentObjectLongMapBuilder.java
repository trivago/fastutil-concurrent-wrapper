package com.trivago.fastutilconcurrentwrapper.objectkeys;

import com.trivago.fastutilconcurrentwrapper.ConcurrentMapBuilder;
import com.trivago.fastutilconcurrentwrapper.objectkeys.map.ConcurrentBusyWaitingObjectLongMap;
import com.trivago.fastutilconcurrentwrapper.objectkeys.map.ConcurrentObjectLongMap;

public final class ConcurrentObjectLongMapBuilder<K> extends ConcurrentMapBuilder<
        ConcurrentObjectLongMapBuilder<K>, ObjectLongMap<K>> {

    private long defaultValue = ObjectLongMap.DEFAULT_VALUE;

    private ConcurrentObjectLongMapBuilder() {
    }

    public static <K> ConcurrentObjectLongMapBuilder<K> newBuilder() {
        return new ConcurrentObjectLongMapBuilder<>();
    }

    public ConcurrentObjectLongMapBuilder<K> withDefaultValue(long defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    @Override
    public ObjectLongMap<K> build() {
        return switch (mapMode) {
            case BUSY_WAITING -> new ConcurrentBusyWaitingObjectLongMap<>(
                    this.buckets,
                    this.initialCapacity,
                    this.loadFactor,
                    this.defaultValue
            );
            case BLOCKING -> new ConcurrentObjectLongMap<>(
                    this.buckets,
                    this.initialCapacity,
                    this.loadFactor,
                    this.defaultValue
            );
        };
    }
}
