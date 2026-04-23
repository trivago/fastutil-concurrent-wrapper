package com.trivago.fastutilconcurrentwrapper.wrapper;
import com.trivago.fastutilconcurrentwrapper.LongShortTreeMap;
import com.trivago.fastutilconcurrentwrapper.impl.longs.FloorableLongShortAVLTreeMap;
import it.unimi.dsi.fastutil.longs.Long2ShortFunction;
import it.unimi.dsi.fastutil.longs.Long2ShortMap;
import java.util.function.BiFunction;
public class PrimitiveFastutilLongShortTreeWrapper implements LongShortTreeMap {
    private final FloorableLongShortAVLTreeMap map;
    private final short defaultValue;
    public PrimitiveFastutilLongShortTreeWrapper(short defaultValue) {
        this.defaultValue = defaultValue;
        this.map = new FloorableLongShortAVLTreeMap();
        this.map.defaultReturnValue(defaultValue);
    }
    @Override
    public Long2ShortMap.Entry floorEntry(long key) {
        return map.floorEntry(key);
    }
    @Override
    public short get(long key) {
        return map.getOrDefault(key, defaultValue);
    }
    @Override
    public short put(long key, short value) {
        return map.put(key, value);
    }
    @Override
    public short getDefaultValue() {
        return defaultValue;
    }
    @Override
    public short remove(long key) {
        return map.remove(key);
    }
    @Override
    public boolean remove(long key, short value) {
        return map.remove(key, value);
    }
    @Override
    public int size() {
        return map.size();
    }
    @Override
    public boolean containsKey(long key) {
        return map.containsKey(key);
    }
    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }
    @Override
    public short computeIfAbsent(long key, Long2ShortFunction mappingFunction) {
        return map.computeIfAbsent(key, mappingFunction);
    }
    @Override
    public short computeIfPresent(long key, BiFunction<Long, Short, Short> mappingFunction) {
        return map.computeIfPresent(key, mappingFunction);
    }
}
