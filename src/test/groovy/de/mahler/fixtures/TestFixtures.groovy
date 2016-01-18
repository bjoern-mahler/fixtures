package de.mahler.fixtures

import groovy.transform.ToString

class TestFixtures extends GroovyTestCase {

    void testBuildWithOneExistingFixture() {
        Fixtures fixtures = new Fixtures(new BasicFixtureBuilder())
        fixtures.build {
            animal_1(Animal, name: 'test', age: 12)
        }

        assertEquals 'test', fixtures.animal_1.name
    }

    void testBuildWithTwoExistingFixture() {
        Fixtures fixtures = new Fixtures(new BasicFixtureBuilder())
        fixtures.build {
            animal_1(Animal, name: 'test1', age: 12)
            animal_2(Animal, name: 'test2', age: 99)
        }

        assertEquals 'test2', fixtures.animal_2.name
        assertEquals 2, fixtures.size()
    }

    void testBuildWithDoubleBuilt() {
        Fixtures fixtures = new Fixtures(new BasicFixtureBuilder())

        shouldFail(IllegalArgumentException, {
            fixtures.build {
                animal_1(Animal, name: 'test1', age: 12)
                animal_1(Animal, name: 'test2', age: 99)
            }
        })
    }

    void testReferingExistingFixture() {
        Fixtures fixtures = new Fixtures(new BasicFixtureBuilder())
        fixtures.build {
            animal_1(Animal, name: 'test1', age: 12)
        }

        fixtures.build {
            default_cage (Cage, animals: [animal_1])
        }

        assertNotNull fixtures.default_cage
        assertEquals 1 ,fixtures.default_cage.animals.size()
        assertEquals fixtures.default_cage.animals[0].name, fixtures.animal_1.name
    }

    void testReferingExistingFixtureInSameBuildClosure() {
        Fixtures fixtures = new Fixtures(new BasicFixtureBuilder())
        fixtures.build {
            animal_1(Animal, name: 'test1', age: 12)
            default_cage (Cage, animals: [animal_1])
        }

        assertNotNull fixtures.default_cage
        assertEquals 1 ,fixtures.default_cage.animals.size()
        assertEquals fixtures.default_cage.animals[0].name, fixtures.animal_1.name
    }

    void testNotYetExistingFixture() {
        Fixtures fixtures = new Fixtures(new BasicFixtureBuilder())

        shouldFail(IllegalArgumentException, {
            fixtures.build {
                default_cage (Cage, animals: [animal_1])
            }
        })
    }

    @ToString
    class Cage {
        List<Animal> animals
    }

    @ToString
    class Animal {
        String name
        int age
    }

}
