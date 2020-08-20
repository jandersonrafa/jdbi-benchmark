/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.jdbi.config;

import com.example.demo.model.Order;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.ConstructorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;

/**
 * @author janderson
 */
@Configuration
public class PersistenceConfiguration {

    @Autowired
    private DataSource dataSource;

    @Bean
    public Jdbi jdbi(DataSource dataSource) {
        // JDBI wants to control the Connection wrap the datasource in a proxy
        // That is aware of the Spring managed transaction
        TransactionAwareDataSourceProxy dataSourceProxy = new TransactionAwareDataSourceProxy(dataSource);
        Jdbi jdbi = Jdbi.create(dataSourceProxy);
        jdbi.installPlugins();
        jdbi.registerRowMapper(Order.class, ConstructorMapper.of(Order.class));

        return jdbi;
    }

}
