'use client';
import { useState, useEffect } from "react";
import Image from "next/image";
import { useRouter } from "next/navigation";
import styles from "./page.module.css";

const carouselImages = [
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

  // Cambia la imagen automáticamente cada 3 segundos
  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentImage((prev) => (prev + 1) % carouselImages.length);
    }, 3000);
    return () => clearInterval(interval);
  }, []);

  const handleImageLoad = () => {
    setLoading(false);  // Desactiva el estado de carga una vez que la imagen se carga
  };

  return (
    <div className={styles.page}>
      {/* Menú */}
      <header className={styles.header}>
        <Image src="/logo.png" alt="Logo" width={100} height={40} />
        <nav className={styles.nav}>
          <a href="/">Inicio</a>
          <a href="/about">Nosotros</a>
          <a href="/contact">Contacto</a>
          <button onClick={handleLoginRedirect} className={styles.loginButton}>
            Iniciar sesión
          </button>
        </nav>
      </header>

      {/* Título y Carrusel */}
      <main className={styles.main}>
        <h1>Bienvenidos a Inmobiliaria Rimoldi</h1>
        <div className={styles.carousel}>
          {loading ? (
            <div className={styles.loader}>Cargando...</div> 
          ) : (
            <Image
              src={carouselImages[currentImage].src}
              alt={carouselImages[currentImage].alt}
              width={1200}
              height={600}
              onLoadingComplete={handleImageLoad} 
            />
          )}
          <p className={styles.caption}>{carouselImages[currentImage].caption}</p>
        </div>

        {/* Información de la inmobiliaria */}
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

      {/* Footer */}
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
