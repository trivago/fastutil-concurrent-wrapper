# FastUtil Concurrent Wrapper

## Description

Set of concurrent wrappers around [fastutil primitive maps](https://github.com/vigna/fastutil).

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
    <version>0.0.x</version>
</dependency>
```

#### Gradle

```groovy
implementation group: 'com.trivago', name: 'fastutil-concurrent-wrapper', version: '0.0.x'
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

## Maintainers
A-Z surname order

- [@mchernyakov](https://github.com/mchernyakov)
- [@erdoganf](https://github.com/erdoganf)
- [@sarveswaran-m](https://github.com/sarveswaran-m)

## Roadmap

- [ ] tests
- [ ] CI
- [ ] general builder
- [ ] wrappers for every map
