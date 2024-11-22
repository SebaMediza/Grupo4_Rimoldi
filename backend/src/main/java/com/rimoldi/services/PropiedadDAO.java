package com.rimoldi.services;

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
    
}
