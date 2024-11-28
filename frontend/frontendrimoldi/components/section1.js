'use client';
import { useState, useEffect } from "react";
import Image from "next/image";
import styles from "../app/styles/section1.module.css"; // Asegúrate de que este archivo exista y esté en la ruta correcta

const images = [
  { src: "/rimoldiimg1.jpg", alt: "Imagen 1", caption: "Descripción de imagen 1" },
  { src: "/rimoldiimg2.jpg", alt: "Imagen 2", caption: "Descripción de imagen 2" },
  { src: "/rimoldiimg3.jpg", alt: "Imagen 3", caption: "Descripción de imagen 3" },
];

export default function Section1() {
  const [currentImage, setCurrentImage] = useState(0);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    setLoading(false);
    const interval = setInterval(() => {
      setCurrentImage((prev) => (prev + 1) % images.length);
    }, 3000);
    return () => clearInterval(interval);
  }, []);

  return (
    <main className={styles.main}>
      <h1>Bienvenidos a Inmobiliaria Rimoldi</h1>
      <div className={styles.imageContainer}>
        {loading ? (
          <div className={styles.loader}>Cargando...</div>
        ) : (
          <Image
            src={images[currentImage].src}
            alt={images[currentImage].alt}
            width={1023}
            height={1279}
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
  );
}
