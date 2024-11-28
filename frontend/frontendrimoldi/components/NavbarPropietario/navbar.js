"use client";
import React, { useEffect, useState, useRef } from "react";
import Image from "next/image";
import { useRouter } from "next/navigation";
import Link from "next/link";
import styles from "./navbar.module.css";
import 'bootstrap/dist/css/bootstrap.min.css';

export default function Navbar() {
  const router = useRouter();
  const [showModal, setShowModal] = useState(false);
  const [menuOpen, setMenuOpen] = useState(false);
  const navRef = useRef(null);
  const [isMenuOpen, setIsMenuOpen] = useState(false);

  useEffect(() => {
    const token = localStorage.getItem("token");
    if (!token) {
      router.push("/login");
    }
  }, [router]);

  const handleLogout = () => {
    localStorage.removeItem("token");
    router.push("/login");
  };

  const closeMenu = () => {
    setMenuOpen(false);
  };

  const toggleMenu = () => {
    setIsMenuOpen(!isMenuOpen);
  };
  useEffect(() => {
    const handleClickOutside = (event) => {
      if (navRef.current && !navRef.current.contains(event.target)) {
        closeMenu();
      }
    };
    document.addEventListener("mousedown", handleClickOutside);
    return () => {
      document.removeEventListener("mousedown", handleClickOutside);
    };
  }, [navRef]);

  useEffect(() => {
    if (menuOpen) {
      document.body.classList.add(styles.menuOpen);
    } else {
      document.body.classList.remove(styles.menuOpen);
    }
  }, [menuOpen]);

  return (
    <header className={styles.header}>
      <div className={styles.logo}>
        <Link href="/propietario/home">
        <Image src="/assets/img/rimoldi-logo.png" alt="Logo" width={142} height={45} />
        </Link>
      </div>
      <button
        className={styles.hamburger}
        onClick={toggleMenu}
        aria-label="Toggle menu"
      >
        ☰
      </button>
        <nav className={`${styles.nav} ${isMenuOpen ? styles.navOpen : ""}`}>
          <Link href="/propietario/verEstadoContrato" className={styles.navItem}>Ver estado de contrato</Link>
          <button
            onClick={() => {
              setShowModal(true);
              closeMenu();
            }}
            className={`${styles.navItem} ${styles.logoutButton}`}
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
