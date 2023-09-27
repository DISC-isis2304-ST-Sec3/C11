package uniandes.edu.co.proyecto.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import uniandes.edu.co.proyecto.modelo.*;

public interface ElementosATiposRepository extends JpaRepository<ElementoATipo, Integer>{
    
    @Query(value = "SELECT * FROM elementosatipos", nativeQuery = true)
    Collection<ElementoATipo> darElementosATipos();

    @Query(value = "SELECT * FROM elementosatipos WHERE elementoshab_id = :elementoshab_id AND tiposhabitaciones_id = :tiposhabitaciones_id", nativeQuery = true)
    ElementoATipo darElementosATiposPorId(@Param("elementoshab_id") Integer elementosHab_id, @Param("tiposhabitaciones_id") Integer tiposhabitaciones_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM elementosatipos WHERE elementoshab_id = :elementoshab_id AND tiposhabitaciones_id = :tiposhabitaciones_id", nativeQuery = true)
    void eliminarElementosATipos(@Param("elementoshab_id") Integer elementoshab_id, @Param("tiposhabitaciones_id") Integer tiposhabitaciones_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE elementosatipos SET elementoshab_id = :elementoshab_id_actualizado, tiposhabitaciones_id = :tiposhabitaciones_id_actualizado WHERE elementoshab_id = :elementoshab_id AND tiposhabitaciones_id = :tiposhabitaciones_id", nativeQuery = true)
    void actualizarElementosATipos(@Param("elementoshab_id") Integer elementoshab_id, @Param("tiposhabitaciones_id") Integer tiposhabitaciones_id, @Param("elementoshab_id_actualizado") Integer elementoshab_id_actualizado, @Param("tiposhabitaciones_id_actualizado") Integer tiposhabitaciones_id_actualizado);
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO elementosatipos (elementoshab_id, tiposhabitaciones_id) VALUES (:elementoshab_id, :tiposhabitaciones_id)", nativeQuery = true)
    void insertarElementosATipos(@Param("elementoshab_id") Integer elementoshab_id, @Param("tiposhabitaciones_id") Integer tiposhabitaciones_id);


}
