package uniandes.edu.co.proyecto.modelo;


import jakarta.persistence.*;

@Entity
@Table(name = "reservashabitaciones")
public class ReservaHabitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "numpersonas")
    private Integer numPersonas;
    @Column(name = "fechainicio")
    private String fechaInicio;
    @Column(name = "fechafin")
    private String fechaFin;
    @Column(name = "fechacheckin")
    private String fechaCheckIn;
    @Column(name = "fechacheckout")
    private String fechaCheckOut;

    @ManyToOne
    @JoinColumn(name = "Usuarios_Id", referencedColumnName = "id")
    private Usuario usuario;

   
    @ManyToOne
    @JoinColumn(name = "habitaciones_id", referencedColumnName = "id")
    private Habitacion habitacion;

    @ManyToOne
    @JoinColumn(name = "planesdeconsumo_id", referencedColumnName = "id")
    private PlanesConsumo planConsumo;
    public ReservaHabitacion(){;}

    public ReservaHabitacion(Integer numPersonas, String fechaInicio,Usuario usuario ,String fechaFin, Habitacion habitacion, String fechaCheckIn, String fechaCheckOut, PlanesConsumo planConsumo) {
        this.numPersonas = numPersonas;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.usuario = usuario;
        this.habitacion = habitacion;
        this.fechaCheckIn = fechaCheckIn;
        this.fechaCheckOut = fechaCheckOut;
        this.planConsumo = planConsumo;
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

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaCheckIn() {
        return fechaCheckIn;
    }

    public void setFechaCheckIn(String fechaCheckIn) {
        this.fechaCheckIn = fechaCheckIn;
    }

    public String getFechaCheckOut() {
        return fechaCheckOut;
    }

    public void setFechaCheckOut(String fechaCheckOut) {
        this.fechaCheckOut = fechaCheckOut;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
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

    public PlanesConsumo getPlanConsumo() {
        return planConsumo;
    }

    public void setPlanConsumo(PlanesConsumo planConsumo) {
        this.planConsumo = planConsumo;
    }

    
}
