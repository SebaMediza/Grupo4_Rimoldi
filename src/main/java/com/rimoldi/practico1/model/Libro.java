package com.rimoldi.practico1.model;

import lombok.Data;

@Data
public class Libro {

    public Libro(String titulo, String autor, String isnb, int anioPublicacion) {
        this.titulo = titulo;
        this.autor = autor;
        this.ISBN = isnb;
        this.anioPublicacion = anioPublicacion;
    }

    private String titulo;
    private String autor;
    private String ISBN;
    private int anioPublicacion;
}
