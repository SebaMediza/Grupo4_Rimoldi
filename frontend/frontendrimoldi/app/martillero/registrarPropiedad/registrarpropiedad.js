"use client";
import React, { useState, useEffect } from "react";
import styles from "./registrarPropiedad.module.css";
export default function RegistrarPropiedad() {
  const initialFormData = {
    // Comunes
    idPropiedad: "",
    direccion: "",
    alquiler: "",
    m2_cubiertos: "",
    m2_descubiertos: "",
    condiciones_garantes: "",
    expensas: "",
    gastos: "",
    cuidad: "",
    idPropietario: "",
    fecha_precio_minimo: "",
    disponible: "",
    imagen: null,
    // Familiar
    idFamiliar: "",
    cant_ambientes: "",
    cant_baños: "",
    cant_autos_cochera: "",
    piscina: false,
    permite_mascotas: false,
    permite_niños: false,
    // Comercial
    idComercial: "",
    permisos_municipales: "",
    baño: false,
    cocina: false,
    vidriera: false,
    deposito: false,
  };
  const [tipoPropiedad, setTipoPropiedad] = useState("familiar");
  const [formData, setFormData] = useState(initialFormData);
  const [ids, setIds] = useState({ propiedad: 0, familiar: 0, comercial: 0 });
  const [username, setUsername] = useState("");

  const fetchIds = async () => {
    const fetchUltimoId = async (url) => {
      try {
        const response = await fetch(url, {
          method: "GET",
        });
        if (!response.ok) {
          throw new Error(`Error al obtener el último ID desde ${url}`);
        }
        const data = await response.json();
        return data.ultimoId;
      } catch (err) {
        alert(`Error al conectar con el servidor (${url}): ${err.message}`);
        return null;
      }
    };


    try {
      const ultimoIdPropiedad = await fetchUltimoId(
        "http://localhost:4567/ultimoIdPropiedad"
      );
      const ultimoIdFamiliar = await fetchUltimoId(
        "http://localhost:4567/ultimoIdFamiliar"
      );
      const ultimoIdComercial = await fetchUltimoId(
        "http://localhost:4567/ultimoIdComercial"
      );

      if (ultimoIdPropiedad !== null && ultimoIdFamiliar !== null && ultimoIdComercial !== null) {
        setIds({
          propiedad: ultimoIdPropiedad + 1,
          familiar: ultimoIdFamiliar + 1,
          comercial: ultimoIdComercial + 1,
        });
      }
    } catch (err) {
      alert("Error al obtener los IDs.");
    }
  };

  useEffect(() => {
    fetchIds();
  }, []);

  const handleChange = (e) => {
    const { name, value, type, checked, files } = e.target;
    setFormData({
      ...formData,
      [name]: type === "checkbox" ? checked : value,
    });
    if (type === "file") {
      setFormData({ ...formData, imagen: files[0] });
    }
  };
  const validarPropietario = async (username) => {
    try {
      const rta = await fetch("http://localhost:4567/idPropietario", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ username }),
      });

      if (rta.ok) {
        const data = await rta.json();
        return data.idPropietario;
      } else {
        return null;
      }
    } catch (err) {
      alert("Error al verificar el propietario.");
      return null;
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const propietarioValido = await validarPropietario(username);
    if (!propietarioValido) {
      alert("Error: El propietario no existe.");
      return;
    }
    const commonData = {
      idPropiedad: ids.propiedad,
      direccion: formData.direccion,
      alquiler: parseInt(formData.alquiler),
      m2_cubiertos: parseInt(formData.m2_cubiertos),
      m2_descubiertos: parseInt(formData.m2_descubiertos),
      condiciones_garantes: formData.condiciones_garantes,
      expensas: parseFloat(formData.expensas),
      gastos: parseFloat(formData.gastos),
      cuidad: formData.cuidad,
      idPropietario: propietarioValido,
      fecha_precio_minimo: formData.fecha_precio_minimo,
      disponible: formData.disponible,
    };

    const specificData =
      tipoPropiedad === "familiar"
        ? {
          idFamiliar: ids.familiar,
          cant_ambientes: parseInt(formData.cant_ambientes),
          cant_baños: parseInt(formData.cant_baños),
          cant_autos_cochera: parseInt(formData.cant_autos_cochera),
          piscina: formData.piscina,
          permite_mascotas: formData.permite_mascotas,
          permite_niños: formData.permite_niños,
          idPropiedad: ids.propiedad,
        }
        : {
          idComercial: ids.comercial,
          permisos_municipales: formData.permisos_municipales,
          baño: formData.baño,
          cocina: formData.cocina,
          vidriera: formData.vidriera,
          deposito: formData.deposito,
          idPropiedad: ids.propiedad,
        };

    const payload = [
      { tipo: tipoPropiedad },
      commonData,
      specificData,
    ];



    const formDataToSend = new FormData();
    formDataToSend.append("json", JSON.stringify(payload));
    if (formData.imagen) {
      formDataToSend.append("imagen", formData.imagen);
    }

    try {
      const response = await fetch("http://localhost:4567/propiedad", {
        method: "POST",
        body: formDataToSend,
      });

      if (response.ok) {
        alert("Propiedad registrada con éxito.");
        setFormData(initialFormData);
        setTipoPropiedad("familiar");
        setUsername("");
        await fetchIds();
      } else {
        alert("Error al registrar la propiedad.");
      }
    } catch (err) {
      alert("Error al conectar con el servidor.");
    }
  };

  return (

    <div className={styles.container}>
      <h1 className={styles.h1}>Registrar Propiedad</h1>
      <form onSubmit={handleSubmit} className={styles.form}>
        {/* Campos Comunes */}
        <input
          type="text"
          name="direccion"
          placeholder="Dirección"
          value={formData.direccion}
          onChange={handleChange}
          required
          className={styles.input}
        />
        <input
          type="number"
          name="alquiler"
          placeholder="Alquiler"
          value={formData.alquiler}
          onChange={handleChange}
          required
          className={styles.input}
        />
        <input
          type="number"
          name="m2_cubiertos"
          placeholder="Metros Cuadrados Cubiertos"
          value={formData.m2_cubiertos}
          onChange={handleChange}
          required
          className={styles.input}
        />
        <input
          type="number"
          name="m2_descubiertos"
          placeholder="Metros Cuadrados Descubiertos"
          value={formData.m2_descubiertos}
          onChange={handleChange}
          required
          className={styles.input}
        />
        <input
          type="text"
          name="condiciones_garantes"
          placeholder="Condiciones garantes"
          value={formData.condiciones_garantes}
          onChange={handleChange}
          required
          className={styles.input}
        />
        <input
          type="number"
          name="expensas"
          placeholder="Expensas"
          value={formData.expensas}
          onChange={handleChange}
          required
          className={styles.input}
        />
        <input
          type="number"
          name="gastos"
          placeholder="Gastos"
          value={formData.gastos}
          onChange={handleChange}
          required
          className={styles.input}
        />
        <input
          type="text"
          name="cuidad"
          placeholder="Ciudad"
          value={formData.cuidad}
          onChange={handleChange}
          required
          className={styles.input}
        />
        <input
          type="text"
          name="username"
          placeholder="Nombre de usuario propietario"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          required
          className={styles.input}
        />
        <input
          type="date"
          name="fecha_precio_minimo"
          placeholder="Fecha precio minimo"
          value={formData.fecha_precio_minimo}
          onChange={handleChange}
          required
          className={styles.input}
        />
        <label className={styles.checkboxLabel}>
          Disponible
          <input
            type="checkbox"
            name="disponible"
            checked={formData.disponible}
            onChange={handleChange}
          />

        </label>
        <label className={styles.labelArchivo}>
          Subir foto
          <input
            type="file"
            name="imagen"
            accept="image/*"
            onChange={handleChange}
            className={styles.inputArchivo}
          />
        </label>
        <label className={styles.labelSelect}>
          Tipo de Propiedad
          <select
            name="tipoPropiedad"
            value={tipoPropiedad}
            onChange={(e) => setTipoPropiedad(e.target.value)}
            className={styles.select}
          >
            <option value="familiar">Familiar</option>
            <option value="comercial">Comercial</option>
          </select>
        </label>
        {/* Campos Específicos */}
        {tipoPropiedad === "familiar" ? (
          <>
            <input
              type="number"
              name="cant_ambientes"
              placeholder="Cantidad de ambientes"
              value={formData.cant_ambientes}
              onChange={handleChange}
              className={styles.input}
            />
            <input
              type="number"
              name="cant_baños"
              placeholder="Cantidad de baños"
              value={formData.cant_baños}
              onChange={handleChange}
              className={styles.input}
            />
            <input
              type="number"
              name="cant_autos_cochera"
              placeholder="Cantidad de autos"
              value={formData.cant_autos_cochera}
              onChange={handleChange}
              className={styles.input}
            />

            <label className={styles.checkboxLabel}>
              Tiene piscina
              <input
                type="checkbox"
                name="piscina"
                checked={formData.piscina}
                onChange={handleChange}
              />

            </label>
            <label className={styles.checkboxLabel}>
              Permite mascotas
              <input
                type="checkbox"
                name="permite_mascotas"
                checked={formData.permite_mascotas}
                onChange={handleChange}
              />

            </label>
            <label className={styles.checkboxLabel}>
              Permite niños
              <input
                type="checkbox"
                name="permite_niños"
                checked={formData.permite_niños}
                onChange={handleChange}
              />

            </label>

          </>
        ) : (
          <>
            <input
              type="text"
              name="permisos_municipales"
              placeholder="Permisos Municipales"
              value={formData.permisos_municipales}
              onChange={handleChange}
              className={styles.input}
            />
            <label className={styles.checkboxLabel}>
              Tiene baño
              <input
                type="checkbox"
                name="baño"
                checked={formData.baño}
                onChange={handleChange}
              />

            </label>
            <label className={styles.checkboxLabel}>
              Tiene cocina
              <input
                type="checkbox"
                name="cocina"
                checked={formData.cocina}
                onChange={handleChange}
              />

            </label>
            <label className={styles.checkboxLabel}>
              Tiene vidriera
              <input
                type="checkbox"
                name="vidriera"
                checked={formData.vidriera}
                onChange={handleChange}
              />

            </label>
            <label className={styles.checkboxLabel}>
              Tiene deposito
              <input
                type="checkbox"
                name="deposito"
                checked={formData.deposito}
                onChange={handleChange}
              />

            </label>

          </>
        )}

        <button type="submit" className={styles.button}>
          Registrar Propiedad
        </button>
      </form>
    </div>
  );
}


