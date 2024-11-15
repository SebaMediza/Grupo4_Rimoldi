'use client'
import { useState } from 'react'
import { useRouter } from "next/navigation";
import styles from "./page.module.css";

export default function sesion() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const router = useRouter();

  const handleLogin = (e) => {
    e.preventDefault();

    // Simulación de autenticación simple
    if (username === "admin" && password === "admin123") {
      console.log("Usuario logueado");
      // Redirigir a la página principal o dashboard
      router.push("/dashboard");
    } else {
      alert("Usuario no registrado, redirigiendo a página de registro");
      router.push("/register"); // Redirige a la página de registro
    }
  };

  return (
    <div className={styles.page}>
      <main className={styles.main}>
        <h1>Iniciar Sesión</h1>
        <form onSubmit={handleLogin} className={styles.form}>
          <label>Nombre de Usuario:</label>
          <input
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
            className={styles.input}
          />
          <label>Contraseña:</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
            className={styles.input}
          />
          <button type="submit" className={styles.button}>
            Iniciar sesión
          </button>
        </form>
      </main>
    </div>
  );
}
