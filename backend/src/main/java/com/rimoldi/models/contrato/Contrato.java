package com.rimoldi.models.contrato;

import java.util.Date;
import java.util.List;

import com.rimoldi.models.persona.Garante;

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
    private List<Garante> garantes;

    public Contrato(int id, Date fechaInicio, Date fechaFin, Date fechaCancelacion, int idInquilino, int idPropiedad, int idMartillero, List<Garante> garantes) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.fechaCancelacion = fechaCancelacion;
        this.idInquilino = idInquilino;
        this.idPropiedad = idPropiedad;
        this.idMartillero = idMartillero;
        this.garantes = garantes;
    }
}
