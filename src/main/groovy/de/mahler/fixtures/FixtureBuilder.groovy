package de.mahler.fixtures

/**
 *
 * Used by the Fixtures facade for building up fixtures and registering them with a given name.
 *
 * The simple DSL is:
 *
 * build {
 *   nameOfFixture (Class, propert1:value1, property1:value2,...)
 *   anotherNameOfAFixture (Class2, somePropertyOfTypeClass: nameOfFixture)
 * }
 *
 * where nameOfFixture can be anything (despite you cannot register a fixture with the same name twice),
 * Class is the class of the fixture you want to register and property1 to propertyN is a given map of properties
 * with values the object of type Class will have. When the closure of build is called instances of the given classes
 * are going to be created with the given set of parameters.
 *
 * You can refer to already registered/built fixtures in the same closure or in a later called build-closure as
 * shown in the second row of the example. If you're refering to an unknown fixture an IllegalArgumentException is
 * thrown.
 */
interface FixtureBuilder {

    /**
     * Registers the fixture under the given name, after creating an instance of the given type with the given
     * set of parameters and their values.
     * @param name of the fixture
     * @param type of the fixture
     * @param parameters to be set to the created instance
     */
    void registerFixture(String name, Class type, Map parameters)

    /**
     * Called right after the instance for the fixture to be registered has been created,
     * can be used to call some orm framework to persist the instance in a database or
     * something yet unknown...
     * @param instance
     */
    void postCreate(Object instance);


    Map<String, Object> getFixtures();



}