package com.rimoldi.services.db;

import org.sql2o.Sql2o;

public class MySQLDAO {
    private static Sql2o sql2o;

    public static Sql2o getCon() {
        try {
            sql2o = new Sql2o("jdbc:mysql://localhost:3306/rimoldi", "root", "");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return sql2o;
    }
}
