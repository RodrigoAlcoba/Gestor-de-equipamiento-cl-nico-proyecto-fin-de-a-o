package uy.edu.utec.servers.entidades;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Funcionalidades")
public class Funcionalidad implements Serializable {

    //-------------------------------ID-SECUENCIA------------------------------------------
    @Id
    @SequenceGenerator(name = "FUNCIONALIDADES_ID_SEQ", sequenceName = "FUNCIONALIDADES_ID_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FUNCIONALIDADES_ID_SEQ")
    @Column(name = "ID_Funcionalidad")
    private Integer idFuncionalidad;

    //-------------------------------COLUMNAS------------------------------------------
    @Column(name = "TIPO_PERMISO", length = 50, nullable = false, unique = true)
    private String tipo_Permiso;

    //-------------------------------RELACIONES------------------------------------------
    @ManyToMany(mappedBy = "funcionalidades")
    private List<Perfil> perfilesFuncionalidad;

    //-------------------------------CONSTRUCTORES------------------------------------------

    public Funcionalidad(String tipo_Permiso, List<Perfil> perfilesFuncionalidad) {
        this.tipo_Permiso = tipo_Permiso;
        this.perfilesFuncionalidad = perfilesFuncionalidad;
    }

    public Funcionalidad(){};

    //-------------------------------GETTERS AND SETTERS------------------------------------------
    public Integer getIdFuncionalidad() {
        return idFuncionalidad;
    }

    public void setIdFuncionalidad(Integer idFuncionalidad) {
        this.idFuncionalidad = idFuncionalidad;
    }

    public String getTipo_Permiso() {
        return tipo_Permiso;
    }

    public void setTipo_Permiso(String tipo_Permiso) {
        this.tipo_Permiso = tipo_Permiso;
    }

    public List<Perfil> getPerfilesFuncionalidad() {
        return perfilesFuncionalidad;
    }

    public void setPerfilesFuncionalidad(List<Perfil> perfilesFuncionalidad) {
        this.perfilesFuncionalidad = perfilesFuncionalidad;
    }

    //-------------------------------TOSTRING------------------------------------------
    @Override
    public String toString() {
        return "Funcionalidad{" +
                "idFuncionalidad=" + idFuncionalidad +
                ", tipo_Permiso='" + tipo_Permiso + '\'' +
                ", perfilesFuncionalidad=" + perfilesFuncionalidad +
                '}';
    }
}
