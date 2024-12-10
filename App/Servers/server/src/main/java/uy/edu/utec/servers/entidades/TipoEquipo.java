package uy.edu.utec.servers.entidades;

import jakarta.persistence.*;
import uy.edu.utec.servers.enums.EstadoGeneral;

import java.util.List;

@Entity
@Table(name = "TIPO_EQUIPO")
public class TipoEquipo {

    //-------------------------------ID-SECUENCIA------------------------------------------
    @Id
    @SequenceGenerator(name = "TIPOS_EQUIPO_ID_SEQ", sequenceName = "TIPOS_EQUIPO_ID_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TIPOS_EQUIPO_ID_SEQ")
    @Column(name = "ID_TIPO_EQUIPO")
    private Integer idTipoEquipo;

    //-------------------------------COLUMNAS------------------------------------------
    @Column(name = "NOM_TIPO", length = 50, nullable = false, unique = true)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO",nullable = false)
    private EstadoGeneral estado;

    //-------------------------------RELACIONES------------------------------------------
    @OneToMany(mappedBy = "tipoEquipo")
    private List<Equipo> equipos;

    //-------------------------------CONSTRUCTORES------------------------------------------

    public TipoEquipo() {
    }

    public TipoEquipo(String nombre, EstadoGeneral estado) {
        this.nombre = nombre;
        this.estado = estado;
    }

    //-------------------------------GETTERS AND SETTERS------------------------------------------

    public Integer getIdTipoEquipo() {
        return idTipoEquipo;
    }

    public void setIdTipoEquipo(Integer idTipoEquipo) {
        this.idTipoEquipo = idTipoEquipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public EstadoGeneral getEstado() {
        return estado;
    }

    public void setEstado(EstadoGeneral estado) {
        this.estado = estado;
    }

    //-------------------------------TOSTRING------------------------------------------

    @Override
    public String toString() {
        return "TipoEquipo{" +
                "idTipoEquipo=" + idTipoEquipo +
                ", nombre='" + nombre + '\'' +
                ", estado=" + estado +
                '}';
    }
}