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
    private final Map<EnumState, Counter> enumMapCounters;
    private final Map<EnumState, Counter> hashMapCounters;

    public SimpleCounter() {
        meterRegistry = new SimpleMeterRegistry();
        counter = meterRegistry.counter("counter");
        enumMapCounters = getCountersEnumMap();
        hashMapCounters = getCountersHashMap();
    }

    private Map<EnumState, Counter> getCountersEnumMap() {
        Map<EnumState, Counter> tempCounters = new EnumMap<>(EnumState.class);
        for(EnumState state : EnumState.values()){
            tempCounters.put(state, meterRegistry.counter("counter", "state", state.name()));
        }
        return tempCounters;
    }

    private Map<EnumState, Counter> getCountersHashMap() {
        Map<EnumState, Counter> tempCounters = new HashMap<>(EnumState.values().length);
        for(EnumState state : EnumState.values()){
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
    public void createAndIncrement(EnumState state) {
        meterRegistry.counter("counter", "state", state.name()).increment();
    }

    /**
     * Increment a {@link Counter} with one Enum tag. Instead of creating the counter on the spot, fetch a reference to
     * an existing counter from an {@link EnumMap}. It is important to distinguish an {@link EnumMap} from a
     * {@link java.util.HashMap} because the former stores values in an array removing the need to hash keys, resulting
     * in much faster retrieval.
     */
    public void incrementEnum(EnumState state) {
        enumMapCounters.get(state).increment();
    }

    /**
     * Increment a {@link Counter} with one Enum tag. Instead of creating the counter on the spot, fetch a reference to
     * an existing counter from a {@link java.util.HashMap}. It is important to distinguish an {@link EnumMap} from a
     * {@link java.util.HashMap} because the former stores values in an array removing the need to hash keys, resulting
     * in much faster retrieval.
     */
    public void incrementHash(EnumState state) {
        hashMapCounters.get(state).increment();
    }

    /**
     * An {@link Enum} representing a finite amount of arbitrary states.
     */
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
