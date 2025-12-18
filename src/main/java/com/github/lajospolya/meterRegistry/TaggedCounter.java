package com.github.lajospolya.meterRegistry;

import io.micrometer.core.instrument.Counter;

public interface TaggedCounter {

    /**
     * Increment a {@link Counter} with one {@link ArbitraryState} tag.
     */
    void increment(ArbitraryState state);
}
