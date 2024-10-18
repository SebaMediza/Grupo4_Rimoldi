package com.rimoldi.controllers;

import com.google.gson.Gson;
import com.rimoldi.services.EstadoContratoDAO;
import spark.Route;

public class EstadoContratoController {
    private static EstadoContratoDAO estadoContratoDAO;
    private static Gson gson;
    public static final Route getEstadoContrato = (req, res) -> {
        int nro_contrato = Integer.parseInt(req.params(":nro_contrato"));
        gson = new Gson();
        estadoContratoDAO = new EstadoContratoDAO();
        if (estadoContratoDAO.getEstadoContrato(nro_contrato)) {
            res.type("application/json");
            res.status(200);
            return gson.toJson("Estado del contrato obtenido");
        }
    };
}
