package uniandes.edu.co.proyecto.repository;

import java.util.Collection;
import java.util.List;

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
    void actualizarServicio(@Param("id") long id, @Param("nombre") String nombre, @Param("descripcion") String descripcion, @Param("costoporunidad") Integer costoporunidad, @Param("unidad") String unidad,@Param("horario") String horario, @Param("tiposervicio") String tiposervicio, @Param("capacidad") Integer capacidad);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO servicios (id, nombre,descripcion,costoporunidad,unidad,horario,tiposervicio,capacidad) VALUES ( serviciossecuencia.nextval , :nombre, :descripcion, :costoporunidad, :unidad, :horario, :tiposervicio, :capacidad)", nativeQuery = true)
    void insertarServicio(@Param("nombre") String nombre, @Param("descripcion") String descripcion, @Param("costoporunidad") Integer costoporunidad, @Param("unidad") String unidad ,@Param("horario") String horario, @Param("tiposervicio") String tiposervicio, @Param("capacidad") Integer capacidad);

    @Query(value = "SELECT s.nombre, s.descripcion, s.costoporunidad, s.unidad, s.horario, s.tiposervicio, s.capacidad FROM servicios s LEFT JOIN consumos c ON s.id = c.servicios_id " +
    "WHERE (:costo1 IS NULL OR (s.costoporunidad BETWEEN :costo1 AND :costo2)) " +
    "AND (:fecha1 IS NULL OR (c.fechaconsumo BETWEEN to_date(:fecha1,'YYYY-MM-DD') AND to_date(:fecha2,'YYYY-MM-DD'))) " +
    "AND (:usuario_id IS NULL OR c.usuarios_id = :usuario_id) " +
    "AND (:tiposervicio IS NULL OR s.tiposervicio = :tiposervicio)", nativeQuery = true)
    List<Object[]> RFC4(@Param("costo1") String costo1,@Param("costo2") String costo2, @Param("fecha1") String fecha1, @Param("fecha2") String fecha2,@Param("tiposervicio") String tiposervicio, @Param("usuario_id") String usuario_id);
}
