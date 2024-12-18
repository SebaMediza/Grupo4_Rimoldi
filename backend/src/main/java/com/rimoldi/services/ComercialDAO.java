package com.rimoldi.services;

import com.rimoldi.models.propiedad.Comercial;

public class ComercialDAO extends CrudDAO<Comercial> {

    private String tableName = "comercial";
    private String tablePK = "idComercial";

    @Override
    public Class<Comercial> getTClass() {
    return Comercial.class;
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
