package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.*;

@Entity
@Table(name="elementoshab")
public class ElementoHab {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)  
    private Integer id;

    private String nombre;

    public ElementoHab()
    {;}

    public ElementoHab(String nombre) {
        this.nombre = nombre;
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

    

}
