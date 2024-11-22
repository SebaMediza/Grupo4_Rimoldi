package com.rimoldi.models.persona;
import lombok.Data;

@Data
public class Martillero {
    private String email;
    private String password;
    private String token;
   
    public Martillero(){}
}
