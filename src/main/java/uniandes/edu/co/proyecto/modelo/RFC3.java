package uniandes.edu.co.proyecto.modelo;

public class RFC3 {
    

    private Long id;
    private Float ocupacion;
    public RFC3(Long id, Float ocupacion) {
        this.id = id;
        this.ocupacion = ocupacion;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Float getOcupacion() {
        return ocupacion;
    }
    public void setOcupacion(Float ocupacion) {
        this.ocupacion = ocupacion;
    }

    
}
