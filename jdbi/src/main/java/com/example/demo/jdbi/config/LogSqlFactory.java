/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.jdbi.config;

import org.jdbi.v3.core.statement.StatementContext;
import org.jdbi.v3.core.statement.StatementCustomizer;
import org.jdbi.v3.sqlobject.customizer.SqlStatementCustomizer;
import org.jdbi.v3.sqlobject.customizer.SqlStatementCustomizerFactory;
import org.jdbi.v3.sqlobject.customizer.SqlStatementCustomizingAnnotation;

import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@SqlStatementCustomizingAnnotation(LogSqlFactory.Factory.class)
public @interface LogSqlFactory {

    static class Factory implements SqlStatementCustomizerFactory {

        @Override
        public SqlStatementCustomizer createForMethod(Annotation annotation, Class sqlObjectType, Method method) {
            return null;
        }

        @Override
        public SqlStatementCustomizer createForType(Annotation annotation, Class sqlObjectType) {
            return q -> q.addCustomizer(new StatementCustomizer() {
                @Override
                public void beforeExecution(PreparedStatement stmt, StatementContext ctx) throws SQLException {
                    System.out.println(stmt.toString());
                }

                @Override
                public void afterExecution(PreparedStatement stmt, StatementContext ctx) throws SQLException { }

            });
        }
    }

}
