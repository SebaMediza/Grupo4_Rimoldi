package com.rimoldi.interfaces;

import com.rimoldi.models.contrato.Contrato;

public interface iContrato {
    public boolean getContrato();
    public boolean insertarContrato(Contrato contrato);
    public boolean insertarFirma(int idContrato, int idGarante);
    public boolean insertarEstado(int nro_contrato);
    public boolean putContrato();
    public boolean deleteContrato();
}
