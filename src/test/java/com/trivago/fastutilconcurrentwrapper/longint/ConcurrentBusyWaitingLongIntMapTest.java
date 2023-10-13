package com.trivago.fastutilconcurrentwrapper.longint;

import com.trivago.fastutilconcurrentwrapper.LongIntMap;
import com.trivago.fastutilconcurrentwrapper.map.ConcurrentBusyWaitingLongIntMap;

public class ConcurrentBusyWaitingLongIntMapTest extends AbstractLongIntMapTest {

  @Override
  LongIntMap createMap() {
    return new ConcurrentBusyWaitingLongIntMap(16, 16, 0.9F, defaultValue);
  }
}
