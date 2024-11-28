package com.rimoldi.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.rimoldi.services.PropietarioDAO;

import spark.Response;
import spark.Route;

public class PropietarioController {
    private final Logger logger = LoggerFactory.getLogger(PropietarioController.class);
    private final String RES_STRING = "application/json";

    public PropietarioController() {
    };

    public final Route getId = (req, res) -> {
        PropietarioDAO propietarioDAO = new PropietarioDAO();
        Gson gson = new Gson();

        String body = req.body();
        JsonObject jsonObject = gson.fromJson(body, JsonObject.class);
        String username = jsonObject.get("username").getAsString();
        int idPropietario = propietarioDAO.getId(username);

        if (idPropietario != -1) {
            res.type(RES_STRING);
            res.status(200);
            JsonObject responseJson = new JsonObject();
            responseJson.addProperty("idPropietario", idPropietario);
            return gson.toJson(responseJson);
        } else {
            return handleError(res, "No se pudo obtener el username", 404, gson);
        }
    };

    private String handleError(Response res, String msj, int status, Gson gson) {
        logger.error(msj);
        res.type(RES_STRING);
        res.status(status);
        return gson.toJson(msj);
    }
}
