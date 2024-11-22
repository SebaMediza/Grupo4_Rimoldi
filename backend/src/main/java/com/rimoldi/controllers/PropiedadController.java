package com.rimoldi.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.*;
import com.rimoldi.models.propiedad.Comercial;
import com.rimoldi.models.propiedad.Familiar;
import com.rimoldi.models.propiedad.Propiedad;
import com.rimoldi.services.ComercialDAO;
import com.rimoldi.services.FamiliarDAO;
import com.rimoldi.services.PropiedadDAO;

import spark.*;

public class PropiedadController {
    private PropiedadDAO propiedadDAO;
    private ComercialDAO comercialDAO;
    private FamiliarDAO familiarDAO;
    private Gson gson;
    private final Logger logger = LoggerFactory.getLogger(PropiedadController.class);

    public PropiedadController() {
        gson = new Gson();
        propiedadDAO = new PropiedadDAO();
        comercialDAO = new ComercialDAO();
        familiarDAO = new FamiliarDAO();
    }

    public String getTipoValue(String jsonString) {
        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
        return jsonObject.get("tipo").getAsString();
    }

    public Route postPropiedad = (Request req, Response res) -> {
        JsonArray jsonArrayParsed = JsonParser.parseString(req.body()).getAsJsonArray();
        Propiedad propiedad = gson.fromJson(jsonArrayParsed.get(1), Propiedad.class);
        switch (getTipoValue(jsonArrayParsed.get(0).toString())) {
            case "comercial":
                try {
                    Comercial comercial = gson.fromJson(jsonArrayParsed.get(2), Comercial.class);
                    propiedadDAO.insert(propiedad);
                    comercialDAO.insert(comercial);
                    res.type("application/json");
                    res.status(201);
                    return gson.toJson("Propiedad creada");
                } catch (Exception e) {
                    // return handleError(res, "Error en el servidor", 500);
                    // throw new Exception("Error en el servidor");
                    res.status(500);
                    logger.error(e.getMessage());
                    return gson.toJson("Error en el servidor");
                }
            case "familiar":
                try {
                    Familiar familiar = gson.fromJson(jsonArrayParsed.get(2), Familiar.class);
                    propiedadDAO.insert(propiedad);
                    familiarDAO.insert(familiar);
                    res.type("application/json");
                    res.status(201);
                    return gson.toJson("Propiedad creada");
                } catch (Exception e) {
                    // return handleError(res, "Error en el servidor", 500);
                    // throw new Exception("Error en el servidor");
                    res.status(500);
                    logger.error(e.getMessage());
                    return gson.toJson("Error en el servidor");
                }
            default:
                res.status(500);
                return gson.toJson("Error en el servidor");
        }// Crear los objetos
    };

    public Route getPropiedades = (Request req, Response res) -> {
        res.type("application/json");
        return gson.toJson(propiedadDAO.get());
    };
}
