package com.rimoldi.practico1.controller;

import com.rimoldi.practico1.model.Juego;
import com.rimoldi.practico1.model.NumeroPrimo;
import com.rimoldi.practico1.model.DolarApi;
import com.rimoldi.practico1.model.Usuario;
import com.rimoldi.practico1.model.UsuarioABM;

import spark.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.rimoldi.practico1.model.Invert;
import com.rimoldi.practico1.model.Libro;
import spark.*;
import com.google.gson.Gson;
import java.net.http.*;
import java.net.URI;
import java.util.*;

public class PracticoController {

    private HttpClient client = HttpClient.newHttpClient();
    private Gson gson = new Gson();
    private ArrayList<Libro> libros = new ArrayList<Libro>();

    // Ejericio 1
    public Route getFahrenheit = (Request req, Response res) -> {
        return req.params(":celsius") + " equivalen a " + ((Integer.parseInt(req.params(":celsius")) * (9 / 5)) + 32)
                + " °Fahrenheit.";
    };

    // Ejercicio 2
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
  
    // Ejercicio 3
    public Route getPar = (Request req, Response res) -> {
        if (Integer.parseInt(req.params(":numero")) % 2 == 0) {
            return req.params(":numero") + " es un número par.";
        } else {
            return req.params(":numero") + " no es un número par.";
        }
    };

    // Ejercicio 4
   public Route invertirCadena = (Request req, Response res) -> {
        String cadenaParam = req.params(":cadena");

        try {
            String cadena = Invert.invertirCadena(cadenaParam);
            return cadena;
        } catch (NumberFormatException e) {
            res.status(400);
            return "Error";
        }
    };

    // Ejercicio 5
    public Route getHora = (Request req, Response res) -> {
        int horas = Integer.parseInt(req.params(":segundos")) / 3600;
        int minutos = (Integer.parseInt(req.params(":segundos")) % 3600) / 60;
        int segundosRestantes = Integer.parseInt(req.params(":segundos")) % 60;
        return String.format("%02d:%02d:%02d", horas, minutos, segundosRestantes);
    };

    // Ejercicio 6
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

    // Ejercicio 7
    public void addLibros() {
        Libro libro1 = new Libro("Cien años de soledad", "Gabriel García Márquez", "978-0307474728", 1967);
        Libro libro2 = new Libro("1984", "George Orwell", "978-0451524935", 1949);
        Libro libro3 = new Libro("Don Quijote de la Mancha", "Miguel de Cervantes", "978-0060934347", 1605);
        Libro libro4 = new Libro("El señor de los anillos", "J.R.R. Tolkien", "978-0544003415", 1954);
        Libro libro5 = new Libro("Fahrenheit 451", "Ray Bradbury", "978-1451673319", 1953);
        Libro libro6 = new Libro("Matar a un ruiseñor", "Harper Lee", "978-0061120084", 1960);
        Libro libro7 = new Libro("Crimen y castigo", "Fiódor Dostoyevski", "978-0486415871", 1866);
        Libro libro8 = new Libro("La Odisea", "Homero", "978-0140268867", -800);
        Libro libro9 = new Libro("Orgullo y prejuicio", "Jane Austen", "978-1503290563", 1813);
        Libro libro10 = new Libro("El gran Gatsby", "F. Scott Fitzgerald", "978-0743273565", 1925);
        libros.add(libro1);
        libros.add(libro2);
        libros.add(libro3);
        libros.add(libro4);
        libros.add(libro5);
        libros.add(libro6);
        libros.add(libro7);
        libros.add(libro8);
        libros.add(libro9);
        libros.add(libro10);
    };

    public Route getLibro = (Request req, Response res) -> {
        if (req.params(":libro") == null) {
            return gson.toJson(libros);
        }
        for (Libro libro : libros) {
            if (libro.getTitulo().equals(req.params(":libro"))) {
                return gson.toJson(libro);
            }
        }
        return "Libro no encontrado";
    };

    // Ejercicio 8
    public Route getCotizacion = (Request req, Response res) -> {
        String monto = req.params(":monto");
        String tipo = req.params(":tipo");
        int montoInt = Integer.parseInt(monto);
        double compra;
        double venta;

        if (montoInt < 0) {
            return "El monto no puede ser negativo.";
        }

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://dolarapi.com/v1/dolares/oficial"))
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

    // Ejercicio 9
    public Route getGame = (Request req, Response res) -> {
        HttpResponse<String> response = null;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://steam2.p.rapidapi.com/appDetail/" + req.params(":game")))
                    .header("x-rapidapi-key", req.headers("x-rapidapi-key"))
                    .header("x-rapidapi-host", req.headers("x-rapidapi-host"))
                    .method("GET", HttpRequest.BodyPublishers.noBody()).build();
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            System.out.println(e);
        }

        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed to get juego");
        }

        Juego juego = gson.fromJson(response.body(), Juego.class);

        return juego.toString();
    };

    // Ejercicio 10
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
};
