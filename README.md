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
    <version>0.2.1</version>
</dependency>
```

#### Gradle

```groovy
implementation group: 'com.trivago', name: 'fastutil-concurrent-wrapper', version: '0.2.1'
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

Results for `FastutilWrapper BusyWaiting mode` vs `FastutilWrapper Default mode` vs [java.util wrappers](https://docs.oracle.com/javase/tutorial/collections/implementations/wrapper.html)

Throughput (more is better)

```shell
Benchmark                                                        Mode  Cnt         Score         Error  Units

FastutilWrapperBusyWaitingBenchmark.testRandomAllOpsThroughput  thrpt   15  14517457,055 ?  795637,784  ops/s
FastutilWrapperBusyWaitingBenchmark.testRandomGetThroughput     thrpt   15  16610181,320 ? 1456776,589  ops/s
FastutilWrapperBusyWaitingBenchmark.testRandomPutThroughput     thrpt   13  11706178,916 ? 2547333,524  ops/s

FastutilWrapperDefaultBenchmark.testRandomAllOpsThroughput      thrpt   15   7385357,514 ? 1127356,032  ops/s
FastutilWrapperDefaultBenchmark.testRandomGetThroughput         thrpt   15  16190621,923 ? 1836415,022  ops/s
FastutilWrapperDefaultBenchmark.testRandomPutThroughput         thrpt   15   8945369,395 ? 1225460,217  ops/s

JavaUtilWrapperBenchmark.testRandomAllOpsThroughput             thrpt   15   4921201,916 ?  410471,239  ops/s
JavaUtilWrapperBenchmark.testRandomGetThroughput                thrpt   15   7827123,690 ?  557193,670  ops/s
JavaUtilWrapperBenchmark.testRandomPutThroughput                thrpt   15   4832517,371 ? 1122344,647  ops/s
```
AverageTime per ops (less is better)

```shell
Benchmark                                                        Mode  Cnt         Score         Error  Units

FastutilWrapperBusyWaitingBenchmark.testRandomAllOpsAvgTime      avgt   15       268,790 ?      22,526  ns/op
FastutilWrapperBusyWaitingBenchmark.testRandomGetAvgTime         avgt   15       231,552 ?      16,116  ns/op
FastutilWrapperBusyWaitingBenchmark.testRandomPutAvgTime         avgt   10       292,246 ?      49,757  ns/op

FastutilWrapperDefaultBenchmark.testRandomAllOpsAvgTime          avgt   15       467,381 ?       9,790  ns/op
FastutilWrapperDefaultBenchmark.testRandomGetAvgTime             avgt   15       237,683 ?      14,167  ns/op
FastutilWrapperDefaultBenchmark.testRandomPutAvgTime             avgt   15       427,441 ?      25,116  ns/op

JavaUtilWrapperBenchmark.testRandomAllOpsAvgTime                 avgt   15       781,869 ?     191,081  ns/op
JavaUtilWrapperBenchmark.testRandomGetAvgTime                    avgt   15       470,869 ?      33,198  ns/op
JavaUtilWrapperBenchmark.testRandomPutAvgTime                    avgt   15       964,613 ?     422,648  ns/op
```

The machine
```shell
MacBook Pro (15-inch, 2019)
Processor 2,6 GHz 6-Core Intel Core i7
Memory 16 GB 2400 MHz DDR4
```

## Maintainers
A-Z surname order

- [@mchernyakov](https://github.com/mchernyakov)
- [@erdoganf](https://github.com/erdoganf)
- [@sarveswaran-m](https://github.com/sarveswaran-m)
