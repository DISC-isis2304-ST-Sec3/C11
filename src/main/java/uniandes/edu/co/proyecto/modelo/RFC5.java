package uniandes.edu.co.proyecto.modelo;

public class RFC5 {
    
    private String usuarios_id;
    private String nombre;
    private String producto;
    public RFC5(String usuarios_id, String nombre, String producto) {
        this.usuarios_id = usuarios_id;
        this.nombre = nombre;
        this.producto = producto;
    }
    public String getUsuarios_id() {
        return usuarios_id;
    }
    public void setUsuarios_id(String usuarios_id) {
        this.usuarios_id = usuarios_id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getproducto() {
        return producto;
    }
    public void setproducto(String producto) {
        this.producto = producto;
    }

    


}
