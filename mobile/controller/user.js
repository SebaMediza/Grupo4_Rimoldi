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