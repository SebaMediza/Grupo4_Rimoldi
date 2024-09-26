package com.rimoldi.services.DAOs;

import com.rimoldi.practico2.model.Inquilino;
import com.rimoldi.services.db.MySQLDAO;
import java.util.List;

import org.sql2o.Connection;

public class InquilinoDAO implements InterfaceInquilinoDAO {
    public boolean insertInquilino(Inquilino inquilino) {
        try (Connection con = MySQLDAO.getCon().open()) {
            String query = "INSERT INTO inquilino (dni, nombre, direccion, fecha_nacimiento, telefono, email, ocupacion, ultimo_sueldo, fecha_recibo) VALUES (:dni, :nombre, :direccion, :fecha_nacimiento, :telefono, :email, :ocupacion, :ultimo_sueldo, :fecha_recibo)";
            con.createQuery(query)
                    .addParameter("dni", inquilino.getDni())
                    .addParameter("nombre", inquilino.getNombre())
                    .addParameter("direccion", inquilino.getDireccion())
                    .addParameter("fecha_nacimiento", inquilino.getFecha_nacimiento())
                    .addParameter("telefono", inquilino.getTelefono())
                    .addParameter("email", inquilino.getEmail())
                    .addParameter("ocupacion", inquilino.getOcupacion())
                    .addParameter("ultimo_sueldo", inquilino.getUltimo_sueldo())
                    .addParameter("fecha_recibo", inquilino.getFecha_recibo())
                    .executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public Inquilino getInquilinoByDni(int dni) {
        String query = "SELECT * FROM inquilino WHERE dni = :dni";
        Inquilino inquilino;
        try (Connection con = MySQLDAO.getCon().open()) {
            inquilino = con.createQuery(query)
                    .addParameter("dni", dni)
                    .executeAndFetchFirst(Inquilino.class);
            return inquilino;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean updateInquilino(int dni, Inquilino inquilino) {
        try (Connection con = MySQLDAO.getCon().open()) {
            String query = "UPDATE inquilino SET nombre = :nombre, direccion = :direccion, fecha_nacimiento = :fecha_nacimiento, telefono = :telefono, email = :email, ocupacion = :ocupacion, ultimo_sueldo = :ultimo_sueldo, fecha_recibo = :fecha_recibo WHERE dni = :dni";
            con.createQuery(query)
                    .addParameter("nombre", inquilino.getNombre())
                    .addParameter("direccion", inquilino.getDireccion())
                    .addParameter("fecha_nacimiento", inquilino.getFecha_nacimiento())
                    .addParameter("telefono", inquilino.getTelefono())
                    .addParameter("email", inquilino.getEmail())
                    .addParameter("ocupacion", inquilino.getOcupacion())
                    .addParameter("ultimo_sueldo", inquilino.getUltimo_sueldo())
                    .addParameter("fecha_recibo", inquilino.getFecha_recibo())
                    .addParameter("dni", "%" + inquilino.getDni() + "%")
                    .executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
        return false;
    }

    public boolean deleteInquilino(int dni) {
        try (Connection con = MySQLDAO.getCon().open()) {
            String query = "DELETE FROM inquilino WHERE dni = :dni";
            con.createQuery(query)
                    .addParameter("dni", dni)
                    .executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public List<Inquilino> getInquilino() {
        String query = "SELECT * FROM inquilino";
        List<Inquilino> inquilinos;
        try (Connection con = MySQLDAO.getCon().open()) {
            inquilinos = con.createQuery(query).executeAndFetch(Inquilino.class);
            return inquilinos;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
