package com.gregory3mecki.rest.control;

import com.gregory3mecki.algorithm.DivisorsCounter;
import com.gregory3mecki.mapper.MapperService;
import com.gregory3mecki.mapper.data.Mapper;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Dependent
public class CalculatorService {

    @Inject
    MapperService mapperService;
    @Inject
    DivisorsCounter divisorsCounter;

    public Map<Integer, Collection<String>> provideMappedDividers(final String mapperName, final Collection<Integer> numbers) {
        final Mapper mapper = mapperService.provideMapper(mapperName);
        return numbers.stream()
                .collect(
                        Collectors.toMap(
                                Function.identity(),
                                number -> provideMappedDivisorsForNumber(number, mapper),
                                (val01, val02) -> val01,
                                TreeMap::new
                        )
                );
    }

    private Collection<String> provideMappedDivisorsForNumber(final int number, final Mapper mapper) {
        return divisorsCounter.count(number).stream()
                .map(divisor -> mapDivisor(divisor, mapper))
                .collect(Collectors.toList());
    }

    private String mapDivisor(final int divisor, final Mapper mapper) {
        final String mappedValue = mapper.getMapping().get(divisor);
        return Optional.ofNullable(mappedValue).orElseGet(() -> String.valueOf(divisor));
    }

}
