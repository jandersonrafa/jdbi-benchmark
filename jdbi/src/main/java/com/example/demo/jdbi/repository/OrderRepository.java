/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.jdbi.repository;

import com.example.demo.jdbi.config.LogSqlFactory;
import com.example.demo.model.Order;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author janderson
 */
//@LogSqlFactory
public interface OrderRepository {

    @SqlUpdate("INSERT INTO orders(amount, one, two, tree, four, five, six, seven, eight, nine, ten, active, numberorder, createddate, createdtime) " +
            "   VALUES (:amount, :one, :two, :tree, :four, :five, :six, :seven, :eight, :nine, :ten, :active, :numberorder, :createddate, :createdtime)")
    @GetGeneratedKeys("id")
    Long insertBean(@BindBean Order user);

    @SqlUpdate("UPDATE orders SET amount=:amount, active=:active, createddate=:createddate, createdtime= :createdtime, numberorder=:numberorder, one=:one, two=:two, tree=:tree, four=:four, five=:five, six=:six, seven=:seven, eight=:eight, nine=:nine, ten=:ten WHERE id=:id")
    void updateBean(@BindBean Order user);

    @SqlQuery("SELECT * FROM orders WHERE id = :id")
    @RegisterBeanMapper(Order.class)
    Order findById(@Bind("id") Long id);

    @SqlUpdate("DELETE FROM orders WHERE id = :id")
    int deleteById(@Bind("id") Long id);

}
