package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "consumos")
public class Consumo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "sumatotal")
    private Integer sumaTotal;
    @Column(name = "numconsumos")
    private Integer numConsumos;

    private String nombre;

   

    @ManyToOne
    @JoinColumn(name = "reservashabitaciones_id", referencedColumnName = "id")
    private ReservaHabitacion reservaHabitacion;

    @ManyToOne
    @JoinColumn(name = "servicios_id", referencedColumnName = "id")
    private Servicio servicio;

    public Consumo(){;}

    public Consumo(Integer sumaTotal, Integer numConsumos, String nombre, ReservaHabitacion reservaHabitacion, Servicio servicio) {
        this.sumaTotal = sumaTotal;
        this.numConsumos = numConsumos;
        this.nombre = nombre;
        this.reservaHabitacion = reservaHabitacion;
        this.servicio = servicio;
        
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

    public Servicio getServicio() {
        return servicio;
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

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

     public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
}
