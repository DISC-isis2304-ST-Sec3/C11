package uniandes.edu.co.proyecto.modelo;


import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "reservashabitaciones")
public class ReservaHabitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer numPersonas;
    private Date fechaInicio;
    private Date fechaFin;
    private Date fechaCheckIn;
    private Date fechaCheckOut;

    @ManyToOne
    @JoinColumn(name = "Usuarios_Id", referencedColumnName = "id")
    private Usuario usuario;

   
    @ManyToOne
    @JoinColumn(name = "Habitaciones_id", referencedColumnName = "id")
    private Habitacion habitacion;

    
    public ReservaHabitacion(){;}

    public ReservaHabitacion(Integer numPersonas, Date fechaInicio,Usuario usuario ,Date fechaFin, Habitacion habitacion, Date fechaCheckIn, Date fechaCheckOut) {
        this.numPersonas = numPersonas;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.usuario = usuario;
        this.habitacion = habitacion;
        this.fechaCheckIn = fechaCheckIn;
        this.fechaCheckOut = fechaCheckOut;
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

    public Date getFechaCheckIn() {
        return fechaCheckIn;
    }

    public void setFechaCheckIn(Date fechaCheckIn) {
        this.fechaCheckIn = fechaCheckIn;
    }

    public Date getFechaCheckOut() {
        return fechaCheckOut;
    }

    public void setFechaCheckOut(Date fechaCheckOut) {
        this.fechaCheckOut = fechaCheckOut;
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

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

}
