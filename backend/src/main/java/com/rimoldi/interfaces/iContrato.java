package com.rimoldi.interfaces;

import com.rimoldi.models.contrato.Contrato;

public interface iContrato {
    public boolean getContrato();
    public static boolean postContrato(Contrato contrato){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public boolean putContrato();
    public boolean deleteContrato();
}
