package com.rimoldi.practico1.controller;

import com.rimoldi.practico1.model.DolarApi;
import com.rimoldi.practico1.model.Libro;
import spark.*;
import com.google.gson.Gson;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.*;

public class PracticoController {

    private HttpClient client = HttpClient.newHttpClient();
    private Gson gson = new Gson();
    private ArrayList<Libro> libros = new ArrayList<Libro>();

    public Route getCotizacion = (Request req, Response res) -> {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://dolarapi.com/v1/dolares/oficial"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed to get cotizacion" + response.statusCode());
        }
        DolarApi dolarApi = gson.fromJson(response.body(), DolarApi.class);

        return "Los " + req.params(":monto") + " dolares son "
                + (Integer.parseInt(req.params(":monto")) * Integer.parseInt(dolarApi.getCompra())) + " pesos.";
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
    }
}
