package com.gregory3mecki.rest.boundary.mock;

import com.gregory3mecki.mapping.DefaultMappingService;
import com.gregory3mecki.mapping.MappingService;
import com.gregory3mecki.mapping.data.Mapping;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.test.Mock;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.Map;

@Mock
public class MockMappingService implements MappingService {

    @Inject
    DefaultMappingService defaultMappingService;

    void startup(@Observes final StartupEvent event) {
        final Mapping mapping = new Mapping();
        mapping.setName("test-mapping");
        mapping.setMappings(
                Map.of(
                        1, "testValue01",
                        2, "testValue02",
                        3, "testValue03",
                        4, "testValue04"
                )
        );
        defaultMappingService.createMapping(mapping);
    }

    @Override
    public Mapping provideMapping(final String name) {
        return defaultMappingService.provideMapping(name);
    }

    @Override
    public void createMapping(final Mapping mapping) {
        defaultMappingService.createMapping(mapping);
    }

    @Override
    public void deleteMapping(final String name) {
        defaultMappingService.deleteMapping(name);
    }

}
