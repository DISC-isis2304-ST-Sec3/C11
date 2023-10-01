package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;

@Entity
@Table(name = "servicios")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nombre;
    private String descripcion;
    @Column(name = "costoporunidad")
    private Integer costoPorUnidad;
    private String unidad;
    private String horario;
    @Column(name = "tiposervicio")
    private String tipoServicio;
    private Integer capacidad;

    public Servicio(){;}

    public Servicio(String nombre, String descripcion, Integer costoPorUnidad, String unidad, String horario,
            String tipoServicio, Integer capacidad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costoPorUnidad = costoPorUnidad;
        this.unidad = unidad;
        this.horario = horario;
        this.tipoServicio = tipoServicio;
        this.capacidad = capacidad;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCostoPorUnidad() {
        return costoPorUnidad;
    }

    public void setCostoPorUnidad(Integer costoPorUnidad) {
        this.costoPorUnidad = costoPorUnidad;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    
    
    
}
