package com.trivago.fastutilconcurrentwrapper.longshort;

import com.trivago.fastutilconcurrentwrapper.LongShortMap;
import com.trivago.fastutilconcurrentwrapper.wrapper.PrimitiveFastutilLongShortWrapper;

public class PrimitiveFastutilLongShortWrapperTest extends AbstractLongShortMapTest {

  @Override
  LongShortMap createMap() {
    return new PrimitiveFastutilLongShortWrapper(5, 0.9F, defaultValue);
  }
}

