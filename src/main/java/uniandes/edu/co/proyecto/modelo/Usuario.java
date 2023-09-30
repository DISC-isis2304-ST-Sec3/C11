package uniandes.edu.co.proyecto.modelo;


import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    private String usuario;  
    private String contraseña; 
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

    public Usuario(String elusuario, String contraseña,String elnombre ,long elNumDoc, String elemail, String eltipoDoc, TipoUsuario elTipoUsuario) {
        this.usuario = elusuario;
        this.contraseña = contraseña;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contraseña;
    }

    public void setContrasena(String contraseña) {
        this.contraseña = contraseña;
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
}
