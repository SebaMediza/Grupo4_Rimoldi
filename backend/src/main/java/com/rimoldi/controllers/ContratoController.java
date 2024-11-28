package com.rimoldi.controllers;

import com.rimoldi.services.ContratoDAO;
import com.rimoldi.models.contrato.Contrato;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import spark.Request;
import spark.Response;
import spark.Route;

public class ContratoController {
    public ContratoController() {
    }

    private final String RES_STRING = "application/json";
    private final String RES_ERROR = "Error en el servidor";
    private final Logger logger = LoggerFactory.getLogger(ContratoController.class);

    public final Route postContrato = (Request req, Response res) -> {
        ContratoDAO contratoDAO = new ContratoDAO();
        Gson gson = new Gson();
        try {
            Contrato contrato = gson.fromJson(req.body(), Contrato.class);

            if (contrato.getFecha_inicio() == null || contrato.getFecha_fin() == null || contrato.getIdPropiedad() == 0
                    || contrato.getIdInquilino() == 0 || contrato.getIdMartillero() == 0) {
                return handleError(res, "Faltan datos", 400, gson);
            }

            if (contrato.getFecha_inicio().after(contrato.getFecha_fin())) {
                return handleError(res, "La fecha de inicio no puede ser posterior a la fecha de fin", 400, gson);
            }

            if (contrato.getFecha_cancelacion() != null) {
                return handleError(res, "No se puede cancelar un contrato al crearlo", 400, gson);
            }

            if (!contratoDAO.insertarContrato(contrato)) {
                return handleError(res, "Error al crear el contrato", 400, gson);
            }

            if (!contratoDAO.insertarFirma(contrato.getNro_contrato(), contrato.getIdGarante())) {
                return handleError(res, "Error al crear la firma del contrato", 400, gson);
            }

            if (!contratoDAO.insertarEstado(contrato.getNro_contrato())) {
                return handleError(res, "Error al crear el estado del contrato", 400, gson);
            }
            res.type(RES_STRING);
            res.status(201);
            return gson.toJson("Contrato creado");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return handleError(res, RES_ERROR, 500, gson);
        }
    };

    public final Route getDatosDelContrato = (Request req, Response res) -> {
        Gson gson = new Gson();
        ContratoDAO contratoDAO = new ContratoDAO();
        try {
            String nroContratoStr = req.params(":nro_contrato");
            if (nroContratoStr == null || nroContratoStr.isEmpty()) {
                return handleError(res, "Número de contrato es obligatorio.", 400, gson);
            }

            int nroContrato;
            try {
                nroContrato = Integer.parseInt(nroContratoStr);
            } catch (NumberFormatException e) {
                return handleError(res, "Número de contrato inválido.", 400, gson);
            }

            JsonObject contratoData = contratoDAO.datosDelContrato(nroContrato);
            if (contratoData == null || contratoData.size() == 0) {
                return handleError(res, "No se encontró el contrato con el número proporcionado.", 404, gson);
            }

            res.type("application/json");
            res.status(200);
            return gson.toJson(contratoData);

        } catch (Exception e) {
            return handleError(res, "Ocurrió un error al procesar la solicitud.", 500, gson);
        }
    };

    private String handleError(Response res, String msj, int status, Gson gson) {
        logger.error(msj);
        res.type(RES_STRING);
        res.status(status);
        return gson.toJson(msj);
    }
}
