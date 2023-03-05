package com.gregory3mecki.mapping;

import com.gregory3mecki.mapping.data.Mapping;
import com.gregory3mecki.mapping.util.MappingsInitializer;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

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
        if (!hasUniqueNames(mapping)) {
            throw new IllegalArgumentException("Mappings has duplicated words.");
        }
        registeredMappings.add(mapping);
    }

    @Override
    public void deleteMapping(final String name) {
        Optional.ofNullable(provideMapping(name))
                .ifPresent(registeredMappings::remove);
    }

    private boolean hasUniqueNames(final Mapping mapping) {
        final Collection<String> values = mapping.getMappings().values();
        return values.size() == Set.copyOf(values).size();
    }

    void startup(@Observes final StartupEvent event) {
        MappingsInitializer.provide().forEach(registeredMappings::add);
    }

}
