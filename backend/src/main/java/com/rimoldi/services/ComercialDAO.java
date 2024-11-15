package com.rimoldi.services;

import com.rimoldi.models.propiedad.Comercial;

public class ComercialDAO extends CrudDAO<Comercial> {

    String tableName = "comercial";
    String tablePK = "idComercial";

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
