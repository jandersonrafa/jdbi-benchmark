package com.example.demo;

import com.example.demo.jdbi.repository.OrderRepository;
import com.example.demo.model.Order;
import com.example.demo.util.OrderUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoApplicationsTests {

    private static OrderUtil orderUtil;
    private static OrderRepository orderRepository;

    @Autowired
    void setFields(OrderUtil orderUtil, OrderRepository orderRepository) {
        DemoApplicationsTests.orderUtil = orderUtil;
        DemoApplicationsTests.orderRepository = orderRepository;
    }

    @State(Scope.Benchmark)
    public static class StateInstanceOrder {
        public Order newOrder;

        @Setup(Level.Invocation)
        public void doSetup() {
            newOrder = orderUtil.createRandomOrder();
        }

        @TearDown(Level.Invocation)
        public void tearDown() {
            orderRepository.deleteById(newOrder.getId());
        }
    }

    @State(Scope.Benchmark)
    public static class StateInsertOrder {
        public Order orderSaved;

        @Setup(Level.Invocation)
        public void doSetup() {
            orderSaved = orderUtil.createRandomOrder();
            orderSaved.setId(orderRepository.insertBean(orderSaved));
        }

        @TearDown(Level.Invocation)
        public void tearDown() {
            try {
                orderRepository.deleteById(orderSaved.getId());
            } catch (Exception ex) {
            }
        }
    }

    @Test
    public void runBenchmarks() throws Exception {
        Options opts = new OptionsBuilder()
                .include(this.getClass().getSimpleName() + ".*")
                .warmupIterations(5)
                .warmupTime(TimeValue.seconds(5))
                .measurementIterations(3)
                .forks(0)
                .threads(1)
                .measurementTime(TimeValue.seconds(5))
                .timeUnit(TimeUnit.MILLISECONDS)
                .mode(Mode.AverageTime)
                .shouldDoGC(true)
                .shouldFailOnError(true)
                .build();

        new Runner(opts).run();
    }

    @Benchmark
    public Long insert(StateInstanceOrder state) {
        state.newOrder.setId(orderRepository.insertBean(state.newOrder));
        return state.newOrder.getId();
    }

    @Benchmark
    public Long update(StateInsertOrder state) {
        state.orderSaved.setAmount(state.orderSaved.getAmount().add(BigDecimal.ONE));
        orderRepository.updateBean(state.orderSaved);
        return state.orderSaved.getId();
    }

    @Benchmark
    public Long findById(StateInsertOrder state) {
        Order order = orderRepository.findById(state.orderSaved.getId());
        return order.getId();
    }

    @Benchmark
    public void deleteById(StateInsertOrder state) {
        orderRepository.deleteById(state.orderSaved.getId());
    }

}