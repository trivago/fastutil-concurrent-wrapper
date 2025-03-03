package com.trivago.kangaroo.object2long;

import com.trivago.fastutilconcurrentwrapper.ConcurrentMapBuilder;
import org.openjdk.jmh.annotations.*;

@State(Scope.Benchmark)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 3, time = 2)
public class FastutilWrapperBusyWaitingObjectLongBenchmark extends AbstractObjectLongBenchHelper {

    @Setup(Level.Trial)
    public void loadData() {
        super.initAndLoadData(ConcurrentMapBuilder.MapMode.BUSY_WAITING);
    }
}
