package org.vincent;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
@Profile("hsqldb")
public class TestHSQLDBJPAConfig {

    @Autowired
    private Environment environment;

    private HikariConfig config() {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(environment.getProperty("db.jdbcUrl"));
        config.setUsername(environment.getProperty("db.username"));
        config.setPassword(environment.getProperty("db.password"));
        config.setDriverClassName(environment.getProperty("db.driverClassName"));

        return config;
    }

    @Bean
    public DataSource dataSource(){
        return new HikariDataSource(config());
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(Database.HSQL);
        jpaVendorAdapter.setShowSql(true);
        jpaVendorAdapter.setGenerateDdl(true);
        return jpaVendorAdapter;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean lemfb = new LocalContainerEntityManagerFactoryBean();
        lemfb.setDataSource(dataSource());
        lemfb.setPersistenceUnitName("javaconfigSamplePersistenceUnit");
        lemfb.setJpaVendorAdapter(jpaVendorAdapter());
        lemfb.setPackagesToScan("org.vincent.data.model");
        lemfb.afterPropertiesSet();
        return lemfb.getObject();
    }

    @Bean
    public JpaTransactionManager transactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory());
        return transactionManager;
    }

    @Bean
    public TransactionTemplate transactionTemplate() {
        TransactionTemplate transactionTemplate = new TransactionTemplate();
        transactionTemplate.setTransactionManager(transactionManager());
        return transactionTemplate;
    }


}