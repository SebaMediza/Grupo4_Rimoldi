package com.rimoldi.models.propiedad;

import lombok.Data;

@Data
public class Comercial {
    private int idComercial;
    private String permisos_municipales;
    private boolean baño;
    private boolean cocina;
    private boolean vidriera;
    private boolean deposito;
    private int idPropiedad;
}
