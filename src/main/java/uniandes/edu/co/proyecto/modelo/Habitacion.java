package uniandes.edu.co.proyecto.modelo;



import jakarta.persistence.*;
@Entity
@Table(name = "habitaciones")
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer capacidad;
    @Column(name = "costoalojamiento")
    private Integer costoAlojamiento;
    private Integer numero;

    @ManyToOne
    @JoinColumn(name = "tiposhabitaciones_id", referencedColumnName = "id")
    private TipoHabitacion tipoHabitacion;

    public Habitacion(){;}

    public Habitacion(Integer capacidad, Integer costoAlojamiento, Integer numero, TipoHabitacion tipoHabitacion) {
        this.capacidad = capacidad;
        this.costoAlojamiento = costoAlojamiento;
        this.numero = numero;
        this.tipoHabitacion = tipoHabitacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public Integer getCostoAlojamiento() {
        return costoAlojamiento;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public void setCostoAlojamiento(Integer costoAlojamiento) {
        this.costoAlojamiento = costoAlojamiento;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }


}
