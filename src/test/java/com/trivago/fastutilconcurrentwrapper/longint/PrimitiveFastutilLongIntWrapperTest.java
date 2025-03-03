package com.trivago.fastutilconcurrentwrapper.longint;

import com.trivago.fastutilconcurrentwrapper.primitivekeys.LongIntMap;
import com.trivago.fastutilconcurrentwrapper.primitivekeys.wrapper.PrimitiveFastutilLongIntWrapper;

public class PrimitiveFastutilLongIntWrapperTest extends AbstractLongIntMapTest {

  @Override
  LongIntMap createMap() {
    return new PrimitiveFastutilLongIntWrapper(5, 0.9F, defaultValue);
  }
}
