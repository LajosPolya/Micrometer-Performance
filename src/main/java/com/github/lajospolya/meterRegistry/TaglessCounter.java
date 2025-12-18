package com.github.lajospolya.meterRegistry;

import io.micrometer.core.instrument.Counter;

public interface TaglessCounter {

    /**
     * Increment a {@link Counter}.
     */
    void increment();
}
