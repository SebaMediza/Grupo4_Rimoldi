import express from 'express';
import { getAllPropiedades, getPropiedadesVenta, getPropiedadesAlquiler, getPropiedadDetalle } from './controller/porpiedad.js';
import { getPropietarios, getInquilinos, getMartillero } from './controller/user.js';
import { contrato } from './controller/contrato.controller.js';

const app = express();
const port = 3000;

app.use(express.json());

//ENDPOINTS DE PROPIEDADES
app.get('/propiedades', getAllPropiedades);
app.get('/alquiler', getPropiedadesAlquiler);
app.get('/venta', getPropiedadesVenta);
app.get('/detalle', getPropiedadDetalle);

//ENDPOINTS DE USUARIOS
app.get('/propietarios', getPropietarios);
app.get('/inquilino', getInquilinos);
app.get('/martillero', getMartillero);

//ENDPOINTS DE CONTRATO
app.get('/contrato', contrato);

app.listen(port, () => {
    console.log(`API corriendo en http://localhost:${port}`);
});
