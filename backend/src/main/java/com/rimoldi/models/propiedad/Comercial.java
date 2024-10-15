package com.rimoldi.models.propiedad;

import lombok.Data;
import lombok.EqualsAndHashCode;
@EqualsAndHashCode(callSuper = true)

@Data
public class Comercial extends Propiedad {
    private String permisosMuninipales;
    private boolean bano;
    private boolean cocina;
    private boolean vidriera;
    private boolean deposito;
}
