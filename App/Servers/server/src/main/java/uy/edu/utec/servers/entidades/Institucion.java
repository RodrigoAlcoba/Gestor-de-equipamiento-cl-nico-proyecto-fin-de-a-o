package uy.edu.utec.servers.entidades;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import uy.edu.utec.servers.enums.EstadoGeneral;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "INSTITUCIONES")
public class Institucion implements Serializable {
    //-------------------------------ID-SECUENCIA------------------------------------------
    @Id
    @SequenceGenerator(name = "INSTITUCIONES_ID_SEQ", sequenceName = "INSTITUCIONES_ID_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INSTITUCIONES_ID_SEQ")
    @Column(name = "ID_Institucion")
    private Integer idInstitucion;

    //-------------------------------COLUMNAS------------------------------------------
    @Column (name = "NOM_INSTITUCION", length = 60, unique = true, nullable = false)
    private String nom_Institucion;

    @Enumerated(EnumType.STRING)
    @Column (name = "ESTADO", length = 60)
    private EstadoGeneral estado;

    //-------------------------------RELACIONES------------------------------------------
    @OneToMany(mappedBy = "institucion")
    @JsonbTransient
    private List<Ubicacion> ubicaciones;

    //-------------------------------CONSTRUCTORES------------------------------------------

    public Institucion(){}

    public Institucion(String nom_Institucion, EstadoGeneral estado, List<Ubicacion> ubicaciones) {
        this.nom_Institucion = nom_Institucion;
        this.estado = estado;
        this.ubicaciones = ubicaciones;
    }

    //-------------------------------GETTERS AND SETTERS------------------------------------------
    public Integer getIdInstitucion() {
        return idInstitucion;
    }

    public void setIdInstitucion(Integer idInstitucion) {
        this.idInstitucion = idInstitucion;
    }

    public String getNom_Institucion() {
        return nom_Institucion;
    }

    public void setNom_Institucion(String nom_Institucion) {
        this.nom_Institucion = nom_Institucion;
    }

    public EstadoGeneral getEstado() {
        return estado;
    }

    public void setEstado(EstadoGeneral estado) {
        this.estado = estado;
    }

    public List<Ubicacion> getUbicaciones() {
        return ubicaciones;
    }

    public void setUbicaciones(List<Ubicacion> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }

    //-------------------------------TOSTRING------------------------------------------

    @Override
    public String toString() {
        return "Institucion{" +
                "ID_Institucion=" + idInstitucion +
                ", nom_Institucion='" + nom_Institucion + '\'' +
                ", estado=" + estado +
                ", ubicaciones=" + ubicaciones +
                '}';
    }
}
