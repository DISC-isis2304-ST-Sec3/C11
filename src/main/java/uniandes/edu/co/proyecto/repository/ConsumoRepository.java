package uniandes.edu.co.proyecto.repository;

import java.util.Collection;

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

    @Query(value = "Select  reservashabitaciones.habitaciones_id, sum (consumos.sumatotal) as sum " + 
    "from reservashabitaciones inner join consumos on consumos.reservashabitaciones_id=reservashabitaciones.id " +
    "where reservashabitaciones.fechainicio > TO_DATE('2023/01/01','yyyy/mm/dd') and reservashabitaciones.fechafin < current_date "
    +"group by reservashabitaciones.habitaciones_id",
    nativeQuery = true)
    Collection<RFC1> RFC1();
   
    
}
