package com.github.lajospolya.meterRegistry;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides an implementation {@link TaggedCounter} which instantiates all possible {@link Counter}s during class
 * construction and caches them in a {@link HashMap}. These counters are then fetched and incremented in
 * {@link #increment(ArbitraryState)}
 */
public class CachedHashMapTaggedCounter implements TaggedCounter {

    private final Map<ArbitraryState, Counter> hashMapCounters;

    public CachedHashMapTaggedCounter() {
        hashMapCounters = getCountersHashMap(new SimpleMeterRegistry());
    }

    private Map<ArbitraryState, Counter> getCountersHashMap(MeterRegistry meterRegistry) {
        Map<ArbitraryState, Counter> tempCounters = new HashMap<>(ArbitraryState.values().length);
        for (ArbitraryState state : ArbitraryState.values()) {
            tempCounters.put(state, meterRegistry.counter("counter", "state", state.name()));
        }
        return tempCounters;
    }

    /**
     * Increment a {@link Counter} with one Enum tag. Instead of creating the counter on the spot, fetch a reference to
     * an existing counter from a {@link java.util.HashMap}. It is important to distinguish an {@link java.util.EnumMap}
     * from a {@link HashMap} because the former stores values in an array removing the need to hash keys, resulting
     * in much faster retrieval.
     */
    @Override
    public void increment(ArbitraryState state) {
        hashMapCounters.get(state).increment();
    }
}
