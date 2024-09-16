package com.rimoldi;

import static spark.Spark.*;
import com.rimoldi.practico1.controller.PracticoController;

public class Main {
    public static void main(String[] args) {
        PracticoController PracticoController = new PracticoController();
        PracticoController PracticoController2=new PracticoController();
        get("/cotizacion/:monto", PracticoController.getCotizacion);
        get("/usuarios", PracticoController2.getUsuarios);
        post("/usuarios", PracticoController2.altaUsuario);
        delete("/usuarios/:id", PracticoController2.eliminarUsuario);
        put("/usuarios/:id", PracticoController2.modificarUsuario);
    }
}