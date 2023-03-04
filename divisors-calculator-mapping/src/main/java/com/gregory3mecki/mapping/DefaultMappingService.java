package com.gregory3mecki.mapping;

import com.gregory3mecki.mapping.data.Mapping;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@ApplicationScoped
public class DefaultMappingService implements MappingService {

    private final Collection<Mapping> registeredMappings = new ArrayList<>();

    @Override
    public Mapping provideMapping(final String name) {
        return registeredMappings.stream()
                .filter(mapping -> mapping.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void createMapping(final Mapping mapping) {
        registeredMappings.add(mapping);
    }

    @Override
    public void deleteMapping(final String name) {
        Optional.ofNullable(provideMapping(name))
                .ifPresent(registeredMappings::remove);
    }

}
