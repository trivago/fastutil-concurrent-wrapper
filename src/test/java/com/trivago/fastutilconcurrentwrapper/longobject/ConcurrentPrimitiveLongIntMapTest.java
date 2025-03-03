package com.trivago.fastutilconcurrentwrapper.longobject;

import com.trivago.fastutilconcurrentwrapper.primitivekeys.LongObjectMap;
import com.trivago.fastutilconcurrentwrapper.primitivekeys.map.ConcurrentLongObjectMap;

public class ConcurrentPrimitiveLongIntMapTest extends AbstractLongObjectMapTest {
    @Override
    LongObjectMap<ObjectKey> createMap() {
        return new ConcurrentLongObjectMap<>(16, 16, 0.9F, defaultValue);
    }

}
