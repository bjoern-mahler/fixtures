package de.mahler.fixtures

import org.codehaus.groovy.control.CompilerConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import org.springframework.stereotype.Component

/**
 * Used to load a set of pre defined fixtures using the dsl proposed by FixtureBuilder.
 * The default package or path to the fixtures is "fixtures" located e.g. in the resources
 * directory of src/test/resources
 */
@Component
class FixtureLoader {

    Fixtures fixtures

    String pathToFixtures = 'fixtures'

    GroovyShell shell;

    @Autowired
    FixtureLoader(Fixtures fixtures) {
        this.fixtures = fixtures
        def compilerConfiguration = new CompilerConfiguration()
        compilerConfiguration.scriptBaseClass = DelegatingScript.class.name
        this.shell = new GroovyShell(this.class.classLoader, compilerConfiguration)
    }

    /**
     * Loads all fixture resources (groovy scripts with declaration of fixtures written with fixtures dsl) in the
     * fixtures path (defaults to 'fixtures'). Has to be on the classpath.
     */
    void loadAll() {
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:$pathToFixtures/**/*.groovy")
        resources.each { Resource resource ->
            Script script = parseScript(resource)
            script.run()
        }
    }

    /**
     * Loads all fixtures defined in the given classpath resource
     * @param resource
     */
    void load(String resource) {
        throw new UnsupportedOperationException('implement me')
    }

    /**
     * parsing the script and delegating unknown methods to the Fixtures class
     * @param resource
     * @return
     */
    private Script parseScript(Resource resource) {
        DelegatingScript script = shell.parse(resource.file) as DelegatingScript
        script.setDelegate(fixtures)
        return script
    }

}
