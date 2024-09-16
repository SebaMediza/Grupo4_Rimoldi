package com.rimoldi;

import static spark.Spark.*;
import com.rimoldi.practico1.controller.PracticoController;

public class Main {
    public static void main(String[] args) {
        PracticoController PracticoController = new PracticoController();
        get("/esPrimo/:numero", PracticoController.getEsPrimo);
        get("/convertir/:monto/tipo/:tipo", PracticoController.getCotizacion);
        get("/cotizacion/:monto", PracticoController.getCotizacion);
        get("/celsiusAfahrenheit/:celsius", PracticoController.getFahrenheit);
        get("/hora/:segundos", PracticoController.getHora);
    }
}