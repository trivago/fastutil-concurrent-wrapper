package com.trivago.fastutilconcurrentwrapper.longlong;

import com.trivago.fastutilconcurrentwrapper.LongLongMap;
import com.trivago.fastutilconcurrentwrapper.wrapper.PrimitiveFastutilLongLongWrapper;

public class PrimitiveFastutilLongLongWrapperTest extends AbstractLongLongMapTest {

  @Override
  LongLongMap createMap() {
    return new PrimitiveFastutilLongLongWrapper(5, 0.9F, defaultValue);
  }
}
