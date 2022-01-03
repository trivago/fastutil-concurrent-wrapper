# FastUtil Concurrent Wrapper

## Description

Set of concurrent wrapper around [fastutil primitive maps](https://github.com/vigna/fastutil).

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

```java
ConcurrentLongLongMapBuilder b = ConcurrentLongLongMapBuilder.newBuilder()
        .withBuckets(2)
        .withDefaultValue(0)
        .withInitialCapacity(100)
        .withMode(ConcurrentLongLongMapBuilder.MapMode.BUSY_WAITING)
        .withLoadFactor(0.9f);

LongLongMap map = b.build();

map.put(1L,10L);
long v = map.get(1L);

```

### MapMode

Currently, for most of the maps we offer two locking modes:

- blocking (default),
- busy-waiting.

## Roadmap

- [ ] tests
- [ ] CI
- [ ] general builder
- [ ] wrappers for every map
