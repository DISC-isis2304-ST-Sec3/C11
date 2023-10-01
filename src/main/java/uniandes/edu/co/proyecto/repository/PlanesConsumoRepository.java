package uniandes.edu.co.proyecto.repository;


import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.PlanesConsumo;

public interface PlanesConsumoRepository extends JpaRepository<PlanesConsumo, Integer> {

    @Query(value = "SELECT * FROM planesdeconsumo", nativeQuery = true)
    Collection<PlanesConsumo> darPlanesConsumo();

    @Query(value = "SELECT * FROM planesdeconsumo WHERE id = :id", nativeQuery = true)
    PlanesConsumo darPlanConsumo(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM planesdeconsumo WHERE id = :id", nativeQuery = true)
    void eliminarPlanConsumo(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE planesdeconsumo SET nombre = :nombre, descuento = :descuento, descripcion = :descripcion WHERE id = :id", nativeQuery = true)
    void actualizarPlanConsumo(@Param("id") long id, @Param("nombre") String nombre, @Param("descuento") Integer costo, @Param("descripcion") String descripcion);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO planesdeconsumo (id, nombre,descuento,descripcion) VALUES ( planesdeconsumosecuencia.nextval , :nombre, :descuento, :descripcion)", nativeQuery = true)
    void insertarPlanConsumo(@Param("nombre") String nombre, @Param("descuento") Integer descuento, @Param("descripcion") String descripcion);

    

    
    
}
