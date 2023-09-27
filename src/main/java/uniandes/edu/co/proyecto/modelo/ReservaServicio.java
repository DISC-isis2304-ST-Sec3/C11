package uniandes.edu.co.proyecto.modelo;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "reservasservicios")
public class ReservaServicio {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer numPersonas;
    private Date fechaInicio;
    private Date fechaFin;

    @ManyToOne
    @JoinColumn(name = "Usuarios_Id", referencedColumnName = "id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "Servicios_id", referencedColumnName = "id")
    private Servicio servicio;

    public ReservaServicio(){;}

    public ReservaServicio(Integer numPersonas, Date fechaInicio,Usuario usuario ,Date fechaFin, Servicio servicio) {
        this.numPersonas = numPersonas;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.usuario = usuario;
        this.servicio = servicio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumPersonas() {
        return numPersonas;
    }

    public void setNumPersonas(Integer numPersonas) {
        this.numPersonas = numPersonas;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    



}
