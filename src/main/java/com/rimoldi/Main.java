package com.rimoldi;

import static spark.Spark.*;
import com.rimoldi.practico1.controller.PracticoController;

public class Main {
    public static void main(String[] args) {
        PracticoController PracticoController = new PracticoController();
        PracticoController.addLibros();
        //Ejercicio 1
        get("/celsiusAfahrenheit/:celsius", PracticoController.getFahrenheit);
        //Ejercicio 2
        get("/esPrimo/:numero", PracticoController.getEsPrimo);
        //Ejercicio 3
        get("/esPar/:numero", PracticoController.getPar);
        //Ejercicio 4
        get("/invertir/:cadena", PracticoController.invertirCadena);
        //Ejercicio 5
        get("/hora/:segundos", PracticoController.getHora);
        //Ejercicio 6
        get("/usuarios", PracticoController.getUsuarios);
        post("/usuarios", PracticoController.altaUsuario);
        delete("/usuarios/:id", PracticoController.eliminarUsuario);
        put("/usuarios/:id", PracticoController.modificarUsuario);
        //Ejercicio 7
        get("libro", PracticoController.getLibro);
        get("libro/:libro", PracticoController.getLibro);
        //Ejercicio 8
        get("/convertir/:monto/tipo/:tipo", PracticoController.getCotizacion);
        //Ejercicio 9
        get("/juego/:game", PracticoController.getGame);
        //Ejercicio 10
        get("/houseOfThrones", PracticoController.getHouseOfThrones);
    }
}