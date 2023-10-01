package uniandes.edu.co.proyecto.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Habitacion;


public interface HabitacionRepository extends JpaRepository<Habitacion, Integer> {

    @Query(value ="select * from habitaciones", nativeQuery = true)
    Collection<Habitacion> darHabitaciones();

    @Query(value = "SELECT * FROM habitaciones WHERE id = :id", nativeQuery = true)
    Habitacion darHabitacion(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM habitaciones WHERE id = :id", nativeQuery = true)
    void eliminarHabitacion(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE habitaciones SET capacidad = :capacidad, costoalojamiento = :costoalojamiento, numero = :numero, tiposhabitaciones_id = :tiposhabitaciones_id  WHERE id = :id", nativeQuery = true)
    void actualizarHabitacion(@Param("id") long id, @Param("capacidad") Integer capacidad, @Param("costoalojamiento") Integer costoalojamiento, @Param("numero") Integer numero, @Param("tiposhabitaciones_id") Integer tiposhabitaciones_id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO habitaciones (id, capacidad,costoalojamiento,numero,tiposhabitaciones_id) VALUES ( habitacionessecuencia.nextval , :capacidad, :costoalojamiento, :numero, :tiposhabitaciones_id)", nativeQuery = true)
    void insertarHabitacion(@Param("capacidad") Integer capacidad, @Param("costoalojamiento") Integer costoalojamiento, @Param("numero") Integer numero, @Param("tiposhabitaciones_id") Integer tiposhabitaciones_id);

    
}
