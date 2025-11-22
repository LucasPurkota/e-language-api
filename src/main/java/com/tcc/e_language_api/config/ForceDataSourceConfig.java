package com.tcc.e_language_api.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Configuration
public class ForceDataSourceConfig {

    //    @Bean
    //    @Primary
    //    public DataSource dataSource() {
    //        String url = "jdbc:postgresql://dpg-d4finmpr0fns73aanp7g-a:5432/e_language_y873?ssl=true&sslmode=require";
    //        String username = "e_language";
    //        String password = "eNLi0Q24PGvd8H98XcdWt5FtT4jRUkhF";
    //
    //        System.out.println("🚀 FORÇANDO DATASOURCE: " + url);
    //
    //        DataSource dataSource = DataSourceBuilder.create()
    //                .url(url)
    //                .username(username)
    //                .password(password)
    //                .driverClassName("org.postgresql.Driver")
    //                .build();
    //
    //        // Forçar inicialização do Hibernate
    //        forceHibernateDDL(dataSource);
    //
    //        return dataSource;
    //    }
    //
    //    private void forceHibernateDDL(DataSource dataSource) {
    //        try (Connection conn = dataSource.getConnection();
    //             Statement stmt = conn.createStatement()) {
    //            // Isso força a inicialização do pool e do Hibernate
    //            stmt.execute("SELECT 1");
    //            System.out.println("✅ DataSource inicializado com sucesso!");
    //        } catch (Exception e) {
    //            System.err.println("❌ Erro ao inicializar DataSource: " + e.getMessage());
    //        }
    //    }
}