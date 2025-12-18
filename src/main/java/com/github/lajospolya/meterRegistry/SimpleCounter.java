package com.github.lajospolya.meterRegistry;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Contains methods used ot performance test various ways of calling increment on a {@link Counter}.
 */
public class SimpleCounter {

    private final MeterRegistry meterRegistry;
    private final Counter counter;
    private final Map<ArbitraryState, Counter> enumMapCounters;
    private final Map<ArbitraryState, Counter> hashMapCounters;

    public SimpleCounter() {
        meterRegistry = new SimpleMeterRegistry();
        counter = meterRegistry.counter("counter");
        enumMapCounters = getCountersEnumMap();
        hashMapCounters = getCountersHashMap();
    }

    private Map<ArbitraryState, Counter> getCountersEnumMap() {
        Map<ArbitraryState, Counter> tempCounters = new EnumMap<>(ArbitraryState.class);
        for(ArbitraryState state : ArbitraryState.values()){
            tempCounters.put(state, meterRegistry.counter("counter", "state", state.name()));
        }
        return tempCounters;
    }

    private Map<ArbitraryState, Counter> getCountersHashMap() {
        Map<ArbitraryState, Counter> tempCounters = new HashMap<>(ArbitraryState.values().length);
        for(ArbitraryState state : ArbitraryState.values()){
            tempCounters.put(state, meterRegistry.counter("counter", "state", state.name()));
        }
        return tempCounters;
    }

    /**
     * Create a {@link Counter} with zero tags and increment it.
     */
    public void createAndIncrement() {
        meterRegistry.counter("counter").increment();
    }

    /**
     * Increment a {@link Counter} with zero tags. Instead of creating the counter on the spot, use a reference to an
     * existing counter.
     */
    public void increment() {
        counter.increment();
    }

    /**
     * Create a {@link Counter} with one Enum tag and increment it.
     */
    public void createAndIncrement(ArbitraryState state) {
        meterRegistry.counter("counter", "state", state.name()).increment();
    }

    /**
     * Increment a {@link Counter} with one Enum tag. Instead of creating the counter on the spot, fetch a reference to
     * an existing counter from an {@link EnumMap}. It is important to distinguish an {@link EnumMap} from a
     * {@link java.util.HashMap} because the former stores values in an array removing the need to hash keys, resulting
     * in much faster retrieval.
     */
    public void incrementEnum(ArbitraryState state) {
        enumMapCounters.get(state).increment();
    }

    /**
     * Increment a {@link Counter} with one Enum tag. Instead of creating the counter on the spot, fetch a reference to
     * an existing counter from a {@link java.util.HashMap}. It is important to distinguish an {@link EnumMap} from a
     * {@link java.util.HashMap} because the former stores values in an array removing the need to hash keys, resulting
     * in much faster retrieval.
     */
    public void incrementHash(ArbitraryState state) {
        hashMapCounters.get(state).increment();
    }
}
