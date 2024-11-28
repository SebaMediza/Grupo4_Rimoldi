"use client";
import React, { useState } from "react";
import styles from "./page.module.css";
import Image from "next/image";
import "bootstrap-icons/font/bootstrap-icons.css";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError("");

    try {
      const response = await fetch("http://localhost:4567/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ email, password }),
      });

      const data = await response.json();
      console.log(data);
      if (response.ok) {
        localStorage.setItem("token", data.token);
        if (data.rol === "propietario") {
          window.location.href = "/propietario/home";
        } else if (data.rol === "martillero") {
          window.location.href = "/martillero/home";
        } else {
          setError("Rol no reconocido");
        }
      } else {
        setError(data.message || "Email o contraseña incorrectos, intente de nuevo");
      }
      setLoading(false);
    } catch (err) {
      console.log(err);
      setError("Error de conexión");
      setLoading(false);
    }
  };

  return (
    <div className={styles.container}>
      <div className={styles.wrapper}>
        {/* Imagen grande para pantallas completas */}
        <div className={styles.illustration}> 
          <Image
            src="/assets/img/rimoldi-fondo.png"
            alt="Imagen grande"
            width={500}
            height={500}
            priority
            className={`${styles.fullImage} ${styles.imageHiddenMobile}`} // Oculta en móviles
          />
          {/* Imagen pequeña (logo) para móviles */}
          <Image
            src="/assets/img/rimoldi-fondo3.png"
            alt="Logo pequeño"
            width={142}
            height={45}
            priority
            className={`${styles.mobileLogo}`} // Visible solo en móviles
          />
        </div>

        {/* Línea divisoria */}
        <div className={styles.divider}></div>

        {/* Formulario de inicio de sesión */}
        <div className={styles.loginCard}>
          <h2 className={styles.title}>Iniciar Sesión</h2>
          <form onSubmit={handleSubmit} className={styles.form}>
            <input
              className={styles.input}
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
              placeholder="Ingrese su email"
            />

            <input
              className={styles.input}
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
              placeholder="Ingrese su contraseña"
            />

            {error && <p className={styles.errorMessage}>{error}</p>}
            <button type="submit" className={styles.button} disabled={loading}>
              {loading ? "Cargando..." : "Iniciar Sesión"}
              <i className="bi bi-box-arrow-in-right"></i>
            </button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default Login;
