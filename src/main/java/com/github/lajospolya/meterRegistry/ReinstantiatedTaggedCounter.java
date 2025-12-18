package com.github.lajospolya.meterRegistry;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

/**
 * Provides an implementation {@link TaglessCounter} which instantiates the {@link Counter} every time
 * {@link #increment(ArbitraryState)} is called.
 */
public class ReinstantiatedTaggedCounter implements TaggedCounter {

    private final MeterRegistry meterRegistry;

    public ReinstantiatedTaggedCounter() {
        meterRegistry = new SimpleMeterRegistry();
    }

    /**
     * Create and increment a counter with the specified state.
     */
    @Override
    public void increment(ArbitraryState state) {
        meterRegistry.counter("counter", "state", state.name()).increment();
    }
}
