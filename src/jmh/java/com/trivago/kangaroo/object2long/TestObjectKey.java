package com.trivago.kangaroo.object2long;

import java.util.concurrent.ThreadLocalRandom;

public class TestObjectKey {

    private final int id = ThreadLocalRandom.current().nextInt();

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
