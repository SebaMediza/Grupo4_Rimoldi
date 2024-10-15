package com.rimoldi.models.propiedad;

import java.util.Date;
import lombok.Data;

@Data
public abstract class Propiedad {
    private int id;
    private String direccion;
    private int alquiler_minimo;
    private int m2Cubiertos;
    private int m2Descubiertos;
    private String condiciones_garantes;
    private Double expensas_minimno;
    private Double gastos_minimos;
    private Date fecha_precio_minimo;
}
