package uniandes.edu.co.proyecto.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Producto;
public interface ProductoRepository extends JpaRepository<Producto, Integer>{

    @Query(value = "SELECT * FROM productos", nativeQuery = true)
    Collection<Producto> darProductos();

    @Query(value = "SELECT * FROM productos WHERE id = :id", nativeQuery = true)
    Producto darProducto(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM productos WHERE id = :id", nativeQuery = true)
    void eliminarProducto(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE productos SET nombre = :nombre, costo = :costo, servicios_id = :servicios_id  WHERE id = :id", nativeQuery = true)
    void actualizarProducto(@Param("id") long id, @Param("nombre") String nombre, @Param("costo") Integer costo, @Param("servicios_id") Integer servicios_id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO productos (id, nombre,costo,servicios_id) VALUES ( sequence.nextval , :nombre, :costo, :servicios_id)", nativeQuery = true)
    void insertarProducto(@Param("nombre") String nombre, @Param("costo") Integer costo, @Param("servicios_id") Integer servicios_id);

    
}
