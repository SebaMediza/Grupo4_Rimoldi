package com.rimoldi.practico1.model;

public class Invert {
    public static String invertirCadena(String cadena) {
        StringBuilder builder = new StringBuilder(cadena);
        return builder.reverse().toString();
    }
}
