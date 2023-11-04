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
    @Column(name = "fechaconsumo")
    private String fechaconsumo;
   

    @ManyToOne
    @JoinColumn(name = "reservashabitaciones_id", referencedColumnName = "id")
    private ReservaHabitacion reservaHabitacion;

    @ManyToOne
    @JoinColumn(name = "servicios_id", referencedColumnName = "id")
    private Servicio servicio;

    @ManyToOne
    @JoinColumn(name = "usuarios_id", referencedColumnName = "id")
    private Usuario usuario;

    public Consumo(){;}

    public Consumo(Integer sumaTotal, Integer numConsumos, String nombre, ReservaHabitacion reservaHabitacion, Servicio servicio, String fechaconsumo, Usuario usuario) {
        this.sumaTotal = sumaTotal;
        this.numConsumos = numConsumos;
        this.nombre = nombre;
        this.reservaHabitacion = reservaHabitacion;
        this.servicio = servicio;
        this.fechaconsumo = fechaconsumo;
        this.usuario = usuario;
        
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

    public String getFechaconsumo() {
        return fechaconsumo;
    }

    public void setFechaconsumo(String fechaconsumo) {
        this.fechaconsumo = fechaconsumo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
    

    
}
