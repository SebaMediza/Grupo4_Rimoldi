package com.rimoldi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;

import com.rimoldi.interfaces.iPropietario;
import com.rimoldi.models.persona.Propietario;

public class PropietarioDAO implements iPropietario{
    private final Logger logger = LoggerFactory.getLogger(PropietarioDAO.class);
    @Override
    public Propietario getPropietario(String email, String password) {
       try (Connection conn = SqL2ODAO.getCon().open()) {
            return conn.createQuery("SELECT p.nombre, p.email, m.password from propietario m INNER JOIN persona p on m.password=:password and m.idPersona=p.id and p.email=:email;")
                .addParameter("email",email)
                .addParameter("password",password)
                .executeAndFetchFirst(Propietario.class);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public int getId(String username){
        try (Connection conn = SqL2ODAO.getCon().open()) {
            return conn.createQuery("SELECT idPropietario from propietario,persona where propietario.idPersona=persona.id and username LIKE :username;")
                .addParameter("username",username)
                .executeAndFetchFirst(Integer.class);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return -1;
        }
    }
    
    
}
