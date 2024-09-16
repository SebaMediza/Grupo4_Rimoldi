package com.rimoldi;

import static spark.Spark.*;
import com.rimoldi.practico1.controller.PracticoController;

public class Main {
    public static void main(String[] args) {
        PracticoController PracticoController = new PracticoController();
<<<<<<< bugfix/ejercicio8
        get("/convertir/:monto/tipo/:tipo", PracticoController.getCotizacion);
=======
        get("/cotizacion/:monto", PracticoController.getCotizacion);
        get("/hora/:segundos", PracticoController.getHora);
>>>>>>> main
    }
}