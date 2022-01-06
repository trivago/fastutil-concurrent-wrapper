package com.trivago.fastutilconcurrentwrapper.longlong;

import com.trivago.fastutilconcurrentwrapper.LongLongMap;
import com.trivago.fastutilconcurrentwrapper.map.ConcurrentBusyWaitingLongLongMap;

public class ConcurrentBusyWaitingLongLongMapTest extends AbstractLongLongMapTest {

  @Override
  LongLongMap createMap() {
    return new ConcurrentBusyWaitingLongLongMap(16, 16, 0.9F, defaultValue);
  }
}
