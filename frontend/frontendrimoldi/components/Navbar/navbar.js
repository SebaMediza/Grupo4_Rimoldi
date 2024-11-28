'use client';
import Image from "next/image";
import { useRouter } from "next/navigation";
import Link from "next/link";
import { useState } from "react";
import styles from "./navbar.module.css";

export default function Navbar() {
  const router = useRouter();
  const [isMenuOpen, setIsMenuOpen] = useState(false);

  const handleLoginRedirect = () => {
    router.push("/login");
  };

  const toggleMenu = () => {
    setIsMenuOpen(!isMenuOpen);
  };

  return (
    <header className={styles.header}>
      <div className={styles.logo}>
        <Link href="/">
          <Image src="/assets/img/rimoldi-logo.png" alt="Logo" width={142} height={45} href="/" />
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
        <Link href="/" className={styles.navItem}>Inicio</Link>
        <Link href="/nosotros" className={styles.navItem}>Nosotros</Link>
        <Link href="/contact" className={styles.navItem}>Contacto</Link>
        <button onClick={handleLoginRedirect} className={`${styles.navItem} ${styles.loginButton}`}>
          Iniciar sesión
        </button>
      </nav>
    </header>
  );
}
