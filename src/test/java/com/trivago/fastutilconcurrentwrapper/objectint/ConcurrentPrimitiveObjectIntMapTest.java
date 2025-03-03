package com.trivago.fastutilconcurrentwrapper.objectint;

import com.trivago.fastutilconcurrentwrapper.objectkeys.ObjectIntMap;
import com.trivago.fastutilconcurrentwrapper.objectkeys.map.ConcurrentObjectIntMap;

public class ConcurrentPrimitiveObjectIntMapTest extends AbstractObjectIntMapTest {
    @Override
    ObjectIntMap<ObjectKey> createMap() {
        return new ConcurrentObjectIntMap<>(16, 16, 0.9F, defaultValue);
    }

}
