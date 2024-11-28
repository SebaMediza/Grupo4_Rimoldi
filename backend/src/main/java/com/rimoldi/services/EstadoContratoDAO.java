package com.rimoldi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;
import com.rimoldi.interfaces.iEstadoContrato;
import com.rimoldi.models.contrato.EstadoContrato;

public class EstadoContratoDAO implements iEstadoContrato {
    private final Logger logger = LoggerFactory.getLogger(EstadoContratoDAO.class);
    public EstadoContrato getEstadoContrato(int nro_contrato)  {
        try (Connection conn = SqL2ODAO.getCon().open()) {
            return conn.createQuery("select e.idEstado, p.nombre, p.dni, p.email, p.celular, p.cuil, c.nro_contrato, e.estado from estado e inner join estadocontrato ec on e.idestado = ec.idestado inner join contrato c on ec.nro_contrato = c.nro_contrato inner join inquilino i on c.idInquilino = i.idPersona inner join persona p on i.idpersona = p.id where c.nro_contrato = :nro_contrato;")
                .addParameter("nro_contrato", nro_contrato)
                .executeAndFetchFirst(EstadoContrato.class);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public boolean postEstadoContrato(int nro_contrato) {
        throw new UnsupportedOperationException("Unimplemented method 'postEstadoContrato'");
    }

    public boolean putEstadoContrato() {
        throw new UnsupportedOperationException("Unimplemented method 'putEstadoContrato'");
    }

    public boolean deleteEstadoContrato() {
        throw new UnsupportedOperationException("Unimplemented method 'deleteEstadoContrato'");
    }
}
