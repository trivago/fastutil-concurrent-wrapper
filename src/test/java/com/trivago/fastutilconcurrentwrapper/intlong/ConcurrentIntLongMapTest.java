package com.trivago.fastutilconcurrentwrapper.intlong;

import com.trivago.fastutilconcurrentwrapper.IntLongMap;
import com.trivago.fastutilconcurrentwrapper.map.ConcurrentIntLongMap;

public class ConcurrentIntLongMapTest extends AbstractIntLongMapTest {

  @Override
  IntLongMap createMap() {
    return new ConcurrentIntLongMap(16, 16, 0.9F, defaultValue);
  }
}
