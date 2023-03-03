package com.gregory3mecki.mapper;

import com.gregory3mecki.mapper.data.Mapper;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@ApplicationScoped
public class DefaultMapperService implements MapperService {

    private final Collection<Mapper> registeredMappers = new ArrayList<>();

    @Override
    public Mapper provideMapper(final String name) {
        return registeredMappers.stream()
                .filter(mapper -> mapper.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void createMapper(final Mapper mapper) {
        registeredMappers.add(mapper);
    }

    @Override
    public void deleteMapper(final String name) {
        Optional.ofNullable(provideMapper(name))
                .ifPresent(registeredMappers::remove);
    }

}
