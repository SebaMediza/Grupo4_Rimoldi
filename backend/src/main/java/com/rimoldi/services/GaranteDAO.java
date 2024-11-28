package com.rimoldi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;

public class GaranteDAO{
    private final Logger logger = LoggerFactory.getLogger(GaranteDAO.class);
    public int getId(String email){
        try (Connection conn = SqL2ODAO.getCon().open()) {
            return conn.createQuery("SELECT idGarante from garante,persona where garante.idPersona=persona.id and email LIKE :email;")
                .addParameter("email",email)
                .executeAndFetchFirst(Integer.class);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return -1;
        }
    }
}
