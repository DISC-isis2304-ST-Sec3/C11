package uniandes.edu.co.proyecto.modelo;


import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String usuario;  
    private String contrasena; 
    private String nombre;    
    private Integer numDocumento;
    private String email;
    private String tipoDocumento;

    

    @ManyToOne
    @JoinColumn(name = "tipoUsuario_id", referencedColumnName = "id")
    private TipoUsuario tipoUsuario;

   

    public Usuario(){;}

    public Usuario(String elusuario, String lacontraseña,String elnombre ,Integer elNumDoc, String elemail, String eltipoDoc, TipoUsuario elTipoUsuario) {
        this.usuario = elusuario;
        this.contrasena = lacontraseña;
        this.nombre = elnombre;
        this.email = elemail;
        this.tipoDocumento = eltipoDoc;
        this.tipoUsuario = elTipoUsuario;

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
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }


    public Integer getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(Integer numDocumento) {
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
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
