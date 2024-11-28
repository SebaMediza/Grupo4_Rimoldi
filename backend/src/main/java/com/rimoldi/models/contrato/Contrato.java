package com.rimoldi.models.contrato;

import java.util.Date;

import lombok.Data;

@Data
public class Contrato {
    private int nro_contrato;
    private Date fecha_inicio;
    private Date fecha_fin;
    private Date fecha_cancelacion;
    private int idInquilino;
    private int idPropiedad;
    private int idMartillero;
    private int idGarante;

    public Contrato(){};

    public Contrato(int nro_contrato, Date fecha_inicio, Date fecha_fin, Date fecha_cancelacion, int idInquilino, int idPropiedad, int idMartillero, int idGarante) {
        this.nro_contrato = nro_contrato;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.fecha_cancelacion = fecha_cancelacion;
        this.idInquilino = idInquilino;
        this.idPropiedad = idPropiedad;
        this.idMartillero = idMartillero;
        this.idGarante = idGarante;
    }
}
