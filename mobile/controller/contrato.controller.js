import connection from "../database/database.js";

export const contrato = (req, res) => {
    let contrato;
    let propiedad;
    let inquilino;
    let martillero;
    connection.query('SELECT * FROM contrato where nro_contrato = ?', [id], (err, rows) => {
        if (err) {
            console.error('Error en la consulta:', err.stack);
            return;
        }
        contrato = rows;
    });
    connection.query(`SELECT p.* FROM propiedad p inner join contrato c on c.${contrato.idPropiedad} = p.idPropiedad`, (err, rows) => {
        if (err) {
            console.error('Error en la consulta:', err.stack);
            return;
        }
        propiedad = rows;
    });

    connection.query(`SELECT p* FROM persona p inner join contrato c on c.${contrato.idPersona} = p.id`, (err, rows) => {
        if (err) {
            console.error('Error en la consulta:', err.stack);
            return;
        }
        inquilino = rows;
    });

    let data = [
        {contrato: contrato},
        {propiedad: propiedad},
        {inquilino: inquilino},
    ]
    res.json(data);
}