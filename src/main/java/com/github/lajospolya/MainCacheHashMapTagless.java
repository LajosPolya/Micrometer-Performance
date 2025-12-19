package com.github.lajospolya;

import com.github.lajospolya.meterRegistry.ArbitraryState;
import com.github.lajospolya.meterRegistry.CachedHashMapTaggedCounter;

public class MainCacheHashMapTagless {

    public static void main(String[] args) {
        CachedHashMapTaggedCounter counter = new CachedHashMapTaggedCounter();

        long currentTime = System.currentTimeMillis();
        long oneMinuteFromNow = 60 * 1000 + currentTime;
        ArbitraryState[] values = ArbitraryState.values();
        for(int i = 0; System.currentTimeMillis() < oneMinuteFromNow; i++){
            counter.increment(values[(int)((i * 3L + 3) % values.length)]);
        }
    }
}
