'use client';
import Image from "next/image";
import { useRouter } from "next/navigation";
import Link from "next/link";
import styles from "../app/styles/navbar.module.css";

export default function Navbar() {
  const router = useRouter();

  const handleLoginRedirect = () => {
    router.push("/sesion");
  };

  return (
    <header className={styles.header}>
      <div className={styles.logo}>
        <Image src="/rimoldi-logo.png" alt="Logo" width={100} height={40} />
      </div>
      <nav className={styles.nav}>
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
