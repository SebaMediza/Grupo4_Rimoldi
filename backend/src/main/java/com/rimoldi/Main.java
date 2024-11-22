package com.rimoldi;

import static spark.Spark.*;
import com.rimoldi.controllers.ContratoController;
import com.rimoldi.controllers.EstadoContratoController;
import com.rimoldi.controllers.MartilleroController;
import com.rimoldi.controllers.PropiedadController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        logger.info("Iniciando servidor...");
        ContratoController contratoController = new ContratoController();
        EstadoContratoController estadoContratoController = new EstadoContratoController();
        MartilleroController martilleroController=new MartilleroController();
        PropiedadController propiedadController = new PropiedadController();
        
        before((req, res) -> {
            res.header("Access-Control-Allow-Origin", "*");
            res.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            res.header("Access-Control-Allow-Headers", "Content-Type, Authorization, Content-Length, X-Requested-With");
            res.header("Access-Control-Allow-Credentials", "true");
        });

        options("/*", (req, res) -> {
            res.status(200); // Responder con código HTTP 200 OK
            return "OK";
        });


        post("/contrato", contratoController.postContrato);
        get("/contrato/:nro_contrato", estadoContratoController.getEstadoContrato);
        post("/login", martilleroController.getMartillero);
        post("/propiedad", propiedadController.postPropiedad);
        get("/propiedad", propiedadController.getPropiedades);
        logger.info("Servidor iniciado. Escuchando en el puerto 4567");
    }
}