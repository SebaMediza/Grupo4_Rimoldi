package com.rimoldi.controllers;

import com.rimoldi.services.ContratoDAO;
import com.rimoldi.models.contrato.Contrato;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

public class ContratoController {
    private static ContratoDAO contratoDAO;
    private static Gson gson;

    public static Route postContrato = (Request req, Response res) -> {
        gson = new Gson();
        contratoDAO = new ContratoDAO();
        System.out.println("POST /contrato");
        String tokenMartillero = req.headers("Authorization");
        if (tokenMartillero == null){
            res.type("application/json");
            res.status(401);
            return "No se ha enviado el token del martillero";
        }

        try {
            Contrato contrato = gson.fromJson(req.body(), Contrato.class);
            System.out.println(contrato);
            contratoDAO.postContrato(contrato);
            res.type("application/json");
            res.status(201);
            return gson.toJson("Contrato creado");
        } catch (Exception e) {
            System.out.println(e);
            res.type("application/json");
            res.status(400);
            return gson.toJson("Error al crear el contrato");
        }
    };
}
