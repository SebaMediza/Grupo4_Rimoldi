package com.rimoldi.practico1.controller;

import com.rimoldi.practico1.model.DolarApi;
import spark.*;
import com.google.gson.Gson;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

public class PracticoController {

    private HttpClient client = HttpClient.newHttpClient();
    private Gson gson = new Gson();

    public Route getCotizacion = (Request req, Response res) -> {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://dolarapi.com/v1/dolares/oficial"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed to get cotizacion" + response.statusCode());
        }
        DolarApi dolarApi = gson.fromJson(response.body(), DolarApi.class);

        return "Los " + req.params(":monto") + " dolares son " + (Integer.parseInt(req.params(":monto")) * Integer.parseInt(dolarApi.getCompra())) + " pesos.";
    };

    public Route getHouseOfThrones = (Request req, Response res) -> {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.anapioficeandfire.com/api/houses"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed to get cotizacion" + response.statusCode());
        }
        return response.body();
    };

}
