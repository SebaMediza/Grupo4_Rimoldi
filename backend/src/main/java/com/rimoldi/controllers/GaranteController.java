package com.rimoldi.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.rimoldi.services.GaranteDAO;

import spark.Response;
import spark.Route;

public class GaranteController {
    public GaranteController(){}
     private final Logger logger = LoggerFactory.getLogger(GaranteController.class);
    private final String RES_STRING = "application/json";

    public final Route getId = (req, res) -> {
        GaranteDAO garanteDAO = new GaranteDAO();
        Gson gson = new Gson();

        String body = req.body();
        JsonObject jsonObject = gson.fromJson(body, JsonObject.class);
        String email = jsonObject.get("email").getAsString();
        int idGarante = garanteDAO.getId(email);

        if (idGarante != -1) {
            res.type(RES_STRING);
            res.status(200);
            JsonObject responseJson = new JsonObject();
            responseJson.addProperty("idGarante", idGarante);
            return gson.toJson(responseJson);
        } else {
            return handleError(res, "No se pudo obtener el email", 404, gson);
        }
    };

    private String handleError(Response res, String msj, int status, Gson gson) {
        logger.error(msj);
        res.type(RES_STRING);
        res.status(status);
        return gson.toJson(msj);
    }
}
