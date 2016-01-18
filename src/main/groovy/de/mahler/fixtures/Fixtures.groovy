package de.mahler.fixtures

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.util.Assert

/**
 * Fixtures is the facade to build and register a set of variables which can be accessed later by
 * using fixtures.variableName. It's a spring component so you can autowire it wherever you want to
 * access or build up data fixtures.
 *
 * the build method uses a closure to register variables of types by using a simple DSL:
 *
 * name_of_variable(Type, property1:value1, property2:value2,...)
 *
 */
@Component
class Fixtures {

    FixtureBuilder fixtureBuilder

    @Autowired
    Fixtures(FixtureBuilder fixtureBuilder) {
        this.fixtureBuilder = fixtureBuilder
    }

    def build(@DelegatesTo(FixtureBuilder) Closure closure) {
        def builderClosure = closure.rehydrate(fixtureBuilder, this, this)
        builderClosure.resolveStrategy = Closure.DELEGATE_FIRST
        builderClosure()
    }

    def propertyMissing(String name) {
        def fixture = fixtureBuilder.fixtures[name]
        Assert.notNull(fixture, "fixture with name $name not found.")
        return fixture
    }

    int size() {
        return fixtureBuilder.fixtures.size()
    }


}
