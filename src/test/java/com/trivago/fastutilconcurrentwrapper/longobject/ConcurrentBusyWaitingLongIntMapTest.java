package com.trivago.fastutilconcurrentwrapper.longobject;

import com.trivago.fastutilconcurrentwrapper.primitivekeys.LongObjectMap;
import com.trivago.fastutilconcurrentwrapper.primitivekeys.map.ConcurrentBusyWaitingLongObjectMap;

public class ConcurrentBusyWaitingLongIntMapTest extends AbstractLongObjectMapTest {

  @Override
  LongObjectMap<ObjectKey> createMap() {
    return new ConcurrentBusyWaitingLongObjectMap<>(16, 16, 0.9F, defaultValue);
  }
}
