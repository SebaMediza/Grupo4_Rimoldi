package com.rimoldi.interfaces;

public interface iEstadoContrato {
    public boolean getEstadoContrato(int nro_contrato);
    public static boolean postEstadoContrato(int idContrato) {
        // Provide a default implementation or throw an exception
        throw new UnsupportedOperationException("Not implemented yet");
    }
    public boolean putEstadoContrato();
    public boolean deleteEstadoContrato();
}
