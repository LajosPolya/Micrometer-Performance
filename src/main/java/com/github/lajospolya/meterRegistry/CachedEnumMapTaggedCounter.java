package com.github.lajospolya.meterRegistry;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

import java.util.EnumMap;
import java.util.Map;

/**
 * Provides an implementation {@link TaggedCounter} which instantiates all possible {@link Counter}s during class
 * construction and caches them in an {@link EnumMap}. These counters are then fetched and incremented in
 * {@link #increment(ArbitraryState)}
 */
public class CachedEnumMapTaggedCounter implements TaggedCounter {

    private final Map<ArbitraryState, Counter> enumMapCounters;

    public CachedEnumMapTaggedCounter() {
        enumMapCounters = getCountersEnumMap(new SimpleMeterRegistry());
    }

    private Map<ArbitraryState, Counter> getCountersEnumMap(MeterRegistry meterRegistry) {
        Map<ArbitraryState, Counter> tempCounters = new EnumMap<>(ArbitraryState.class);
        for (ArbitraryState state : ArbitraryState.values()) {
            tempCounters.put(state, meterRegistry.counter("counter", "state", state.name()));
        }
        return tempCounters;
    }

    /**
     * Increment a {@link Counter} with one Enum tag. Instead of creating the counter on the spot, fetch a reference to
     * an existing counter from an {@link EnumMap}. It is important to distinguish an {@link EnumMap} from a
     * {@link java.util.HashMap} because the former stores values in an array removing the need to hash keys, resulting
     * in much faster retrieval.
     */
    @Override
    public void increment(ArbitraryState state) {
        enumMapCounters.get(state).increment();
    }
}
