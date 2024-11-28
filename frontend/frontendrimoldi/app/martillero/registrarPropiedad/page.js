"use client";
import React, { useState } from "react";
import Navbar from "/components/NavbarMartillero/navbar";
import Footer from "/components/Footer/footer";
import RegistrarPropiedad from './registrarpropiedad.js'

const Home = () => {

  return (
    <div>
      <Navbar />
      <RegistrarPropiedad/>
      <Footer />
    </div>
  );
};

export default Home;

