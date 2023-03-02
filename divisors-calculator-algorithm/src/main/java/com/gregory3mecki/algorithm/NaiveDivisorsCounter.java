package com.gregory3mecki.algorithm;

// http://makeseleniumeasy.com/2017/10/04/java-program-12-java-program-to-find-divisors-of-given-number/

import java.util.Collection;
import java.util.TreeSet;

class NaiveDivisorsCounter implements DivisorsCounter {

    @Override
    public Collection<Integer> count(final int number) {
        Collection<Integer> dividers = new TreeSet<>();
        int maxD = (int) Math.sqrt(number);
        for (int i = 1; i <= maxD; i++) {
            if (number % i == 0) {
                // If divisors are equal, print only one
                if (number / i == i)
                    dividers.add(i);
                    // Otherwise print both
                else {
                    dividers.add(i);
                    dividers.add(number / i);
                }
            }
        }
        return dividers;
    }
}
