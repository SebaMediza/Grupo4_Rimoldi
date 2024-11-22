package com.rimoldi.models.persona;

import lombok.Data;
import lombok.EqualsAndHashCode;
@EqualsAndHashCode(callSuper = true)

@Data
public class Inquilino extends Persona {
    private boolean tieneMascota;
    private String empresa_trabaja;
    private int ingresos;
}
