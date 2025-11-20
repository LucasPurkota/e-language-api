package com.tcc.e_language_api.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties(DataSourceProperties.class)
public class ForceDataSourceConfig {

    @Bean
    @Primary
    public DataSource dataSource() {
        String url = "jdbc:postgresql://dpg-d4finmpr0fns73aanp7g-a:5432/e_language_y873?ssl=true&sslmode=require";
        String username = "e_language";
        String password = "eNLi0Q24PGvd8H98XcdWt5FtT4jRUkhF";

        System.out.println("🚀 FORÇANDO DATASOURCE: " + url);

        return org.springframework.boot.jdbc.DataSourceBuilder.create()
                .url(url)
                .username(username)
                .password(password)
                .driverClassName("org.postgresql.Driver")
                .build();
    }
}