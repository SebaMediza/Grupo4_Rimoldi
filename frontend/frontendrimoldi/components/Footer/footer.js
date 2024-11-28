import styles from "./footer.module.css";

export default function Footer() {
    const logo = "/assets/img/rimoldi-logo.png";
    const facebook = "/assets/img/facebook.png";
    const instagram = "/assets/img/instagram.png";
    const whatsapp = "/assets/img/whatsapp.png";

    return (
        <footer className={styles.footer}>
            <div className={styles.container}>
                <div className={styles.left}>
                    <img src={logo} alt="Logo Rimoldi" className={styles.logo} />
                    <p>Rimoldi Inmobiliaria - General Pico, La Pampa</p>
                </div>

                <div className={styles.right}>
                    <h4>Contacta</h4>
                    <p>Calle 15 Número 1124, General Pico, La Pampa</p>
                    <p>Teléfono: +54 2302 426429</p>
                    <p>Email: contacto@rimoldi.com.ar</p>
                    <p>WhatsApp: 2302 418397</p>

                    <div className={styles.socialIcons}>
                        <a href="https://facebook.com" target="_blank" rel="noopener noreferrer">
                            <img src={facebook} alt="Facebook" />
                        </a>
                        <a href="https://instagram.com" target="_blank" rel="noopener noreferrer">
                            <img src={instagram} alt="Instagram" />
                        </a>
                        <a href="https://whatsapp.com" target="_blank" rel="noopener noreferrer">
                            <img src={whatsapp} alt="WhatsApp" />
                        </a>
                    </div>
                </div>
            </div>
            <div className={styles.footerBottom}>
                <p>&copy; 2024 Rimoldi Inmobiliaria. Todos los derechos reservados.</p>
            </div>
        </footer>
    );
}
