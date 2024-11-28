"use client";
import React, { useEffect, useState } from "react";
import { useRouter } from "next/navigation";
import Link from 'next/link';
import Navbar from "/components/NavbarPropietario/navbar";
import Footer from "/components/Footer/footer";
import styles from "./home.module.css";
import Section2 from "./section2";
const Home = () => {


  return (
    <div>
      <Navbar />
      <Section2 />
      <Footer />
    </div>
  );
};

export default Home;
