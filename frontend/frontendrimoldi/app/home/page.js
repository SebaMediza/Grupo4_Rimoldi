"use client";
import React, { useEffect, useState } from "react";
import { useRouter } from "next/navigation";
import Link from 'next/link';
import Navbar from "/components/NavbarLogeado/navbar";
import Footer from "/components/Footer/footer";
import styles from "./home.module.css";

const Home = () => {


  return (
    <div><Navbar />

      <div className={styles.container}>
        <h1>Bienvenido a la página principal</h1>
        <div className={styles.container}>
          
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default Home;
