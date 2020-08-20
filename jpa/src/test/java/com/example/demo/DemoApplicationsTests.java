package com.example.demo;

import com.example.demo.model.Orders;
import com.example.demo.repository.OrderRepository;
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
        public Orders newOrder;

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
        public Orders orderSaved;

        @Setup(Level.Invocation)
        public void doSetup() {
            orderSaved = orderUtil.createRandomOrder();
            orderSaved = orderRepository.save(orderSaved);
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
        Orders order = orderRepository.save(state.newOrder);
        return order.getId();
    }

    @Benchmark
    public Long update(StateInsertOrder state) {
        state.orderSaved.setAmount(state.orderSaved.getAmount().add(BigDecimal.ONE));
        Orders order = orderRepository.save(state.orderSaved);
        return order.getId();
    }

    @Benchmark
    public Long findById(StateInsertOrder state) {
        Orders order = orderRepository.findById(state.orderSaved.getId()).get();
        return order.getId();
    }

    @Benchmark
    public void deleteById(StateInsertOrder state) {
        orderRepository.deleteById(state.orderSaved.getId());
    }
}