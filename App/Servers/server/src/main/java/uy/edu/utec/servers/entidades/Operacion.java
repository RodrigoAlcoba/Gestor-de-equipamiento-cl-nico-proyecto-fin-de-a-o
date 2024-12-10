package uy.edu.utec.servers.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "Operaciones")
public class Operacion {
    //-------------------------------ID-SECUENCIA------------------------------------------
    @Id
    @SequenceGenerator(name = "OPERACIONES_ID_SEQ", sequenceName = "OPERACIONES_ID_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OPERACIONES_ID_SEQ")
    @Column(name = "ID_OPERACION")
    private Integer idOperacion;


    //-------------------------------COLUMNAS------------------------------------------
    @Column(name = "Nom_Operacion", length = 50,nullable = false)
    private String nombreOperacion;

    //-------------------------------CONSTRUCTORES------------------------------------------

    public Operacion() {
    }

    public Operacion(String nombreOperacion) {
        this.nombreOperacion = nombreOperacion;
    }

    //-------------------------------GETTERS AND SETTERS------------------------------------------


    public Integer getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(Integer idOperacion) {
        this.idOperacion = idOperacion;
    }

    public String getNombreOperacion() {
        return nombreOperacion;
    }

    public void setNombreOperacion(String nombreOperacion) {
        this.nombreOperacion = nombreOperacion;
    }

    //-------------------------------TOSTRING------------------------------------------

    @Override
    public String toString() {
        return "Operacion{" +
                "idOperacion=" + idOperacion +
                ", nombreOperacion='" + nombreOperacion + '\'' +
                '}';
    }
}