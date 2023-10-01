package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "tiposdeusuario")
public class TipoUsuario {
    
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)

        private Integer id;
        private String nombre;
        private String permisos;
    
        public TipoUsuario(){;}
    
        public TipoUsuario(String nombre, String permisos) {
            this.nombre = nombre;
            this.permisos = permisos;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getPermisos() {
            return permisos;
        }

        public void setPermisos(String permisos) {
            this.permisos = permisos;
        }
    
        
        


}
