package uniandes.edu.co.proyecto.modelo;



import jakarta.persistence.*;
@Entity
@Table(name = "habitaciones")
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer capacidad;
    private Integer costoAlejamiento;
    private Integer numero;

    @ManyToOne
    @JoinColumn(name = "TipoHabitaciones_id", referencedColumnName = "id")
    private TipoHabitacion tipoHabitacion;

    public Habitacion(){;}

    public Habitacion(Integer capacidad, Integer costoAlejamiento, Integer numero, TipoHabitacion tipoHabitacion) {
        this.capacidad = capacidad;
        this.costoAlejamiento = costoAlejamiento;
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

    public Integer getCostoAlejamiento() {
        return costoAlejamiento;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public void setCostoAlejamiento(Integer costoAlejamiento) {
        this.costoAlejamiento = costoAlejamiento;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }


}
