package uniandes.edu.co.proyecto.modelo;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class ElementoATipoPK implements Serializable {
    @ManyToOne
    @JoinColumn(name="ElementosHab_id", referencedColumnName = "id")
    private ElementoHabitacion getId_elementoHabitacion;

    @ManyToOne
    @JoinColumn(name = "TiposHabitaciones_id", referencedColumnName = "id")
    private TipoHabitacion id_TipoHabitacion;

    public ElementoATipoPK(ElementoHabitacion getId_elementoHabitacion, TipoHabitacion id_TipoHabitacion) {
        super();
        this.getId_elementoHabitacion = getId_elementoHabitacion;
        this.id_TipoHabitacion = id_TipoHabitacion;
    }

    public void setId_elementoHab(ElementoHabitacion getId_elementoHabitacion) {
        this.getId_elementoHabitacion = getId_elementoHabitacion;
    }

    public void setId_TipoHabitacion(TipoHabitacion id_TipoHabitacion) {
        this.id_TipoHabitacion = id_TipoHabitacion;
    }

    public ElementoHabitacion getId_elementoHabitacion() {
        return getId_elementoHabitacion;
    }

    public TipoHabitacion getId_TipoHabitacion() {
        return id_TipoHabitacion;
    }

    

    
}
