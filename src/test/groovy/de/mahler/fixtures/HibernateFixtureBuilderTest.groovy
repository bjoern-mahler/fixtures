package de.mahler.fixtures

import org.hibernate.SessionFactory
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertNotNull

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SimpleHibernateConfiguration)
@Configuration
class HibernateFixtureBuilderTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    SessionFactory sessionFactory

    @Autowired
    Fixtures fixtures

    @Test
    void testContext() {
        assertNotNull(sessionFactory)
        assertNotNull(fixtures)
    }

    @Test
    void testCreatingFixtureWithHibernateFixtureBuilder() {
        fixtures.build {
            test (TestEntity, name: 'name', value: 99)
        }

        sessionFactory.getCurrentSession().flush()

        TestEntity loadedEntity = sessionFactory.getCurrentSession().load(TestEntity, 1L) as TestEntity

        assertNotNull(loadedEntity)
        assertEquals('name', loadedEntity.name)

        assertEquals 'name', fixtures.test.name
        assertEquals 99, fixtures.test.value
        assertEquals 1L, fixtures.test.id
    }


    @Entity
    class TestEntity {

        @Id
        @GeneratedValue
        Long id;

        @Column
        String name

        @Column
        int value

    }

}
