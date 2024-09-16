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
};
    public static Route getHora = (Request req, Response res) -> {
        int horas = Integer.parseInt(req.params(":segundos")) / 3600;
        int minutos = (Integer.parseInt(req.params(":segundos")) % 3600) / 60;
        int segundosRestantes = Integer.parseInt(req.params(":segundos")) % 60;
        return String.format("%02d:%02d:%02d", horas, minutos, segundosRestantes);
    };
    
    public Route getFahrenheit = (Request req, Response res) -> {
        return req.params(":celsius") + " equivalen a " + ((Integer.parseInt(req.params(":celsius")) * (9 / 5)) + 32)
                + " °Fahrenheit.";
    };
}