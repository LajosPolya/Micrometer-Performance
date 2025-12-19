package com.github.lajospolya;

import com.github.lajospolya.meterRegistry.ReinstantiatedTaglessCounter;

public class MainReinstantiateTagless {

    public static void main(String[] args) {
        ReinstantiatedTaglessCounter counter = new ReinstantiatedTaglessCounter();

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            counter.increment();
        }
    }
}
