package com.example.demo.repository;

import com.example.demo.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderRepository {
    private static final String INSERT_ORDER_FULL_QUERY = "INSERT INTO orders(amount, one, two, tree, four, five, six, seven, eight, nine, ten, active, numberorder, createddate, createdtime) VALUES (:amount, :one, :two, :tree, :four, :five,:six, :seven, :eight, :nine, :ten, :active, :numberorder, :createddate, :createdtime);";
    private static final String SELECT_ORDERS_QUERY = "SELECT * FROM orders";
    private static final String SELECT_ORDER_QUERY = "SELECT * FROM orders WHERE id=:id";
    private static final String DELETE_BY_ID = "DELETE FROM orders WHERE id=:id";
    private static final String UPDATE = "UPDATE orders SET amount=:amount, active=:active, createddate=:createddate, createdtime= :createdtime, numberorder=:numberorder, one=:one, two=:two, tree=:tree, four=:four, five=:five, six=:six, seven=:seven, eight=:eight, nine=:nine, ten=:ten WHERE id=:id";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void update(Order order) {
        MapSqlParameterSource parametros = new MapSqlParameterSource();
        parametros.addValue("id", order.getId());
        parametros.addValue("amount", order.getAmount());
        parametros.addValue("one", order.getOne());
        parametros.addValue("two", order.getTwo());
        parametros.addValue("tree", order.getTree());
        parametros.addValue("four", order.getFour());
        parametros.addValue("five", order.getFive());
        parametros.addValue("six", order.getSix());
        parametros.addValue("seven", order.getSeven());
        parametros.addValue("eight", order.getEight());
        parametros.addValue("nine", order.getNine());
        parametros.addValue("ten", order.getTen());
        parametros.addValue("active", order.getActive());
        parametros.addValue("numberorder", order.getNumberorder());
        parametros.addValue("createddate", order.getCreateddate());
        parametros.addValue("createdtime", order.getCreatedtime());

        int status = namedParameterJdbcTemplate.update(UPDATE, parametros);
    }

    public Order findById(Long id) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("id", id);
        List<Order> query = namedParameterJdbcTemplate.query(SELECT_ORDER_QUERY, parametros, new BeanPropertyRowMapper(Order.class));
        return query.size() > 0 ? query.get(0) : null;
    }

    public Order save(Order order) {
        MapSqlParameterSource parametros = new MapSqlParameterSource();
        parametros.addValue("id", order.getId());
        parametros.addValue("amount", order.getAmount());
        parametros.addValue("one", order.getOne());
        parametros.addValue("two", order.getTwo());
        parametros.addValue("tree", order.getTree());
        parametros.addValue("four", order.getFour());
        parametros.addValue("five", order.getFive());
        parametros.addValue("six", order.getSix());
        parametros.addValue("seven", order.getSeven());
        parametros.addValue("eight", order.getEight());
        parametros.addValue("nine", order.getNine());
        parametros.addValue("ten", order.getTen());
        parametros.addValue("active", order.getActive());
        parametros.addValue("numberorder", order.getNumberorder());
        parametros.addValue("createddate", order.getCreateddate());
        parametros.addValue("createdtime", order.getCreatedtime());
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(INSERT_ORDER_FULL_QUERY, parametros, generatedKeyHolder);
        order.setId(Long.parseLong(generatedKeyHolder.getKeyList().get(0).get("id").toString()));
        return order;
    }

    public int deleteById(Long id) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("id", id);
        return namedParameterJdbcTemplate.update(DELETE_BY_ID, parametros);
    }
}
