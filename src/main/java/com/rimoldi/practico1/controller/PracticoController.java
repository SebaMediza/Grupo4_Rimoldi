package com.rimoldi.practico1.controller;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.rimoldi.practico1.model.DolarApi;
import com.rimoldi.practico1.model.NumeroPrimo;

import spark.*;

public class PracticoController {
    private HttpClient client = HttpClient.newHttpClient();
    private Gson gson = new Gson();

    public Route getEsPrimo = (Request request, Response response) -> {
        String nroParam = request.params(":numero");

        try {
            int numero = Integer.parseInt(nroParam);
            boolean primo = NumeroPrimo.esPrimo(numero);

            if (primo == true) {
                return "El número " + numero + " es primo.";
            }
            return "El número " + numero + " NO es primo.";
        } catch (NumberFormatException e) {
            response.status(400);
            return "Error: El parámetro 'numero' debe ser un número.";
        }
    };

    public Route getCotizacion = (Request req, Response res) -> {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://dolarapi.com/v1/dolares/oficial"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed to get cotizacion" + response.statusCode());
        }
        DolarApi dolarApi = gson.fromJson(response.body(), DolarApi.class);

        if (req.queryParams("tipo").equals("dolar")) {
            return "Los " + req.queryParams("monto") + " dolares son "
                    + (Integer.parseInt(req.queryParams("monto")) * Integer.parseInt(dolarApi.getCompra())) + " pesos.";
        } else {
            return "Los " + req.queryParams("monto") + " pesos son "
                    + (Integer.parseInt(req.queryParams("monto")) / Integer.parseInt(dolarApi.getCompra()))
                    + " dolares.";
        }
    };
};
