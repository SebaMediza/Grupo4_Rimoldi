-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-11-2024 a las 15:22:33
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `rimoldi`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comercial`
--

CREATE TABLE `comercial` (
  `idComercial` int(11) NOT NULL,
  `permisos_municipales` varchar(45) NOT NULL,
  `baño` tinyint(4) NOT NULL,
  `cocina` tinyint(4) NOT NULL,
  `vidriera` tinyint(4) NOT NULL,
  `deposito` tinyint(4) NOT NULL,
  `idPropiedad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `comercial`
--

INSERT INTO `comercial` (`idComercial`, `permisos_municipales`, `baño`, `cocina`, `vidriera`, `deposito`, `idPropiedad`) VALUES
(1, 'Permit A', 1, 1, 1, 1, 26),
(3, 'Permit C', 1, 0, 1, 0, 28),
(5, 'Permit E', 0, 1, 1, 0, 30),
(7, 'Permit G', 0, 1, 0, 1, 32),
(9, 'Permit I', 0, 0, 1, 1, 34),
(11, 'Permit K', 0, 1, 1, 1, 36),
(13, 'Permit M', 0, 1, 1, 0, 38),
(15, 'Permit O', 0, 0, 1, 1, 40),
(17, 'Permit Q', 0, 1, 0, 1, 42),
(19, 'Permit S', 0, 1, 1, 0, 44),
(21, 'Permit U', 0, 0, 1, 1, 46),
(23, 'Permit W', 0, 1, 0, 1, 48),
(25, 'Permit Y', 0, 1, 1, 0, 50),
(26, 'Todos', 1, 0, 1, 1, 51),
(30, 'Todo', 0, 0, 1, 1, 63);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contrato`
--

CREATE TABLE `contrato` (
  `nro_contrato` int(11) NOT NULL,
  `fecha_inicio` date NOT NULL,
  `fecha_fin` date NOT NULL,
  `fecha_cancelacion` date DEFAULT NULL,
  `idPropiedad` int(11) NOT NULL,
  `idInquilino` int(11) NOT NULL,
  `idMartillero` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `contrato`
--

INSERT INTO `contrato` (`nro_contrato`, `fecha_inicio`, `fecha_fin`, `fecha_cancelacion`, `idPropiedad`, `idInquilino`, `idMartillero`) VALUES
(1, '2024-11-01', '2027-11-01', NULL, 1, 2, 4),
(2, '2024-11-05', '2027-11-05', NULL, 3, 2, 3),
(3, '2024-11-02', '2027-11-02', NULL, 40, 10, 4),
(5, '2024-11-02', '2027-11-02', NULL, 40, 10, 4),
(10, '2024-11-27', '2027-07-14', NULL, 8, 4, 4),
(13, '2024-11-27', '2024-11-30', NULL, 22, 2, 4),
(14, '2024-11-27', '2024-11-29', NULL, 24, 10, 4),
(15, '2024-11-27', '2024-11-30', NULL, 1, 10, 4),
(16, '2024-10-30', '2024-10-31', NULL, 51, 1, 4),
(41, '2024-11-05', '2024-11-14', NULL, 53, 6, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado`
--

CREATE TABLE `estado` (
  `idEstado` int(11) NOT NULL,
  `estado` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `estado`
--

INSERT INTO `estado` (`idEstado`, `estado`) VALUES
(1, 'Borrador'),
(2, 'En revision'),
(3, 'Aprobado'),
(4, 'Esperando firmas'),
(5, 'Firmado'),
(6, 'Activo'),
(7, 'Cancelado'),
(8, 'Terminado'),
(9, 'En proceso de renovación'),
(10, 'Archivado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estadocontrato`
--

CREATE TABLE `estadocontrato` (
  `nro_contrato` int(11) NOT NULL,
  `idEstado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `estadocontrato`
--

INSERT INTO `estadocontrato` (`nro_contrato`, `idEstado`) VALUES
(1, 6),
(2, 1),
(3, 1),
(5, 1),
(10, 1),
(13, 1),
(14, 1),
(15, 1),
(16, 1),
(41, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `familiar`
--

CREATE TABLE `familiar` (
  `idFamiliar` int(11) NOT NULL,
  `cant_ambientes` int(11) NOT NULL,
  `cant_baños` int(11) NOT NULL,
  `cant_autos_cochera` int(11) NOT NULL,
  `piscina` tinyint(4) NOT NULL,
  `permite_mascotas` tinyint(4) NOT NULL,
  `permite_niños` tinyint(4) NOT NULL,
  `idPropiedad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `familiar`
--

INSERT INTO `familiar` (`idFamiliar`, `cant_ambientes`, `cant_baños`, `cant_autos_cochera`, `piscina`, `permite_mascotas`, `permite_niños`, `idPropiedad`) VALUES
(1, 3, 2, 1, 1, 1, 1, 1),
(2, 4, 3, 2, 0, 0, 1, 2),
(3, 2, 1, 1, 1, 1, 0, 3),
(4, 5, 4, 3, 0, 0, 1, 4),
(5, 3, 2, 1, 1, 1, 0, 5),
(6, 4, 3, 2, 0, 0, 1, 6),
(7, 2, 1, 1, 1, 1, 0, 7),
(8, 5, 4, 3, 0, 0, 1, 8),
(10, 4, 3, 2, 0, 0, 1, 10),
(12, 5, 4, 3, 0, 0, 1, 12),
(14, 4, 3, 2, 0, 0, 1, 14),
(16, 5, 4, 3, 0, 0, 1, 16),
(18, 4, 3, 2, 0, 0, 1, 18),
(20, 5, 4, 3, 0, 0, 1, 20),
(22, 4, 3, 2, 0, 0, 1, 22),
(24, 5, 4, 3, 0, 0, 1, 24),
(26, 2, 1, 2, 1, 1, 1, 52),
(27, 4, 2, 3, 1, 1, 1, 53),
(28, 4, 2, 2, 1, 1, 1, 64);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `firma`
--

CREATE TABLE `firma` (
  `nro_contrato` int(11) NOT NULL,
  `idGarante` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `firma`
--

INSERT INTO `firma` (`nro_contrato`, `idGarante`) VALUES
(1, 1),
(2, 2),
(3, 5),
(5, 5),
(10, 1),
(13, 1),
(14, 1),
(15, 1),
(16, 1),
(41, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `garante`
--

CREATE TABLE `garante` (
  `idGarante` int(11) NOT NULL,
  `ingresos` int(11) NOT NULL,
  `empresa_trabaja` varchar(45) NOT NULL,
  `contacto_trabaja` varchar(45) NOT NULL,
  `idPersona` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `garante`
--

INSERT INTO `garante` (`idGarante`, `ingresos`, `empresa_trabaja`, `contacto_trabaja`, `idPersona`) VALUES
(1, 150000, 'Tecro', '5492302555398', 11),
(2, 160000, 'Google', '(650) 253-0000', 12),
(3, 170000, 'Microsoft', '+34 917 547 010', 13),
(4, 180000, 'Amazon', 'contact4', 14),
(5, 190000, 'Mercado Libre', '(54 11) 4640-8000', 15);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inquilino`
--

CREATE TABLE `inquilino` (
  `idInquilino` int(11) NOT NULL,
  `mascotas` tinyint(4) NOT NULL,
  `empresa_trabaja` varchar(45) NOT NULL,
  `cantidad_integrantes` varchar(45) NOT NULL,
  `ingresos` int(11) NOT NULL,
  `idPersona` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `inquilino`
--

INSERT INTO `inquilino` (`idInquilino`, `mascotas`, `empresa_trabaja`, `cantidad_integrantes`, `ingresos`, `idPersona`) VALUES
(1, 1, 'Microsoft', '3', 50000, 1),
(2, 0, 'Apple', '2', 60000, 2),
(3, 1, 'Nvidia', '4', 70000, 3),
(4, 0, 'AMD', '1', 80000, 4),
(5, 1, 'Goole', '5', 90000, 5),
(6, 0, 'Facebook', '3', 100000, 6),
(7, 1, 'Mercado Libre', '2', 110000, 7),
(8, 0, 'Tesla', '4', 120000, 8),
(9, 1, 'Tecro', '1', 130000, 9),
(10, 0, 'Oracle', '5', 140000, 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `martillero`
--

CREATE TABLE `martillero` (
  `idMartillero` int(11) NOT NULL,
  `password` varchar(45) NOT NULL,
  `nro_matricula` int(11) NOT NULL,
  `idPersona` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `martillero`
--

INSERT INTO `martillero` (`idMartillero`, `password`, `nro_matricula`, `idPersona`) VALUES
(1, 'password1', 1001, 20),
(2, 'password2', 1002, 21),
(3, 'password3', 1003, 22),
(4, 'admin', 1004, 23),
(5, 'password5', 1005, 24);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `id` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `dni` bigint(20) NOT NULL,
  `email` varchar(45) NOT NULL,
  `celular` bigint(20) NOT NULL,
  `fecha_nac` date NOT NULL,
  `username` varchar(45) NOT NULL,
  `cuil` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`id`, `nombre`, `dni`, `email`, `celular`, `fecha_nac`, `username`, `cuil`) VALUES
(1, 'John Doe', 12345678, 'john.doe@example.com', 1234567890, '1980-01-01', 'johndoe', 20123456789),
(2, 'Jane Smith', 23456789, 'jane.smith@example.com', 2345678901, '1985-02-02', 'janesmith', 20234567890),
(3, 'Alice Johnson', 34567890, 'alice.johnson@example.com', 3456789012, '1990-03-03', 'alicejohnson', 20345678901),
(4, 'Bob Brown', 45678901, 'bob.brown@example.com', 4567890123, '1995-04-04', 'bobbrown', 20456789012),
(5, 'Charlie Davis', 56789012, 'charlie.davis@example.com', 5678901234, '2000-05-05', 'charliedavis', 20567890123),
(6, 'David Evans', 67890123, 'david.evans@example.com', 6789012345, '1981-06-06', 'davidevans', 20678901234),
(7, 'Eva Green', 78901234, 'eva.green@example.com', 7890123456, '1986-07-07', 'evagreen', 20789012345),
(8, 'Frank Harris', 89012345, 'frank.harris@example.com', 8901234567, '1991-08-08', 'frankharris', 20890123456),
(9, 'Grace Lee', 90123456, 'grace.lee@example.com', 9012345678, '1996-09-09', 'gracelee', 20901234567),
(10, 'Henry Martin', 12345679, 'henry.martin@example.com', 1234567891, '1982-10-10', 'henrymartin', 20123456790),
(11, 'Ivy Nelson', 23456780, 'ivy.nelson@example.com', 2345678902, '1987-11-11', 'ivynelson', 20234567891),
(12, 'Jack Owens', 34567891, 'jack.owens@example.com', 3456789013, '1992-12-12', 'jackowens', 20345678902),
(13, 'Karen Perez', 45678902, 'karen.perez@example.com', 4567890124, '1997-01-13', 'karenperez', 20456789013),
(14, 'Leo Quinn', 56789013, 'leo.quinn@example.com', 5678901235, '1983-02-14', 'leoquinn', 20567890124),
(15, 'Mia Roberts', 67890124, 'mia.roberts@example.com', 6789012346, '1988-03-15', 'miaroberts', 20678901235),
(16, 'Nina Scott', 78901235, 'nina.scott@example.com', 7890123457, '1993-04-16', 'ninascott', 20789012346),
(17, 'Oscar Turner', 89012346, 'oscar.turner@example.com', 8901234568, '1998-05-17', 'oscarturner', 20890123457),
(18, 'Paul Walker', 90123457, 'paul.walker@example.com', 9012345679, '1984-06-18', 'paulwalker', 20901234568),
(19, 'Quinn Young', 12345680, 'quinn.young@example.com', 1234567892, '1989-07-19', 'quinnyoung', 20123456791),
(20, 'Rachel Adams', 23456781, 'rachel.adams@example.com', 2345678903, '1994-08-20', 'racheladams', 20234567892),
(21, 'Sam Baker', 34567892, 'sam.baker@example.com', 3456789014, '1999-09-21', 'sambaker', 20345678903),
(22, 'Tina Clark', 45678903, 'tina.clark@example.com', 4567890125, '1985-10-22', 'tinaclark', 20456789014),
(23, 'Federico Rimoldi', 56789014, 'rimoldi@gmail.com', 5678901236, '1990-11-23', 'federimoldi', 20567890125),
(24, 'Victor Evans', 67890125, 'victor.evans@example.com', 6789012347, '1995-12-24', 'victorevans', 20678901236),
(25, 'Wendy Foster', 78901236, 'wendy.foster@example.com', 7890123458, '2000-01-25', 'wendyfoster', 20789012347);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `propiedad`
--

CREATE TABLE `propiedad` (
  `idPropiedad` int(11) NOT NULL,
  `direccion` varchar(45) NOT NULL,
  `alquiler` double NOT NULL,
  `m2_cubiertos` int(11) NOT NULL,
  `m2_descubiertos` int(11) NOT NULL,
  `condiciones_garantes` varchar(45) NOT NULL,
  `expensas` double NOT NULL,
  `gastos` double NOT NULL,
  `fecha_precio_minimo` date NOT NULL,
  `cuidad` varchar(45) NOT NULL,
  `idPropietario` int(11) NOT NULL,
  `disponible` tinyint(1) NOT NULL,
  `imagen` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `propiedad`
--

INSERT INTO `propiedad` (`idPropiedad`, `direccion`, `alquiler`, `m2_cubiertos`, `m2_descubiertos`, `condiciones_garantes`, `expensas`, `gastos`, `fecha_precio_minimo`, `cuidad`, `idPropietario`, `disponible`, `imagen`) VALUES
(1, '123 Main St', 1200, 100, 50, 'Good credit', 200, 100, '2023-01-01', 'City A', 1, 1, 'familiar1.jpg'),
(2, '456 Elm St', 1300, 110, 60, 'No pets', 210, 110, '2023-02-01', 'City B', 2, 0, NULL),
(3, '789 Oak St', 1400, 120, 70, 'Stable job', 220, 120, '2023-03-01', 'City C', 3, 1, 'familiar2.jpg'),
(4, '101 Pine St', 1500, 130, 80, 'No smoking', 230, 130, '2023-04-01', 'City D', 4, 0, NULL),
(5, '202 Maple St', 1600, 140, 90, 'Good references', 240, 140, '2023-05-01', 'City E', 1, 1, 'familiar3.jpg'),
(6, '303 Birch St', 1700, 150, 100, 'No pets', 250, 150, '2023-06-01', 'City F', 2, 0, NULL),
(7, '404 Cedar St', 1800, 160, 110, 'Good credit', 260, 160, '2023-07-01', 'City G', 3, 1, 'familiar4.jpg'),
(8, '505 Walnut St', 1900, 170, 120, 'Stable job', 270, 170, '2023-08-01', 'City H', 4, 0, NULL),
(10, '707 Ash St', 2100, 190, 140, 'Good references', 290, 190, '2023-10-01', 'City J', 2, 0, NULL),
(12, '909 Fir St', 2300, 210, 160, 'Good credit', 310, 210, '2023-12-01', 'City L', 4, 0, NULL),
(14, '1111 Cypress St', 2500, 230, 180, 'No smoking', 330, 230, '2024-02-01', 'City N', 2, 0, NULL),
(16, '1313 Willow St', 2700, 250, 200, 'No pets', 350, 250, '2024-04-01', 'City P', 4, 0, NULL),
(18, '1515 Magnolia St', 2900, 270, 220, 'Stable job', 370, 270, '2024-06-01', 'City R', 2, 0, NULL),
(20, '1717 Hickory St', 3100, 290, 240, 'Good references', 390, 290, '2024-08-01', 'City T', 4, 0, NULL),
(22, '1919 Beech St', 3300, 310, 260, 'Good credit', 410, 310, '2024-10-01', 'City V', 2, 0, NULL),
(24, '2121 Hawthorn St', 3500, 330, 280, 'No smoking', 430, 330, '2024-12-01', 'City X', 4, 0, NULL),
(26, '2323 Laurel St', 3700, 350, 300, 'No pets', 450, 350, '2025-02-01', 'City Z', 2, 0, NULL),
(28, '2525 Palm St', 3900, 370, 320, 'Stable job', 470, 370, '2025-04-01', 'City BB', 4, 0, NULL),
(30, '2727 Cedar St', 4100, 390, 340, 'Good references', 490, 390, '2025-06-01', 'City DD', 2, 0, NULL),
(32, '2929 Elm St', 4300, 410, 360, 'Good credit', 510, 410, '2025-08-01', 'City FF', 4, 0, NULL),
(34, '3131 Pine St', 4500, 430, 380, 'No smoking', 530, 430, '2025-10-01', 'City HH', 2, 0, NULL),
(36, '3333 Fir St', 4700, 450, 400, 'No pets', 550, 450, '2025-12-01', 'City JJ', 4, 0, NULL),
(38, '3535 Cypress St', 4900, 470, 420, 'Stable job', 570, 470, '2026-02-01', 'City LL', 2, 0, NULL),
(40, '3737 Willow St', 5100, 490, 440, 'Good references', 590, 490, '2026-04-01', 'City NN', 4, 0, NULL),
(42, '3939 Magnolia St', 5300, 510, 460, 'Good credit', 610, 510, '2026-06-01', 'City PP', 2, 0, NULL),
(44, '4141 Hickory St', 5500, 530, 480, 'No smoking', 630, 530, '2026-08-01', 'City RR', 4, 0, NULL),
(46, '4343 Beech St', 5700, 550, 500, 'No pets', 650, 550, '2026-10-01', 'City TT', 2, 0, NULL),
(48, '4545 Hawthorn St', 5900, 570, 520, 'Stable job', 670, 570, '2026-12-01', 'City VV', 4, 0, NULL),
(50, '4747 Laurel St', 6100, 590, 540, 'Good references', 690, 590, '2027-02-01', 'City XX', 2, 0, NULL),
(51, 'Calle 2 N877', 450000, 8, 3, 'Boleta sueldo en blanco', 41000, 32000, '2024-11-25', 'General pico', 1, 1, '20190811_150543.jpg'),
(52, 'Calle 112 N62', 222000, 8, 1, 'Boleta sueldo en blanco', 12000, 36000, '2024-11-25', 'General pico', 3, 1, '20190822_184203.jpg'),
(53, 'Calle 22 N342', 750000, 50, 10, 'Trabajo fijo', 78000, 42500, '2024-11-24', 'General pico', 1, 1, '20191024_122639.jpg'),
(63, '1120 nr45', 150000, 4, 7, 'nada', 14000, 45200, '2024-11-05', 'Alvear', 3, 1, 'images.jfif'),
(64, '110 nro65', 18000, 8, 5, 'nada', 4000, 3999, '2024-11-05', 'General Pico', 1, 1, 'DpMGRmLXgAM5EdG.jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `propietario`
--

CREATE TABLE `propietario` (
  `idPropietario` int(11) NOT NULL,
  `cbu` bigint(20) NOT NULL,
  `idPersona` int(11) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `propietario`
--

INSERT INTO `propietario` (`idPropietario`, `cbu`, `idPersona`, `password`) VALUES
(1, 1234567890123456, 16, 'Scott123'),
(2, 2345678901234567, 17, 'Turner123'),
(3, 3456789012345678, 18, 'Walker123'),
(4, 4567890123456789, 19, 'Young123');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `comercial`
--
ALTER TABLE `comercial`
  ADD PRIMARY KEY (`idComercial`,`idPropiedad`),
  ADD KEY `fk_Comercial_Propiedad1` (`idPropiedad`);

--
-- Indices de la tabla `contrato`
--
ALTER TABLE `contrato`
  ADD PRIMARY KEY (`nro_contrato`),
  ADD KEY `fk_Contrato_Propiedad1_idx` (`idPropiedad`),
  ADD KEY `fk_Contrato_Inquilino1_idx` (`idInquilino`),
  ADD KEY `fk_Contrato_Martillero1_idx` (`idMartillero`);

--
-- Indices de la tabla `estado`
--
ALTER TABLE `estado`
  ADD PRIMARY KEY (`idEstado`),
  ADD UNIQUE KEY `idEstado_UNIQUE` (`idEstado`);

--
-- Indices de la tabla `estadocontrato`
--
ALTER TABLE `estadocontrato`
  ADD PRIMARY KEY (`nro_contrato`,`idEstado`),
  ADD KEY `fk_Contrato_has_Estado_Estado1_idx` (`idEstado`),
  ADD KEY `fk_Contrato_has_Estado_Contrato1_idx` (`nro_contrato`);

--
-- Indices de la tabla `familiar`
--
ALTER TABLE `familiar`
  ADD PRIMARY KEY (`idFamiliar`,`idPropiedad`),
  ADD KEY `fk_Familiar_Propiedad1` (`idPropiedad`);

--
-- Indices de la tabla `firma`
--
ALTER TABLE `firma`
  ADD PRIMARY KEY (`nro_contrato`,`idGarante`),
  ADD KEY `fk_Contrato_has_Garante_Garante1_idx` (`idGarante`);

--
-- Indices de la tabla `garante`
--
ALTER TABLE `garante`
  ADD PRIMARY KEY (`idGarante`,`idPersona`),
  ADD KEY `fk_Garante_Persona1_idx` (`idPersona`);

--
-- Indices de la tabla `inquilino`
--
ALTER TABLE `inquilino`
  ADD PRIMARY KEY (`idInquilino`,`idPersona`),
  ADD KEY `fk_Inquilino_Persona1` (`idPersona`);

--
-- Indices de la tabla `martillero`
--
ALTER TABLE `martillero`
  ADD PRIMARY KEY (`idMartillero`,`idPersona`),
  ADD KEY `fk_Martillero_Persona1_idx` (`idPersona`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_UNIQUE` (`id`),
  ADD UNIQUE KEY `dni_UNIQUE` (`dni`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indices de la tabla `propiedad`
--
ALTER TABLE `propiedad`
  ADD PRIMARY KEY (`idPropiedad`),
  ADD UNIQUE KEY `idPropiedad_UNIQUE` (`idPropiedad`),
  ADD KEY `fk_Propiedad_Propietario1_idx` (`idPropietario`);

--
-- Indices de la tabla `propietario`
--
ALTER TABLE `propietario`
  ADD PRIMARY KEY (`idPropietario`,`idPersona`),
  ADD KEY `fk_Propietario_Persona1_idx` (`idPersona`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `comercial`
--
ALTER TABLE `comercial`
  MODIFY `idComercial` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- AUTO_INCREMENT de la tabla `contrato`
--
ALTER TABLE `contrato`
  MODIFY `nro_contrato` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT de la tabla `familiar`
--
ALTER TABLE `familiar`
  MODIFY `idFamiliar` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT de la tabla `propiedad`
--
ALTER TABLE `propiedad`
  MODIFY `idPropiedad` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=69;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `comercial`
--
ALTER TABLE `comercial`
  ADD CONSTRAINT `fk_Comercial_Propiedad1` FOREIGN KEY (`idPropiedad`) REFERENCES `propiedad` (`idPropiedad`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Filtros para la tabla `contrato`
--
ALTER TABLE `contrato`
  ADD CONSTRAINT `fk_Contrato_Inquilino1` FOREIGN KEY (`idInquilino`) REFERENCES `inquilino` (`idInquilino`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Contrato_Martillero1` FOREIGN KEY (`idMartillero`) REFERENCES `martillero` (`idMartillero`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Contrato_Propiedad1` FOREIGN KEY (`idPropiedad`) REFERENCES `propiedad` (`idPropiedad`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Filtros para la tabla `estadocontrato`
--
ALTER TABLE `estadocontrato`
  ADD CONSTRAINT `fk_Contrato_has_Estado_Contrato1` FOREIGN KEY (`nro_contrato`) REFERENCES `contrato` (`nro_contrato`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Contrato_has_Estado_Estado1` FOREIGN KEY (`idEstado`) REFERENCES `estado` (`idEstado`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Filtros para la tabla `familiar`
--
ALTER TABLE `familiar`
  ADD CONSTRAINT `fk_Familiar_Propiedad1` FOREIGN KEY (`idPropiedad`) REFERENCES `propiedad` (`idPropiedad`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Filtros para la tabla `firma`
--
ALTER TABLE `firma`
  ADD CONSTRAINT `fk_Contrato_has_Garante_Contrato1` FOREIGN KEY (`nro_contrato`) REFERENCES `contrato` (`nro_contrato`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Contrato_has_Garante_Garante1` FOREIGN KEY (`idGarante`) REFERENCES `garante` (`idGarante`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Filtros para la tabla `garante`
--
ALTER TABLE `garante`
  ADD CONSTRAINT `fk_Garante_Persona1` FOREIGN KEY (`idPersona`) REFERENCES `persona` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Filtros para la tabla `inquilino`
--
ALTER TABLE `inquilino`
  ADD CONSTRAINT `fk_Inquilino_Persona1` FOREIGN KEY (`idPersona`) REFERENCES `persona` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Filtros para la tabla `martillero`
--
ALTER TABLE `martillero`
  ADD CONSTRAINT `fk_Martillero_Persona1` FOREIGN KEY (`idPersona`) REFERENCES `persona` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Filtros para la tabla `propiedad`
--
ALTER TABLE `propiedad`
  ADD CONSTRAINT `fk_Propiedad_Propietario1` FOREIGN KEY (`idPropietario`) REFERENCES `propietario` (`idPropietario`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Filtros para la tabla `propietario`
--
ALTER TABLE `propietario`
  ADD CONSTRAINT `fk_Propietario_Persona1` FOREIGN KEY (`idPersona`) REFERENCES `persona` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
