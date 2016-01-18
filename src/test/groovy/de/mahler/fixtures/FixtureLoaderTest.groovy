package de.mahler.fixtures

/**
 */
class FixtureLoaderTest extends GroovyTestCase {

    void testLoadScriptForExample1Only() {
        Fixtures fixtures = new Fixtures(new BasicFixtureBuilder())
        FixtureLoader loader = new FixtureLoader(fixtures)
        loader.pathToFixtures = 'fixtures/example1'
        loader.load()

        assertEquals 3, fixtures.size()
    }

    void testSubFolderScriptsLoadedForExample1() {
        Fixtures fixtures = new Fixtures(new BasicFixtureBuilder())
        FixtureLoader loader = new FixtureLoader(fixtures)
        loader.pathToFixtures = 'fixtures/example1'
        loader.load()

        assertEquals 'test3', fixtures.test3.property //located in subtest.groovy
    }

    void testLoadAllFixtures() {
        Fixtures fixtures = new Fixtures(new BasicFixtureBuilder())
        FixtureLoader loader = new FixtureLoader(fixtures)
        loader.load()

        assertEquals 4, fixtures.size()
    }
}
