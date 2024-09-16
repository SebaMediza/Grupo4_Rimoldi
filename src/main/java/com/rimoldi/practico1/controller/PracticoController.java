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
        String monto = req.params(":monto");
        String tipo = req.params(":tipo");
        int montoInt = Integer.parseInt(monto);
        double compra;
        double venta;

        if (montoInt < 0) {
            return "El monto no puede ser negativo.";
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://dolarapi.com/v1/dolares/oficial"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed to get cotizacion" + response.statusCode());
        }

        DolarApi dolarApi = gson.fromJson(response.body(), DolarApi.class);
        try {
            compra = Double.parseDouble(dolarApi.getCompra());
            venta = Double.parseDouble(dolarApi.getVenta());
        } catch (NumberFormatException e) {
            return "Error al convertir el monto.";
        }

        if (tipo.equals("dolar")) {
            return "Los " + monto + " dolares son "
                    + (montoInt * venta) + " pesos.";
        } else {
            return "Los " + monto + " pesos son "
                    + (montoInt / compra) + " dolares.";
        }
    };
}
