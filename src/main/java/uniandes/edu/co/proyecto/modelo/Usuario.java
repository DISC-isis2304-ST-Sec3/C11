package uniandes.edu.co.proyecto.modelo;


import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "nombreusuario")
    private String nombreUsuario;  
    private String contrasena; 
    private String nombre; 

    @Column(name = "numdocumento")
    private long numDocumento;
    private String email;
    @Column(name = "tipodocumento")
    private String tipoDocumento;

    

    @ManyToOne
    @JoinColumn(name = "tiposdeusuario_id", referencedColumnName = "id")
    private TipoUsuario tiposdeusuario;

   

    public Usuario(){;}

    public Usuario(String elusuario, String contrasena,String elnombre ,long elNumDoc, String elemail, String eltipoDoc, TipoUsuario elTipoUsuario) {
        this.nombreUsuario = elusuario;
        this.contrasena = contrasena;
        this.nombre = elnombre;
        this.email = elemail;
        this.tipoDocumento = eltipoDoc;
        this.tiposdeusuario = elTipoUsuario;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }


    public long getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(long numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public TipoUsuario getTipoUsuario() {
        return tiposdeusuario;
    }

    public void setTipoUsuario(TipoUsuario tiposdeusuario) {
        this.tiposdeusuario = tiposdeusuario;
    }

    

    public TipoUsuario getTiposdeusuario() {
        return tiposdeusuario;
    }

    public void setTiposdeusuario(TipoUsuario tiposdeusuario) {
        this.tiposdeusuario = tiposdeusuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
}
