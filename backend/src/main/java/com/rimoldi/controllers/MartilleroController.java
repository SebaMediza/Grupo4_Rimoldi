package com.rimoldi.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.rimoldi.models.persona.Martillero;
import com.rimoldi.services.MartilleroDAO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import spark.Response;
import spark.Route;

public class MartilleroController {
    public MartilleroController() {
    }

    private final Logger logger = LoggerFactory.getLogger(MartilleroController.class);
    private final String RES_STRING = "application/json";

    public final Route getMartillero = (req, res) -> {
        Gson gson = new Gson();
        JsonObject requestBody = gson.fromJson(req.body(), JsonObject.class);
    
        if (requestBody.has("email") && requestBody.has("password")) {
            String email = requestBody.get("email").getAsString();
            String password = requestBody.get("password").getAsString();
    
            MartilleroDAO martilleroDAO = new MartilleroDAO();
            Martillero martillero = martilleroDAO.getMartillero(email, password);
    
            if (martillero != null) {
                String token = generarToken(email);
                martillero.setToken(token);
    
                res.type(RES_STRING);
                res.status(200);
                return gson.toJson(martillero);
            } else {
                return handleError(res, "No se pudo obtener el martillero", 404, gson);
            }
        } else {
            return handleError(res, "Debe ingresar email y contraseña", 400, gson);
        }
    };

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
