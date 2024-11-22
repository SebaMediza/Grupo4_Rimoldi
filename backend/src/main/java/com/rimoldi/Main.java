package com.rimoldi;

import static spark.Spark.*;
import com.rimoldi.controllers.ContratoController;
import com.rimoldi.controllers.EstadoContratoController;
import com.rimoldi.controllers.PropiedadController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        logger.info("Iniciando servidor...");
        ContratoController contratoController = new ContratoController();
        EstadoContratoController estadoContratoController = new EstadoContratoController();
        PropiedadController propiedadController = new PropiedadController();
        before((req, res) -> {
            res.header("Access-Control-Allow-Origin", "*");
            res.header("Access-Control-Allow-Origin", "GET, POST, PUT, DELETE, OPTIONS");
            res.header("Access-Control-Allow-Origin", "Content-Type, Authorization, Content-Length, X-Requested-With");
        });

        post("/contrato", contratoController.postContrato);
        get("/contrato/:nro_contrato", estadoContratoController.getEstadoContrato);
        post("/propiedad", propiedadController.postPropiedad);
        get("/propiedad", propiedadController.getPropiedades);
        logger.info("Servidor iniciado. Escuchando en el puerto 4567");
    }
}