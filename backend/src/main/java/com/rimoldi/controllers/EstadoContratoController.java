package com.rimoldi.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.rimoldi.models.contrato.EstadoContrato;
import com.rimoldi.services.EstadoContratoDAO;

import spark.Response;
import spark.Route;

public class EstadoContratoController {
    public EstadoContratoController() {
    }
    private static final Logger logger = LoggerFactory.getLogger(EstadoContratoController.class);
    private static final String RES_STRING = "application/json";
    
    public final Route getEstadoContrato = (req, res) -> {
        /* String tokenPropietario = req.headers("Authorization").split(" ")[1];
        if (!tokenPropietario.equals("token123456")) {
            return handleError(res, "Token Invalido", 401);
        } */
        EstadoContratoDAO estadoContratoDAO = new EstadoContratoDAO();
        Gson gson = new Gson();
        int nroContrato = Integer.parseInt(req.params(":nro_contrato"));
        EstadoContrato estado = estadoContratoDAO.getEstadoContrato(nroContrato);
        if (estado != null) {
            res.type(RES_STRING);
            res.status(200);
            return gson.toJson(estado);
        } else {
            return handleError(res, "No se pudo obtener el estado del contrato", 404, gson);
        }
    };

    private static String handleError(Response res, String msj, int status, Gson gson) {
        logger.error(msj);
        res.type(RES_STRING);
        res.status(status);
        return gson.toJson(msj);
    }
}
