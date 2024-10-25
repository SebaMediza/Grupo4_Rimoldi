import connection from "../database/database.js";
import {promisify} from 'util'

export const contrato = async (req, res) => {
    const { id } = req.body;
    const query = promisify(connection.query).bind(connection);
    let contrato;
    let propiedad;
    let inquilino;
    let martillero;
    try {
        // Primera consulta
        const contratoRows = await query('SELECT * FROM contrato where nro_contrato = ?', [id]);
        contrato = contratoRows[0]; // Obtener el primer resultado
    
        // Segunda consulta
        const propiedadRows = await query('SELECT p.* FROM propiedad p INNER JOIN contrato c ON c.idPropiedad = p.idPropiedad WHERE c.idPropiedad = ?', [contrato.idPropiedad]);
        propiedad = propiedadRows[0];

        // Tercera consulta
        const inquilinoRows = await query('SELECT p.* FROM persona p INNER JOIN contrato c ON c.idPersona = p.id WHERE p.id = ?', [contrato.idPersona]);
        inquilino = inquilinoRows[0];

        // Cuarta consulta
        const martilleroRows = await query('SELECT p.* FROM persona p INNER JOIN contrato c ON c.idMartillero = p.id WHERE p.id = ?', [contrato.idMartillero]);
        martillero = martilleroRows[0];
    
        // Enviar la respuesta
        res.json({
            contrato,
            propiedad,
            inquilino,
            martillero
        });
        
    } catch (err) {
        console.error('Error en la consulta:', err.stack);
        res.status(500).json({ error: 'Error en la consulta' });
    }
}