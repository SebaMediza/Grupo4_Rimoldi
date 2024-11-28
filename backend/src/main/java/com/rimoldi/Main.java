package com.rimoldi;

import static spark.Spark.*;
import com.rimoldi.controllers.ContratoController;
import com.rimoldi.controllers.EstadoContratoController;
import com.rimoldi.controllers.GaranteController;
import com.rimoldi.controllers.InquilinoController;
import com.rimoldi.controllers.LoginController;
import com.rimoldi.controllers.MartilleroController;
import com.rimoldi.controllers.PropiedadController;
import com.rimoldi.controllers.PropietarioController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        logger.info("Iniciando servidor...");
        ContratoController contratoController = new ContratoController();
        EstadoContratoController estadoContratoController = new EstadoContratoController();
        LoginController loginController=new LoginController();
        PropiedadController propiedadController = new PropiedadController();
        PropietarioController propietarioController=new PropietarioController();
        MartilleroController martilleroController=new MartilleroController();
        GaranteController garanteController=new GaranteController();
        InquilinoController inquilinoController=new InquilinoController();
        
        before((req, res) -> {
            res.header("Access-Control-Allow-Origin", "*");
            res.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            res.header("Access-Control-Allow-Headers", "Content-Type, Authorization, Content-Length, X-Requested-With");
            res.header("Access-Control-Allow-Credentials", "true");
        });

        options("/*", (req, res) -> {
            res.status(200); // Responder con código HTTP 200 OK
            return "OK";
        });


        post("/contrato", contratoController.postContrato);
        get("/contrato/:nro_contrato", estadoContratoController.getEstadoContrato);
        post("/login", loginController.login);
        post("/propiedad", propiedadController.postPropiedad);
        get("/propiedad", propiedadController.getPropiedades);
        get("/ultimoIdPropiedad",propiedadController.getUltimoIdPropiedad);
        get("/ultimoIdComercial",propiedadController.getUltimoIdComercial);
        get("/ultimoIdFamiliar",propiedadController.getUltimoIdFamiliar);
        post("/idPropietario",propietarioController.getId);
        get("/obtenerDatos/:nro_contrato", contratoController.getDatosDelContrato);
        post("/idMartillero",martilleroController.getId);
        post("/idGarante",garanteController.getId);
        post("/idInquilino",inquilinoController.getId);
        post("/idPropiedad", propiedadController.getId);
        logger.info("Servidor iniciado. Escuchando en el puerto 4567");
    }
}