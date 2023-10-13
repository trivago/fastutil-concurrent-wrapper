package com.trivago.fastutilconcurrentwrapper.longint;

import com.trivago.fastutilconcurrentwrapper.LongIntMap;
import com.trivago.fastutilconcurrentwrapper.longint.AbstractLongIntMapTest;
import com.trivago.fastutilconcurrentwrapper.map.ConcurrentLongIntMap;


public class ConcurrentPrimitiveLongIntMapTest extends AbstractLongIntMapTest {
    @Override
    LongIntMap createMap() {
        return new ConcurrentLongIntMap(16, 16, 0.9F, defaultValue);
    }

}
