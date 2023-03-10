package com.gregory3mecki.rest.boundary;

import com.gregory3mecki.core.util.exception.StatusCodeException;
import com.gregory3mecki.core.util.rest.RestExceptionHandler;
import com.gregory3mecki.rest.api.boundary.CalculatorApi;
import com.gregory3mecki.rest.api.entity.ResponseDTO;
import com.gregory3mecki.rest.control.CalculatorService;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultCalculatorApi implements CalculatorApi {

    @Inject
    CalculatorService calculatorService;

    @RestExceptionHandler
    @Override
    public Response provideMappedDividers(final String mappingName, final List<String> numbers) {
        final var numbersToProcess = prepareNumbers(numbers);
        final var result = calculatorService.provideMappedDividers(mappingName, numbersToProcess);
        final var response = new ResponseDTO();
        response.setData(result);
        return Response
                .ok(response)
                .build();
    }

    private Collection<Integer> prepareNumbers(final Collection<String> numbers) {
        return numbers.stream()
                .peek(this::checkIsPositiveNumber)
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

    private void checkIsPositiveNumber(final String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException | NullPointerException exception) {
            throw StatusCodeException.builder()
                    .statusCode(406)
                    .exceptionMessage("Input value should be positive number.")
                    .details(String.format("Invalid input value %s.", input))
                    .build();
        }
    }

}
