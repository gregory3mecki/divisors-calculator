package com.gregory3mecki.rest.control;

import com.gregory3mecki.core.algorithm.DivisorsCounter;
import com.gregory3mecki.mapping.MappingService;
import com.gregory3mecki.mapping.data.Mapping;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Dependent
public class DefaultCalculatorService implements CalculatorService {

    @Inject
    MappingService mappingService;
    @Inject
    DivisorsCounter divisorsCounter;

    @Override
    public Map<Integer, Collection<String>> provideMappedDividers(final String mappingName, final Collection<Integer> numbers) {
        final Mapping mapping = mappingService.provideMapping(mappingName);
        return numbers.stream()
                .peek(this::checkIsLessThanOrEqualToMaxValue)
                .collect(
                        Collectors.toMap(
                                Function.identity(),
                                number -> provideMappedDivisorsForNumber(number, mapping),
                                (val01, val02) -> val01,
                                TreeMap::new
                        )
                );
    }

    private void checkIsLessThanOrEqualToMaxValue(final int number) {
        if (number > 20) {
            throw new RuntimeException();
        }
    }

    private Collection<String> provideMappedDivisorsForNumber(final int number, final Mapping mapping) {
        return divisorsCounter.count(number).stream()
                .map(divisor -> mapDivisor(divisor, mapping))
                .collect(Collectors.toList());
    }

    private String mapDivisor(final int divisor, final Mapping mapping) {
        final String mappedValue = mapping.getMappings().get(divisor);
        return Optional.ofNullable(mappedValue).orElseGet(() -> String.valueOf(divisor));
    }

}
