package com.example.demo.jdbi.repository;

import com.example.demo.model.Order;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderFluentRepository {

    private static final String INSERT_ORDER_QUERY = "INSERT INTO orders(amount, one, two, tree, four, five, six, seven, eight, nine, ten, active, numberorder, createddate, createdtime) VALUES (:amount, :one, :two, :tree, :four, :five, :six, :seven, :eight, :nine, :ten, :active, :numberorder, :createddate, :createdtime)";
    private static final String UPDATE_ORDER_QUERY = "UPDATE orders SET amount=:amount, active=:active, createddate=:createddate, createdtime= :createdtime, numberorder=:numberorder, one=:one, two=:two, tree=:tree, four=:four, five=:five, six=:six, seven=:seven, eight=:eight, nine=:nine, ten=:ten WHERE id=:id";
    private static final String SELECT_ORDER_QUERY = "SELECT * FROM orders WHERE id= :id";
    private static final String DELETE_BY_ID = "DELETE FROM orders WHERE id= :id";

    @Autowired
    private Jdbi jdbi;

    public void deleteById(Long id) {
        jdbi.useHandle(handle ->
                handle.createUpdate(DELETE_BY_ID)
                        .bind("id", id)
                        .execute());
    }

    public Order findById(Long id) {
        return jdbi.withHandle(handle ->
                handle.createQuery(SELECT_ORDER_QUERY)
                        .bind("id", id)
                        .mapTo(Order.class).one());
    }

    public void updateBean(Order orderSaved) {
        jdbi.useHandle(handle ->
                handle.createUpdate(UPDATE_ORDER_QUERY)
                        .bindBean(orderSaved)
                        .execute());
    }

    public Long insertBean(Order newOrder) {
        return jdbi.withHandle(handle ->
                handle.createUpdate(INSERT_ORDER_QUERY)
                        .bindBean(newOrder)
                        .executeAndReturnGeneratedKeys("id")
                        .mapTo(Long.class)
                        .one())
                ;
    }
}
