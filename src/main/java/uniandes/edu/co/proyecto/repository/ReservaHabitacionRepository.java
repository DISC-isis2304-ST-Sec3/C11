package uniandes.edu.co.proyecto.repository;

import java.sql.Date;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import uniandes.edu.co.proyecto.modelo.ReservaHabitacion;

public interface ReservaHabitacionRepository extends JpaRepository<ReservaHabitacion, Integer>   {

    @Query(value = "SELECT * FROM reservashabitaciones", nativeQuery = true)
    Collection<ReservaHabitacion> darReservasServicios();

    @Query(value = "SELECT * FROM reservashabitaciones WHERE id = :id", nativeQuery = true)
    ReservaHabitacion darReservaHabitacion(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM reservashabitaciones WHERE id = :id", nativeQuery = true)
    void eliminarReservaHabitacion(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE reservashabitaciones SET numpersonas = :numpersonas, fechainicio = :fechainicio, fechafin = :fechafin, usuarios_id = :usuarios_id, habitaciones_id = :habitaciones_id WHERE id = :id", nativeQuery = true)
    void actualizarReservaHabitacion(@Param("id") long id, @Param("numpersonas") Integer numpersonas, @Param("fechainicio") Date fechainicio,@Param("fechafin") Date fechafin,@Param("usuarios_id") Integer usuarios_id,@Param("habitaciones_id") Integer habitaciones_id);
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO reservashabitaciones (id, numpersonas,fechainicio,fechafin,usuarios_id,habitaciones_id) VALUES ( reservashabitacionessecuencia.nextval , :nombre, :numpersonas, :fechainicio, :fechafin, :usuarios_id, :habitaciones_id)", nativeQuery = true)
    void insertarReservaHabitacion(@Param("numpersonas") Integer numpersonas, @Param("fechainicio") Date fechainicio,@Param("fechafin") Date fechafin,@Param("usuarios_id") Integer usuarios_id,@Param("habitaciones_id") Integer habitaciones_id);

    
}
