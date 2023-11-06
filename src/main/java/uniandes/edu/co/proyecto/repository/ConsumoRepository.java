package uniandes.edu.co.proyecto.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.*;


public interface ConsumoRepository extends JpaRepository<Consumo, Integer> {
    @Query(value = "SELECT * FROM consumos", nativeQuery = true)
    Collection<Consumo> darConsumos();

    @Query(value = "SELECT * FROM consumos WHERE id = :id", nativeQuery = true)
    Consumo darConsumo(@Param("id") Integer id);

    @Query(value = "SELECT * FROM consumos WHERE ReservasHabitaciones_id = :id", nativeQuery = true)
    Consumo darConsumoPorReservaHabitacion(@Param("id") Integer id);

    @Query(value = "SELECT * FROM consumos WHERE ReservasServicios_id = :id", nativeQuery = true)
    Consumo darConsumoPorReservaServicio(@Param("id") Integer id);


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM consumos WHERE id = :id", nativeQuery = true)
    void eliminarConsumo(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE consumos SET sumaTotal = :sumaTotal, numConsumos = :numConsumos,nombre = :nombre, ReservasHabitaciones_id = :reservaHabitacion_id, servicios_id = :servicio_id WHERE id = :id", nativeQuery = true)
    void actualizarConsumo(@Param("id") Integer id, @Param("sumaTotal") Integer sumaTotal, @Param("numConsumos") Integer numConsumos, @Param("nombre") String nombre, @Param("reservaHabitacion_id") Integer reservaHabitacion_id, @Param("servicio_id") Integer servicio_id);

    

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO consumos (id, sumaTotal, numConsumos, nombre, reservashabitaciones_id, servicios_id, fechaconsumo, usuarios_id) VALUES (consumossecuencia.nextval , :sumaTotal, :numConsumos, :nombre, :reservaHabitacion, :servicios_id, TO_DATE(:fechaconsumo, 'YYYY-MM-DD'), :usuarios_id)", nativeQuery = true)
    void insertarConsumo(@Param("sumaTotal") Integer sumaTotal, @Param("numConsumos") Integer numConsumos, @Param("nombre") String nombre, @Param("reservaHabitacion") Integer reservaHabitacion, @Param("servicios_id") Integer servicios_id, @Param("fechaconsumo") String fechaconsumo, @Param("usuarios_id") long usuarios_id);

    @Query(value = "select * from consumos where reservashabitaciones_id = :id", nativeQuery = true)
    Collection<Consumo> darConsumosReservaHabitacion(@Param("id") Integer id);

    @Query(value = "SELECT h.id ,h.numero, COALESCE(SUM(c.sumatotal),0) "+
                    "FROM habitaciones h "+
                    "LEFT JOIN reservashabitaciones r ON h.id = r.habitaciones_id "+
                    "AND r.fechainicio BETWEEN ADD_MONTHS(SYSDATE, -12) AND SYSDATE "+
                    "LEFT JOIN consumos c ON r.id = c.reservashabitaciones_id "+
                    "GROUP BY h.id, h.numero "+
                    "ORDER BY h.id",
                    nativeQuery = true)
    List<Object[]> RFC1();

    @Query(value =  "SELECT s.id,s.nombre,COUNT(c.id)" +
                    "FROM servicios s "+
                    "JOIN consumos c ON s.id = c.servicios_id "+
                    "WHERE EXISTS (SELECT 1 FROM reservashabitaciones r WHERE c.reservashabitaciones_id = r.id AND r.fechainicio BETWEEN TO_DATE(:fecha1, 'YYYY-MM-DD') AND TO_DATE(:fecha2, 'YYYY-MM-DD')) "+ 
                    "GROUP BY s.id, s.nombre "+
                    "ORDER BY count(c.id) DESC "+
                    "FETCH FIRST 20 ROWS ONLY", nativeQuery = true)
    List<Object[]> RFC2(@Param("fecha1") String fecha1, @Param("fecha2") String fecha2);

    @Query(value = "SELECT  u.id AS usuarios_id, u.nombre AS nombre, s.nombre AS producto, c.sumatotal AS precio FROM consumos c "+
            "JOIN reservashabitaciones rh ON c.reservashabitaciones_id = rh.id "+
            "JOIN servicios s ON c.servicios_id = s.id JOIN usuarios u ON rh.usuarios_id = u.id "+
            "WHERE  u.id = :usuario_id AND rh.fechainicio BETWEEN TO_DATE(:fecha1,'YYYY-MM-DD') AND TO_DATE(:fecha2,'YYYY-MM-DD')", nativeQuery =  true)
List<Object[]> RFC5(@Param("usuario_id") Long usuario_id, @Param("fecha1") String fecha1, @Param("fecha2") String fecha2);
   
    @Query(value = "SELECT s.nombre,  COUNT(*) "+
            "FROM Consumos c, servicios s "+
            "WHERE (c.fechaconsumo >= ADD_MONTHS(SYSDATE, -12) and c.servicios_id = s.id)  "+
            "GROUP BY s.nombre, TO_CHAR(c.FECHACONSUMO, 'IYYY-IW') " +
            "HAVING COUNT(*) < 3", nativeQuery = true)
    List<Object[]> RFC8();

    @Query(value = "select nombre from servicios where id not in (select servicios_id from consumos)", nativeQuery = true)
    List<Object[]> RFC8AUX();

    @Query(value = "SELECT  u.nombre,u.numdocumento,s.nombre,COUNT(c.numconsumos) AS cuenta "+
                    "FROM consumos c "+
                    "JOIN usuarios u ON c.usuarios_id = u.id JOIN servicios s ON c.servicios_id = s.id "+
                    "WHERE c.fechaconsumo BETWEEN TO_DATE(:fecha1,'YYYY-MM-DD') AND TO_DATE(:fecha2,'YYYY-MM-DD') AND s.id = :servicio_id "+
                    "GROUP BY DECODE(:agrupamiento, 'usuario', u.nombre, 'documento', u.numdocumento),u.nombre,s.nombre, u.numdocumento "+
                    "ORDER BY DECODE(:ordenamiento, 'usuario', u.nombre,'documento', u.numdocumento, 'count', cuenta) ", nativeQuery = true)
    List<Object[]> RFC9(@Param("fecha1") String fecha1,@Param("fecha2") String fecha2, @Param("agrupamiento") String agrupamiento, @Param("ordenamiento") String ordenamiento, @Param("servicio_id") String servicio_id);
    
    @Query(value = "SELECT  u.id ,u.nombre,u.numdocumento from usuarios u "+
                    "where u.id not in (select u.id  from consumos c JOIN usuarios u ON c.usuarios_id = u.id JOIN servicios s ON c.servicios_id = s.id "+
                    "WHERE c.fechaconsumo BETWEEN TO_DATE(:fecha1,'YYYY-MM-DD') AND TO_DATE(:fecha2,'YYYY-MM-DD') AND s.id = :servicio_id) "+
                    "GROUP BY DECODE(:agrupamiento, 'usuario', u.nombre, 'documento', u.numdocumento, 'id', u.id),u.nombre,u.numdocumento,u.id "+
                    "ORDER BY DECODE(:ordenamiento, 'usuario', u.nombre,'documento', u.numdocumento, 'id', u.id)", nativeQuery =  true)
    List<Object[]> RFC10(@Param("fecha1") String fecha1,@Param("fecha2") String fecha2, @Param("agrupamiento") String agrupamiento, @Param("ordenamiento") String ordenamiento, @Param("servicio_id") String servicio_id);


    @Query(value = "WITH SemanaConsumo AS ( "+
                    "SELECT TO_NUMBER(TO_CHAR(fechaconsumo, 'IYYY')) AS year, TO_NUMBER(TO_CHAR(fechaconsumo, 'IW')) AS week,TRUNC(fechaconsumo, 'IW') - 1 AS start_date,TRUNC(fechaconsumo, 'IW') + 5 AS end_date,  servicios_id, decode(:razon, 'dinero', sum(sumatotal) , 'cant', count(*), 'num', sum(numconsumos)  ) AS total_consumo "+
                    "FROM consumos "+
                    "GROUP BY TO_NUMBER(TO_CHAR(fechaconsumo, 'IYYY')),TO_NUMBER(TO_CHAR(fechaconsumo, 'IW')),TRUNC(fechaconsumo, 'IW'),servicios_id), "+
                    "MaxMinConsumo AS ( "+
                    "SELECT year,week,start_date,end_date, MAX(total_consumo) AS max_consumo,MIN(total_consumo) AS min_consumo "+
                    "FROM SemanaConsumo "+
                    "GROUP BY year, week, start_date, end_date) "+
                    "SELECT s.year,s.week,s.start_date,s.end_date,sv.nombre AS servicio_mas_consumido_nombre,m.max_consumo AS max_consumo_servicio, sv1.nombre AS servicio_menos_consumido_nombre,m.min_consumo AS min_consumo_servicio "+
                    "FROM SemanaConsumo s "+
                    "JOIN servicios sv ON s.servicios_id = sv.id "+
                    "JOIN MaxMinConsumo m ON s.year = m.year AND s.week = m.week AND s.total_consumo = m.max_consumo "+
                    "JOIN SemanaConsumo s1 ON s1.year = m.year AND s1.week = m.week AND s1.total_consumo = m.min_consumo "+
                    "JOIN servicios sv1 ON s1.servicios_id = sv1.id", nativeQuery = true)
    List<Object[]> RFC11A(@Param("razon") String razon);

    @Query(value = "WITH SemanaHabitacion AS ( "+
                    "SELECT TO_NUMBER(TO_CHAR(fechainicio, 'IYYY')) AS year,TO_NUMBER(TO_CHAR(fechainicio, 'IW')) AS week,TRUNC(fechainicio, 'IW') - 1 AS start_date,TRUNC(fechainicio, 'IW') + 5 AS end_date, habitaciones_id,COUNT(id) AS total_reservas "+
                    "FROM reservashabitaciones "+
                    "GROUP BY  TO_NUMBER(TO_CHAR(fechainicio, 'IYYY')),TO_NUMBER(TO_CHAR(fechainicio, 'IW')),TRUNC(fechainicio, 'IW'), habitaciones_id), "+
                    "MaxMinHabitacion AS (SELECT year,week,start_date,end_date,MAX(total_reservas) AS max_reservas,MIN(total_reservas) AS min_reservas "+
                    "FROM SemanaHabitacion "+
                    "GROUP BY year, week, start_date, end_date) "+
                    "SELECT h.year,h.week,h.start_date,h.end_date,hab.numero,m.max_reservas,hab1.numero,m.min_reservas "+
                    "FROM SemanaHabitacion h "+
                    "JOIN habitaciones hab ON h.habitaciones_id = hab.id "+
                    "JOIN MaxMinHabitacion m ON h.year = m.year AND h.week = m.week AND h.total_reservas = m.max_reservas "+
                    "JOIN SemanaHabitacion h1 ON h1.year = m.year AND h1.week = m.week AND h1.total_reservas = m.min_reservas "+
                    "JOIN habitaciones hab1 ON h1.habitaciones_id = hab1.id",nativeQuery = true)
    List<Object> RFC11B(); 
}
