"use client";
import React, { useEffect, useState } from "react";
import Image from "next/image";
import { useRouter } from "next/navigation";
import Link from "next/link";
import styles from "./navbar.module.css";

export default function Navbar() {
  const router = useRouter();
  const [showModal, setShowModal] = useState(false); // El estado showModal controla si el modal está visible o no.

  useEffect(() => {
    const token = localStorage.getItem("token");
    if (!token) {
      router.push("/login"); // Redirigir si no hay token
    }
  }, [router]);

  const handleLogout = () => {
    localStorage.removeItem("token"); // Eliminar el token
    router.push("/login"); // Redirigir al login
  };

  return (

    <header className={styles.header}>
      <div className={styles.logo}>
        <Image src="/assets/img/rimoldi-logo.png" alt="Logo" width={100} height={40} />
      </div>
      <nav className={styles.nav}>
        <Link href="/registrarPropiedad" className={styles.navItem}>Añadir propiedad</Link>
        <Link href="/nosotros" className={styles.navItem}>Nosotros</Link>
        <Link href="/contact" className={styles.navItem}>Contacto</Link>
        <button
          onClick={() => setShowModal(true)}
          className={styles.logoutButton}
        >
          Cerrar Sesión
        </button>

        {showModal && (
          <div className={styles.modalOverlay}>
            <div className={styles.modalContent}>
              <h2>¿Estás seguro de cerrar sesión?</h2>
              <div className={styles.modalButtons}>
                <button
                  onClick={() => setShowModal(false)}
                  className={styles.cancelButton}
                >
                  Cancelar
                </button>
                <button
                  onClick={handleLogout}
                  className={styles.confirmButton}
                >
                  Cerrar Sesión
                </button>
              </div>
            </div>
          </div>
        )}
      </nav>
    </header>

  );
}
