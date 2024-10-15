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
}
