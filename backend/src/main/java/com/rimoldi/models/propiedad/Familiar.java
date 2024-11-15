package com.rimoldi.models.propiedad;

import lombok.Data;
/* import lombok.EqualsAndHashCode;
@EqualsAndHashCode(callSuper = true) */

@Data
public class Familiar /* extends Propiedad */ {
    private int idFamiliar;
    private int cant_ambientes;
    private int cant_baños;
    private int cant_autos_cochera;
    private boolean piscina;
    private boolean permite_mascotas;
    private boolean permite_niños;
    private int idPropiedad;
}
