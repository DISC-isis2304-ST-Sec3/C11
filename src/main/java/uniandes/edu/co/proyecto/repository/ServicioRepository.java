package uniandes.edu.co.proyecto.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.*;

public interface ServicioRepository extends JpaRepository<Servicio, Integer>{
    @Query(value = "SELECT * FROM servicios", nativeQuery = true)
    Collection<Servicio> darServicios();

    @Query(value = "SELECT * FROM servicios WHERE id = :id", nativeQuery = true)
    Servicio darServicio(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM servicios WHERE id = :id", nativeQuery = true)
    void eliminarServicio(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE servicios SET nombre = :nombre, descripcion = :descripcion, costoporunidad = :costoporunidad, unidad = :unidad, horario = :horario, tiposervicio = :tiposervicio, capacidad = :capacidad  WHERE id = :id", nativeQuery = true)
    void actualizarServicio(@Param("id") long id, @Param("nombre") String nombre, @Param("descripcion") String descripcion, @Param("costoporunidad") Integer costoporunidad, @Param("horario") String horario, @Param("tiposervicio") String tiposervicio, @Param("capacidad") Integer capacidad);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO servicios (id, nombre,descripcion,costoporunidad,unidad,horario,tiposervicio,capacidad) VALUES ( sequence.nextval , :nombre, :descripcion, :costoporunidad, :unidad, :horario, :tiposervicio, :capacidad)", nativeQuery = true)
    void insertarServicio(@Param("nombre") String nombre, @Param("descripcion") String descripcion, @Param("costoporunidad") Integer costoporunidad, @Param("horario") String horario, @Param("tiposervicio") String tiposervicio, @Param("capacidad") Integer capacidad);

}
