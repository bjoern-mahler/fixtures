package de.mahler.fixtures

class FixtureLoaderTest extends GroovyTestCase {

    void testLoadScriptForExample1Only() {
        Fixtures fixtures = new Fixtures(new BasicFixtureBuilder())
        FixtureLoader loader = new FixtureLoader(fixtures)
        loader.pathToFixtures = 'fixtures/example1'
        loader.loadAll()

        assertEquals 3, fixtures.size()
    }

    void testSubFolderScriptsLoadedForExample1() {
        Fixtures fixtures = new Fixtures(new BasicFixtureBuilder())
        FixtureLoader loader = new FixtureLoader(fixtures)
        loader.pathToFixtures = 'fixtures/example1'
        loader.loadAll()

        assertEquals 'test3', fixtures.test3.property //located in subtest.groovy
    }

    void testLoadAllFixtures() {
        Fixtures fixtures = new Fixtures(new BasicFixtureBuilder())
        FixtureLoader loader = new FixtureLoader(fixtures)
        loader.loadAll()

        assertEquals 4, fixtures.size()
    }
}
