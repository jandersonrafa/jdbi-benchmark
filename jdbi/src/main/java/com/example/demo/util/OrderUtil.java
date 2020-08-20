package com.example.demo.util;


import com.example.demo.model.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class OrderUtil {

    public Order createRandomOrder() {
        Order orders = new Order();
        orders.setAmount(BigDecimal.TEN);
        orders.setActive(true);
        orders.setNumberorder(100);
        orders.setCreateddate(LocalDate.now());
        orders.setCreatedtime(LocalDateTime.now());
        String randomData = "testedatatestedatatestedata";
        orders.setOne(randomData);
        orders.setTwo(randomData);
        orders.setTree(randomData);
        orders.setFour(randomData);
        orders.setFive(randomData);
        orders.setSix(randomData);
        orders.setSeven(randomData);
        orders.setEight(randomData);
        orders.setNine(randomData);
        orders.setTen(randomData);
        return orders;
    }

}
