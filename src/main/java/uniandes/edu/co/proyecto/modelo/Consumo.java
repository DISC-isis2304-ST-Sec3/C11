package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "consumos")
public class Consumo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer sumaTotal;
    private Integer numConsumos;

    @ManyToOne
    @JoinColumn(name = "ReservasHabitaciones_id", referencedColumnName = "id")
    private ReservaHabitacion reservaHabitacion;

    @ManyToOne
    @JoinColumn(name = "ReservasServicios_id", referencedColumnName = "id")
    private ReservaServicio reservaServicio;

    public Consumo(){;}

    public Consumo(Integer sumaTotal, Integer numConsumos, ReservaHabitacion reservaHabitacion, ReservaServicio reservaServicio) {
        this.sumaTotal = sumaTotal;
        this.numConsumos = numConsumos;
        this.reservaHabitacion = reservaHabitacion;
        this.reservaServicio = reservaServicio;
    }

    public Integer getId() {
        return id;
    }

    public Integer getSumaTotal() {
        return sumaTotal;
    }

    public Integer getNumConsumos() {
        return numConsumos;
    }

    public ReservaHabitacion getReservaHabitacion() {
        return reservaHabitacion;
    }

    public ReservaServicio getReservaServicio() {
        return reservaServicio;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSumaTotal(Integer sumaTotal) {
        this.sumaTotal = sumaTotal;
    }

    public void setNumConsumos(Integer numConsumos) {
        this.numConsumos = numConsumos;
    }

    public void setReservaHabitacion(ReservaHabitacion reservaHabitacion) {
        this.reservaHabitacion = reservaHabitacion;
    }

    public void setReservaServicio(ReservaServicio reservaServicio) {
        this.reservaServicio = reservaServicio;
    }

    

    
}
