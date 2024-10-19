package com.rimoldi;

import static spark.Spark.*;
import com.rimoldi.controllers.ContratoController;
import com.rimoldi.controllers.EstadoContratoController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        post("/contrato", ContratoController.postContrato);
        get("/contrato/:nro_contrato", EstadoContratoController.getEstadoContrato);
    }
}