# FastUtil Concurrent Wrapper

![Java CI](https://github.com/trivago/fastutil-concurrent-wrapper/actions/workflows/gradle.yml/badge.svg)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.trivago/fastutil-concurrent-wrapper/badge.svg?style=plastic)](https://maven-badges.herokuapp.com/maven-central/com.trivago/fastutil-concurrent-wrapper/)

## Description

Set of concurrent wrappers around [fastutil primitive maps](https://github.com/vigna/fastutil).

[How we use it at trivago.](https://tech.trivago.com/post/2022-03-09-why-and-how-we-use-primitive-maps)

Main purpose is to provide useful concurrent builders around 
fastutil primitive maps with flexible locking policy.

Advantages over [java.util wrappers](https://docs.oracle.com/javase/tutorial/collections/implementations/wrapper.html):

- builders provide maps with buckets,
- every map uses `striped ReadWriteLocks` instead of `synchronized(mutex)`; one RW-lock per map's bucket, 
- two lock modes: `standard` and `busy-waiting` (could be good for low-latency systems),
- no extra memory on stack -- API based on `primitive types`.

Check [usage](#usage) section for more details.

_Note_: currently the lib contains wrappers not for every primitive map. Feel free to contribute.

## Install

#### Maven

```xml

<dependency>
    <groupId>com.trivago</groupId>
    <artifactId>fastutil-concurrent-wrapper</artifactId>
    <version>0.2.2</version>
</dependency>
```

#### Gradle

```groovy
implementation group: 'com.trivago', name: 'fastutil-concurrent-wrapper', version: '0.2.2'
```

## Usage

### Builder options
- `number of buckets` -- number of buckets in the map (default `8`),
- `default value` -- default value, for _getOrDefault()_ method
- `initial capacity` -- initial map capacity (default `100_000`),
- `concurrent mode` -- lock mode: _default_ and _busy-waiting_,
- `load factor` -- map load factor (default `0.8f`).

### Basic usage

```java
ConcurrentLongLongMapBuilder b = ConcurrentLongLongMapBuilder.newBuilder()
        .withBuckets(2)
        .withDefaultValue(0)
        .withInitialCapacity(100)
        .withMode(ConcurrentLongLongMapBuilder.MapMode.BUSY_WAITING)
        .withLoadFactor(0.9f);

LongLongMap map = b.build();

map.put(1L, 10L);
long v = map.get(1L);

```

Examples of creation and usage could be found inside 
[test directory](https://github.com/trivago/fastutil-concurrent-wrapper/tree/master/src/test/java/com/trivago/fastutilconcurrentwrapper);

### MapMode

Currently, we offer two locking modes:

- `blocking` (default),
- `busy-waiting`.

### JMH tests

For running JMH tests just execute:
```bash
./gradlew jmh
```

Results for `FastutilWrapper BusyWaiting mode` vs `FastutilWrapper Default mode` vs [java.util wrappers](https://docs.oracle.com/javase/tutorial/collections/implementations/wrapper.html) vs [java.util.concurrent](https://docs.oracle.com/javase/tutorial/collections/implementations/map.html)

#### LongLongMap

Throughput (more is better)

```shell
Benchmark                                                                Mode  Cnt          Score         Error  Units

FastutilWrapperBusyWaitingLongLongBenchmark.testRandomAllOpsThroughput  thrpt   15   14890349.957 ± 2041249.822  ops/s
FastutilWrapperBusyWaitingLongLongBenchmark.testRandomGetThroughput     thrpt   15   25449527.963 ± 2600729.022  ops/s
FastutilWrapperBusyWaitingLongLongBenchmark.testRandomPutThroughput     thrpt   14   11351650.286 ± 3324513.684  ops/s

FastutilWrapperDefaultLongLongBenchmark.testRandomAllOpsThroughput      thrpt   15    9241548.296 ± 1501812.910  ops/s
FastutilWrapperDefaultLongLongBenchmark.testRandomGetThroughput         thrpt   15   23205312.991 ± 2243533.089  ops/s
FastutilWrapperDefaultLongLongBenchmark.testRandomPutThroughput         thrpt   15    8705378.516 ± 2193254.025  ops/s

JavaUtilWrapperLongLongBenchmark.testRandomAllOpsThroughput             thrpt   15    4807759.211 ±  235212.245  ops/s
JavaUtilWrapperLongLongBenchmark.testRandomGetThroughput                thrpt   15   10518803.436 ±  343489.210  ops/s
JavaUtilWrapperLongLongBenchmark.testRandomPutThroughput                thrpt   15    3893033.361 ± 1091839.389  ops/s

JavaConcurrentLongLongBenchmark.testRandomAllOpsThroughput              thrpt   15    8338702.617 ± 2339627.650  ops/s
JavaConcurrentLongLongBenchmark.testRandomGetThroughput                 thrpt   15  115734084.910 ± 1021773.718  ops/s
JavaConcurrentLongLongBenchmark.testRandomPutThroughput                 thrpt   15    2120419.422 ± 1616120.572  ops/s
```
AverageTime per ops (less is better)

```shell
Benchmark                                                                Mode  Cnt          Score         Error  Units

FastutilWrapperBusyWaitingLongLongBenchmark.testRandomAllOpsAvgTime      avgt   15        271.732 ±      23.990  ns/op
FastutilWrapperBusyWaitingLongLongBenchmark.testRandomGetAvgTime         avgt   15        152.339 ±      20.281  ns/op
FastutilWrapperBusyWaitingLongLongBenchmark.testRandomPutAvgTime         avgt   15        376.696 ±     104.558  ns/op

FastutilWrapperDefaultLongLongBenchmark.testRandomAllOpsAvgTime          avgt   15        450.080 ±      74.515  ns/op
FastutilWrapperDefaultLongLongBenchmark.testRandomGetAvgTime             avgt   15        158.247 ±      12.916  ns/op
FastutilWrapperDefaultLongLongBenchmark.testRandomPutAvgTime             avgt   15        480.561 ±     142.326  ns/op

JavaUtilWrapperLongLongBenchmark.testRandomAllOpsAvgTime                 avgt   15        848.636 ±      37.767  ns/op
JavaUtilWrapperLongLongBenchmark.testRandomGetAvgTime                    avgt   15        380.703 ±      18.391  ns/op
JavaUtilWrapperLongLongBenchmark.testRandomPutAvgTime                    avgt   15       1083.204 ±     323.376  ns/op

JavaConcurrentLongLongBenchmark.testRandomAllOpsAvgTime                  avgt   15        511.132 ±     166.567  ns/op
JavaConcurrentLongLongBenchmark.testRandomGetAvgTime                     avgt   15         34.583 ±       0.301  ns/op
JavaConcurrentLongLongBenchmark.testRandomPutAvgTime                     avgt   15      16723.754 ±   24368.274  ns/op
```

#### ObjectLongMap

Throughput (more is better)

```shell
Benchmark                                                                 Mode  Cnt          Score         Error  Units

FastutilWrapperBusyWaitingObjectLongBenchmark.testRandomAllOpsThroughput thrpt   15   12560651.390 ± 2610919.005  ops/s
FastutilWrapperBusyWaitingObjectLongBenchmark.testRandomGetThroughput    thrpt   15   26366394.724 ± 2433787.656  ops/s
FastutilWrapperBusyWaitingObjectLongBenchmark.testRandomPutThroughput    thrpt   15    5633586.398 ± 2592634.909  ops/s

FastutilWrapperDefaultObjectLongBenchmark.testRandomAllOpsThroughput     thrpt   15    8107579.700 ± 1356806.057  ops/s
FastutilWrapperDefaultObjectLongBenchmark.testRandomGetThroughput        thrpt   15   22899340.190 ± 2745129.956  ops/s
FastutilWrapperDefaultObjectLongBenchmark.testRandomPutThroughput        thrpt   15    5115575.519 ± 1510259.367  ops/s

JavaUtilWrapperObjectLongBenchmark.testRandomAllOpsThroughput            thrpt   15    5146475.879 ±  876528.313  ops/s
JavaUtilWrapperObjectLongBenchmark.testRandomGetThroughput               thrpt   15   15354340.310 ±  413172.576  ops/s
JavaUtilWrapperObjectLongBenchmark.testRandomPutThroughput               thrpt   15    3349970.826 ±  466818.411  ops/s

JavaConcurrentObjectLongBenchmark.testRandomAllOpsThroughput             thrpt   15    9802597.556 ±  845121.891  ops/s
JavaConcurrentObjectLongBenchmark.testRandomGetThroughput                thrpt   15  144324735.489 ± 3072330.160  ops/s
JavaConcurrentObjectLongBenchmark.testRandomPutThroughput                thrpt   15    2717410.407 ± 1995361.332  ops/s
```
AverageTime per ops (less is better)

```shell
Benchmark                                                                 Mode  Cnt          Score         Error  Units

FastutilWrapperBusyWaitingObjectLongBenchmark.testRandomAllOpsAvgTime     avgt   15        339.809 ±      80.612  ns/op
FastutilWrapperBusyWaitingObjectLongBenchmark.testRandomGetAvgTime        avgt   15        193.411 ±      26.775  ns/op
FastutilWrapperBusyWaitingObjectLongBenchmark.testRandomPutAvgTime        avgt   15        951.206 ±     617.310  ns/op

FastutilWrapperDefaultObjectLongBenchmark.testRandomAllOpsAvgTime         avgt   15        496.373 ±      70.106  ns/op
FastutilWrapperDefaultObjectLongBenchmark.testRandomGetAvgTime            avgt   15        179.065 ±      18.389  ns/op
FastutilWrapperDefaultObjectLongBenchmark.testRandomPutAvgTime            avgt   15        831.579 ±     268.410  ns/op

JavaUtilWrapperObjectLongBenchmark.testRandomAllOpsAvgTime                avgt   15        785.415 ±     153.079  ns/op
JavaUtilWrapperObjectLongBenchmark.testRandomGetAvgTime                   avgt   15        251.149 ±      10.494  ns/op
JavaUtilWrapperObjectLongBenchmark.testRandomPutAvgTime                   avgt   15       1211.072 ±     152.786  ns/op

JavaConcurrentObjectLongBenchmark.testRandomAllOpsAvgTime                 avgt   15        418.897 ±      35.770  ns/op
JavaConcurrentObjectLongBenchmark.testRandomGetAvgTime                    avgt   15         27.664 ±       0.125  ns/op
JavaConcurrentObjectLongBenchmark.testRandomPutAvgTime                    avgt   15       7039.788 ±   10679.090  ns/op
```

#### Info

The machine
```shell
OS Kubuntu 24.10
Processor 3,4 GHz 16-Core AMD Ryzen 9 5950X
Memory 64 GB 3600 MHz DDR4
```

## Maintainers
A-Z surname order

- [@mchernyakov](https://github.com/mchernyakov)
- [@erdoganf](https://github.com/erdoganf)
- [@sarveswaran-m](https://github.com/sarveswaran-m)
