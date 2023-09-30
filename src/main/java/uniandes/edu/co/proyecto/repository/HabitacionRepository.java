package uniandes.edu.co.proyecto.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Habitacion;


public interface HabitacionRepository extends JpaRepository<Habitacion, Integer> {

    @Query(value = "SELECT * FROM habitaciones", nativeQuery = true)
    Collection<Habitacion> darHabitaciones();

    @Query(value = "SELECT * FROM habitaciones WHERE id = :id", nativeQuery = true)
    Habitacion darHabitacion(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM habitaciones WHERE id = :id", nativeQuery = true)
    void eliminarHabitacion(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE habitaciones SET capacidad = :capacidad, costoalejamiento = :costoalejamiento, numero = :numero, tipohabitaciones_id = :tipohabitaciones_id  WHERE id = :id", nativeQuery = true)
    void actualizarHabitacion(@Param("id") long id, @Param("capacidad") Integer capacidad, @Param("costoalejamiento") Integer costoalejamiento, @Param("numero") Integer numero, @Param("tipohabitaciones_id") Integer tipohabitaciones_id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO habitaciones (id, capacidad,costoalejamiento,numero,tipohabitaciones_id) VALUES ( habitacionessecuencia.nextval , :capacidad, :costoalejamiento, :numero, :tipohabitaciones_id)", nativeQuery = true)
    void insertarHabitacion(@Param("capacidad") Integer capacidad, @Param("costoalejamiento") Integer costoalejamiento, @Param("numero") Integer numero, @Param("tipohabitaciones_id") Integer tipohabitaciones_id);

    
}
