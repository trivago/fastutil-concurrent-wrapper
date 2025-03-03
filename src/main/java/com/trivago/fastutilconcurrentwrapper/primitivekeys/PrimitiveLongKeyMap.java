package com.trivago.fastutilconcurrentwrapper.primitivekeys;

import com.trivago.fastutilconcurrentwrapper.KeyMap;

public interface PrimitiveLongKeyMap extends KeyMap {
    boolean containsKey(long key);
}
