package de.mahler.fixtures

import org.springframework.util.Assert

/**
 * Simple implementation of a FixtureBuilder, only creates instances and registers them in a map, so you can
 * access them later under a given name. Nothing is going to be persisted.
 */
class BasicFixtureBuilder implements FixtureBuilder {

    Map<String, Object> fixtures = [:]

    def methodMissing(String name, def args) {
        Assert.isTrue(args.length > 0, "There should be at least one parameter of type class")
        Assert.isTrue(args instanceof Object[])

        Class type = retrieveType(args)
        Map parameters = retrieveParameterMap(args)

        registerFixture(name, type, parameters)
    }

    private static Map retrieveParameterMap(args) {
        args.find { it instanceof Map } as Map
    }

    private static Class retrieveType(args) {
        args.find { it instanceof Class } as Class
    }

    void registerFixture(String name, Class type, Map parameters) {
        assertFixtureNameNotAlreadyRegistered(name)
        def instance = type.newInstance(parameters)
        postCreate(instance)

        fixtures[name] = instance
    }

    /**
     * Called right after the instance for the fixture to be registered has been created,
     * can be used to call some orm framework to persist the instance in a database or
     * something yet unknown...
     * @param instance
     */
    void postCreate(Object instance) {

    }

    private void assertFixtureNameNotAlreadyRegistered(String name) {
        if (fixtures.containsKey(name)) {
            throw new IllegalArgumentException("$name already registered")
        }
    }
}
