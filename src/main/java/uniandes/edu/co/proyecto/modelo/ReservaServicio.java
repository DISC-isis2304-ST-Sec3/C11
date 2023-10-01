package uniandes.edu.co.proyecto.modelo;


import jakarta.persistence.*;

@Entity
@Table(name = "reservasservicios")
public class ReservaServicio {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "numpersonas")
    private Integer numPersonas;
    @Column(name = "fechainicio")
    private String fechaInicio;
    @Column(name = "fechafin")
    private String fechaFin;

    @ManyToOne
    @JoinColumn(name = "usuarios_id", referencedColumnName = "id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "servicios_id", referencedColumnName = "id")
    private Servicio servicio;

    public ReservaServicio(){;}

    public ReservaServicio(Integer numPersonas, String fechaInicio,Usuario usuario ,String fechaFin, Servicio servicio) {
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

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
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

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    



}
