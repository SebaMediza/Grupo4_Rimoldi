'use client';
import { useState, useEffect } from "react";
import Image from "next/image";
import { useRouter } from "next/navigation";
import styles from "./page.module.css";
import Section1 from "@/components/section1";
import Navbar from "@/components/navbar";
import Footer from "@/components/footer";

export default function Home() {
  return (
    <div className={styles.page}>
      <Navbar />
      <Section1 />
      <Footer />
    </div>
  );
}
