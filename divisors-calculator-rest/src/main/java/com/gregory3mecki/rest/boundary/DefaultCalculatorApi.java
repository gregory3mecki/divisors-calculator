package com.gregory3mecki.rest.boundary;

import com.gregory3mecki.rest.api.boundary.CalculatorApi;
import com.gregory3mecki.rest.control.CalculatorService;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DefaultCalculatorApi implements CalculatorApi {

    @Inject
    CalculatorService calculatorService;

    @Override
    public Response provideMappedDividers(final String mappingName, final List<String> numbers) {
        final Collection<Integer> numbersToProcess = prepareNumbers(numbers);
        final Map<Integer, Collection<String>> result = calculatorService.provideMappedDividers(mappingName, numbersToProcess);
        return Response.ok(result).build();
    }

    private Collection<Integer> prepareNumbers(final Collection<String> numbers) {
        return numbers.stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

}
