package com.rimoldi.services;

import org.sql2o.Connection;

import com.rimoldi.Main;
import com.rimoldi.interfaces.iContrato;
import com.rimoldi.models.contrato.Contrato;
import java.text.SimpleDateFormat;

public class ContratoDAO implements iContrato {

    @Override
    public boolean getContrato() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getContrato'");
    }

    public static boolean postContrato(Contrato contrato) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try (Connection conn = SqL2ODAO.getCon().open()) {
            conn.createQuery(
                    "INSERT INTO contrato (nro_contrato, fecha_inicio, fecha_fin, fecha_cancelacion, idPropiedad, idPersona, idMartillero) VALUES (:nro_contrato, :fechaInicio, :fechaFin, :fechaCancelacion, :idPropiedad, :idPersona, :idMartillero)")
                    .addParameter("nro_contrato", contrato.getId())
                    .addParameter("fechaInicio", formatter.format(contrato.getFechaInicio()))
                    .addParameter("fechaFin", formatter.format(contrato.getFechaFin()))
                    .addParameter("fechaCancelacion", contrato.getFechaCancelacion())
                    .addParameter("idPropiedad", contrato.getIdPropiedad())
                    .addParameter("idPersona", contrato.getIdInquilino())
                    .addParameter("idMartillero", contrato.getIdMartillero())
                    .executeUpdate();
        } catch (Exception e) {
            Main.logger.error("Error al crear el contrato: " + e.getMessage());
            return false;
        }
        return true;
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
