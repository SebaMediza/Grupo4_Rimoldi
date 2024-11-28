package com.rimoldi.controllers;



import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.rimoldi.models.persona.Martillero;
import com.rimoldi.models.persona.Propietario;
import com.rimoldi.services.MartilleroDAO;
import com.rimoldi.services.PropietarioDAO;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import spark.Request;
import spark.Response;
import spark.Route;

public class LoginController {

    public LoginController(){}

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private final String RES_STRING = "application/json";

    public final Route login = (Request req, Response res) -> {
        Gson gson = new Gson();
        JsonObject requestBody = gson.fromJson(req.body(), JsonObject.class);

        if (requestBody.has("email") && requestBody.has("password")) {
            String email = requestBody.get("email").getAsString();
            String password = requestBody.get("password").getAsString();
           
            PropietarioDAO propietarioDAO = new PropietarioDAO();
            Propietario propietario = propietarioDAO.getPropietario(email, password);
            if (propietario != null) {
                propietario.setToken(generarToken(email));
                return generarRespuesta(res, gson, "propietario", propietario);
            }

            // Si no es propietario, intenta como martillero
            MartilleroDAO martilleroDAO = new MartilleroDAO();
            Martillero martillero = martilleroDAO.getMartillero(email, password);
            if (martillero != null) {
                martillero.setToken(generarToken(email));
                return generarRespuesta(res, gson, "martillero", martillero);
            }

            // Si no encuentra al usuario
            return handleError(res, "Usuario no encontrado", 404, gson);
        } else {
            return handleError(res, "Debe ingresar email y contraseña", 400, gson);
        }
    };

    private String generarRespuesta(Response res, Gson gson, String rol, Object usuario) {
        JsonObject response = gson.toJsonTree(usuario).getAsJsonObject();
        response.addProperty("rol", rol);
    
        res.type("application/json");
        res.status(200);
        return gson.toJson(response);
    }

    private String generarToken(String email) {
        String secretKey = "miClaveSecreta";
        long expirationTime = 1000 * 60 * 60;

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    private String handleError(Response res, String msj, int status, Gson gson) {
        logger.error(msj);
        res.type(RES_STRING);
        res.status(status);
        return gson.toJson(msj);
    }
}


