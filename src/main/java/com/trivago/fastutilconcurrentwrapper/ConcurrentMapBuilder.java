package com.trivago.fastutilconcurrentwrapper;

@SuppressWarnings("unchecked")
public abstract class ConcurrentMapBuilder<B extends ConcurrentMapBuilder<B, K>, K extends KeyMap> {

    protected MapMode mapMode;
    protected int buckets = 8;
    protected int initialCapacity = 100_000;
    protected float loadFactor = 0.8f;

    protected ConcurrentMapBuilder() {
    }

    public B withBuckets(int buckets) {
        this.buckets = buckets;
        return (B) this;
    }

    public B withInitialCapacity(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        return (B) this;
    }

    public B withLoadFactor(float loadFactor) {
        this.loadFactor = loadFactor;
        return (B) this;
    }

    public B withMode(MapMode mapMode) {
        this.mapMode = mapMode;
        return (B) this;
    }

    public abstract K build();

    public enum MapMode {
        BUSY_WAITING,
        BLOCKING
    }
}
