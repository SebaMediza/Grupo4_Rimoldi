'use client';
import { useState, useEffect } from "react";
import Image from "next/image";
import { useRouter } from "next/navigation";
import styles from "./globals.css";
import Section1 from "./index/section1";
import Navbar from "../components/Navbar/navbar";
import Footer from "../components/Footer/footer";

export default function Home() {
  return (
    <div className={styles.page}>
      <Navbar />
      <Section1 />
      <Footer />
    </div>
  );
}
