package com.rimoldi.models.contrato;

import java.util.Date;

import lombok.Data;

@Data
public class Contrato {
    private int id;
    private Date fechaInicio;
    private Date fechaFin;
    private Date fechaCancelacion;
    private int idInquilino;
    private int idPropiedad;
    private int idMartillero;
    private int idGarante;

    public Contrato(){};

    public Contrato(int id, Date fechaInicio, Date fechaFin, Date fechaCancelacion, int idInquilino, int idPropiedad, int idMartillero, int idGarante) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.fechaCancelacion = fechaCancelacion;
        this.idInquilino = idInquilino;
        this.idPropiedad = idPropiedad;
        this.idMartillero = idMartillero;
        this.idGarante = idGarante;
    }
}
