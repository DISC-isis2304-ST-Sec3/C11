package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "planesconsumo")  

public class PlanesConsumo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String descripcion;
    private Integer descuento;
    private String nombre;

    public PlanesConsumo(){;}

    public PlanesConsumo(String descripcion, Integer descuento, String nombre) {
        this.descripcion = descripcion;
        this.descuento = descuento;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Integer getDescuento() {
        return descuento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDescuento(Integer descuento) {
        this.descuento = descuento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

  

    

    
}
