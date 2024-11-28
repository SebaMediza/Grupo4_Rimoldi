package com.rimoldi.interfaces;

import com.rimoldi.models.persona.Propietario;

public interface iPropietario {
    public Propietario getPropietario(String email, String password);
}
