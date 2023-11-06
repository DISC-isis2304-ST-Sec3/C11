SELECT u.id,u.nombre,s.nombre ,c.sumatotal
FROM consumos c
JOIN reservashabitaciones rh ON c.reservashabitaciones_id = rh.id
JOIN servicios s ON c.servicios_id = s.id
JOIN usuarios u ON rh.usuarios_id = u.id
WHERE  u.id = :usuarios_id
AND rh.fechainicio BETWEEN TO_DATE(:fecha1,'YYYY-MM-DD') AND TO_DATE(:fecha2,'YYYY-MM-DD'); --req  5   
    
SELECT h.id,h.numero AS,COALESCE(ROUND(100 * SUM(NVL(r.fechafin, SYSDATE) - r.fechainicio) / 365, 2), 0)
FROM habitaciones h
LEFT JOIN reservashabitaciones r ON h.id = r.habitaciones_id AND r.fechainicio BETWEEN ADD_MONTHS(SYSDATE, -12) AND SYSDATE
GROUP BY  h.id, h.numero
ORDER BY h.id; --req 3

SELECT h.id ,h.numero, COALESCE(SUM(c.sumatotal),0)
FROM habitaciones h
LEFT JOIN reservashabitaciones r ON h.id = r.habitaciones_id
AND r.fechainicio BETWEEN ADD_MONTHS(SYSDATE, -12) AND SYSDATE
LEFT JOIN consumos c ON r.id = c.reservashabitaciones_id
GROUP BY h.id, h.numero
ORDER BY h.id; --req1

SELECT s.id,s.nombre,COUNT(c.id)
FROM servicios s
JOIN consumos c ON s.id = c.servicios_id
WHERE EXISTS (SELECT 1 FROM reservashabitaciones r WHERE c.reservashabitaciones_id = r.id AND r.fechainicio BETWEEN TO_DATE('2021-01-01', 'YYYY-MM-DD') AND TO_DATE('2025-01-01', 'YYYY-MM-DD'))
GROUP BY s.id, s.nombre
ORDER BY count(c.id) DESC
FETCH FIRST 20 ROWS ONLY; --req 2


WITH DIAS AS(
SELECT usuarios.nombre as id, SUM(reservashabitaciones.fechafin - reservashabitaciones.fechainicio) as diashotel
FROM usuarios, reservashabitaciones
WHERE reservashabitaciones.usuarios_id = usuarios.id 
GROUP BY usuarios.nombre), gastos as(
select usuarios.nombre as id, sum(consumos.sumatotal) as gasto from consumos, usuarios,reservashabitaciones
where usuarios.id = reservashabitaciones.usuarios_id and consumos.reservashabitaciones_id  = reservashabitaciones.id
group by usuarios.nombre)
Select gastos.id,gastos.gasto,dias.diashotel from gastos,dias 
where dias.id = gastos.id and (gastos.gasto >= 15000000 or dias.diashotel >= 14); --req 7

--req 6
SELECT fechainicio, COUNT(*) AS ocupacion
FROM reservashabitaciones
GROUP BY fechainicio
ORDER BY ocupacion DESC
FETCH FIRST 10 ROWS ONLY;
SELECT c.fechaconsumo, SUM(c.sumatotal) AS ingresos
FROM consumos c
GROUP BY c.fechaconsumo
ORDER BY ingresos DESC
FETCH FIRST 10 ROWS ONLY;
SELECT fechainicio, COUNT(*) AS ocupacion
FROM reservashabitaciones
GROUP BY fechainicio
ORDER BY ocupacion ASC
FETCH FIRST 10 ROWS ONLY;
--fin req 6

select s.nombre, s.descripcion, s.costoporunidad, s.unidad, s.horario, s.tiposervicio, s.capacidad from servicios s,consumos c
where costoporunidad Between 0 and 1 and
s.id = c.servicios_id and c.fechaconsumo between to_date('2020-01-01','YYYY-MM-DD') and to_date('2025-01-01','YYYY-MM-DD') and
c.usuarios_id = 12 and s.tiposervicio = 'joyas'; --req 4


SELECT s.nombre,  COUNT(*) 
FROM Consumos c, servicios s 
WHERE (c.fechaconsumo >= ADD_MONTHS(SYSDATE, -12) and c.servicios_id = s.id) 
GROUP BY s.nombre, TO_CHAR(c.FECHACONSUMO, 'IYYY-IW')
HAVING COUNT(*) < 3; -- req 8 

SELECT  u.nombre,u.numdocumento,s.nombre,COUNT(c.numconsumos) AS cuenta
FROM consumos c
JOIN usuarios u ON c.usuarios_id = u.id JOIN servicios s ON c.servicios_id = s.id
WHERE c.fechaconsumo BETWEEN TO_DATE(:fecha1,'YYYY-MM-DD') AND TO_DATE(:fecha2,'YYYY-MM-DD') AND s.id = :servicio_id
GROUP BY DECODE(:agrupamiento, 'usuario', u.nombre, 'documento', u.numdocumento),u.nombre,s.nombre, u.numdocumento
ORDER BY DECODE(:ordenamiento, 'usuario', u.nombre,'documento', u.numdocumento, 'count', cuenta); --req 9 


SELECT  u.id ,u.nombre,u.numdocumento from usuarios u
where u.id not in (select u.id  from consumos c JOIN usuarios u ON c.usuarios_id = u.id JOIN servicios s ON c.servicios_id = s.id
                        WHERE c.fechaconsumo BETWEEN TO_DATE(:fecha1,'YYYY-MM-DD') AND TO_DATE(:fecha2,'YYYY-MM-DD') AND s.id = :servicio_id)
GROUP BY DECODE(:agrupamiento, 'usuario', u.nombre, 'documento', u.numdocumento, 'id', u.id),u.nombre,u.numdocumento,u.id
ORDER BY DECODE(:ordenamiento, 'usuario', u.nombre,'documento', u.numdocumento, 'id', u.id); --req 10


--req 11
WITH SemanaConsumo AS (
SELECT TO_NUMBER(TO_CHAR(fechaconsumo, 'IYYY')) AS year, TO_NUMBER(TO_CHAR(fechaconsumo, 'IW')) AS week,TRUNC(fechaconsumo, 'IW') - 1 AS start_date,TRUNC(fechaconsumo, 'IW') + 5 AS end_date,  servicios_id, decode(:razon, 'dinero', sum(sumatotal) , 'cant', count(*), 'num', sum(numconsumos)  ) AS total_consumo
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

--req 11


SELECT DISTINCT u.id, u.nombre, u.numdocumento, u.email FROM usuarios u
WHERE u.id IN (SELECT r.usuarios_id FROM reservashabitaciones r GROUP BY r.usuarios_id HAVING COUNT(DISTINCT TRUNC(r.fechainicio, 'Q')) = 4)
OR u.id IN (SELECT c.usuarios_id FROM consumos c JOIN servicios s ON c.servicios_id = s.id WHERE s.costoporunidad > 300000 GROUP BY c.usuarios_id HAVING COUNT(DISTINCT c.id) = COUNT(DISTINCT c.reservashabitaciones_id))
OR u.id IN (SELECT r.usuarios_id FROM reservasservicios r JOIN servicios s ON r.servicios_id = s.id WHERE (s.nombre = 'SPA' OR s.tiposervicio = 'Salon de reuniones') AND (r.fechafin - r.fechainicio) * 24 > 4 GROUP BY r.usuarios_id HAVING COUNT(DISTINCT r.id) = COUNT(DISTINCT r.servicios_id)); 
---req 12



