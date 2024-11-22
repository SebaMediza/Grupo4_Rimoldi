'use client';
import Image from "next/image";
import { useRouter } from "next/navigation";
import Link from "next/link";
import styles from "../app/styles/footer.module.css"; 

export default function Footer() {
    <footer className={styles.footer}>
        <p>© 2023 Inmobiliaria Rimoldi. Todos los derechos reservados.</p>
        <nav>
          <a href="/privacy">Privacidad</a>
          <a href="/terms">Términos y condiciones</a>
        </nav>
      </footer>
}