package com.rimoldi.services;

import org.sql2o.Connection;
import com.rimoldi.Main;
import com.rimoldi.interfaces.iEstadoContrato;
import com.rimoldi.models.contrato.EstadoContrato;

public class EstadoContratoDAO implements iEstadoContrato {
    public static EstadoContrato getEstadoContrato(int nro_contrato)  {
        try (Connection conn = SqL2ODAO.getCon().open()) {
            EstadoContrato estado = conn.createQuery("select e.idEstado, p.nombre, p.dni, p.email, p.celular, p.cuil, c.nro_contrato, e.estado from estado e inner join estadocontrato ec on e.idestado = ec.idestado inner join contrato c on ec.nro_contrato = c.nro_contrato inner join inquilino i on c.idPersona = i.idPersona inner join persona p on i.idpersona = p.id where c.nro_contrato = :nro_contrato;")
                .addParameter("nro_contrato", nro_contrato)
                .executeAndFetchFirst(EstadoContrato.class);
            return estado;
        } catch (Exception e) {
            Main.logger.error(e.getMessage());
            return null;
        }
    }

    public static boolean postEstadoContrato(int nro_contrato) {
        try (Connection conn = SqL2ODAO.getCon().open()){
            conn.createQuery("INSERT INTO estadocontrato (nro_contrato, idEstado) VALUES (:nro_contrato, :idEstado)")
                .addParameter("nro_contrato", nro_contrato)
                .addParameter("idEstado", 1)
                .executeUpdate();
            return true;
        } catch (Exception e) {
            Main.logger.error(e.getMessage());
            return false;
        }
        
    }

    public boolean putEstadoContrato() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'putEstadoContrato'");
    }

    public boolean deleteEstadoContrato() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteEstadoContrato'");
    }
}
