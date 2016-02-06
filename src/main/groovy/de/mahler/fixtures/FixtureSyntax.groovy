package de.mahler.fixtures

/**
 * The actual syntax, methods for creating or manipulating Fixtures.
 */
interface FixtureSyntax {

    /**
     * Creates a set of fixtures by using this simple dsl in the given closure:
     * nameOfFixture(Class, property1:value1, ...propertyN:valueN)
     * ...
     *
     * @param closure containing a description of which classes with which values should be created under which name
     * @return
     */
    def build(Closure closure)


    /**
     * Load a yet created fixture after it is instantiated and calls the
     * given closure to adapt the state of the fixture after it's created.
     * @param fixtureName name of the fixture to loadAll
     * @param closure the code which has to be executed after the fixture is instantiated
     */
    void post(String fixtureName, Closure closure)

}