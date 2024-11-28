package com.rimoldi.models.persona;

import lombok.Data;

@Data
public class Propietario{
    private String email;
    private String password;
    private String token;
    private String nombre;
   
    public Propietario(){}
}
