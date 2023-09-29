package uniandes.edu.co.proyecto.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import uniandes.edu.co.proyecto.modelo.TipoHabitacion;


public interface TipoHabitacionRepository extends JpaRepository<TipoHabitacion,Integer>{
    
    @Query(value = "SELECT * FROM tiposhabitaciones", nativeQuery = true)
    Collection<TipoHabitacion> darTiposHabitaciones();

    @Query(value = "SELECT * FROM tiposhabitaciones WHERE id = :id", nativeQuery = true)
    TipoHabitacion darTipoHabitacion(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tiposhabitaciones WHERE id = :id", nativeQuery = true)
    void eliminarTipoHabitacion(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE tiposhabitaciones SET nombre = :nombre, capacidad = :capacidad  WHERE id = :id", nativeQuery = true)
    void actualizarTipoHabitacion(@Param("id") long id, @Param("nombre") String nombre, @Param("capacidad") Integer capacidad);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tiposhabitaciones (id, nombre,capacidad) VALUES ( tiposhabitacionessecuencia.nextval , :nombre, :capacidad)", nativeQuery = true)
    void insertarTipoHabitacion(@Param("nombre") String nombre, @Param("capacidad") Integer capacidad);

}
