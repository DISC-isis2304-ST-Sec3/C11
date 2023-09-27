package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="elemATip")
public class ElemATip {
    @EmbeddedId
    private ElemATipPK pk;

    public ElemATip(){;}

    public ElemATip(ElementoHab elementoHab, TipoHabitacion tipoHabitacion) {
        this.pk = new ElemATipPK(elementoHab,tipoHabitacion);
    }

    public ElemATipPK getPk() {
        return pk;
    }

    public void setPk(ElemATipPK pk) {
        this.pk = pk;
    }

    



}
