package uniandes.edu.co.proyecto.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import uniandes.edu.co.proyecto.modelo.*;

public interface ElemATipRepository extends JpaRepository<ElemATip, Integer>{
    
    @Query(value = "SELECT * FROM elematip", nativeQuery = true)
    Collection<ElemATip> darElementosATipos();

    @Query(value = "SELECT * FROM elematip WHERE elementoshab_id = :elementoshab_id AND tiposhabitaciones_id = :tiposhabitaciones_id", nativeQuery = true)
    ElemATip darElemATipPorId(@Param("elementoshab_id") Integer elementosHab_id, @Param("tiposhabitaciones_id") Integer tiposhabitaciones_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM elematip WHERE elementoshab_id = :elementoshab_id AND tiposhabitaciones_id = :tiposhabitaciones_id", nativeQuery = true)
    void eliminarElemATip(@Param("elementoshab_id") Integer elementoshab_id, @Param("tiposhabitaciones_id") Integer tiposhabitaciones_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE elematip SET elementoshab_id = :elementoshab_id_actualizado, tiposhabitaciones_id = :tiposhabitaciones_id_actualizado WHERE elementoshab_id = :elementoshab_id AND tiposhabitaciones_id = :tiposhabitaciones_id", nativeQuery = true)
    void actualizarElemATip(@Param("elementoshab_id") Integer elementoshab_id, @Param("tiposhabitaciones_id") Integer tiposhabitaciones_id, @Param("elementoshab_id_actualizado") Integer elementoshab_id_actualizado, @Param("tiposhabitaciones_id_actualizado") Integer tiposhabitaciones_id_actualizado);
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO elematip (elementoshab_id, tiposhabitaciones_id) VALUES (:elementoshab_id, :tiposhabitaciones_id)", nativeQuery = true)
    void insertarElemATip(@Param("elementoshab_id") Integer elementoshab_id, @Param("tiposhabitaciones_id") Integer tiposhabitaciones_id);


}
