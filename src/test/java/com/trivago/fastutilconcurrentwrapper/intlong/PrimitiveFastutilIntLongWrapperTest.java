package com.trivago.fastutilconcurrentwrapper.intlong;

import com.trivago.fastutilconcurrentwrapper.IntLongMap;
import com.trivago.fastutilconcurrentwrapper.wrapper.PrimitiveFastutilIntLongWrapper;

public class PrimitiveFastutilIntLongWrapperTest extends AbstractIntLongMapTest {

  @Override
  IntLongMap createMap() {
    return new PrimitiveFastutilIntLongWrapper(5, 0.9F, defaultValue);
  }
}
