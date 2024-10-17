package com.rimoldi.services;

import org.sql2o.Connection;

import com.rimoldi.interfaces.iContrato;
import com.rimoldi.models.contrato.Contrato;

public class ContratoDAO implements iContrato {

    @Override
    public boolean getContrato() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getContrato'");
    }

    @Override
    public boolean postContrato(Contrato contrato) {
        try (Connection conn = SqL2ODAO.getCon().open()){
            conn.createQuery("INSERT INTO contrato (fechaInicio, fechaFin, fechaCancelacion, idInquilino, idPropiedad, idMartillero) VALUES (:fechaInicio, :fechaFin, :fechaCancelacion, :idInquilino, :idPropiedad, :idMartillero)")
                .addParameter("fechaInicio", contrato.getFechaInicio())
                .addParameter("fechaFin", contrato.getFechaFin())
                .addParameter("fechaCancelacion", contrato.getFechaCancelacion())
                .addParameter("idInquilino", contrato.getIdInquilino())
                .addParameter("idPropiedad", contrato.getIdPropiedad())
                .addParameter("idMartillero", contrato.getIdMartillero())
                .executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean putContrato() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'putContrato'");
    }

    @Override
    public boolean deleteContrato() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteContrato'");
    }
    
    
}
