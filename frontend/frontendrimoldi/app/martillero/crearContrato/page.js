"use client";
import React, { useState } from "react";
import Navbar from "/components/NavbarMartillero/navbar";
import Footer from "/components/Footer/footer";
import CrearContrato from "./crearContrato";


const Home = () => {
    return (
        <div>
            <Navbar />
            <CrearContrato />
            <Footer />
        </div>
    );
};

export default Home;
