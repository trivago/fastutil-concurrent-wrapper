package com.trivago.fastutilconcurrentwrapper.intlong;

import com.trivago.fastutilconcurrentwrapper.IntLongMap;
import com.trivago.fastutilconcurrentwrapper.map.ConcurrentBusyWaitingIntLongMap;

public class ConcurrentBusyWaitingIntLongMapTest extends AbstractIntLongMapTest {

  @Override
  IntLongMap createMap() {
    return new ConcurrentBusyWaitingIntLongMap(16, 16, 0.9F, defaultValue);
  }
}
