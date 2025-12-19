package com.github.lajospolya;

import com.github.lajospolya.meterRegistry.CachedTaglessCounter;

public class MainCacheTagless {

    public static void main(String[] args) {
        CachedTaglessCounter counter = new CachedTaglessCounter();

        long currentTime = System.currentTimeMillis();
        long oneMinuteFromNow = 60 * 1000 + currentTime;
        while (System.currentTimeMillis() < oneMinuteFromNow) {
            counter.increment();
        }
    }
}
