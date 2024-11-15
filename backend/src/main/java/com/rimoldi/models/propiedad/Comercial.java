package com.rimoldi.models.propiedad;

import lombok.Data;
/* import lombok.EqualsAndHashCode; */
/* @EqualsAndHashCode(callSuper = true) */

@Data
public class Comercial /* extends Propiedad */ {
    private int idComercial;
    private String permisos_municipales;
    private boolean baño;
    private boolean cocina;
    private boolean vidriera;
    private boolean deposito;
    private int idPropiedad;
}
