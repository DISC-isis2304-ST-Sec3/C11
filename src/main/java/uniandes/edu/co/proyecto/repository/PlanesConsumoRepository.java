package uniandes.edu.co.proyecto.repository;


import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PlanesConsumoRepository extends JpaRepository<PlanesConsumoRepository, Integer> {

    @Query(value = "SELECT * FROM planesconsumo", nativeQuery = true)
    Collection<PlanesConsumoRepository> darPlanesConsumo();

    @Query(value = "SELECT * FROM planesconsumo WHERE id = :id", nativeQuery = true)
    PlanesConsumoRepository darPlanConsumo(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM planesconsumo WHERE id = :id", nativeQuery = true)
    void eliminarPlanConsumo(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE planesconsumo SET nombre = :nombre, descuento = :descuento, descripcion = :descripcion WHERE id = :id", nativeQuery = true)
    void actualizarPlanConsumo(@Param("id") long id, @Param("nombre") String nombre, @Param("descuento") Integer costo, @Param("descripcion") String descripcion);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO planesconsumo (id, nombre,descuento,descripcion) VALUES ( planesconsumosecuencia.nextval , :nombre, :descuento, :descripcion)", nativeQuery = true)
    void insertarPlanConsumo(@Param("nombre") String nombre, @Param("descuento") Integer descuento, @Param("descripcion") String descripcion);

    

    
    
}
