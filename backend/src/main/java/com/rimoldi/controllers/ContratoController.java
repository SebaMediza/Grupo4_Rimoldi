package com.rimoldi.controllers;

import com.rimoldi.services.ContratoDAO;
import com.rimoldi.models.contrato.Contrato;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

public class ContratoController {
    public ContratoController() {}
    private static final String RES_STRING = "application/json";
    private static final String RES_ERROR = "Error en el servidor";
    private final Logger logger = LoggerFactory.getLogger(ContratoController.class);

    public final Route postContrato = (Request req, Response res) -> {
        ContratoDAO contratoDAO = new ContratoDAO();
        Gson gson = new Gson();
        /* String tokenMartillero = req.headers("Authorization").split(" ")[1];
        if (!tokenMartillero.equals("token123456")) {
            logger.error("Token Invalido");
            return handleError(res, "Token Invalido", 401, gson);
        } */
        try {
            Contrato contrato = gson.fromJson(req.body(), Contrato.class);
            String garantes = gson.toJson(contrato.getGarantes());
            if (contrato.getFechaInicio() == null || contrato.getFechaFin() == null || contrato.getIdPropiedad() == 0
                    || contrato.getIdInquilino() == 0 || contrato.getIdMartillero() == 0) {
                return handleError(res, "Faltan datos", 400, gson);
            }
            if (contrato.getFechaInicio().after(contrato.getFechaFin())) {
                return handleError(res, "La fecha de inicio no puede ser posterior a la fecha de fin", 400, gson);
            }
            if (!contratoDAO.postContrato(contrato, garantes)) {
                return handleError(res, "Error al crear el contrato", 400, gson);
            }
            res.type(RES_STRING);
            res.status(201);
            return gson.toJson("Contrato creado");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return handleError(res, RES_ERROR, 500, gson);
        }
    };

    private String handleError(Response res, String msj, int status, Gson gson) {
        logger.error(msj);
        res.type(RES_STRING);
        res.status(status);
        return gson.toJson(msj);
    }
}
