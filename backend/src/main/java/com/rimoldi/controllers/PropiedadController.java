package com.rimoldi.controllers;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Map;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;

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
    private final String RES_STRING = "application/json";

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
        // multipart para recibir archivos
        req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));

        // leo el json como array json
        Part jsonPart = req.raw().getPart("json");
        JsonArray jsonArray = JsonParser.parseReader(new InputStreamReader(jsonPart.getInputStream())).getAsJsonArray();

        // tipo de propiedad
        JsonObject tipoObject = jsonArray.get(0).getAsJsonObject();
        String tipoPropiedad = tipoObject.get("tipo").getAsString();

        // imagen
        Part filePart = req.raw().getPart("imagen");
        String imageName = null;

        if (filePart != null) {
            // nombre de la imagen
            imageName = filePart.getSubmittedFileName();
            // creo la carpeta para guardar imagenes si no existe
            File uploadsDir = new File("frontend/frontendrimoldi/public/assets/uploads");
            if (!uploadsDir.exists()) {
                uploadsDir.mkdirs();
            }

            // guardo la imagen en la ruta especificada
            Path imagePath = Path.of("frontend/frontendrimoldi/public/assets/uploads", imageName);
            try (InputStream inputStream = filePart.getInputStream()) {
                Files.copy(inputStream, imagePath, StandardCopyOption.REPLACE_EXISTING);
            }
        }

        // guardo el nombre de la imagen en el json para guardar en la bd
        if (imageName != null) {
            jsonArray.get(1).getAsJsonObject().addProperty("imagen", imageName);
        }

        Propiedad propiedad = gson.fromJson(jsonArray.get(1), Propiedad.class);

        try {
            switch (tipoPropiedad) {
                case "comercial":
                    Comercial comercial = gson.fromJson(jsonArray.get(2), Comercial.class);
                    propiedadDAO.insert(propiedad);
                    comercialDAO.insert(comercial);
                    break;

                case "familiar":
                    Familiar familiar = gson.fromJson(jsonArray.get(2), Familiar.class);
                    propiedadDAO.insert(propiedad);
                    familiarDAO.insert(familiar);
                    break;

                default:
                    res.status(400);
                    return gson.toJson("Tipo de propiedad no válido");
            }
            res.type("application/json");
            res.status(201);
            return gson.toJson("Propiedad creada exitosamente");

        } catch (Exception e) {
            res.status(500);
            logger.error(e.getMessage());
            return gson.toJson("Error en el servidor");
        }
    };

    public Route getPropiedades = (Request req, Response res) -> {
        res.type("application/json");
        return gson.toJson(propiedadDAO.get());
    };

    public Route getUltimoIdPropiedad = (Request req, Response res) -> {
        res.type("application/json");
        int ultimoId = propiedadDAO.getUltimoId();
        return gson.toJson(Map.of("ultimoId", ultimoId));
    };

    public Route getUltimoIdFamiliar = (Request req, Response res) -> {
        res.type("application/json");
        int ultimoId = familiarDAO.getUltimoId();
        return gson.toJson(Map.of("ultimoId", ultimoId));
    };

    public Route getUltimoIdComercial = (Request req, Response res) -> {
        res.type("application/json");
        int ultimoId = comercialDAO.getUltimoId();
        return gson.toJson(Map.of("ultimoId", ultimoId));
    };

    public final Route getId = (req, res) -> {
        Gson gson = new Gson();

        String body = req.body();
        JsonObject jsonObject = gson.fromJson(body, JsonObject.class);
        String direccion = jsonObject.get("direccion").getAsString();
        int idPropiedad = propiedadDAO.getId(direccion);

        if (idPropiedad != -1) {
            res.type(RES_STRING);
            res.status(200);
            JsonObject responseJson = new JsonObject();
            responseJson.addProperty("idPropiedad", idPropiedad);
            return gson.toJson(responseJson);
        } else {
            return handleError(res, "No se pudo obtener la direccion", 404, gson);
        }
    };
    private String handleError(Response res, String msj, int status, Gson gson) {
        logger.error(msj);
        res.type(RES_STRING);
        res.status(status);
        return gson.toJson(msj);
    }
}
