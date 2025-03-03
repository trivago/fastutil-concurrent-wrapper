package com.trivago.fastutilconcurrentwrapper.objectkeys;

import com.trivago.fastutilconcurrentwrapper.KeyMap;

public interface ObjectKeyMap<K> extends KeyMap {
    boolean containsKey(K key);
}
