package com.rimoldi.services;

/* import java.lang.reflect.Field;

import org.sql2o.Connection;

public abstract class CrudDAO<T> {
    public abstract Class<T> getTClass();

    public abstract String getTablePK();

    public abstract String getTableName();

    public void insert(T t) {
        Class cls = t.getClass();
        Field[] fields = cls.getDeclaredFields();
        String valuesInsertSQL = " (";
        String columnsInsertSQL = " (";
        String name;
        for (Field field : fields) {
            name = field.getName();
            columnsInsertSQL = columnsInsertSQL + " " + name + " ,";
            valuesInsertSQL = valuesInsertSQL + " :" + name + " ,";
        }
        String insertSQL = "INSERT INTO " + getTableName() + " " + columnsInsertSQL + " VALUES " + valuesInsertSQL;
        try (Connection con = SqL2ODAO.getCon().open()) {
            con.createQuery(insertSQL).bind(t).executeUpdate();
        }
    }
} */

import org.sql2o.Connection;
import org.sql2o.Sql2o;
import com.rimoldi.services.SqL2ODAO;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Clase genérica para implementar un CRUD utilizando reflexión y Sql2o.
 * 
 * @param <T> Tipo genérico que representa la entidad gestionada.
 */
public abstract class CrudDAO<T> {
    /* private final Class<T> type;
    private final Sql2o sql2o; */

    public abstract Class<T> getTClass();
    public abstract String getTablePK();
    public abstract String getTableName();

    /**
     * Constructor que inicializa la clase y la conexión.
     * 
     * @param type  Clase del tipo genérico (ejemplo: MyEntity.class).
     * @param sql2o Conexión Sql2o a la base de datos.
     */
    /* public CrudDAO(Class<T> type, Sql2o sql2o) {
        this.type = type;
        this.sql2o = sql2o;
    } */

    /**
     * Inserta una nueva entidad en la base de datos.
     * 
     * @param t Entidad a insertar.
     * @throws Exception Si ocurre un error durante la inserción.
     */
    public void insert(T t) throws Exception {
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

        String insertSQL = "INSERT INTO " + getTableName() + " " + columnsInsertSQL + " VALUES " + valuesInsertSQL;

        try (Connection con = SqL2ODAO.getCon().open()) {
            con.createQuery(insertSQL).bind(t).executeUpdate();
            System.out.println("Entidad insertada: " + t);
        }
    }

    /**
     * Lee todas las entidades de la tabla.
     * 
     * @return Lista de todas las entidades almacenadas.
     */
    public List<T> get() {
        throw new UnsupportedOperationException("Not supported yet.");
        /* String selectSQL = "SELECT * FROM " + getTableName();
        try (Connection con = SqL2ODAO.getCon().open()) {
            return con.createQuery(selectSQL).executeAndFetch(type);
        } */
    }

    /**
     * Actualiza una entidad en la base de datos según el campo identificador.
     * 
     * @param idField Nombre del campo identificador.
     * @param idValue Valor del identificador.
     * @param t       Nuevos valores de la entidad.
     * @throws Exception Si ocurre un error durante la actualización.
     */
    public void update(String idField, Object idValue, T t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
        /*  Class<?> cls = t.getClass();
        Field[] fields = cls.getDeclaredFields();
        StringBuilder setSQL = new StringBuilder();

        for (Field field : fields) {
            String name = field.getName();
            setSQL.append(name).append(" = :").append(name).append(",");
        }

        // Quitar la última coma
        setSQL.setLength(setSQL.length() - 1);

        String updateSQL = "UPDATE " + getTableName() + " SET " + setSQL + " WHERE " + idField + " = :idValue";

        try (Connection con = SqL2ODAO.getCon().open()) {
            con.createQuery(updateSQL)
                    .addParameter("idValue", idValue)
                    .bind(t)
                    .executeUpdate();
            System.out.println("Entidad actualizada con " + idField + " = " + idValue);
        } */
    }

    /**
     * Elimina una entidad de la base de datos según el campo identificador.
     * 
     * @param idField Nombre del campo identificador.
     * @param idValue Valor del identificador.
     * @throws Exception Si ocurre un error durante la eliminación.
     */
    public void delete(String idField, Object idValue) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
        /* String deleteSQL = "DELETE FROM " + getTableName() + " WHERE " + idField + " = :idValue";

        try (Connection con = SqL2ODAO.getCon().open()) {
            con.createQuery(deleteSQL)
                    .addParameter("idValue", idValue)
                    .executeUpdate();
            System.out.println("Entidad eliminada con " + idField + " = " + idValue);
        } */
    }
}
