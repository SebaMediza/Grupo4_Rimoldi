import React, { useEffect, useState } from 'react';
import Image from 'next/image';
import styles from './propiedades.module.css';
import "bootstrap-icons/font/bootstrap-icons.css";

const Propiedades = () => {
    const [propiedades, setPropiedades] = useState([]);
    const [visibleCount, setVisibleCount] = useState(4); // Mostrar 4 propiedades por vez
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    // Array de imágenes para las propiedades
    const imagenes = [
        '/assets/img/test.jpg',
        '/assets/img/test2.jpg',
        '/assets/img/test3.jpg',
        '/assets/img/test4.jpg',
    ];

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
            setError("Hubo un problema al cargar las propiedades.");
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchPropiedades();
    }, []);

    const cargarMas = () => {
        setVisibleCount((prev) => prev + 4); // Mostrar 4 más al cargar
    };

    return (
        <div className={styles.container}>
            <h1>Propiedades destacadas</h1>
            {loading ? (
                <p>Cargando propiedades...</p>
            ) : error ? (
                <p className={styles.error}>{error}</p>
            ) : (
                <div>
                    <div className={styles.grid}>
                        {propiedades.slice(0, visibleCount).map((propiedad, index) => (
                            <div className={styles.card} key={propiedad.id}>
                                <div className={styles.imageWrapper}>
                                    <Image
                                        src={'/assets/uploads/'+propiedad.imagen} // Asigna imagen cíclicamente
                                        alt={propiedad.direccion}
                                        layout="fill"
                                        objectFit="cover"
                                        className={styles.image}
                                    />
                                    <div className={styles.overlay}>
                                        <i className="fas fa-map-marker-alt"></i> {propiedad.direccion}
                                    </div>
                                </div>
                                <div className={styles.info}>
                                    <p>
                                        <strong>Precio: </strong><i className="bi bi-currency-dollar"></i>{propiedad.alquiler}
                                    </p>
                                    <p>
                                        <strong>Ciudad:</strong><i className="bi bi-geo-alt"></i> {propiedad.cuidad}
                                    </p>
                                </div>
                            </div>
                        ))}
                    </div>
                    {visibleCount < propiedades.length && (
                        <button className={styles.button} onClick={cargarMas}>
                            <i className="bi bi-plus"></i>
                        </button>
                    )}
                </div>
            )}
        </div>
    );
};

export default Propiedades;
