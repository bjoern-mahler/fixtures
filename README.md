# fixtures
Groovy Fixtures

The goal of this project is to provide a test fixtures approach with a simple groovy DSL. You can create and receive fixtures from a Fixtures facade and load a default set of common fixtures in a resources package called fixtures (default). 
The jpa entities can be developed in Java as well, you can also use a java compatible fixture facade from JUnit tests written in Java. 

This project is strongly influenced by the great fixtures and test-data plugins of Grails. The advantage is that you can use fixtures outside of Grails (e.g. using Spring-Boot with Groovy or if you want to test your Java-Project with Cucumber&Groovy step implementations).

## Usage


### Build/Save a fixture
Main method of the dsl is called `build` which takes a closure with a block of fixtures with a name, the type (of type Class) of the object to be instantiated followed by map of properties:

`nameOfFixture(Type, nameOfProperty1: 'value1', nameOfProperty2: 'value2',...)`

If you choosed the `BasicFixtureBuilder` an instance of the choosen class will be created and saved in an intern map of the fixtures facade. If you want to save JPA entities in a database (e.g. a hsqldb for tests) you can configure a datasource and hibernate (at the moment you can only use hibernate directly not via JPA) and choose the `HibernateFixtureBuilder` with Fixtures.

```groovy
build {
    test1(SomeEntity, property: 'test')
}
```

### Get a fixture
To get a fixture you already created before and use it in tests you can call:
```groovy
   //e.g. in a unit test class
    @Autowired
    Fixtures fixtures
   
    @Test
    void testSomething() {
        assertEquals 'test', fixtures.test1.property
    }
```



