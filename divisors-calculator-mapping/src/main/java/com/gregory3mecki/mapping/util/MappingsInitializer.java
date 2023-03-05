package com.gregory3mecki.mapping.util;

import com.gregory3mecki.mapping.data.Mapping;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MappingsInitializer {

    private MappingsInitializer() {
        throw new AssertionError("Class with static utilities.");
    }

    public static Collection<Mapping> provide() {
        return IntStream.rangeClosed(1, 10)
                .mapToObj(MappingsInitializer::createRawMapping)
                .peek(MappingsInitializer::initMappings)
                .collect(Collectors.toList());
    }

    private static Mapping createRawMapping(final int number) {
        final Mapping mapping = new Mapping();
        mapping.setName("mapping" + number);
        return mapping;
    }

    private static void initMappings(final Mapping mapping) {
        final Map<Integer, String> mappings = IntStream.rangeClosed(1, 20)
                .mapToObj(number -> new AbstractMap.SimpleEntry<>(number, mapping.getName() + "_val" + number))
                .collect(Collectors.toMap(
                        AbstractMap.SimpleEntry::getKey,
                        AbstractMap.SimpleEntry::getValue,
                        (o, o2) -> o,
                        TreeMap::new
                ));
        mapping.setMappings(mappings);
    }

}
