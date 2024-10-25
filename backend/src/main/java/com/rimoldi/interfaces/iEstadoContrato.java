package com.rimoldi.interfaces;

import com.rimoldi.models.contrato.EstadoContrato;

public interface iEstadoContrato {
    public EstadoContrato getEstadoContrato(int nro_contrato);
    public boolean postEstadoContrato(int nro_contrato);
    public boolean putEstadoContrato();
    public boolean deleteEstadoContrato();
}
