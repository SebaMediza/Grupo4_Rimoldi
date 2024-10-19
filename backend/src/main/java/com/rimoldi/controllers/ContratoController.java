package com.rimoldi.controllers;

import com.rimoldi.services.ContratoDAO;
import com.rimoldi.services.EstadoContratoDAO;
import com.rimoldi.models.contrato.Contrato;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;
import com.rimoldi.Main;

public class ContratoController {
    private ContratoController() {}
    private static Gson gson;
    private static final String RES_STRING = "application/json";

    public static final Route postContrato = (Request req, Response res) -> {
        gson = new Gson();
        String tokenMartillero = req.headers("Authorization").split(" ")[1];
        if (!tokenMartillero.equals("token123456")) {
            Main.logger.error("Token Invalido");
            return handleError(res, "Token Invalido", 401);
        }

        try {
            Contrato contrato = gson.fromJson(req.body(), Contrato.class);
            if (contrato.getFechaInicio() == null || contrato.getFechaFin() == null || contrato.getIdPropiedad() == 0
                    || contrato.getIdInquilino() == 0 || contrato.getIdMartillero() == 0) {
                return handleError(res, "Faltan datos", 400);
            }
            if (contrato.getFechaInicio().after(contrato.getFechaFin())) {
                return handleError(res, "La fecha de inicio no puede ser posterior a la fecha de fin", 400);
            }
            if (!ContratoDAO.postContrato(contrato)) {
                return handleError(res, "Error al crear el contrato", 400);
            }
            if (!EstadoContratoDAO.postEstadoContrato(contrato.getId())) {
                return handleError(res, "Error al crear el estado del contrato", 400);
            }
            res.type(RES_STRING);
            res.status(201);
            return gson.toJson("Contrato creado");
        } catch (Exception e) {
            res.type(RES_STRING);
            res.status(500);
            return gson.toJson("Error en el servidor");
        }
    };

    private static String handleError(Response res, String msj, int status) {
        res.type(RES_STRING);
        res.status(status);
        return gson.toJson(msj);
    }
}
