/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.jdbi.config;

import com.example.demo.jdbi.repository.OrderRepository;
import org.jdbi.v3.core.Jdbi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author janderson
 */
@Configuration
public class RepositoryConfiguration {

    @Bean
    public OrderRepository orderRepository(Jdbi jdbi) {
        return jdbi.onDemand(OrderRepository.class);
    }

}
