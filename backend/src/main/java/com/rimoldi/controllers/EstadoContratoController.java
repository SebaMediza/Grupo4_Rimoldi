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
    private final Logger logger = LoggerFactory.getLogger(EstadoContratoController.class);
    private final String RES_STRING = "application/json";
    
    public final Route getEstadoContrato = (req, res) -> {
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

    private String handleError(Response res, String msj, int status, Gson gson) {
        logger.error(msj);
        res.type(RES_STRING);
        res.status(status);
        return gson.toJson(msj);
    }
}
