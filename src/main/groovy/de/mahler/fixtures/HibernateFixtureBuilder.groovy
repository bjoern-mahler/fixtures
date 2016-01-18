package de.mahler.fixtures

import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Hibernate-Implementation of a FixtureBuilder. After creating an instance of a fixture, it is going to be persisted
 * if it's an valid hibernate entity.
 */
@Component
class HibernateFixtureBuilder extends BasicFixtureBuilder {

    SessionFactory sessionFactory

    @Autowired
    HibernateFixtureBuilder(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory
    }

    @Override
    void postCreate(Object instance) {
        sessionFactory.getCurrentSession().saveOrUpdate(instance)
    }
}
