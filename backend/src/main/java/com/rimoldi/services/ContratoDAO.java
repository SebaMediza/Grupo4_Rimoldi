package com.rimoldi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;
import com.rimoldi.interfaces.iContrato;
import com.rimoldi.models.contrato.Contrato;
import java.text.SimpleDateFormat;

public class ContratoDAO implements iContrato {
    private final Logger logger = LoggerFactory.getLogger(ContratoDAO.class);

    @Override
    public boolean getContrato() {
        throw new UnsupportedOperationException("Unimplemented method 'getContrato'");
    }

    public boolean postContrato(Contrato contrato, String garantes) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try (Connection conn = SqL2ODAO.getCon().open()) {
            conn.createQuery("CALL crear_contrato(:nro_contrato, :fechaInicio, :fechaFin, :fechaCancelacion, :idPropiedad, :idPersona, :idMartillero, :garantes)")
                .addParameter("nro_contrato", contrato.getId())
                .addParameter("fechaInicio", formatter.format(contrato.getFechaInicio())) // Asume que el formato de fecha es compatible con MySQL
                .addParameter("fechaFin", formatter.format(contrato.getFechaFin()))
                .addParameter("fechaCancelacion", contrato.getFechaCancelacion() != null ? formatter.format(contrato.getFechaCancelacion()) : null)
                .addParameter("idPropiedad", contrato.getIdPropiedad())
                .addParameter("idPersona", contrato.getIdInquilino())
                .addParameter("idMartillero", contrato.getIdMartillero())
                .addParameter("garantes", garantes) // Pasar el JSON con los garantes
                .executeUpdate();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean putContrato() {
        throw new UnsupportedOperationException("Unimplemented method 'putContrato'");
    }

    @Override
    public boolean deleteContrato() {
        throw new UnsupportedOperationException("Unimplemented method 'deleteContrato'");
    }
}
