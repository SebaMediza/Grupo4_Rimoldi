package com.rimoldi.practico2.model;

import lombok.Data;

@Data
public class Inquilino {
    private int id;
    private int dni;
    private String nombre;
    private String direccion;
    private String fecha_nacimiento;
    private String telefono;
    private String email;
    private String ocupacion;
    private float ultimo_sueldo;
    private String fecha_recibo;

    public Inquilino() {}

    public Inquilino(int dni, String nombre, String direccion, String fecha_nacimiento, String telefono, String email, String ocupacion, float ultimo_sueldo, String fecha_recibo) {
        this.dni = dni;
        this.nombre = nombre;
        this.direccion = direccion;
        this.fecha_nacimiento = fecha_nacimiento;
        this.telefono = telefono;
        this.email = email;
        this.ocupacion = ocupacion;
        this.ultimo_sueldo = ultimo_sueldo;
        this.fecha_recibo = fecha_recibo;
    }

    public String toString() {
        return "Inquilino: DNI:"+ this.dni + "nombre" + this.nombre + " Direccion: " + this.direccion + " Fecha de nacimiento: " + this.fecha_nacimiento + " Telefono: " + this.telefono + " Email: " + this.email + " Ocupacion: " + this.ocupacion + " Ultimo sueldo: " + this.ultimo_sueldo + " Fecha de recibo: " + this.fecha_recibo;
    }
}
