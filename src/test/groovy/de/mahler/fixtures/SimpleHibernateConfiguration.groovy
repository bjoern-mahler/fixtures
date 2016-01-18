package de.mahler.fixtures

import org.hibernate.SessionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.orm.hibernate4.HibernateTransactionManager
import org.springframework.orm.hibernate4.LocalSessionFactoryBean
import org.springframework.transaction.annotation.EnableTransactionManagement

import javax.sql.DataSource

@EnableTransactionManagement(proxyTargetClass = true)
@EnableAspectJAutoProxy
@Configuration
class SimpleHibernateConfiguration {

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("de.spinwork.datacuke.entities");
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.HSQL).build();
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }


    Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("hibernate.hbm2ddl.auto", "create-drop");
                setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
                setProperty("hibernate.globally_quoted_identifiers", "true");
            }
        };
    }

    @Bean
    HibernateFixtureBuilder fixtureBuilder(SessionFactory sessionFactory) {
        return new HibernateFixtureBuilder(sessionFactory)
    }

    @Bean
    Fixtures fixtures(HibernateFixtureBuilder fixtureBuilder) {
        return new Fixtures(fixtureBuilder)
    }
}
