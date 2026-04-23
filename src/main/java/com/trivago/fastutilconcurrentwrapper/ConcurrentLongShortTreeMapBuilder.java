package com.trivago.fastutilconcurrentwrapper;
import com.trivago.fastutilconcurrentwrapper.map.ConcurrentBusyWaitingLongShortTreeMap;
import com.trivago.fastutilconcurrentwrapper.map.ConcurrentLongShortTreeMap;
public final class ConcurrentLongShortTreeMapBuilder {
    private MapMode mapMode = MapMode.BLOCKING;
    private int buckets = 8;
    private short defaultValue = LongShortMap.DEFAULT_VALUE;
    private ConcurrentLongShortTreeMapBuilder() {
    }
    public static ConcurrentLongShortTreeMapBuilder newBuilder() {
        return new ConcurrentLongShortTreeMapBuilder();
    }
    public ConcurrentLongShortTreeMapBuilder withBuckets(int buckets) {
        this.buckets = buckets;
        return this;
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
                        builder.buckets,
                        builder.defaultValue);
            }
        },
        BLOCKING {
            @Override
            LongShortTreeMap createMap(ConcurrentLongShortTreeMapBuilder builder) {
                return new ConcurrentLongShortTreeMap(
                        builder.buckets,
                        builder.defaultValue);
            }
        };
        abstract LongShortTreeMap createMap(ConcurrentLongShortTreeMapBuilder builder);
    }
}
