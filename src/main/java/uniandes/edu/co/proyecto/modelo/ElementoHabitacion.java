package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.*;

@Entity
@Table(name="elementoshabitaciones")
public class ElementoHabitacion {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)  
    private Integer id;

    private String nombre;

    public ElementoHabitacion()
    {;}

    public ElementoHabitacion(String nombre) {
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
