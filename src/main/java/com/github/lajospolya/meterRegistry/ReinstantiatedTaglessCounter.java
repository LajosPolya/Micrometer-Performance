package com.github.lajospolya.meterRegistry;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

/**
 * Provides an implementation {@link TaglessCounter} which instantiates the {@link Counter} every time
 * {@link #increment()} is called.
 */
public class ReinstantiatedTaglessCounter implements TaglessCounter {

    private final MeterRegistry meterRegistry;

    public ReinstantiatedTaglessCounter() {
        meterRegistry = new SimpleMeterRegistry();
    }

    /**
     * Create a {@link Counter} and increment it.
     */
    @Override
    public void increment() {
        meterRegistry.counter("counter").increment();
    }
}
