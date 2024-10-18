package com.rimoldi.controllers;

import com.rimoldi.services.ContratoDAO;
import com.rimoldi.services.EstadoContratoDAO;
import com.rimoldi.models.contrato.Contrato;
import com.rimoldi.controllers.EstadoContratoController;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

public class ContratoController {
    private static ContratoDAO contratoDAO;
    private static Gson gson;

    public static final Route postContrato = (Request req, Response res) -> {
        gson = new Gson();
        contratoDAO = new ContratoDAO();
        String tokenMartillero = req.headers("Authorization").split(" ")[1];
        if (!tokenMartillero.equals("token123456")) {
            res.type("application/json");
            res.status(401);
            return "No se ha enviado el token del martillero";
        }

        try {
            Contrato contrato = gson.fromJson(req.body(), Contrato.class);
            if (contrato.getFechaInicio() == null || contrato.getFechaFin() == null || contrato.getIdPropiedad() == 0
                    || contrato.getIdInquilino() == 0 || contrato.getIdMartillero() == 0) {
                String msj = "Faltan datos";
                res.type("application/json");
                res.status(400);
                return gson.toJson(msj);
            
            }
            if (contrato.getFechaInicio().after(contrato.getFechaFin())) {
                String msj = "La fecha de inicio no puede ser posterior a la fecha de fin";
                res.type("application/json");
                res.status(400);
                return gson.toJson(msj);
            }
            if (!contratoDAO.postContrato(contrato)) {
                String msj = "Error al crear el contrato";
                res.type("application/json");
                res.status(400);
                return gson.toJson(msj);
            }
            if (!EstadoContratoDAO.postEstadoContrato(contrato.getId())) {
                String msj = "Error al crear el estado del contrato";
                res.type("application/json");
                res.status(400);
                return gson.toJson(msj);
            }
            res.type("application/json");
            res.status(201);
            return gson.toJson("Contrato creado, y estado del contrato creado");
        } catch (Exception e) {
            res.type("application/json");
            res.status(500);
            return gson.toJson("Error en el servidor");
        }
    };
}
