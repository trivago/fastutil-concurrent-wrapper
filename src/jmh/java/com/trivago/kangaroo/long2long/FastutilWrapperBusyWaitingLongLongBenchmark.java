package com.trivago.kangaroo.long2long;

import com.trivago.fastutilconcurrentwrapper.ConcurrentMapBuilder;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

@State(Scope.Benchmark)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 3, time = 2)
public class FastutilWrapperBusyWaitingLongLongBenchmark extends AbstractLongLongBenchHelper {

    @Setup(Level.Trial)
    public void loadData() {
        super.initAndLoadData(ConcurrentMapBuilder.MapMode.BUSY_WAITING);
    }
}
