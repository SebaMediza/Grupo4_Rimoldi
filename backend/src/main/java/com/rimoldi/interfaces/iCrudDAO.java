package com.rimoldi.interfaces;

import java.util.List;

public interface iCrudDAO<T> {
    public abstract Class<T> getTClass();
    public abstract String getTablePK();
    public abstract String getTableName();
    public void insert(T t);
    public List<T> get();
    public void update(String idField, Object idValue, T t);
    public void delete(String idField, Object idValue);
}
