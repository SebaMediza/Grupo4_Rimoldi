package com.rimoldi.practico2.controller;

import java.util.List;

import com.google.gson.Gson;
import com.rimoldi.practico2.model.Inquilino;
import com.rimoldi.services.DAOs.InquilinoDAO;

import spark.Route;

public class InquilinoController {

    private InquilinoDAO inquilinoDAO = new InquilinoDAO();
    private Gson gSon = new Gson();

    public Route postInquilino = (req, res) -> {
        try {
            Inquilino inquilino = new Inquilino(
                    Integer.parseInt(req.queryParams("dni")),
                    req.queryParams("nombre"),
                    req.queryParams("direccion"),
                    req.queryParams("fecha_nacimiento"),
                    req.queryParams("telefono"),
                    req.queryParams("email"),
                    req.queryParams("ocupacion"),
                    Float.parseFloat(req.queryParams("ultimo_sueldo")),
                    req.queryParams("fecha_recibo"));
            if (inquilinoDAO.insertInquilino(inquilino)) {
                res.type("application/json");
                res.status(201);
                return "Inquilino creado";
            } else {
                throw new Exception();
            }

        } catch (Exception e) {
            res.status(500);
            return "Error al crear inquilino";
        }
    };

    public Route getInquilino = (req, res) -> {
        try {
            List<Inquilino> data = inquilinoDAO.getInquilino();
            res.type("application/json");
            return gSon.toJson(data);
        } catch (Exception e) {
            res.status(500);
            return "Error al obtener inquilinos";
        }
    };

    public Route deleteInquilino = (req, res) -> {
        try {
            if (inquilinoDAO.deleteInquilino(Integer.parseInt(req.queryParams("dni")))) {
                res.type("application/json");
                res.status(200);
                return "Inquilino eliminado";
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            res.status(500);
            return "Error al eliminar inquilino";
        }
    };

    public Route updateInquilino = (req, res) -> {
        int dni = Integer.parseInt(req.queryParams("dni"));
        String nombre = req.queryParams("nombre");
        String direccio = req.queryParams("direccion");
        String fecha_nac = req.queryParams("fecha_nacimiento");
        String tel = req.queryParams("telefono");
        String mail = req.queryParams("email");
        String trabajo = req.queryParams("ocupacion");
        float sueldo = Float.parseFloat(req.queryParams("ultimo_sueldo"));;
        String recibo = req.queryParams("fecha_recibo");
        Inquilino inquilino = new Inquilino(dni, nombre, direccio, fecha_nac, tel, mail, trabajo, sueldo, recibo);
        try {
            if (inquilinoDAO.updateInquilino(dni, inquilino)) {
                res.type("application/json");
                res.status(200);
                return "Inquilino actualizado";
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println(e);
            res.status(500);
            return "Error al actualizar inquilino";
        }
    };
}
