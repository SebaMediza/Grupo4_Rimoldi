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

    @Override
    public boolean insertarContrato(Contrato contrato) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try (Connection conn = SqL2ODAO.getCon().open()) {
            conn.createQuery(
                    "INSERT INTO contrato (nro_contrato, fecha_inicio, fecha_fin, idPropiedad, idPersona, idMartillero) VALUES (:nro_contrato, :fechaInicio, :fechaFin, :idPropiedad, :idPersona, :idMartillero)")
                    .addParameter("nro_contrato", contrato.getId())
                    .addParameter("fechaInicio", formatter.format(contrato.getFechaInicio())) 
                    .addParameter("fechaFin", formatter.format(contrato.getFechaFin()))
                    .addParameter("idPropiedad", contrato.getIdPropiedad())
                    .addParameter("idPersona", contrato.getIdInquilino())
                    .addParameter("idMartillero", contrato.getIdMartillero())
                    .executeUpdate();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean insertarFirma(int idContrato, int idGarante) {
        try (Connection conn = SqL2ODAO.getCon().open()) {
            conn.createQuery("INSERT INTO firma (idGarante, nro_contrato) VALUES (:idGarante, :nro_contrato)")
                    .addParameter("idGarante", idGarante)
                    .addParameter("nro_contrato", idContrato)
                    .executeUpdate();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean insertarEstado(int nro_contrato) {
        try (Connection conn = SqL2ODAO.getCon().open()) {
            conn.createQuery("INSERT INTO estadocontrato (idEstado, nro_contrato) VALUES (1, :nro_contrato);")
                    .addParameter("nro_contrato", nro_contrato)
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
