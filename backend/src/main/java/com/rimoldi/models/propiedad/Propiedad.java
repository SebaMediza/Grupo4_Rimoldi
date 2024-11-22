package com.rimoldi.models.propiedad;

import java.util.Date;
import lombok.Data;

@Data
public class Propiedad {
    private int idPropiedad;
    private String direccion;
    private int alquiler;
    private int m2_cubiertos;
    private int m2_descubiertos;
    private String condiciones_garantes;
    private Double expensas;
    private Double gastos;
    private String cuidad;
    private int idPropietario;
    private Date fecha_precio_minimo;
    private boolean disponible;
    private String imagen;
}
