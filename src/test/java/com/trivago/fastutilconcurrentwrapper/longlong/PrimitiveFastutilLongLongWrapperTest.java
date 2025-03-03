package com.trivago.fastutilconcurrentwrapper.longlong;

import com.trivago.fastutilconcurrentwrapper.primitivekeys.LongLongMap;
import com.trivago.fastutilconcurrentwrapper.primitivekeys.wrapper.PrimitiveFastutilLongLongWrapper;

public class PrimitiveFastutilLongLongWrapperTest extends AbstractLongLongMapTest {

  @Override
  LongLongMap createMap() {
    return new PrimitiveFastutilLongLongWrapper(5, 0.9F, defaultValue);
  }
}
