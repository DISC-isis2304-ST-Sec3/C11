package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "tiposUsuarios")
public class TipoUsuario {
    
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)

        private Integer id;
        private String tipoUsuario;
    
        public TipoUsuario(){;}
    
        public TipoUsuario(String tipoUsuario) {
            this.tipoUsuario = tipoUsuario;
        }
    
        public Integer getId() {
            return id;
        }
    
        public String getTipoUsuario() {
            return tipoUsuario;
        }
    
        public void setId(Integer id) {
            this.id = id;
        }
    
        public void setTipoUsuario(String tipoUsuario) {
            this.tipoUsuario = tipoUsuario;
        }




}
