package com.rimoldi.practico1.controller;

import com.rimoldi.practico1.model.NumeroPrimo;

import spark.*;

public class PracticoController {
    public Route getNumero=(Request request, Response response) -> {
        String nroParam = request.params(":numero");

        try {
            int numero = Integer.parseInt(nroParam);
            boolean primo = NumeroPrimo.esPrimo(numero);

            if(primo==true){
                return "El número "+numero+" es primo.";
            }
            return "El número "+numero+" NO es primo.";
        } catch (NumberFormatException e) {
            response.status(400);
            return "Error: El parámetro 'numero' debe ser un número.";
        }
    };
}
