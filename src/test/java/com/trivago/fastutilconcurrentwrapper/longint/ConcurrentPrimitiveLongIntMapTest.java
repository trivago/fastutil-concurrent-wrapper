package com.trivago.fastutilconcurrentwrapper.longint;

import com.trivago.fastutilconcurrentwrapper.primitivekeys.LongIntMap;
import com.trivago.fastutilconcurrentwrapper.primitivekeys.map.ConcurrentLongIntMap;


public class ConcurrentPrimitiveLongIntMapTest extends AbstractLongIntMapTest {
    @Override
    LongIntMap createMap() {
        return new ConcurrentLongIntMap(16, 16, 0.9F, defaultValue);
    }

}
