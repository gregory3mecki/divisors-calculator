package com.gregory3mecki.mapping.validation;

import com.gregory3mecki.core.util.exception.StatusCodeException;
import com.gregory3mecki.mapping.data.Mapping;

import java.util.*;
import java.util.stream.Collectors;

public class MappingValidation {

    private final Map<String, Mapping> registeredMappings;

    public MappingValidation(final Map<String, Mapping> registeredMappings) {
        this.registeredMappings = registeredMappings;
    }

    public void checkIsPossibleToGetMapping(final String name) {
        if (!Objects.nonNull(registeredMappings.get(name))) {
            throw StatusCodeException.builder()
                    .statusCode(404)
                    .exceptionMessage("Mapping not exists.")
                    .details(String.format("Mapping with name %s is not registered.", name))
                    .build();
        }
    }

    public void checkIsMappingNotAlreadyRegistered(final String name) {
        if (Objects.nonNull(registeredMappings.get(name))) {
            throw StatusCodeException.builder()
                    .statusCode(406)
                    .exceptionMessage("Mapping is already registered.")
                    .details(String.format("Mapping with name %s is already registered, unable to create.", name))
                    .build();
        }
    }

    public void checkMappingHasDuplicatedWords(final Mapping mapping) {
        final Collection<String> duplicatedWords = provideDuplicatedWords(mapping);
        if (!duplicatedWords.isEmpty()) {
            throw StatusCodeException.builder()
                    .statusCode(406)
                    .exceptionMessage("Mapping word(s) is not unique.")
                    .details(String.format("Duplicated mapping words: %s.", duplicatedWords))
                    .build();
        }
    }

    private Collection<String> provideDuplicatedWords(final Mapping mapping) {
        final Collection<String> values = mapping.getMappings().values();
        return values.stream()
                .filter(word -> Collections.frequency(values, word) > 1)
                .collect(Collectors.toCollection(TreeSet::new));
    }

}
