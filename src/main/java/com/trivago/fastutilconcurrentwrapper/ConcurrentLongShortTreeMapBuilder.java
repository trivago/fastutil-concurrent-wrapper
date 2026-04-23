package com.trivago.fastutilconcurrentwrapper;

import com.trivago.fastutilconcurrentwrapper.map.ConcurrentBusyWaitingLongShortTreeMap;
import com.trivago.fastutilconcurrentwrapper.map.ConcurrentLongShortTreeMap;

public final class ConcurrentLongShortTreeMapBuilder {
    private MapMode mapMode = MapMode.BLOCKING;
    // floorEntry() requires all keys to reside in a single bucket to return
    // correct results, so the bucket count is fixed at 1.
    // ToDo: Identify a bucketing methodology that works for TreeMap
    private static final int BUCKETS = 1;
    private short defaultValue = LongShortMap.DEFAULT_VALUE;

    private ConcurrentLongShortTreeMapBuilder() {
    }

    public static ConcurrentLongShortTreeMapBuilder newBuilder() {
        return new ConcurrentLongShortTreeMapBuilder();
    }


    public ConcurrentLongShortTreeMapBuilder withMode(MapMode mapMode) {
        this.mapMode = mapMode;
        return this;
    }

    public ConcurrentLongShortTreeMapBuilder withDefaultValue(short defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public LongShortTreeMap build() {
        return mapMode.createMap(this);
    }

    public enum MapMode {
        BUSY_WAITING {
            @Override
            LongShortTreeMap createMap(ConcurrentLongShortTreeMapBuilder builder) {
                return new ConcurrentBusyWaitingLongShortTreeMap(
                        BUCKETS,
                        builder.defaultValue);
            }
        },
        BLOCKING {
            @Override
            LongShortTreeMap createMap(ConcurrentLongShortTreeMapBuilder builder) {
                return new ConcurrentLongShortTreeMap(
                        BUCKETS,
                        builder.defaultValue);
            }
        };

        abstract LongShortTreeMap createMap(ConcurrentLongShortTreeMapBuilder builder);
    }
}
