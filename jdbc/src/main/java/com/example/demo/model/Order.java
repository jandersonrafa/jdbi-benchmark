/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 *
 * @author janderson
 */
@Getter
@Setter
@NoArgsConstructor
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
