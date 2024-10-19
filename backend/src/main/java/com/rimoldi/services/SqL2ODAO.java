package com.rimoldi.services;

import org.sql2o.Sql2o;

import com.rimoldi.Main;

public class SqL2ODAO {
    private SqL2ODAO() {}
    private static Sql2o sql2o;

    public static Sql2o getCon() {
        try {
            sql2o = new Sql2o("jdbc:mysql://localhost:3306/rimoldi", "root", "");
        } catch (Exception e) {
            Main.logger.error(e.getMessage());
        }
        return sql2o;
    }
}
