package com.github.lajospolya;

import com.github.lajospolya.meterRegistry.ReinstantiatedTaglessCounter;

public class MainReinstantiateTagless {

    public static void main(String[] args) {
        ReinstantiatedTaglessCounter counter = new ReinstantiatedTaglessCounter();

        long currentTime = System.currentTimeMillis();
        long oneMinuteFromNow = 60 * 1000 + currentTime;
        while (System.currentTimeMillis() < oneMinuteFromNow) {
            counter.increment();
        }
    }
}
