INSERT INTO rimoldi.persona
    (id, nombre, dni, email, celular, fecha_nac, username, cuil)
VALUES
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
    (23, 'Uma Davis', 56789014, 'uma.davis@example.com', 5678901236, '1990-11-23', 'umadavis', 20567890125),
    (24, 'Victor Evans', 67890125, 'victor.evans@example.com', 6789012347, '1995-12-24', 'victorevans', 20678901236),
    (25, 'Wendy Foster', 78901236, 'wendy.foster@example.com', 7890123458, '2000-01-25', 'wendyfoster', 20789012347);

INSERT INTO rimoldi.inquilino
    (mascotas, empresa_trabaja, cantidad_integrantes, ingresos, idPersona, idInquilino)
VALUES
    (1, 'Microsoft', '3', 50000, 1, 1),
    (0, 'Apple', '2', 60000, 2, 2),
    (1, 'Nvidia', '4', 70000, 3, 3),
    (0, 'AMD', '1', 80000, 4, 4),
    (1, 'Goole', '5', 90000, 5, 5),
    (0, 'Facebook', '3', 100000, 6, 6),
    (1, 'Mercado Libre', '2', 110000, 7, 7),
    (0, 'Tesla', '4', 120000, 8, 8),
    (1, 'Tecro', '1', 130000, 9, 9),
    (0, 'Oracle', '5', 140000, 10, 10);

INSERT INTO rimoldi.garante
    (ingresos, empresa_trabaja, contacto_trabaja, idPersona, idGarante)
VALUES
    (150000, 'Tecro', '5492302555398', 11, 1),
    (160000, 'Google', '(650) 253-0000', 12, 2),
    (170000, 'Microsoft', '+34 917 547 010', 13, 3),
    (180000, 'Amazon', 'contact4', 14, 4),
    (190000, 'Mercado Libre', '(54 11) 4640-8000', 15, 5);

INSERT INTO rimoldi.propietario
    (cbu, idPersona, idPropietario)
VALUES
    (1234567890123456, 16, 1),
    (2345678901234567, 17, 2),
    (3456789012345678, 18, 3),
    (4567890123456789, 19, 4);

INSERT INTO rimoldi.martillero
    (idMartillero, contraseña, nro_matricula, idPersona)
VALUES
    (1, 'password1', 1001, 20),
    (2, 'password2', 1002, 21),
    (3, 'password3', 1003, 22),
    (4, 'password4', 1004, 23),
    (5, 'password5', 1005, 24);

INSERT INTO rimoldi.propiedad
    (idPropiedad, direccion, alquiler, m2_cubiertos, m2_descubiertos, condiciones_garantes, expensas, gastos, fecha_precio_minimo, idPropietario, cuidad)
VALUES
    (1, '123 Main St', 1200.00, 100, 50, 'Good credit', 200.00, 100.00, '2023-01-01', 1, 'City A'),
    (2, '456 Elm St', 1300.00, 110, 60, 'No pets', 210.00, 110.00, '2023-02-01', 2, 'City B'),
    (3, '789 Oak St', 1400.00, 120, 70, 'Stable job', 220.00, 120.00, '2023-03-01', 3, 'City C'),
    (4, '101 Pine St', 1500.00, 130, 80, 'No smoking', 230.00, 130.00, '2023-04-01', 4, 'City D'),
    (5, '202 Maple St', 1600.00, 140, 90, 'Good references', 240.00, 140.00, '2023-05-01', 1, 'City E'),
    (6, '303 Birch St', 1700.00, 150, 100, 'No pets', 250.00, 150.00, '2023-06-01', 2, 'City F'),
    (7, '404 Cedar St', 1800.00, 160, 110, 'Good credit', 260.00, 160.00, '2023-07-01', 3, 'City G'),
    (8, '505 Walnut St', 1900.00, 170, 120, 'Stable job', 270.00, 170.00, '2023-08-01', 4, 'City H'),
    (9, '606 Chestnut St', 2000.00, 180, 130, 'No smoking', 280.00, 180.00, '2023-09-01', 1, 'City I'),
    (10, '707 Ash St', 2100.00, 190, 140, 'Good references', 290.00, 190.00, '2023-10-01', 2, 'City J'),
    (11, '808 Spruce St', 2200.00, 200, 150, 'No pets', 300.00, 200.00, '2023-11-01', 3, 'City K'),
    (12, '909 Fir St', 2300.00, 210, 160, 'Good credit', 310.00, 210.00, '2023-12-01', 4, 'City L'),
    (13, '1010 Redwood St', 2400.00, 220, 170, 'Stable job', 320.00, 220.00, '2024-01-01', 1, 'City M'),
    (14, '1111 Cypress St', 2500.00, 230, 180, 'No smoking', 330.00, 230.00, '2024-02-01', 2, 'City N'),
    (15, '1212 Palm St', 2600.00, 240, 190, 'Good references', 340.00, 240.00, '2024-03-01', 3, 'City O'),
    (16, '1313 Willow St', 2700.00, 250, 200, 'No pets', 350.00, 250.00, '2024-04-01', 4, 'City P'),
    (17, '1414 Poplar St', 2800.00, 260, 210, 'Good credit', 360.00, 260.00, '2024-05-01', 1, 'City Q'),
    (18, '1515 Magnolia St', 2900.00, 270, 220, 'Stable job', 370.00, 270.00, '2024-06-01', 2, 'City R'),
    (19, '1616 Dogwood St', 3000.00, 280, 230, 'No smoking', 380.00, 280.00, '2024-07-01', 3, 'City S'),
    (20, '1717 Hickory St', 3100.00, 290, 240, 'Good references', 390.00, 290.00, '2024-08-01', 4, 'City T'),
    (21, '1818 Sycamore St', 3200.00, 300, 250, 'No pets', 400.00, 300.00, '2024-09-01', 1, 'City U'),
    (22, '1919 Beech St', 3300.00, 310, 260, 'Good credit', 410.00, 310.00, '2024-10-01', 2, 'City V'),
    (23, '2020 Alder St', 3400.00, 320, 270, 'Stable job', 420.00, 320.00, '2024-11-01', 3, 'City W'),
    (24, '2121 Hawthorn St', 3500.00, 330, 280, 'No smoking', 430.00, 330.00, '2024-12-01', 4, 'City X'),
    (25, '2222 Juniper St', 3600.00, 340, 290, 'Good references', 440.00, 340.00, '2025-01-01', 1, 'City Y'),
    (26, '2323 Laurel St', 3700.00, 350, 300, 'No pets', 450.00, 350.00, '2025-02-01', 2, 'City Z'),
    (27, '2424 Olive St', 3800.00, 360, 310, 'Good credit', 460.00, 360.00, '2025-03-01', 3, 'City AA'),
    (28, '2525 Palm St', 3900.00, 370, 320, 'Stable job', 470.00, 370.00, '2025-04-01', 4, 'City BB'),
    (29, '2626 Maple St', 4000.00, 380, 330, 'No smoking', 480.00, 380.00, '2025-05-01', 1, 'City CC'),
    (30, '2727 Cedar St', 4100.00, 390, 340, 'Good references', 490.00, 390.00, '2025-06-01', 2, 'City DD'),
    (31, '2828 Birch St', 4200.00, 400, 350, 'No pets', 500.00, 400.00, '2025-07-01', 3, 'City EE'),
    (32, '2929 Elm St', 4300.00, 410, 360, 'Good credit', 510.00, 410.00, '2025-08-01', 4, 'City FF'),
    (33, '3030 Oak St', 4400.00, 420, 370, 'Stable job', 520.00, 420.00, '2025-09-01', 1, 'City GG'),
    (34, '3131 Pine St', 4500.00, 430, 380, 'No smoking', 530.00, 430.00, '2025-10-01', 2, 'City HH'),
    (35, '3232 Spruce St', 4600.00, 440, 390, 'Good references', 540.00, 440.00, '2025-11-01', 3, 'City II'),
    (36, '3333 Fir St', 4700.00, 450, 400, 'No pets', 550.00, 450.00, '2025-12-01', 4, 'City JJ'),
    (37, '3434 Redwood St', 4800.00, 460, 410, 'Good credit', 560.00, 460.00, '2026-01-01', 1, 'City KK'),
    (38, '3535 Cypress St', 4900.00, 470, 420, 'Stable job', 570.00, 470.00, '2026-02-01', 2, 'City LL'),
    (39, '3636 Palm St', 5000.00, 480, 430, 'No smoking', 580.00, 480.00, '2026-03-01', 3, 'City MM'),
    (40, '3737 Willow St', 5100.00, 490, 440, 'Good references', 590.00, 490.00, '2026-04-01', 4, 'City NN'),
    (41, '3838 Poplar St', 5200.00, 500, 450, 'No pets', 600.00, 500.00, '2026-05-01', 1, 'City OO'),
    (42, '3939 Magnolia St', 5300.00, 510, 460, 'Good credit', 610.00, 510.00, '2026-06-01', 2, 'City PP'),
    (43, '4040 Dogwood St', 5400.00, 520, 470, 'Stable job', 620.00, 520.00, '2026-07-01', 3, 'City QQ'),
    (44, '4141 Hickory St', 5500.00, 530, 480, 'No smoking', 630.00, 530.00, '2026-08-01', 4, 'City RR'),
    (45, '4242 Sycamore St', 5600.00, 540, 490, 'Good references', 640.00, 540.00, '2026-09-01', 1, 'City SS'),
    (46, '4343 Beech St', 5700.00, 550, 500, 'No pets', 650.00, 550.00, '2026-10-01', 2, 'City TT'),
    (47, '4444 Alder St', 5800.00, 560, 510, 'Good credit', 660.00, 560.00, '2026-11-01', 3, 'City UU'),
    (48, '4545 Hawthorn St', 5900.00, 570, 520, 'Stable job', 670.00, 570.00, '2026-12-01', 4, 'City VV'),
    (49, '4646 Juniper St', 6000.00, 580, 530, 'No smoking', 680.00, 580.00, '2027-01-01', 1, 'City WW'),
    (50, '4747 Laurel St', 6100.00, 590, 540, 'Good references', 690.00, 590.00, '2027-02-01', 2, 'City XX');

INSERT INTO rimoldi.familiar
    (cant_ambientes, cant_baños, cant_autos_cochera, piscina, permite_mascotas, permite_niños, idPropiedad, idFamiliar)
VALUES
    (3, 2, 1, 1, 1, 1, 1, 1),
    (4, 3, 2, 0, 0, 1, 2, 2),
    (2, 1, 1, 1, 1, 0, 3, 3),
    (5, 4, 3, 0, 0, 1, 4, 4),
    (3, 2, 1, 1, 1, 0, 5, 5),
    (4, 3, 2, 0, 0, 1, 6, 6),
    (2, 1, 1, 1, 1, 0, 7, 7),
    (5, 4, 3, 0, 0, 1, 8, 8),
    (3, 2, 1, 1, 1, 0, 9, 9),
    (4, 3, 2, 0, 0, 1, 10, 10),
    (2, 1, 1, 1, 1, 0, 11, 11),
    (5, 4, 3, 0, 0, 1, 12, 12),
    (3, 2, 1, 1, 1, 0, 13, 13),
    (4, 3, 2, 0, 0, 1, 14, 14),
    (2, 1, 1, 1, 1, 0, 15, 15),
    (5, 4, 3, 0, 0, 1, 16, 16),
    (3, 2, 1, 1, 1, 0, 17, 17),
    (4, 3, 2, 0, 0, 1, 18, 18),
    (2, 1, 1, 1, 1, 0, 19, 19),
    (5, 4, 3, 0, 0, 1, 20, 20),
    (3, 2, 1, 1, 1, 0, 21, 21),
    (4, 3, 2, 0, 0, 1, 22, 22),
    (2, 1, 1, 1, 1, 0, 23, 23),
    (5, 4, 3, 0, 0, 1, 24, 24),
    (3, 2, 1, 1, 1, 0, 25, 25);

INSERT INTO rimoldi.comercial
    (permisos_municipales, baño, cocina, vidriera, deposito, idPropiedad, idComercial)
VALUES
    ('Permit A', 1, 1, 1, 1, 26, 1),
    ('Permit B', 0, 1, 0, 1, 27, 2),
    ('Permit C', 1, 0, 1, 0, 28, 3),
    ('Permit D', 1, 1, 0, 1, 29, 4),
    ('Permit E', 0, 1, 1, 0, 30, 5),
    ('Permit F', 1, 0, 1, 1, 31, 6),
    ('Permit G', 0, 1, 0, 1, 32, 7),
    ('Permit H', 1, 1, 1, 0, 33, 8),
    ('Permit I', 0, 0, 1, 1, 34, 9),
    ('Permit J', 1, 1, 0, 0, 35, 10),
    ('Permit K', 0, 1, 1, 1, 36, 11),
    ('Permit L', 1, 0, 0, 1, 37, 12),
    ('Permit M', 0, 1, 1, 0, 38, 13),
    ('Permit N', 1, 1, 0, 1, 39, 14),
    ('Permit O', 0, 0, 1, 1, 40, 15),
    ('Permit P', 1, 1, 1, 0, 41, 16),
    ('Permit Q', 0, 1, 0, 1, 42, 17),
    ('Permit R', 1, 0, 1, 1, 43, 18),
    ('Permit S', 0, 1, 1, 0, 44, 19),
    ('Permit T', 1, 1, 0, 1, 45, 20),
    ('Permit U', 0, 0, 1, 1, 46, 21),
    ('Permit V', 1, 1, 1, 0, 47, 22),
    ('Permit W', 0, 1, 0, 1, 48, 23),
    ('Permit X', 1, 0, 1, 1, 49, 24),
    ('Permit Y', 0, 1, 1, 0, 50, 25);

INSERT INTO rimoldi.estado
    (idEstado, estado)
VALUES
    (1, 'borrador'),
    (2, 'en revision'),
    (3, 'aprobado'),
    (4, 'esperando firmas'),
    (5, 'firmado'),
    (6, 'activo'),
    (7, 'cancelado'),
    (8, 'terminado'),
    (9, 'en proceso de renovacion'),
    (10, 'archivado')