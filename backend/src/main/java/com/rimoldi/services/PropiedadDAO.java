package com.rimoldi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;

import com.rimoldi.models.propiedad.Propiedad;

public class PropiedadDAO extends CrudDAO<Propiedad> {

    private String tableName = "propiedad";
    private String tablePK = "idPropiedad";

    @Override
    public Class<Propiedad> getTClass() {
        return Propiedad.class;
    }

    @Override
    public String getTablePK() {
       return tablePK;
    }

    @Override
    public String getTableName() {
        return tableName;
    }
    private final Logger logger = LoggerFactory.getLogger(PropiedadDAO.class);
    public int getId(String direccion){
        try (Connection conn = SqL2ODAO.getCon().open()) {
            return conn.createQuery("SELECT idPropiedad from propiedad where direccion LIKE :direccion;")
                .addParameter("direccion",direccion)
                .executeAndFetchFirst(Integer.class);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return -1;
        }
    }
    
}
