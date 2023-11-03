package uniandes.edu.co.proyecto.modelo;

public class RFC1 {
    
    private Long habitacionesId;

    private Long sum;

    public RFC1(Long habitacionesId, Long sum) {
        this.habitacionesId = habitacionesId;
        this.sum = sum;
    }

    public Long getHabitacionesId() {
        return habitacionesId;
    }

    public void setHabitacionesId(Long habitacionesId) {
        this.habitacionesId = habitacionesId;
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }

    
}
