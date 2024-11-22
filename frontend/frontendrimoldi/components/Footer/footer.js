import styles from "./footer.module.css";
export default function footer() {
    const logo = "/assets/img/rimoldi-logo.png";
    const facebook = "/assets/img/facebook.png";
    const instagram = "/assets/img/instagram.png";
    const whatsapp = "/assets/img/whatsapp.png";
    return (<footer className={styles.footer}>
        <div className={styles.footerContent}>
            <div><img src={logo} alt="Logo de la Inmobiliaria" /></div>

            <div className={styles.footerSection}>
                <h4>Sobre Nosotros</h4>
                <p>Ofrecemos una amplia variedad de opciones, ya sea que busques casa, departamento, oficina o local. Nos dedicamos a brindarte el mejor servicio para que encuentres tu espacio ideal.</p>
            </div>

            <div className={styles.footerSection}>
                <h4>Síguenos</h4>
                <div className={styles.socialIcons}>
                    <a href="https://facebook.com" target="_blank" rel="noopener noreferrer"><img src={facebook} alt="fb" /></a>
                    <a href="https://whatsapp.com" target="_blank" rel="noopener noreferrer"><img src={instagram} alt="ig" /></a>
                    <a href="https://instagram.com" target="_blank" rel="noopener noreferrer"><img src={whatsapp} alt="wp" /></a>
                </div>
            </div>
        </div>

        <div className={styles.footerBottom}>
            <p>&copy; 2024 Rimoldi Inmobiliaria. Todos los derechos reservados.</p>
        </div>
    </footer>);
}