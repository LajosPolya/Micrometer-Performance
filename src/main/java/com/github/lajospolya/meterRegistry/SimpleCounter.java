package com.github.lajospolya.meterRegistry;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

public class SimpleCounter {

    private final MeterRegistry meterRegistry;
    private final Counter counter;

    public SimpleCounter() {
        meterRegistry = new SimpleMeterRegistry();
        counter = meterRegistry.counter("counter");
    }

    public void createAndIncrement() {
        meterRegistry.counter("counter").increment();
    }

    public void increment() {
        counter.increment();
    }
}
