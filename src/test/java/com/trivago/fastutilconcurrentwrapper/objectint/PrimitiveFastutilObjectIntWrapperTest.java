package com.trivago.fastutilconcurrentwrapper.objectint;

import com.trivago.fastutilconcurrentwrapper.objectkeys.ObjectIntMap;
import com.trivago.fastutilconcurrentwrapper.objectkeys.wrapper.PrimitiveFastutilObjectIntWrapper;

public class PrimitiveFastutilObjectIntWrapperTest extends AbstractObjectIntMapTest {

  @Override
  ObjectIntMap<ObjectKey> createMap() {
    return new PrimitiveFastutilObjectIntWrapper<>(5, 0.9F, defaultValue);
  }
}
