package com.rimoldi;
import com.rimoldi.controllers.ContratoController;


import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
       post("/contrato", ContratoController.postContrato);
    }
}