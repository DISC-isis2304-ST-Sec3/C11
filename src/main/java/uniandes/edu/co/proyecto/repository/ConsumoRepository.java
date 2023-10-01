package uniandes.edu.co.proyecto.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Consumo;


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
    @Query(value = "UPDATE consumos SET sumaTotal = :sumaTotal, numConsumos = :numConsumos,nombre = :nombre, ReservasHabitaciones_id = :reservaHabitacion_id, ReservasServicios_id = :reservaServicio_id WHERE id = :id", nativeQuery = true)
    void actualizarConsumo(@Param("id") Integer id, @Param("sumaTotal") Integer sumaTotal, @Param("numConsumos") Integer numConsumos, @Param("nombre") String nombre, @Param("reservaHabitacion") Integer reservaHabitacion, @Param("reservaServicio") Integer reservaServicio);

    

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO consumos (sumaTotal, numConsumos, nombre, ReservasHabitaciones_id, ReservasServicios_id) VALUES (:sumaTotal, :numConsumos, :nombre, :reservaHabitacion, :reservaServicio)", nativeQuery = true)
    void insertarConsumo(@Param("sumaTotal") Integer sumaTotal, @Param("numConsumos") Integer numConsumos, @Param("nombre") String nombre, @Param("reservaHabitacion") Integer reservaHabitacion, @Param("reservaServicio") Integer reservaServicio);

   
    
}
