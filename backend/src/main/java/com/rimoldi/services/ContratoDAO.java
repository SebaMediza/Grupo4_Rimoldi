package com.rimoldi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;

import com.google.gson.JsonObject;
import com.rimoldi.interfaces.iContrato;
import com.rimoldi.models.contrato.Contrato;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

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
                    "INSERT INTO contrato (nro_contrato, fecha_inicio, fecha_fin, idPropiedad, idInquilino, idMartillero) VALUES (:nro_contrato, :fecha_inicio, :fecha_fin, :idPropiedad, :idInquilino, :idMartillero)")
                    .addParameter("nro_contrato", contrato.getNro_contrato())
                    .addParameter("fecha_inicio", formatter.format(contrato.getFecha_inicio()))
                    .addParameter("fecha_fin", formatter.format(contrato.getFecha_fin()))
                    .addParameter("idPropiedad", contrato.getIdPropiedad())
                    .addParameter("idInquilino", contrato.getIdInquilino())
                    .addParameter("idMartillero", contrato.getIdMartillero())
                    .executeUpdate();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean insertarFirma(int nro_contrato, int idGarante) {
        try (Connection conn = SqL2ODAO.getCon().open()) {
            conn.createQuery("INSERT INTO firma (nro_contrato, idGarante) VALUES (:nro_contrato, :idGarante)")
                    .addParameter("nro_contrato", nro_contrato)
                    .addParameter("idGarante", idGarante)
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

    public JsonObject datosDelContrato(int nro_contrato) {
        JsonObject contratoJson = new JsonObject();

        try (Connection conn = SqL2ODAO.getCon().open()) {
            // Ejecuta la consulta y obtiene los resultados
            List<Map<String, Object>> resultados = conn.createQuery(
                    "SELECT p.direccion AS propiedad_direccion, p.alquiler AS propiedad_alquiler, " +
                            "p.m2_cubiertos AS propiedad_m2_cubiertos, p.m2_descubiertos AS propiedad_m2_descubiertos, "
                            +
                            "p.condiciones_garantes AS propiedad_condiciones_garantes, p.expensas AS propiedad_expensas, "
                            +
                            "p.gastos AS propiedad_gastos, p.fecha_precio_minimo AS propiedad_fecha_precio_minimo, " +
                            "p.cuidad AS propiedad_cuidad, i.mascotas AS inquilino_mascotas, i.empresa_trabaja AS inquilino_empresa_trabaja, "
                            +
                            "i.cantidad_integrantes AS inquilino_cantidad_integrantes, i.ingresos AS inquilino_ingresos, "
                            +
                            "c.fecha_inicio AS contrato_fecha_inicio, c.fecha_fin AS contrato_fecha_fin, " +
                            "c.nro_contrato AS contrato_nro_contrato, m.nro_matricula AS martillero_nro_matricula, " +
                            "a.nombre AS inquilino_nombre, a.dni AS inquilino_dni, a.email AS inquilino_email, " +
                            "a.celular AS inquilino_celular, a.fecha_nac AS inquilino_fecha_nac, a.username AS inquilino_username, "
                            +
                            "a.cuil AS inquilino_cuil, j.nombre AS martillero_nombre, j.dni AS martillero_dni, " +
                            "j.email AS martillero_email, j.celular AS martillero_celular, j.fecha_nac AS martillero_fecha_nac, "
                            +
                            "j.username AS martillero_username, j.cuil AS martillero_cuil, q.nombre AS garante_nombre, "
                            +
                            "q.dni AS garante_dni, q.email AS garante_email, q.celular AS garante_celular, " +
                            "q.fecha_nac AS garante_fecha_nac, q.username AS garante_username, q.cuil AS garante_cuil, "
                            +
                            "g.ingresos AS garante_ingresos, g.empresa_trabaja AS garante_empresa_trabaja, " +
                            "g.contacto_trabaja AS garante_contacto_trabaja " +
                            "FROM contrato c " +
                            "INNER JOIN propiedad p ON p.idPropiedad = c.idPropiedad " +
                            "INNER JOIN inquilino i ON i.idInquilino = c.idInquilino " +
                            "INNER JOIN martillero m ON m.idMartillero = c.idMartillero " +
                            "INNER JOIN persona a ON a.id = i.idPersona " +
                            "INNER JOIN persona j ON j.id = m.idPersona " +
                            "INNER JOIN firma f ON f.nro_contrato = c.nro_contrato " +
                            "INNER JOIN garante g ON g.idGarante = f.idGarante " +
                            "INNER JOIN persona q ON q.id = g.idPersona " +
                            "WHERE c.nro_contrato = :nro_contrato")
                    .addParameter("nro_contrato", nro_contrato)
                    .executeAndFetchTable()
                    .asList();

            // Si no hay resultados, devolver un JSON vacío
            if (resultados.isEmpty()) {
                return contratoJson;
            }

            // Procesar el primer resultado como un JSON
            Map<String, Object> contrato = resultados.get(0);

            // Agrupar los resultados en objetos JSON
            JsonObject propiedadJson = new JsonObject();
            JsonObject inquilinoJson = new JsonObject();
            JsonObject martilleroJson = new JsonObject();
            JsonObject garanteJson = new JsonObject();
            JsonObject contratoDetailsJson = new JsonObject();

            // Llenar las secciones del JSON
            for (Map.Entry<String, Object> entry : contrato.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();

                if (key.startsWith("propiedad_")) {
                    propiedadJson.addProperty(key.replace("propiedad_", ""), value == null ? null : value.toString());
                } else if (key.startsWith("inquilino_")) {
                    inquilinoJson.addProperty(key.replace("inquilino_", ""), value == null ? null : value.toString());
                } else if (key.startsWith("martillero_")) {
                    martilleroJson.addProperty(key.replace("martillero_", ""), value == null ? null : value.toString());
                } else if (key.startsWith("garante_")) {
                    garanteJson.addProperty(key.replace("garante_", ""), value == null ? null : value.toString());
                } else if (key.startsWith("contrato_")) {
                    contratoDetailsJson.addProperty(key.replace("contrato_", ""),
                            value == null ? null : value.toString());
                }
            }

            // Construir el JSON final
            contratoJson.add("propiedad", propiedadJson);
            contratoJson.add("inquilino", inquilinoJson);
            contratoJson.add("martillero", martilleroJson);
            contratoJson.add("garante", garanteJson);
            contratoJson.add("contrato", contratoDetailsJson);

        } catch (Exception e) {
            logger.error("Error al obtener los datos del contrato: " + e.getMessage());
        }

        return contratoJson;
    }

}
