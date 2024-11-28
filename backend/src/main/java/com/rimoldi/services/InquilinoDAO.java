package com.rimoldi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;

public class InquilinoDAO {
    private final Logger logger = LoggerFactory.getLogger(InquilinoDAO.class);
    public int getId(String username){
        try (Connection conn = SqL2ODAO.getCon().open()) {
            return conn.createQuery("SELECT idInquilino from inquilino,persona where inquilino.idPersona=persona.id and username LIKE :username;")
                .addParameter("username",username)
                .executeAndFetchFirst(Integer.class);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return -1;
        }
    }
}
