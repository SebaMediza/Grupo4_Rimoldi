package com.rimoldi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;

import com.rimoldi.interfaces.iMartillero;
import com.rimoldi.models.persona.Martillero;



public class MartilleroDAO implements iMartillero{
    private final Logger logger = LoggerFactory.getLogger(MartilleroDAO.class);
    
    public Martillero getMartillero(String email, String password) {
        try (Connection conn = SqL2ODAO.getCon().open()) {
            return conn.createQuery("SELECT p.nombre, p.email, m.password from martillero m INNER JOIN persona p on m.password=:password and m.idPersona=p.id and p.email=:email;")
                .addParameter("email",email)
                .addParameter("password",password)
                .executeAndFetchFirst(Martillero.class);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
}
