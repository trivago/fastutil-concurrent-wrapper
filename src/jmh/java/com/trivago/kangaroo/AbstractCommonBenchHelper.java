package com.trivago.kangaroo;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Threads;

import java.util.concurrent.TimeUnit;

abstract public class AbstractCommonBenchHelper {
    @Threads(4)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void testRandomGetThroughput() {
        testGet();
    }

    @Threads(4)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void testRandomPutThroughput() {
        testPut();
    }

    @Threads(4)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void testRandomAllOpsThroughput() {
        testAllOps();
    }

    @Threads(4)
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void testRandomGetAvgTime() {
        testGet();
    }

    @Threads(4)
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void testRandomPutAvgTime() {
        testPut();
    }

    @Threads(4)
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void testRandomAllOpsAvgTime() {
        testAllOps();
    }

    abstract public void testGet();

    abstract public void testPut();

    abstract public void testAllOps();
}
