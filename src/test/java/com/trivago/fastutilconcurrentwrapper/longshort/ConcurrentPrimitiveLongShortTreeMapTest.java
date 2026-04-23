package com.trivago.fastutilconcurrentwrapper.longshort;

import com.trivago.fastutilconcurrentwrapper.LongShortTreeMap;
import com.trivago.fastutilconcurrentwrapper.map.ConcurrentLongShortTreeMap;

public class ConcurrentPrimitiveLongShortTreeMapTest extends AbstractLongShortTreeMapTest {
    @Override
    LongShortTreeMap createMap() {
        return new ConcurrentLongShortTreeMap(1, defaultValue);
    }
}
