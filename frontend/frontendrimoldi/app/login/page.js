"use client";
import React, { useState } from "react";
import styles from "./page.module.css";
import Footer from "/components/Footer/footer";

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
      if (response.ok) {
        // Si el login es exitoso, guarda el token en localStorage y redirige
        localStorage.setItem("token", data.token);
        window.location.href = "/home";
      } else {
        // Mostrar error si las credenciales son incorrectas
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
      <div className={styles.page}>
        <h2 className={styles.titulo}>Iniciar Sesión</h2>
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
          </button>
        </form>
      </div>
      <Footer />
    </div>
  );
};

export default Login;

