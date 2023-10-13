package com.trivago.fastutilconcurrentwrapper.longint;

import com.trivago.fastutilconcurrentwrapper.LongIntMap;
import com.trivago.fastutilconcurrentwrapper.wrapper.PrimitiveFastutilLongIntWrapper;

public class PrimitiveFastutilLongIntWrapperTest extends AbstractLongIntMapTest {

  @Override
  LongIntMap createMap() {
    return new PrimitiveFastutilLongIntWrapper(5, 0.9F, defaultValue);
  }
}
