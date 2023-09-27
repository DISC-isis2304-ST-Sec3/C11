package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="elementosatipos")
public class ElementoATipo {
    @EmbeddedId
    private ElementoATipoPK pk;

    public ElementoATipo(){;}

    public ElementoATipo(ElementoHabitacion elementoHabitacion, TipoHabitacion tipoHabitacion) {
        this.pk = new ElementoATipoPK(elementoHabitacion,tipoHabitacion);
    }

    public ElementoATipoPK getPk() {
        return pk;
    }

    public void setPk(ElementoATipoPK pk) {
        this.pk = pk;
    }

    



}
