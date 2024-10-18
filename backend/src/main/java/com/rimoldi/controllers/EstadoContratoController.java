package com.rimoldi.controllers;

import com.google.gson.Gson;
import com.rimoldi.services.EstadoContratoDAO;
import spark.Route;

public class EstadoContratoController {
    private EstadoContratoController(){}
    private static EstadoContratoDAO estadoContratoDAO;
    private static Gson gson;
    public static final Route getEstadoContrato = (req, res) -> {
        int nroContrato = Integer.parseInt(req.params(":nro_contrato"));
        gson = new Gson();
        estadoContratoDAO = new EstadoContratoDAO();
        if (estadoContratoDAO.getEstadoContrato(nroContrato)) {
            res.type("application/json");
            res.status(200);
            return gson.toJson("Estado del contrato obtenido");
        }
        res.type("application/json");
        res.status(404);
        return gson.toJson("No se pudo obtener el estado del contrato");
    };
}
