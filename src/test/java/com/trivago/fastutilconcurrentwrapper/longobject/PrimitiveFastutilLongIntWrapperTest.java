package com.trivago.fastutilconcurrentwrapper.longobject;

import com.trivago.fastutilconcurrentwrapper.primitivekeys.LongObjectMap;
import com.trivago.fastutilconcurrentwrapper.primitivekeys.wrapper.PrimitiveFastutilLongObjectWrapper;

public class PrimitiveFastutilLongIntWrapperTest extends AbstractLongObjectMapTest {

  @Override
  LongObjectMap<ObjectKey> createMap() {
    return new PrimitiveFastutilLongObjectWrapper<>(5, 0.9F, defaultValue);
  }
}
