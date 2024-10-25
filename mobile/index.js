import express from 'express';
import { getPropiedades, getPropiedadesVenta, getPropiedadesAlquiler, getPropiedadDetalle } from './controller/porpiedad.js';
import { getPropietarios } from './controller/user.js';

const app = express();
const port = 3000;

app.use(express.json());
app.get('/propiedades', getPropiedades);
app.get('/alquiler', getPropiedadesAlquiler);
app.get('/venta', getPropiedadesVenta);
app.get('/propietarios', getPropietarios);
app.get('/familiar', getPropiedadDetalle);

app.listen(port, () => {
    console.log(`API corriendo en http://localhost:${port}`);
});
