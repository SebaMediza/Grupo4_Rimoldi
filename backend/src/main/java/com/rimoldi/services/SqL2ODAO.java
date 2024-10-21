package com.rimoldi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Sql2o;

public class SqL2ODAO {
    private SqL2ODAO() {}
    private static Sql2o sql2o;
    private static final Logger logger = LoggerFactory.getLogger(SqL2ODAO.class);

    public static Sql2o getCon() {
        try {
            sql2o = new Sql2o("jdbc:mysql://localhost:3306/rimoldi", "root", "");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return sql2o;
    }
}
