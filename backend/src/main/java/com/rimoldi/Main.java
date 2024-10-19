package com.rimoldi;

import com.rimoldi.controllers.ContratoController;

import static spark.Spark.*;
import org.slf4j.*;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.info("Starting server");
        post("/contrato", ContratoController.postContrato);
    }
}