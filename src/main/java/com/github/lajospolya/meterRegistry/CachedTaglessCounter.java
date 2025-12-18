package com.github.lajospolya.meterRegistry;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

/**
 * Provides an implementation {@link TaglessCounter} which instantiates the {@link Counter} during class construction
 * and caches a reference to it in an instance variable. This counter is then fetched and incremented in
 * {@link #increment()}
 */
public class CachedTaglessCounter implements TaglessCounter {

    private final Counter counter;

    public CachedTaglessCounter() {
        MeterRegistry meterRegistry = new SimpleMeterRegistry();
        counter = meterRegistry.counter("counter");
    }

    /**
     * Increment the cached counter
     */
    @Override
    public void increment() {
        counter.increment();
    }
}
