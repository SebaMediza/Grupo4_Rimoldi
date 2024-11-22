package com.rimoldi.models.contrato;

import lombok.Data;

@Data
public class EstadoContrato {
    private int nro_contrato;
    private int idEstado;
    private String nombre;
    private long dni;
    private String email;
    private long celular;
    private long cuil;
    private String estado;

    public EstadoContrato(){}
    public EstadoContrato(int idEstado, int nro_contrato, String nombre, long dni, String email, long celular, long cuil, String estado) {
        this.idEstado = idEstado;
        this.nombre = nombre;
        this.dni = dni;
        this.email = email;
        this.celular = celular;
        this.cuil = cuil;
        this.nro_contrato = nro_contrato;
        this.estado = estado;
    }
}