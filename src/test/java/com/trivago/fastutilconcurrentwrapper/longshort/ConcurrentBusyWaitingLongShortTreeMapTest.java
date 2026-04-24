package com.trivago.fastutilconcurrentwrapper.longshort;

import com.trivago.fastutilconcurrentwrapper.LongShortTreeMap;
import com.trivago.fastutilconcurrentwrapper.map.ConcurrentBusyWaitingLongShortTreeMap;

public class ConcurrentBusyWaitingLongShortTreeMapTest extends AbstractLongShortTreeMapTest {
    @Override
    LongShortTreeMap createMap() {
        return new ConcurrentBusyWaitingLongShortTreeMap(defaultValue);
    }
}
