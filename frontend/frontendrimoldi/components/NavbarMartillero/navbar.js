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
        <Image src="/assets/img/rimoldi-logo.png" alt="Logo" width={100} height={40} />
      </div>
      <nav ref={navRef} className={`${styles.nav} ${menuOpen ? styles.open : ""}`}>
        <button 
          className={styles.hamburgerButton}
          onClick={() => setMenuOpen(!menuOpen)}
        >
          ☰
        </button>
        <div className={styles.navItems}>
          <Link href="/martillero/registrarPropiedad" className={styles.navItem} onClick={closeMenu}>Añadir propiedad</Link>
          <button
            onClick={() => {
              setShowModal(true);
              closeMenu();
            }}
            className={styles.logoutButton}
          >
            Cerrar Sesión
          </button>
        </div>
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
