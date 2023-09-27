package uniandes.edu.co.proyecto.modelo;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class ElemATipPK implements Serializable {
    @ManyToOne
    @JoinColumn(name="ElementosHab_id", referencedColumnName = "id")
    private ElementoHab id_elementoHab;

    @ManyToOne
    @JoinColumn(name = "TiposHabitaciones_id", referencedColumnName = "id")
    private TipoHabitacion id_TipoHabitacion;

    public ElemATipPK(ElementoHab id_elementoHab, TipoHabitacion id_TipoHabitacion) {
        super();
        this.id_elementoHab = id_elementoHab;
        this.id_TipoHabitacion = id_TipoHabitacion;
    }

    public void setId_elementoHab(ElementoHab id_elementoHab) {
        this.id_elementoHab = id_elementoHab;
    }

    public void setId_TipoHabitacion(TipoHabitacion id_TipoHabitacion) {
        this.id_TipoHabitacion = id_TipoHabitacion;
    }

    public ElementoHab getId_elementoHab() {
        return id_elementoHab;
    }

    public TipoHabitacion getId_TipoHabitacion() {
        return id_TipoHabitacion;
    }

    

    
}
