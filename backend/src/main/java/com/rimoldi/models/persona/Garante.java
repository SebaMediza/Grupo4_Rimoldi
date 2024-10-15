package com.rimoldi.models.persona;

import lombok.Data;
import lombok.EqualsAndHashCode;
@EqualsAndHashCode(callSuper = true)

@Data
public class Garante extends Persona {
    private int ingresos;
    private String empresa_trabaja;
    private String contacto_trabaja;
}
