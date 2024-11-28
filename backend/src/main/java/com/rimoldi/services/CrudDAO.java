package com.rimoldi.services;

import org.sql2o.Connection;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Clase genérica para implementar un CRUD utilizando reflexión y Sql2o.
 * 
 * @param <T> Tipo genérico que representa la entidad gestionada.
 */
public abstract class CrudDAO<T> implements iCrudDAO<T> {
    /**
     * Inserta una nueva entidad en la base de datos.
     * 
     * @param t Entidad a insertar.
     * @throws Exception Si ocurre un error durante la inserción.
     */
    public void insert(T t) {
        Class<?> cls = t.getClass();
        Field[] fields = cls.getDeclaredFields();
        StringBuilder columnsInsertSQL = new StringBuilder("(");
        StringBuilder valuesInsertSQL = new StringBuilder("(");

        for (Field field : fields) {
            String name = field.getName();
            columnsInsertSQL.append(name).append(",");
            valuesInsertSQL.append(":").append(name).append(",");
        }

        // Quitar la última coma
        columnsInsertSQL.setLength(columnsInsertSQL.length() - 1);
        valuesInsertSQL.setLength(valuesInsertSQL.length() - 1);

        columnsInsertSQL.append(")");
        valuesInsertSQL.append(")");

        String insertSQL = "INSERT INTO " + getTableName() + " " + columnsInsertSQL + " VALUEs " + valuesInsertSQL;

        try (Connection con = SqL2ODAO.getCon().open()) {
            con.createQuery(insertSQL).bind(t).executeUpdate();
        }
    }

    /**
     * Lee todas las entidades de la tabla.
     * 
     * @return Lista de todas las entidades almacenadas.
     */
    public List<T> get() {
        String selectSQL = "SELECT * FROM " + getTableName();
        try (Connection con = SqL2ODAO.getCon().open()) {
            return con.createQuery(selectSQL).executeAndFetch(getTClass());
        }
    }

    public int getUltimoId() {
        try (Connection conn = SqL2ODAO.getCon().open()) {
            
            String query = "SELECT " + getTablePK() + " FROM " + getTableName() + " ORDER BY " + getTablePK() + " DESC LIMIT 1";
            
            Integer ultimoId = conn.createQuery(query).executeScalar(Integer.class);

            if (ultimoId != null) {
                return ultimoId;
            } else {
                return -1;  
            }

        } catch (Exception e) {
            e.printStackTrace();
            return -1;  
        }
    }
    /**
     * Actualiza una entidad en la base de datos según el campo identificador.
     * 
     * @param idField Nombre del campo identificador.
     * @param idValue Valor del identificador.
     * @param t       Nuevos valores de la entidad.
     * @throws Exception Si ocurre un error durante la actualización.
     */
    public void update(String idField, Object idValue, T t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Elimina una entidad de la base de datos según el campo identificador.
     * 
     * @param idField Nombre del campo identificador.
     * @param idValue Valor del identificador.
     * @throws Exception Si ocurre un error durante la eliminación.
     */
    public void delete(String idField, Object idValue) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
