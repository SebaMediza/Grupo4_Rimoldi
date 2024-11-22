import React, { useEffect, useState } from 'react';

const Propiedades = () => {
    const [propiedades, setPropiedades] = useState([]);
    const [loading, setLoading] = useState(true); 

    const fetchPropiedades = async () => {
        try {
            const response = await fetch("http://localhost:4567/propiedad", {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                },
            });

            if (!response.ok) {
                throw new Error(`Error al obtener las propiedades: ${response.statusText}`);
            }

            const data = await response.json();
            setPropiedades(data);
        } catch (error) {
            console.error("Hubo un problema al obtener las propiedades:", error);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchPropiedades();
    }, []);

    return (
        <div>
            <h1>Lista de Propiedades</h1>
            {loading ? (
                <p>Cargando propiedades...</p>
            ) : (
                <div style={{ display: 'flex', flexWrap: 'wrap', gap: '1rem' }}>
                    {propiedades.length > 0 ? (
                        propiedades.map((propiedad) => (
                            <div
                                key={propiedad.id}
                                style={{
                                    border: '1px solid #ccc',
                                    padding: '1rem',
                                    width: '300px',
                                    borderRadius: '8px',
                                }}
                            >
                                <h2>{propiedad.direccion}</h2>
                                <p><strong>Precio:</strong> {propiedad.alquiler}</p>
                                <p><strong>Ciudad:</strong> {propiedad.cuidad}</p>
                            </div>
                        ))
                    ) : (
                        <p>No se encontraron propiedades.</p>
                    )}
                </div>
            )}
        </div>
    );
};

export default Propiedades;
