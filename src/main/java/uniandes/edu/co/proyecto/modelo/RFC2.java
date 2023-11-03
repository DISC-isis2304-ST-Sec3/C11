package uniandes.edu.co.proyecto.modelo;

public class RFC2 {
    //atributos servicio
    private Long id;
    private String nombre;
    private String numero;
    public RFC2(String nombre, String numero, Long id) {
        this.nombre = nombre;
        this.numero = numero;
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
