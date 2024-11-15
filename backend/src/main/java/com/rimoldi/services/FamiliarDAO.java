package com.rimoldi.services;

import com.rimoldi.models.propiedad.Familiar;

public class FamiliarDAO extends CrudDAO<Familiar> {

    String tableName = "familiar";
    String tablePK = "idFamiliar";

    @Override
    public Class<Familiar> getTClass() {
        return Familiar.class;
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
