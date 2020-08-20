package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jdbi.v3.core.mapper.reflect.JdbiConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author janderson
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__({@JdbiConstructor}))
public class Order {

    private Long id;
    private BigDecimal amount;
    private Boolean active;
    private LocalDate createddate;
    private LocalDateTime createdtime;
    private Integer numberorder;
    private String one;
    private String two;
    private String tree;
    private String four;
    private String five;
    private String six;
    private String seven;
    private String eight;
    private String nine;
    private String ten;

}
