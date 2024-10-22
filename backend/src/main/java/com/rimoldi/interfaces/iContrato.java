package com.rimoldi.interfaces;

import com.rimoldi.models.contrato.Contrato;

public interface iContrato {
    public boolean getContrato();
    public boolean postContrato(Contrato contrato, String garantes);
    public boolean putContrato();
    public boolean deleteContrato();
}
