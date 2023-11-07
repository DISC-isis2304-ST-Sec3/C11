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
    @Query(value = "SELECT * FROM usuarios FETCH FIRST 30 ROWS ONLY", nativeQuery = true)
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
            "where dias.id = gastos.id and (gastos.gasto >= 15000000 or dias.diashotel >= 14) FETCH FIRST 30 ROWS ONLY ", nativeQuery = true)
    List<Object[]> RFC7();

    @Query(value = "WITH ClientesExcelentes AS ( "+
        "(SELECT r.usuarios_id, 'Estancias por trimestre' AS Razon  "+
        "FROM reservashabitaciones r  "+
        "GROUP BY r.usuarios_id  "+
        "HAVING COUNT(DISTINCT TRUNC(r.fechainicio, 'Q')) = 4) "+
        "UNION "+
        "(SELECT c.usuarios_id, 'Servicio costoso' AS Razon  "+
        "FROM consumos c  "+
        "JOIN servicios s ON c.servicios_id = s.id  "+
        "WHERE s.costoporunidad > 300000  "+
        "GROUP BY c.usuarios_id  "+
        "HAVING COUNT(DISTINCT c.id) = COUNT(DISTINCT c.reservashabitaciones_id)) "+
        "UNION "+
        "(SELECT r.usuarios_id, 'Servicio SPA o Salón > 4 horas' AS Razon  "+
        "FROM reservasservicios r  "+
        "JOIN servicios s ON r.servicios_id = s.id  "+
        "WHERE (s.nombre = 'SPA' OR s.tiposervicio = 'Salon de reuniones')  "+
        "    AND (r.fechafin - r.fechainicio) * 24 > 4  "+
        "GROUP BY r.usuarios_id  "+
        "HAVING COUNT(DISTINCT r.id) = COUNT(DISTINCT r.servicios_id))) "+
        "SELECT u.id, u.nombre, ce.Razon "+
        "FROM usuarios u "+
        "JOIN ClientesExcelentes ce ON u.id = ce.usuarios_id "+
        "ORDER BY u.id, u.nombre FETCH FIRST 30 ROWS ONLY ", nativeQuery = true )
        List<Object[]> RFC12();
}
