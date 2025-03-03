package com.trivago.fastutilconcurrentwrapper.objectint;

import com.trivago.fastutilconcurrentwrapper.objectkeys.ObjectIntMap;
import com.trivago.fastutilconcurrentwrapper.objectkeys.map.ConcurrentBusyWaitingObjectIntMap;

public class ConcurrentBusyWaitingObjectIntMapTest extends AbstractObjectIntMapTest {

  @Override
  ObjectIntMap<ObjectKey> createMap() {
    return new ConcurrentBusyWaitingObjectIntMap<>(16, 16, 0.9F, defaultValue);
  }
}
