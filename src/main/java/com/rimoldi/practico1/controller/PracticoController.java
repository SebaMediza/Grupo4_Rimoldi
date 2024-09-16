package com.rimoldi.practico1.controller;

import com.rimoldi.practico1.model.DolarApi;
import com.rimoldi.practico1.model.Usuario;
import com.rimoldi.practico1.model.UsuarioABM;

import spark.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

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
    
    public Route getUsuarios=(Request req, Response res) -> {
        res.type("application/json");
        return gson.toJson(UsuarioABM.getUsuarios());
    };

    public Route altaUsuario=(Request req, Response res) -> {
        JsonObject json = gson.fromJson(req.body(), JsonObject.class);
        String nombre = json.get("nombre").getAsString();
        String email = json.get("email").getAsString();
        Usuario usuario = UsuarioABM.crearUsuario(nombre, email);
        res.status(201);
        return gson.toJson(usuario);
    };

    public Route eliminarUsuario=(Request req, Response res) -> {
        int id = Integer.parseInt(req.params(":id"));
        Usuario usuario = UsuarioABM.eliminarUsuario(id);
        if (usuario != null) {
            res.status(200);
            return gson.toJson(usuario);
        } else {
            res.status(404);
            return "{\"error\": \"Usuario no encontrado.\"}";
        }
    };

    public Route modificarUsuario=(Request req, Response res) -> {
        int id = Integer.parseInt(req.params(":id"));
        JsonObject json = gson.fromJson(req.body(), JsonObject.class);
        String nombre = json.get("nombre").getAsString();
        String email = json.get("email").getAsString();
        Usuario usuario = UsuarioABM.modificarUsuario(id, nombre, email);
        if (usuario != null) {
            res.status(200);
            return gson.toJson(usuario);
        } else {
            res.status(404);
            return "{\"error\": \"Usuario no encontrado.\"}";
        }
    };

}
