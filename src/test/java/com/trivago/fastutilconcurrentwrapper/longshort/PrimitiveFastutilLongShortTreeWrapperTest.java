package com.trivago.fastutilconcurrentwrapper.longshort;
import com.trivago.fastutilconcurrentwrapper.LongShortTreeMap;
import com.trivago.fastutilconcurrentwrapper.wrapper.PrimitiveFastutilLongShortTreeWrapper;
public class PrimitiveFastutilLongShortTreeWrapperTest extends AbstractLongShortTreeMapTest {
    @Override
    LongShortTreeMap createMap() {
        return new PrimitiveFastutilLongShortTreeWrapper(defaultValue);
    }
}
