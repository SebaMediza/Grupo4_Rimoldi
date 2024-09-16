package com.rimoldi;

import static spark.Spark.*;
import com.rimoldi.practico1.controller.PracticoController;

public class Main {
    public static void main(String[] args) {
        PracticoController PracticoController = new PracticoController();
        get("/cotizacion/:monto", PracticoController.getCotizacion);
        PracticoController practicoController2=new PracticoController();
        get("/invertir/:cadena", practicoController2.invertirCadena);
    }
}