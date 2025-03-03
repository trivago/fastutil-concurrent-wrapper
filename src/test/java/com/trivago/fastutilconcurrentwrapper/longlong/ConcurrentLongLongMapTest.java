package com.trivago.fastutilconcurrentwrapper.longlong;

import com.trivago.fastutilconcurrentwrapper.primitivekeys.LongLongMap;
import com.trivago.fastutilconcurrentwrapper.primitivekeys.map.ConcurrentLongLongMap;

public class ConcurrentLongLongMapTest extends AbstractLongLongMapTest {

  @Override
  LongLongMap createMap() {
    return new ConcurrentLongLongMap(16, 16, 0.9F, defaultValue);
  }
}
