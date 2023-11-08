--RFC 1

-- Caso exitoso
SELECT h.id, h.numero, COALESCE(SUM(c.sumatotal), 0) AS sum
FROM habitaciones h
LEFT JOIN reservashabitaciones r ON h.id = r.habitaciones_id
LEFT JOIN consumos c ON r.id = c.reservashabitaciones_id
AND c.fechaconsumo BETWEEN ADD_MONTHS(SYSDATE, -12) AND SYSDATE
GROUP BY h.id, h.numero
ORDER BY sum DESC FETCH FIRST 30 ROWS ONLY;

-- Caso fallido
SELECT h.id, h.numero, COALESCE(SUM(c.sumatotal), 0) AS sum
FROM habitaciones h
LEFT JOIN reservashabitaciones r ON h.id = r.habitaciones_id
LEFT JOIN consumos c ON r.id = c.reservashabitaciones_id
AND c.fechaconsumo BETWEEN SYSDATE + 2 AND SYSDATE + 1 -- Fechas en un rango sin sentido
GROUP BY h.id, h.numero
ORDER BY sum DESC FETCH FIRST 30 ROWS ONLY;

-- Caso en fechas sin registros
SELECT h.id, h.numero, COALESCE(SUM(c.sumatotal), 0) AS sum
FROM habitaciones h
LEFT JOIN reservashabitaciones r ON h.id = r.habitaciones_id
LEFT JOIN consumos c ON r.id = c.reservashabitaciones_id
AND c.fechaconsumo BETWEEN TO_DATE('2000-01-01', 'YYYY-MM-DD') AND TO_DATE('2000-01-02', 'YYYY-MM-DD') -- Known no consumption period
GROUP BY h.id, h.numero
ORDER BY sum DESC FETCH FIRST 30 ROWS ONLY;


--RFC 2
-- Caso exitoso
SELECT s.id, s.nombre, COUNT(c.id)
FROM servicios s
JOIN consumos c ON s.id = c.servicios_id
WHERE EXISTS (
    SELECT 1 FROM reservashabitaciones r 
    WHERE c.reservashabitaciones_id = r.id 
    AND r.fechainicio BETWEEN TO_DATE('2021-01-01', 'YYYY-MM-DD') 
    AND TO_DATE('2025-01-01', 'YYYY-MM-DD'))
GROUP BY s.id, s.nombre
ORDER BY COUNT(c.id) DESC
FETCH FIRST 20 ROWS ONLY;

-- Caso de falla
SELECT s.id, s.nombre, COUNT(c.id)
FROM servicios s
JOIN consumos c ON s.id = c.servicios_id
WHERE EXISTS (
    SELECT 1 FROM reservashabitaciones r 
    WHERE c.reservashabitaciones_id = r.id 
    AND r.fechainicio BETWEEN TO_DATE('2025-01-02', 'YYYY-MM-DD') 
    AND TO_DATE('2025-01-01', 'YYYY-MM-DD')) --Rango invalido
GROUP BY s.id, s.nombre
ORDER BY COUNT(c.id) DESC
FETCH FIRST 20 ROWS ONLY;

-- Caso con fechas en las que no hay registros
SELECT s.id, s.nombre, COUNT(c.id)
FROM servicios s
JOIN consumos c ON s.id = c.servicios_id
WHERE EXISTS (
    SELECT 1 FROM reservashabitaciones r 
    WHERE c.reservashabitaciones_id = r.id 
    AND r.fechainicio BETWEEN TO_DATE('1900-01-01', 'YYYY-MM-DD') 
    AND TO_DATE('1901-01-01', 'YYYY-MM-DD'))
GROUP BY s.id, s.nombre
ORDER BY COUNT(c.id) DESC
FETCH FIRST 20 ROWS ONLY;

--RFC 3
--Caso exitoso
SELECT h.id, h.numero AS room_number, COALESCE(ROUND(100 * SUM(NVL(r.fechafin, SYSDATE) - r.fechainicio) / 365, 2), 0)
FROM habitaciones h
LEFT JOIN reservashabitaciones r ON h.id = r.habitaciones_id AND r.fechainicio BETWEEN ADD_MONTHS(SYSDATE, -12) AND SYSDATE
GROUP BY h.id, h.numero
ORDER BY h.id;
--Caso de falla
SELECT h.id, h.numero AS room_number, COALESCE(ROUND(100 * SUM(NVL(r.fechafin, SYSDATE + 365) - r.fechainicio) / 365, 2), 0)
FROM habitaciones h
LEFT JOIN reservashabitaciones r ON h.id = r.habitaciones_id AND r.fechainicio BETWEEN SYSDATE + 365 AND SYSDATE + 1 --fechas sin sentido
GROUP BY h.id, h.numero
ORDER BY h.id;
--Caso con habitación que no existe
SELECT h.id, h.numero AS room_number, COALESCE(ROUND(100 * SUM(NVL(r.fechafin, SYSDATE) - r.fechainicio) / 365, 2), 0) AS occupancy_rate
FROM habitaciones h
LEFT JOIN reservashabitaciones r ON h.id = r.habitaciones_id AND r.fechainicio BETWEEN ADD_MONTHS(SYSDATE, -12) AND SYSDATE
WHERE h.id = 9999999
GROUP BY h.id, h.numero
ORDER BY h.id;

--RFC 4
--Caso exitoso
SELECT s.nombre, s.descripcion, s.costoporunidad, s.unidad, s.horario, s.tiposervicio, s.capacidad
FROM servicios s, consumos c
WHERE costoporunidad BETWEEN 0 AND 100000000
AND s.id = c.servicios_id
AND c.fechaconsumo BETWEEN TO_DATE('2020-01-01','YYYY-MM-DD') AND TO_DATE('2025-01-01','YYYY-MM-DD')
AND c.usuarios_id = 12 
AND s.tiposervicio = 'Deporte';
--Caso fallido
SELECT s.nombre, s.descripcion, s.costoporunidad, s.unidad, s.horario, s.tiposervicio, s.capacidad
FROM servicios s, consumos c
WHERE costoporunidad BETWEEN 0 AND 100000000
AND s.id = c.servicios_id
AND c.fechaconsumo BETWEEN TO_DATE('2020-01-01','YYYY-MM-DD') AND TO_DATE('2025-01-01','YYYY-MM-DD')
AND c.usuarios_id = 12 
AND s.tiposervicio = 'DeporteX'; --Tipo de servcio que no existe
--Caso con usuario que no existe
SELECT s.nombre, s.descripcion, s.costoporunidad, s.unidad, s.horario, s.tiposervicio, s.capacidad
FROM servicios s, consumos c
WHERE costoporunidad BETWEEN 0 AND 100000000
AND s.id = c.servicios_id
AND c.fechaconsumo BETWEEN TO_DATE('2020-01-01','YYYY-MM-DD') AND TO_DATE('2025-01-01','YYYY-MM-DD')
AND c.usuarios_id = -1
AND s.tiposervicio = 'Deporte';

--RFC 5
--Exitoso
SELECT u.id, u.nombre, s.nombre, c.sumatotal
FROM consumos c
JOIN reservashabitaciones rh ON c.reservashabitaciones_id = rh.id
JOIN servicios s ON c.servicios_id = s.id
JOIN usuarios u ON rh.usuarios_id = u.id
WHERE u.id = 100
AND rh.fechainicio BETWEEN TO_DATE('2020-01-01','YYYY-MM-DD') AND TO_DATE('2023-01-01','YYYY-MM-DD');
--Fallido
SELECT u.id, u.nombre, s.nombre, c.sumatotal
FROM consumos c
JOIN reservashabitaciones rh ON c.reservashabitaciones_id = rh.id
JOIN servicios s ON c.servicios_id = s.id
JOIN usuarios u ON rh.usuarios_id = u.id
WHERE u.id = -1 -- Usuario que no existe
AND rh.fechainicio BETWEEN TO_DATE('2022-01-01','YYYY-MM-DD') AND TO_DATE('2023-01-01','YYYY-MM-DD');

--RFC 6

--Exitoso
SELECT fechainicio, COUNT(*) AS ocupacion
FROM reservashabitaciones
GROUP BY fechainicio
ORDER BY ocupacion DESC
FETCH FIRST 10 ROWS ONLY;
SELECT fechainicio, COUNT(*) AS ocupacion
FROM reservashabitaciones
GROUP BY fechainicio
ORDER BY ocupacion ASC
FETCH FIRST 10 ROWS ONLY;
SELECT c.fechaconsumo, SUM(c.sumatotal) AS ingresos
FROM consumos c
GROUP BY c.fechaconsumo
ORDER BY ingresos DESC
FETCH FIRST 10 ROWS ONLY;

--Fallido
SELECT fechainicio, COUNT(*) AS ocupacion
FROM reservashabitaciones
where fechainicio < to_date('1999-01-01','YYYY-MM-DD')--fecha sin registros
GROUP BY fechainicio
ORDER BY ocupacion DESC
FETCH FIRST 10 ROWS ONLY;
SELECT fechainicio, COUNT(*) AS ocupacion
FROM reservashabitaciones
where fechainicio < to_date('1999-01-01','YYYY-MM-DD')--fecha sin registros
GROUP BY fechainicio
ORDER BY ocupacion ASC
FETCH FIRST 10 ROWS ONLY;
SELECT c.fechaconsumo, SUM(c.sumatotal) AS ingresos
FROM consumos c
where fechaconsumo < to_date('1999-01-01','YYYY-MM-DD')--fecha sin registros
GROUP BY c.fechaconsumo
ORDER BY ingresos DESC
FETCH FIRST 10 ROWS ONLY;

--RFC 7

--Exitoso
WITH DIAS AS (SELECT usuarios.nombre as id, SUM(reservashabitaciones.fechafin - reservashabitaciones.fechainicio) as diashotel
    FROM usuarios, reservashabitaciones
    WHERE reservashabitaciones.usuarios_id = usuarios.id 
    GROUP BY usuarios.nombre), 
GASTOS AS (SELECT usuarios.nombre as id, sum(consumos.sumatotal) as gasto 
    FROM consumos, usuarios, reservashabitaciones
    WHERE usuarios.id = reservashabitaciones.usuarios_id 
    AND consumos.reservashabitaciones_id  = reservashabitaciones.id
    GROUP BY usuarios.nombre)
SELECT gastos.id, gastos.gasto, dias.diashotel 
FROM gastos, dias 
WHERE dias.id = gastos.id 
AND (gastos.gasto >= 15000000 OR dias.diashotel >= 14);

--Fallido
WITH DIAS AS (
    SELECT usuarios.nombre as id, SUM(reservashabitaciones.fechafin - reservashabitaciones.fechainicio) as diashotel
    FROM usuarios, reservashabitaciones
    WHERE reservashabitaciones.usuarios_id = usuarios.id 
    GROUP BY usuarios.nombre
), 
GASTOS AS (
    SELECT usuarios.nombre as id, sum(consumos.sumatotal) as gasto 
    FROM consumos, usuarios, reservashabitaciones
    WHERE usuarios.id = reservashabitaciones.usuarios_id 
    AND consumos.reservashabitaciones_id  = reservashabitaciones.id
    GROUP BY usuarios.nombre
)
SELECT gastos.id, gastos.gasto, dias.diashotel 
FROM gastos, dias 
WHERE dias.id = gastos.id 
AND (gastos.gasto < -1 OR dias.diashotel >= 100000000000000000000000000);

--RFC 8
--Exitoso
SELECT s.nombre, COUNT(*) 
FROM Consumos c, servicios s 
WHERE (c.fechaconsumo >= ADD_MONTHS(SYSDATE, -12) and c.servicios_id = s.id) 
GROUP BY s.nombre, TO_CHAR(c.fechaconsumo, 'IYYY-IW')
HAVING COUNT(*) < 3;

--Fallido
SELECT s.nombre, COUNT(*) 
FROM Consumos c, servicios s 
WHERE (c.fechaconsumo >= ADD_MONTHS(SYSDATE, -12) and c.servicios_id = s.id) 
GROUP BY s.nombre, TO_CHAR(c.fechaconsumo, 'IYYY-IW')
HAVING COUNT(*) < 0;

--RFC 9
-- Existoso
SELECT u.nombre, u.numdocumento, s.nombre, COUNT(c.numconsumos) AS cuenta
FROM consumos c
JOIN usuarios u ON c.usuarios_id = u.id 
JOIN servicios s ON c.servicios_id = s.id
WHERE c.fechaconsumo BETWEEN TO_DATE('2020-01-01','YYYY-MM-DD') AND TO_DATE('2023-01-01','YYYY-MM-DD') 
AND s.id = 6
GROUP BY u.nombre, u.numdocumento, s.nombre
ORDER BY cuenta;
-- Fallido
SELECT u.nombre, u.numdocumento, s.nombre, COUNT(c.numconsumos) AS cuenta
FROM consumos c
JOIN usuarios u ON c.usuarios_id = u.id 
JOIN servicios s ON c.servicios_id = s.id
WHERE c.fechaconsumo BETWEEN TO_DATE('2020-01-01','YYYY-MM-DD') AND TO_DATE('2023-01-01','YYYY-MM-DD') 
AND s.id = -1--servicio que no existe
GROUP BY u.nombre, u.numdocumento, s.nombre
ORDER BY cuenta;

--RFC 10
-- Existoso
SELECT u.id, u.nombre, u.numdocumento
FROM usuarios u
WHERE u.id NOT IN (SELECT u.id 
    FROM consumos c 
    JOIN usuarios u ON c.usuarios_id = u.id 
    JOIN servicios s ON c.servicios_id = s.id
    WHERE c.fechaconsumo BETWEEN TO_DATE('2020-01-01','YYYY-MM-DD') AND TO_DATE('2023-01-01','YYYY-MM-DD') 
    AND s.id = 1 )
GROUP BY u.id, u.nombre, u.numdocumento
ORDER BY u.id;

-- Fallido
SELECT u.id, u.nombre, u.numdocumento
FROM usuarios u
WHERE u.id NOT IN (SELECT u.id 
    FROM consumos c 
    JOIN usuarios u ON c.usuarios_id = u.id 
    JOIN servicios s ON c.servicios_id = s.id
    WHERE c.fechaconsumo BETWEEN TO_DATE('2020-01-01','YYYY-MM-DD') AND TO_DATE('2023-01-01','YYYY-MM-DD') 
    AND s.id between 1 and 51 ) --esto implica a todos los usuarios
GROUP BY u.id, u.nombre, u.numdocumento
ORDER BY u.id;

--RFC 11

--Exitoso
WITH SemanaConsumo AS (
SELECT TO_NUMBER(TO_CHAR(fechaconsumo, 'IYYY')) AS year, TO_NUMBER(TO_CHAR(fechaconsumo, 'IW')) AS week,TRUNC(fechaconsumo, 'IW') - 1 AS start_date,TRUNC(fechaconsumo, 'IW') + 5 AS end_date,  servicios_id, count(*) AS total_consumo
FROM consumos
GROUP BY TO_NUMBER(TO_CHAR(fechaconsumo, 'IYYY')),TO_NUMBER(TO_CHAR(fechaconsumo, 'IW')),TRUNC(fechaconsumo, 'IW'),servicios_id),
MaxMinConsumo AS (
SELECT year,week,start_date,end_date, MAX(total_consumo) AS max_consumo,MIN(total_consumo) AS min_consumo
FROM SemanaConsumo
GROUP BY year, week, start_date, end_date)
SELECT s.year,s.week,s.start_date,s.end_date,sv.nombre AS servicio_mas_consumido_nombre,m.max_consumo AS max_consumo_servicio, sv1.nombre AS servicio_menos_consumido_nombre,m.min_consumo AS min_consumo_servicio
FROM SemanaConsumo s
JOIN servicios sv ON s.servicios_id = sv.id
JOIN MaxMinConsumo m ON s.year = m.year AND s.week = m.week AND s.total_consumo = m.max_consumo
JOIN SemanaConsumo s1 ON s1.year = m.year AND s1.week = m.week AND s1.total_consumo = m.min_consumo
JOIN servicios sv1 ON s1.servicios_id = sv1.id;
WITH SemanaHabitacion AS (
SELECT TO_NUMBER(TO_CHAR(fechainicio, 'IYYY')) AS year,TO_NUMBER(TO_CHAR(fechainicio, 'IW')) AS week,TRUNC(fechainicio, 'IW') - 1 AS start_date,TRUNC(fechainicio, 'IW') + 5 AS end_date, habitaciones_id,COUNT(id) AS total_reservas
FROM reservashabitaciones
GROUP BY  TO_NUMBER(TO_CHAR(fechainicio, 'IYYY')),TO_NUMBER(TO_CHAR(fechainicio, 'IW')),TRUNC(fechainicio, 'IW'), habitaciones_id),
MaxMinHabitacion AS (SELECT year,week,start_date,end_date,MAX(total_reservas) AS max_reservas,MIN(total_reservas) AS min_reservas
FROM SemanaHabitacion
GROUP BY year, week, start_date, end_date)
SELECT h.year,h.week,h.start_date,h.end_date,hab.numero,m.max_reservas,hab1.numero,m.min_reservas 
FROM SemanaHabitacion h
JOIN habitaciones hab ON h.habitaciones_id = hab.id
JOIN MaxMinHabitacion m ON h.year = m.year AND h.week = m.week AND h.total_reservas = m.max_reservas
JOIN SemanaHabitacion h1 ON h1.year = m.year AND h1.week = m.week AND h1.total_reservas = m.min_reservas
JOIN habitaciones hab1 ON h1.habitaciones_id = hab1.id;

--Fallido
WITH SemanaConsumo AS (
SELECT TO_NUMBER(TO_CHAR(fechaconsumo, 'IYYY')) AS year, TO_NUMBER(TO_CHAR(fechaconsumo, 'IW')) AS week,TRUNC(fechaconsumo, 'IW') - 1 AS start_date,TRUNC(fechaconsumo, 'IW') + 5 AS end_date,  servicios_id, count(*) AS total_consumo
FROM consumos
GROUP BY TO_NUMBER(TO_CHAR(fechaconsumo, 'IYYY')),TO_NUMBER(TO_CHAR(fechaconsumo, 'IW')),TRUNC(fechaconsumo, 'IW'),servicios_id),
MaxMinConsumo AS (
SELECT year,week,start_date,end_date, MAX(total_consumo) AS max_consumo,MIN(total_consumo) AS min_consumo
FROM SemanaConsumo
GROUP BY year, week, start_date, end_date)
SELECT s.year,s.week,s.start_date,s.end_date,sv.nombre AS servicio_mas_consumido_nombre,m.max_consumo AS max_consumo_servicio, sv1.nombre AS servicio_menos_consumido_nombre,m.min_consumo AS min_consumo_servicio
FROM SemanaConsumo s
JOIN servicios sv ON s.servicios_id = sv.id
JOIN MaxMinConsumo m ON s.year = m.year AND s.week = m.week AND s.total_consumo = m.max_consumo
JOIN SemanaConsumo s1 ON s1.year = m.year AND s1.week = m.week AND s1.total_consumo = m.min_consumo
JOIN servicios sv1 ON s1.servicios_id = sv1.id;
WITH SemanaHabitacion AS (
SELECT TO_NUMBER(TO_CHAR(fechainicio, 'IYYY')) AS year,TO_NUMBER(TO_CHAR(fechainicio, 'IW')) AS week,TRUNC(fechainicio, 'IW') - 1 AS start_date,TRUNC(fechainicio, 'IW') + 5 AS end_date, habitaciones_id,COUNT(id) AS total_reservas
FROM reservashabitaciones
GROUP BY  TO_NUMBER(TO_CHAR(fechainicio, 'IYYY')),TO_NUMBER(TO_CHAR(fechainicio, 'IW')),TRUNC(fechainicio, 'IW'), habitaciones_id),
MaxMinHabitacion AS (SELECT year,week,start_date,end_date,MAX(total_reservas) AS max_reservas,MIN(total_reservas) AS min_reservas
FROM SemanaHabitacion
GROUP BY year, week, start_date, end_date)
SELECT h.year,h.week,h.start_date,h.end_date,hab.numero,m.max_reservas,hab1.numero,m.min_reservas 
FROM SemanaHabitacion h
JOIN habitaciones hab ON h.habitaciones_id = hab.id
JOIN MaxMinHabitacion m ON h.year = m.year AND h.week = m.week AND h.total_reservas = m.max_reservas
JOIN SemanaHabitacion h1 ON h1.year = m.year AND h1.week = m.week AND h1.total_reservas = m.min_reservas
JOIN habitaciones hab1 ON h1.habitaciones_id = hab1.id;


--RFC 12

--Exitoso
WITH ClientesExcelentes AS (
    (SELECT r.usuarios_id, 'Estancias por trimestre' AS Razon 
    FROM reservashabitaciones r 
    GROUP BY r.usuarios_id 
    HAVING COUNT(DISTINCT TRUNC(r.fechainicio, 'Q')) = 4)
    UNION
    (SELECT c.usuarios_id, 'Servicio costoso' AS Razon 
    FROM consumos c 
    JOIN servicios s ON c.servicios_id = s.id 
    WHERE s.costoporunidad > 300000 
    GROUP BY c.usuarios_id 
    HAVING COUNT(DISTINCT c.id) = COUNT(DISTINCT c.reservashabitaciones_id))
    UNION
    (SELECT r.usuarios_id, 'Servicio SPA o Salón > 4 horas' AS Razon 
    FROM reservasservicios r 
    JOIN servicios s ON r.servicios_id = s.id 
    WHERE (s.nombre = 'SPA' OR s.tiposervicio = 'Salon de reuniones') 
        AND (r.fechafin - r.fechainicio) * 24 > 4 
    GROUP BY r.usuarios_id 
    HAVING COUNT(DISTINCT r.id) = COUNT(DISTINCT r.servicios_id))
)
SELECT u.id, u.nombre, ce.Razon
FROM usuarios u
JOIN ClientesExcelentes ce ON u.id = ce.usuarios_id
ORDER BY u.id, u.nombre;

--Fallido (condiciones que no se pueden cumplir)
WITH ClientesExcelentes AS (
    (SELECT r.usuarios_id, 'Estancias por trimestre' AS Razon 
    FROM reservashabitaciones r 
    GROUP BY r.usuarios_id 
    HAVING COUNT(DISTINCT TRUNC(r.fechainicio, 'Q')) = 5)
    UNION
    (SELECT c.usuarios_id, 'Servicio costoso' AS Razon 
    FROM consumos c 
    JOIN servicios s ON c.servicios_id = s.id 
    WHERE s.costoporunidad > 100e100
    GROUP BY c.usuarios_id 
    HAVING COUNT(DISTINCT c.id) = COUNT(DISTINCT c.reservashabitaciones_id))
    UNION
    (SELECT r.usuarios_id, 'Servicio SPA o Salón > 4 horas' AS Razon 
    FROM reservasservicios r 
    JOIN servicios s ON r.servicios_id = s.id 
    WHERE (s.nombre = 'SPA' OR s.tiposervicio = 'Salon de reuniones') 
        AND (r.fechafin - r.fechainicio) * 24 > 10e100 
    GROUP BY r.usuarios_id 
    HAVING COUNT(DISTINCT r.id) = COUNT(DISTINCT r.servicios_id))
)
SELECT u.id, u.nombre, ce.Razon
FROM usuarios u
JOIN ClientesExcelentes ce ON u.id = ce.usuarios_id

ORDER BY u.id, u.nombre;













