import connection from "../database/database.js"

export const getAllPropiedades = async (req, res) => {
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
    const { tipo } = req.body;
    
    if (tipo === 'comercial') {
        connection.query('select p.*, f.* from propiedad p inner join comercial f on p.idPropiedad = f.idPropiedad;', (err, rows) => {
            if (err) {
                console.error('Error en la consulta:', err.stack);
                return;
            }
            res.json(rows);
        });
    } else {
        connection.query('select p.*, f.* from propiedad p inner join familiar f on p.idPropiedad = f.idPropiedad;', (err, rows) => {
            if (err) {
                console.error('Error en la consulta:', err.stack);
                return;
            }
            res.json(rows);
        });
    }
}