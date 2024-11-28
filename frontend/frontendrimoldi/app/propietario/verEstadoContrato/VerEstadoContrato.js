"use client";
import React, { useState } from "react";
import styles from "./verEstadoContrato.module.css";

export default function VerEstadoContrato () {
    const [numeroContrato, setNumeroContrato] = useState("");
    const [estadoContrato, setEstadoContrato] = useState(null);
    const [error, setError] = useState("");

    const fetchEstadoContrato = async () => {
        setError("");
        setEstadoContrato(null);

        try {
            const response = await fetch(`http://localhost:4567/contrato/${numeroContrato}`, {
                method: "GET"
            });
            if (!response.ok) {
                throw new Error("Contrato no encontrado");
            }

            const data = await response.json();
            setEstadoContrato(data);
        } catch (err) {
            setError(err.message || "Error al obtener el estado del contrato");
        }
    };
    return (
        <div>
                <div className={styles.contenedorContrato}>
                    <h1 className={styles.tituloContrato}>Estado del Contrato</h1>
                    <div className={styles.inputContainer}>
                        <input
                            type="text"
                            placeholder="Número de contrato..."
                            value={numeroContrato}
                            onChange={(e) => setNumeroContrato(e.target.value)}
                            className={styles.inputContrato}
                        />
                        <button
                            onClick={fetchEstadoContrato}
                            className={styles.botonBuscar}
                        >
                            Buscar contrato
                        </button>
                    </div>

                    {error && <p className={styles.errorMessage} >{error}</p>}

                    {estadoContrato && (
                        <table className={styles.tableContrato}>
                            <thead>
                                <tr>
                                    <th>Contrato N°</th>
                                    <th>{numeroContrato}</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>Estado</td>
                                    <td>{estadoContrato.estado}</td>
                                </tr>
                                <tr>
                                    <td>Inquilino</td>
                                    <td>{estadoContrato.nombre}</td>
                                </tr>
                                <tr>
                                    <td>DNI</td>
                                    <td>{estadoContrato.dni}</td>
                                </tr>
                                <tr>
                                    <td>Email</td>
                                    <td>{estadoContrato.email}</td>
                                </tr>
                                <tr>
                                    <td>Teléfono</td>
                                    <td>{estadoContrato.celular}</td>
                                </tr>
                                <tr>
                                    <td>CUIL</td>
                                    <td>{estadoContrato.cuil}</td>
                                </tr>
                            </tbody>
                        </table>
                    )}
                </div>
        </div>
    );
};

