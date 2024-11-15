'use client';
import { useState, useEffect } from "react";
import Image from "next/image";
import { useRouter } from "next/navigation";
import styles from "./page.module.css";

const images = [
  { src: "/rimoldiimg1.jpg", alt: "Imagen 1", caption: "Descripción de imagen 1" },
  { src: "/rimoldiimg2.jpg", alt: "Imagen 2", caption: "Descripción de imagen 2" },
  { src: "/rimoldiimg3.jpg", alt: "Imagen 3", caption: "Descripción de imagen 3" },
];

export default function Home() {
  const [currentImage, setCurrentImage] = useState(0);
  const [loading, setLoading] = useState(true);
  const router = useRouter();

  const handleLoginRedirect = () => {
    router.push("/sesion");
  };

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentImage((prev) => (prev + 1) % images.length);
    }, 3000); // Cambia la imagen cada 3 segundos
    return () => clearInterval(interval);
  }, []);


  return (
    <div className={styles.page}>
      <header className={styles.header}>
        <Image src="/rimoldi-logo.png" alt="Logo" width={100} height={40} />
        <nav className={styles.nav}>
          <a href="/">Inicio</a>
          <a href="/about">Nosotros</a>
          <a href="/contact">Contacto</a>
          <button onClick={handleLoginRedirect} className={styles.loginButton}>
            Iniciar sesión
          </button>
        </nav>
      </header>

      <main className={styles.main}>
        <h1>Bienvenidos a Inmobiliaria Rimoldi</h1>

        <div className={styles.imageContainer}>
          {loading ? (
            <div className={styles.loader}>Cargando...</div>
          ) : (
            <Image
              src={images[currentImage].src}
              alt={images[currentImage].alt}
              width={1200}
              height={600}
            />
          )}
          <p className={styles.caption}>{images[currentImage].caption}</p>
        </div>

        <section className={styles.infoSection}>
          <div className={styles.infoBox}>
            <h3>Buenos tratos</h3>
            <p>Rimoldi Inmobiliaria busca generar buenos tratos, en el sentido más integral que se pueda imaginar.</p>
          </div>
          <div className={styles.infoBox}>
            <h3>Excelencia</h3>
            <p>Somos empresa siempre vigente, a la altura de las exigencias del mercado. Te ofrecemos las mejores opciones.</p>
          </div>
          <div className={styles.infoBox}>
            <h3>Respaldo</h3>
            <p>Nuestro apellido ha respaldado el hacer de la inmobiliaria durante más de 30 años de trayectoria.</p>
          </div>
        </section>
      </main>

      <footer className={styles.footer}>
        <p>© 2023 Inmobiliaria Rimoldi. Todos los derechos reservados.</p>
        <nav>
          <a href="/privacy">Privacidad</a>
          <a href="/terms">Términos y condiciones</a>
        </nav>
      </footer>
    </div>
  );
}
