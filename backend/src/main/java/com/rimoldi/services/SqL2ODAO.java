package com.rimoldi.services;

import org.sql2o.Sql2o;

public class SqL2ODAO {
    private static Sql2o sql2o;

    public static Sql2o getCon() {
        try {
            sql2o = new Sql2o("jdbc:mysql://localhost:3306/rimoldi", "root", "");
            System.out.println("Connected to database");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return sql2o;
    }
}
