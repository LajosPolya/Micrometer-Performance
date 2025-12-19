package com.github.lajospolya;

import com.github.lajospolya.meterRegistry.ArbitraryState;
import com.github.lajospolya.meterRegistry.CachedEnumMapTaggedCounter;

public class MainCacheEnumMapTagless {

    public static void main(String[] args) {
        CachedEnumMapTaggedCounter counter = new CachedEnumMapTaggedCounter();

        ArbitraryState[] values = ArbitraryState.values();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            counter.increment(values[(int) ((i * 3L + 3) % values.length)]);
        }
    }
}
