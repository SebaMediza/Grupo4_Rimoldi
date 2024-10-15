package com.rimoldi.models.persona;

import lombok.Data;
import lombok.EqualsAndHashCode;
@EqualsAndHashCode(callSuper = true)

@Data
public class Propietario extends Persona {
    private int cbu;
}
