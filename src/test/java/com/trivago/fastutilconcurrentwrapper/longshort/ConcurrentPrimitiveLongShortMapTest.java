package com.trivago.fastutilconcurrentwrapper.longshort;

import com.trivago.fastutilconcurrentwrapper.LongShortMap;
import com.trivago.fastutilconcurrentwrapper.map.ConcurrentLongShortMap;

public class ConcurrentPrimitiveLongShortMapTest extends AbstractLongShortMapTest {
    @Override
    LongShortMap createMap() {
        return new ConcurrentLongShortMap(16, 16, 0.9F, defaultValue);
    }
}

