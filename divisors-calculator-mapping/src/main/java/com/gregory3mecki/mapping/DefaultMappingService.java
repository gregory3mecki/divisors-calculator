package com.gregory3mecki.mapping;

import com.gregory3mecki.mapping.data.Mapping;
import com.gregory3mecki.mapping.util.MappingsInitializer;
import com.gregory3mecki.mapping.validation.MappingValidation;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class DefaultMappingService implements MappingService {

    private final Map<String, Mapping> registeredMappings = new HashMap<>();
    private final MappingValidation validation;

    public DefaultMappingService() {
        this.validation = new MappingValidation(registeredMappings);
    }

    @Override
    public Mapping provideMapping(final String name) {
        validation.checkIsPossibleToGetMapping(name);
        return registeredMappings.get(name);
    }

    @Override
    public void createMapping(final Mapping mapping) {
        validation.checkIsMappingNotAlreadyRegistered(mapping.getName());
        validation.checkMappingHasDuplicatedWords(mapping);
        registeredMappings.put(mapping.getName(), mapping);
    }

    @Override
    public void deleteMapping(final String name) {
        registeredMappings.remove(name);
    }

    void startup(@Observes final StartupEvent event) {
        MappingsInitializer.provide()
                .forEach(mapping -> registeredMappings.put(mapping.getName(), mapping));
    }

}
