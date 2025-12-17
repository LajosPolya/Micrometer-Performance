package com.github.lajospolya.meterRegistry;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

import java.util.EnumMap;
import java.util.Map;

public class SimpleCounter {

    private final MeterRegistry meterRegistry;
    private final Counter counter;
    private final Map<EnumState, Counter> counters;

    public SimpleCounter() {
        meterRegistry = new SimpleMeterRegistry();
        counter = meterRegistry.counter("counter");
        counters = getCounters();
    }

    private Map<EnumState, Counter> getCounters() {
        Map<EnumState, Counter> tempCounters = new EnumMap<>(EnumState.class);
        for(EnumState state : EnumState.values()){
            tempCounters.put(state, counter);
        }
        return tempCounters;
    }

    public void createAndIncrement() {
        meterRegistry.counter("counter").increment();
    }

    public void increment() {
        counter.increment();
    }

    public void createAndIncrement(EnumState state) {
        meterRegistry.counter("counter", "state", state.name()).increment();
    }

    public void increment(EnumState state) {
        counters.get(state).increment();
    }

    public enum EnumState {
        _1,
        _2,
        _3,
        _4,
        _5,
        _6,
        _7,
        _8,
        _9,
        _10,
        _11,
        _12,
        _13,
        _14,
        _15,
        _16,
        _17,
        _18,
        _19,
        _20,
        _21,
        _22,
        _23,
        _24,
        _25,
        _26,
        _27,
        _28,
        _29,
        _30,
        _31
    }
}
