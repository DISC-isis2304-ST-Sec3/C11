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
    @Query(value = "INSERT INTO consumos (id, sumaTotal, numConsumos, nombre, reservashabitaciones_id, servicios_id) VALUES (consumossecuencia.nextval , :sumaTotal, :numConsumos, :nombre, :reservaHabitacion, :servicios_id)", nativeQuery = true)
    void insertarConsumo(@Param("sumaTotal") Integer sumaTotal, @Param("numConsumos") Integer numConsumos, @Param("nombre") String nombre, @Param("reservaHabitacion") Integer reservaHabitacion, @Param("servicios_id") Integer servicios_id);

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

    @Query(value = "select servicios.id,servicios.nombre, count  (*) as num_reservas " +
    "from  reservasservicios inner join servicios on servicios.id=reservasservicios.servicios_id "  + 
    "where reservasservicios.fechainicio > TO_DATE('2023/01/01','yyyy/mm/dd') " +
    "and reservasservicios.fechafin < current_date " + 
    "group by servicios.id,servicios.nombre " + 
    "order by num_reservas desc", nativeQuery = true)
    List<Object[]> RFC2(@Param("fecha1") String fecha1, @Param("fecha2") String fecha2);

    @Query(value = "SELECT  u.id AS usuarios_id, u.nombre AS nombre, s.nombre AS producto, c.sumatotal AS precio FROM consumos c "+
            "JOIN reservashabitaciones rh ON c.reservashabitaciones_id = rh.id "+
            "JOIN servicios s ON c.servicios_id = s.id JOIN usuarios u ON rh.usuarios_id = u.id "+
            "WHERE  u.id = :usuario_id AND rh.fechainicio BETWEEN TO_DATE(:fecha1,'YYYY-MM-DD') AND TO_DATE(:fecha2,'YYYY-MM-DD')", nativeQuery =  true)
List<Object[]> RFC5(@Param("usuario_id") Long usuario_id, @Param("fecha1") String fecha1, @Param("fecha2") String fecha2);
   
    
}
