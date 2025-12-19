package com.github.lajospolya;

import com.github.lajospolya.meterRegistry.CachedTaglessCounter;

public class MainCacheTagless {

    public static void main(String[] args) {
        CachedTaglessCounter counter = new CachedTaglessCounter();

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            counter.increment();
        }
    }
}
