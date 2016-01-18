package de.mahler.fixtures

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
        this.shell = new GroovyShell()
    }

    def load() {
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:$pathToFixtures/**/*.groovy")
        resources.each { Resource resource ->
            Script script = parseScript(resource)
            script.run()
        }
    }

    private Script parseScript(Resource resource) {
        Script script = shell.parse(resource.file)

        script.metaClass = createEMC(script.class, {
            ExpandoMetaClass emc ->
                emc.build = { Closure cl ->
                    fixtures.build(cl)      //for a found method build use fixtures build method instead...
                }
        })

        return script
    }

    private static ExpandoMetaClass createEMC(Class clazz, Closure cl) {
        ExpandoMetaClass emc = new ExpandoMetaClass(clazz, false)
        cl(emc)
        emc.initialize()
        return emc
    }

}
