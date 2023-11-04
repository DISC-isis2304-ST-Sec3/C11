package uniandes.edu.co.proyecto.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import uniandes.edu.co.proyecto.modelo.Usuario;
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>  {
    @Query(value = "SELECT * FROM usuarios", nativeQuery = true)
    Collection<Usuario> darUsuarios();

    @Query(value = "SELECT * FROM usuarios WHERE id = :id", nativeQuery = true)
    Usuario darUsuario(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM usuarios WHERE id = :id", nativeQuery = true)
    void eliminarUsuario(@Param("id") long id);


    @Modifying
    @Transactional
    @Query(value = "UPDATE usuarios SET nombreUsuario = :nombreUsuario, contrasena = :contrasena, nombre = :nombre, numDocumento = :numDocumento, email = :email, tipoDocumento = :tipoDocumento, tiposdeusuario_id = :tiposdeusuario_id WHERE id = :id", nativeQuery = true)
    void actualizarUsuario(@Param("id") long id, @Param("nombreUsuario") String nombreUsuario, @Param("contrasena") String contrasena, @Param("nombre") String nombre, @Param("numDocumento") long numDocumento,
                         @Param("email") String email, @Param("tipoDocumento") String tipoDocumento, @Param("tiposdeusuario_id") Integer tiposdeusuario_id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO usuarios (id, nombreUsuario, contrasena, nombre, numDocumento, email, tipoDocumento, tiposdeusuario_id) VALUES ( usuariossecuencia.nextval , :nombreUsuario, :contrasena, :nombre, :numDocumento, :email, :tipoDocumento, :tiposdeusuario_id)", nativeQuery = true)
    void insertarUsuario(@Param("nombreUsuario") String nombreUsuario, @Param("contrasena") String contrasena, @Param("nombre") String nombre, @Param("numDocumento") long numDocumento,
                             @Param("email") String email, @Param("tipoDocumento") String tipoDocumento, @Param("tiposdeusuario_id") Integer tiposdeusuario_id);

    @Query(value = "SELECT * FROM Usuarios  WHERE (contrasena = :contrasena AND nombreUsuario = :nombreUsuario)", nativeQuery = true)
    Usuario encontrarUsuarioPorUsuarioYcontrasena(@Param("nombreUsuario") String nombreUsuario, @Param("contrasena") String contrasena);

    @Query(value = "WITH DIAS AS( " + 
            "SELECT usuarios.nombre as id, SUM(reservashabitaciones.fechafin - reservashabitaciones.fechainicio) as diashotel "+
            "FROM usuarios, reservashabitaciones "+
            "WHERE reservashabitaciones.usuarios_id = usuarios.id "+
            "GROUP BY usuarios.nombre), gastos as( "+
            "select usuarios.nombre as id, sum(consumos.sumatotal) as gasto from consumos, usuarios,reservashabitaciones "+
            "where usuarios.id = reservashabitaciones.usuarios_id and consumos.reservashabitaciones_id  = reservashabitaciones.id "+
            "group by usuarios.nombre) "+
            "Select gastos.id,gastos.gasto,dias.diashotel from gastos,dias  "+
            "where dias.id = gastos.id and (gastos.gasto >= 15000000 or dias.diashotel >= 14)", nativeQuery = true)
    List<Object[]> RFC7();
}
