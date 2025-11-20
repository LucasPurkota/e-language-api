package com.tcc.e_language_api.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
public class ForceDataSourceConfig {

    @Bean
    @Primary
    public DataSource dataSource() {
        String url = "jdbc:postgresql://dpg-d4finmpr0fns73aanp7g-a:5432/e_language_y873?ssl=true&sslmode=require";
        String username = "e_language";
        String password = "eNLi0Q24PGvd8H98XcdWt5FtT4jRUkhF";

        System.out.println("🚀 FORÇANDO DATASOURCE: " + url);

        return DataSourceBuilder.create()
                .url(url)
                .username(username)
                .password(password)
                .driverClassName("org.postgresql.Driver")
                .build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.tcc.e_language_api.model"); // Ajuste para seu pacote de entidades

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update"); // ou "create" para recriar sempre
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.jdbc.lob.non_contextual_creation", "true");

        // Forçar criação do schema
        properties.put("hibernate.hbm2ddl.auto", "create-drop"); // Use "create-drop" para desenvolvimento
        // Ou use "update" para produção:
        // properties.put("hibernate.hbm2ddl.auto", "update");

        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    @Primary
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}