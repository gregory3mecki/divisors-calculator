package com.gregory3mecki.algorithm;

import com.google.common.base.Preconditions;

import javax.enterprise.context.Dependent;
import java.util.Collection;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Dependent
public class NaiveDivisorsCounter implements DivisorsCounter {

    /**
     * Algorithm returns the collection of divisors for given {@code number}. It is based on an article
     * <a href="http://makeseleniumeasy.com/2017/10/04/java-program-12-java-program-to-find-divisors-of-given-number">makeseleniumeasy</a>.
     *
     * @param number number for which the divisors are calculated
     * @return sorted collection of divisors
     */
    @Override
    public Collection<Integer> count(final int number) {
        Preconditions.checkArgument(number >= 0, "Negative number %s", number);
        final int maxDivisor = (int) Math.sqrt(number);
        return IntStream.rangeClosed(1, maxDivisor)
                .filter(value -> number % value == 0)
                .flatMap(value -> countDivisors(number, value))
                .boxed()
                .collect(Collectors.toCollection(TreeSet::new));
    }

    private IntStream countDivisors(final int number, final int divisor) {
        // divisor is a number from range [1, sqrt(number)]
        // we have to count divisor for range (sqrt(number), number]
        // to do this we can perform 'number / divisor' - example: 12 => {1, 2, 3, 4, 6, 12} => 1·12 = 2·6 = 3·4
        // we skip duplicates with condition 'number / divisor == divisor'
        return (number / divisor == divisor) ? IntStream.of(divisor) : IntStream.of(divisor, number / divisor);
    }

}
