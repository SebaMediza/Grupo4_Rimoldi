package com.rimoldi.models.propiedad;

import lombok.Data;
import lombok.EqualsAndHashCode;
@EqualsAndHashCode(callSuper = true)

@Data
public class Familiar extends Propiedad {
    private int cantAmbientes;
    private int cantBanios;
    private int cantAutosCochera;
    private boolean piscina;
    private boolean permiteMascotas;
    private boolean permiteNinios;
}
