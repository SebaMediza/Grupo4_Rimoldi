"use client";
import React, { useState } from "react";
import styles from "./crearContrato.module.css";

export default function CrearContrato() {
    const [formData, setFormData] = useState({
        nroContrato: "",
        fechaInicio: "",
        fechaFin: "",
        propiedad: "",
        inquilino: "",
        garantes: "",
        martillero: "",
    });

    const [mensaje, setMensaje] = useState("");
    const [contratoDetails, setContratoDetails] = useState(null);
    const [usernameMartillero, setUsernameMartillero] = useState("");
    const [emailGarante, setEmailGarante] = useState("");
    const [usernameInquilino, setUsernameInquilino] = useState("");
    const [direccion, setDireccion] = useState("");

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };
    const validarPropiedad = async (direccion) => {
        try {
            const rta = await fetch("http://localhost:4567/idPropiedad", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ direccion: direccion }),
            });

            if (rta.ok) {
                const data = await rta.json();
                return data.idPropiedad;
            } else {
                return null;
            }
        } catch (err) {
            alert("Error al verificar la Propiedad.");
            return null;
        }
    };
    const validarMartillero = async (usernameMartillero) => {
        try {
            const rta = await fetch("http://localhost:4567/idMartillero", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ username: usernameMartillero }),
            });

            if (rta.ok) {
                const data = await rta.json();
                return data.idMartillero;
            } else {
                return null;
            }
        } catch (err) {
            alert("Error al verificar el Martillero.");
            return null;
        }
    };
    const validarGarante = async (emailGarante) => {
        try {
            const rta = await fetch("http://localhost:4567/idGarante", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ email: emailGarante }),
            });

            if (rta.ok) {
                const data = await rta.json();
                return data.idGarante;
            } else {
                return null;
            }
        } catch (err) {
            alert("Error al verificar el Garante.");
            return null;
        }
    };
    const validarInquilino = async (usernameInquilino) => {
        try {
            const rta = await fetch("http://localhost:4567/idInquilino", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ username: usernameInquilino }),
            });

            if (rta.ok) {
                const data = await rta.json();
                return data.idInquilino;
            } else {
                return null;
            }
        } catch (err) {
            alert("Error al verificar el Inquilino.");
            return null;
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const martilleroValido = await validarMartillero(usernameMartillero);
        if (!martilleroValido) {
            alert("Error: El martillero no existe.");
            return;
        }
        const garanteValido = await validarGarante(emailGarante);
        if (!garanteValido) {
            alert("Error: El garante no existe.");
            return;
        }
        const propiedadValido = await validarPropiedad(direccion);
        if (!propiedadValido) {
            alert("Error: La propiedad no existe.");
            return;
        }
        const inquilinoValido = await validarInquilino(usernameInquilino);
        if (!inquilinoValido) {
            alert("Error: El inquilino no existe.");
            return;
        }
        if (!formData.nroContrato || !formData.fechaInicio || !formData.fechaFin) {
            setMensaje("Por favor, completa todos los campos.");
            return;
        }

        try {
            const response = await fetch("http://localhost:4567/contrato", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    nro_contrato: formData.nroContrato,
                    fecha_inicio: formData.fechaInicio,
                    fecha_fin: formData.fechaFin,
                    fecha_cancelacion: null,
                    idPropiedad: propiedadValido,
                    idInquilino: inquilinoValido,
                    idMartillero: martilleroValido,
                    idGarante: garanteValido,
                }),
            });

            const data = await response.json();

            if (response.ok) {
                setMensaje("Contrato creado exitosamente.");
                getContratoDetails(formData.nroContrato);
            } else {
                setMensaje(data.error || "Error al crear el contrato.");
            }
        } catch (error) {
            setMensaje("Error en la conexión con el servidor.");
        }
    };

    const getContratoDetails = async (nroContrato) => {
        try {
            const response = await fetch(`http://localhost:4567/obtenerDatos/${nroContrato}`);
            const data = await response.json();
            if (response.ok) {
                setContratoDetails(data);
            } else {
                setMensaje(data.error || "Error al obtener los detalles del contrato.");
            }
        } catch (error) {
            setMensaje("Error al obtener los detalles del contrato.");
        }
    };

    return (
        <div className={styles.container}>
            <h1 className={styles.h1}>Crear Contrato</h1>

            <form onSubmit={handleSubmit} className={styles.form}>
                {/* Nro Contrato - Toda la fila */}
                <div>
                    <input
                        className={`${styles.input} ${styles.fullWidth}`}
                        type="text"
                        name="nroContrato"
                        value={formData.nroContrato}
                        onChange={handleChange}
                        placeholder="Nro Contrato"
                    />
                </div>

                {/* Fechas en el mismo nivel */}
                <div className={styles.row}>
                    <div className={styles.column}>
                        <label className={styles.label}>Fecha Inicio:</label>
                        <input
                            className={styles.input}
                            type="date"
                            name="fechaInicio"
                            value={formData.fechaInicio}
                            onChange={handleChange}
                        />
                    </div>
                    <div className={styles.column}>
                        <label className={styles.label}>Fecha Fin:</label>
                        <input
                            className={styles.input}
                            type="date"
                            name="fechaFin"
                            value={formData.fechaFin}
                            onChange={handleChange}
                        />
                    </div>
                </div>

                {/* Resto de los campos */}
                <div>
                    <input
                        className={styles.input}
                        type="text"
                        name="propiedad"
                        placeholder="Direccion de la propiedad"
                        value={direccion}
                        onChange={(e) => setDireccion(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <input
                        className={styles.input}
                        type="text"
                        name="inquilino"
                        placeholder="Usuario del inquilino"
                        value={usernameInquilino}
                        onChange={(e) => setUsernameInquilino(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <input
                        className={styles.input}
                        type="text"
                        name="garantes"
                        placeholder="Email del Garante"
                        value={emailGarante}
                        onChange={(e) => setEmailGarante(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <input
                        className={styles.input}
                        type="text"
                        name="martillero"
                        placeholder="Usuario del martillero"
                        value={usernameMartillero}
                        onChange={(e) => setUsernameMartillero(e.target.value)}
                        required
                    />
                </div>

                <div>{mensaje && <p className={styles.errorMessage}>{mensaje}</p>}</div>

                <button type="submit" className={styles.button}>Crear Contrato</button>
            </form>

            {contratoDetails && (
    <div className={styles.containerDetalles}>
        <h2>Detalles del Contrato</h2>

        {/* Contenedor de las tarjetas */}
        <div className={styles.detallesTarjetas}>
            {/* Tarjeta Contrato */}
            <div className={styles.tarjeta}>
                <h3>Contrato</h3>
                <p><strong>Número de Contrato:</strong> {contratoDetails.contrato.nro_contrato}</p>
                <p><strong>Fecha de Inicio:</strong> {contratoDetails.contrato.fecha_inicio}</p>
                <p><strong>Fecha de Fin:</strong> {contratoDetails.contrato.fecha_fin}</p>
            </div>

            {/* Tarjeta Propiedad */}
            <div className={styles.tarjeta}>
                <h3>Propiedad</h3>
                <p><strong>Dirección:</strong> {contratoDetails.propiedad.direccion}</p>
                <p><strong>Ciudad:</strong> {contratoDetails.propiedad.cuidad}</p>
                <p><strong>Metros Cubiertos:</strong> {contratoDetails.propiedad.m2_cubiertos} m²</p>
                <p><strong>Metros Descubiertos:</strong> {contratoDetails.propiedad.m2_descubiertos} m²</p>
                <p><strong>Alquiler:</strong> ${contratoDetails.propiedad.alquiler}</p>
            </div>

            {/* Tarjeta Inquilino */}
            <div className={styles.tarjeta}>
                <h3>Inquilino</h3>
                <p><strong>Nombre:</strong> {contratoDetails.inquilino.nombre}</p>
                <p><strong>DNI:</strong> {contratoDetails.inquilino.dni}</p>
                <p><strong>CUIL:</strong> {contratoDetails.inquilino.cuil}</p>
                <p><strong>Email:</strong> {contratoDetails.inquilino.email}</p>
            </div>

            {/* Tarjeta Martillero */}
            <div className={styles.tarjeta}>
                <h3>Martillero</h3>
                <p><strong>Nombre:</strong> {contratoDetails.martillero.nombre}</p>
                <p><strong>DNI:</strong> {contratoDetails.martillero.dni}</p>
                <p><strong>CUIL:</strong> {contratoDetails.martillero.cuil}</p>
                <p><strong>Email:</strong> {contratoDetails.martillero.email}</p>
            </div>

            {/* Tarjeta Garante */}
            <div className={styles.tarjeta}>
                <h3>Garante</h3>
                <p><strong>Nombre:</strong> {contratoDetails.garante.nombre}</p>
                <p><strong>DNI:</strong> {contratoDetails.garante.dni}</p>
                <p><strong>CUIL:</strong> {contratoDetails.garante.cuil}</p>
                <p><strong>Email:</strong> {contratoDetails.garante.email}</p>
            </div>
        </div>
    </div>
)}


        </div>
    );
};
