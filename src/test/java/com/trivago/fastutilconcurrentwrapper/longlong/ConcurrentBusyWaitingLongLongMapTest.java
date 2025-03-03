package com.trivago.fastutilconcurrentwrapper.longlong;

import com.trivago.fastutilconcurrentwrapper.primitivekeys.LongLongMap;
import com.trivago.fastutilconcurrentwrapper.primitivekeys.map.ConcurrentBusyWaitingLongLongMap;

public class ConcurrentBusyWaitingLongLongMapTest extends AbstractLongLongMapTest {

  @Override
  LongLongMap createMap() {
    return new ConcurrentBusyWaitingLongLongMap(16, 16, 0.9F, defaultValue);
  }
}
