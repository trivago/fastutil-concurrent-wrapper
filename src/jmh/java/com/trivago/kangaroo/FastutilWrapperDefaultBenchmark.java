package com.trivago.kangaroo;

import com.trivago.fastutilconcurrentwrapper.ConcurrentLongLongMapBuilder;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

@State(Scope.Benchmark)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 3, time = 2)
public class FastutilWrapperDefaultBenchmark extends AbstractBenchHelper {

    @Setup(Level.Trial)
    public void loadData() {
        super.initAndLoadData(ConcurrentLongLongMapBuilder.MapMode.BLOCKING);
    }
}
