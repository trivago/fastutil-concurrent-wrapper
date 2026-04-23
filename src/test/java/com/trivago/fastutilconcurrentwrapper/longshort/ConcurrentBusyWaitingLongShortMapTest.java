package com.trivago.fastutilconcurrentwrapper.longshort;

import com.trivago.fastutilconcurrentwrapper.LongShortMap;
import com.trivago.fastutilconcurrentwrapper.map.ConcurrentBusyWaitingLongShortMap;

public class ConcurrentBusyWaitingLongShortMapTest extends AbstractLongShortMapTest {

  @Override
  LongShortMap createMap() {
    return new ConcurrentBusyWaitingLongShortMap(16, 16, 0.9F, defaultValue);
  }
}

