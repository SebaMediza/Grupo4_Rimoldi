package com.rimoldi.models.persona;

import java.util.Date;
import lombok.Data;

@Data
public abstract class Persona {
    private int id;
    private String nombre;
    private int dni;
    private String email;
    private String celular;
    private Date fechaNacimiento;
}
