package com.github.lajospolya;

import com.github.lajospolya.meterRegistry.ArbitraryState;
import com.github.lajospolya.meterRegistry.CachedHashMapTaggedCounter;

public class MainCacheHashMapTagless {

    public static void main(String[] args) {
        CachedHashMapTaggedCounter counter = new CachedHashMapTaggedCounter();

        ArbitraryState[] values = ArbitraryState.values();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            counter.increment(values[(int) ((i * 3L + 3) % values.length)]);
        }
    }
}
