package com.gregory3mecki.core.algorithm;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class DivisorsCounterTest {

    @Inject
    DivisorsCounter counter;

    @Test
    void checkCounterReturnsValidDivisorsForSimpleCases() {
        assertIterableEquals(List.of(), counter.count(0));
        assertIterableEquals(List.of(1, 2, 3, 6), counter.count(6));
        assertIterableEquals(List.of(1, 7), counter.count(7));
        assertIterableEquals(List.of(1, 2, 5, 10), counter.count(10));
        assertFalse(counter.count(Integer.MAX_VALUE).isEmpty());
    }

    @Test
    void checkCounterThrowsExceptionForNegativeNumber() {
        final int negativeNumber = -1;
        final var exception = assertThrows(IllegalArgumentException.class, () -> counter.count(negativeNumber));
        assertEquals("Negative number " + negativeNumber, exception.getMessage());
    }

    @Test
    void checkCounterThrowsExceptionForNullValue() {
        final Integer nullableNumber = null;
        assertThrows(NullPointerException.class, () -> counter.count(nullableNumber));
    }

}
