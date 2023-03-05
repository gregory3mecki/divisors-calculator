package com.gregory3mecki.rest.control;

import java.util.Collection;
import java.util.Map;

public interface CalculatorService {

    Map<Integer, Collection<String>> provideMappedDividers(String mappingName, Collection<Integer> numbers);

}
