"use client";
import React, { useState } from "react";
import Navbar from "/components/NavbarPropietario/navbar";
import Footer from "/components/Footer/footer";
import VerEstadoContrato from './VerEstadoContrato';

const Home = () => {
    return (
        <div>
            <Navbar />
            <VerEstadoContrato />
            <Footer />
        </div>
    );
};

export default Home;
