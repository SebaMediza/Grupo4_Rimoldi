import connection from "../database/database.js";

export const getPropietarios = (req, res) => {
    connection.query('SELECT pr.*, p.* FROM propietario p inner join persona pr on p.idPersona = pr.id', (err, rows) => {
        if (err) {
            console.error('Error en la consulta:', err.stack);
            return;
        }
        res.json(rows);
    });
}

export const getInquilinos = (req, res) => {
    connection.query('SELECT pr.*, p.* FROM inquilino p inner join persona pr on p.idPersona = pr.id', (err, rows) => {
        if (err) {
            console.error('Error en la consulta:', err.stack);
            return;
        }
        res.json(rows);
    });
}

export const getMartillero = (req, res) => {
    //const { id } = req.body;
    connection.query('SELECT pr.*, m.* FROM martillero m inner join persona pr on m.idPersona = pr.id', (err, rows) => {
        if (err) {
            console.error('Error en la consulta:', err.stack);
            return;
        }
        res.json(rows);
    });
}