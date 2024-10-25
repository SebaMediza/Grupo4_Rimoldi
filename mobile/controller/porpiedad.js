import e from "express";
import connection from "../database/database.js"

export const getPropiedades = async (req, res) => {
    connection.query('SELECT * FROM propiedad', (err, rows) => {
        if (err) {
            console.error('Error en la consulta:', err.stack);
            return;
        }
        res.json(rows);
    });
}
export const getPropiedadesAlquiler = async (req, res) => {
    connection.query('SELECT * FROM propiedad WHERE isAlquiler = 1', (err, rows) => {
        if (err) {
            console.error('Error en la consulta:', err.stack);
            return;
        }
        res.json(rows);
    });
}
export const getPropiedadesVenta = async (req, res) => {
    connection.query('SELECT * FROM propiedad WHERE isAlquiler = 0', (err, rows) => {
        if (err) {
            console.error('Error en la consulta:', err.stack);
            return;
        }
        res.json(rows);
    });
}

export const getPropiedadDetalle = async (req, res) => {
    const { id } = req.body;
    connection.query('select p.*, f.* from propiedad p inner join familiar f on p.idPropiedad = f.idPropiedad;', (err, rows) => {
        if (err) {
            console.error('Error en la consulta:', err.stack);
            return;
        }
        res.json(rows);
    });
}