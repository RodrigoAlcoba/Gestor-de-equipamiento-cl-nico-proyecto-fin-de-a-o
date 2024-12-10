package uy.edu.utec.servers.entidades;

import jakarta.persistence.*;
import uy.edu.utec.servers.enums.EstadoGeneral;

import java.util.List;

@Entity
@Table(name = "Paises")
public class Pais {
    //-------------------------------ID-SECUENCIA------------------------------------------
    @Id
    @SequenceGenerator(name = "PAISES_ID_SEQ", sequenceName = "PAISES_ID_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PAISES_ID_SEQ")
    @Column(name = "ID_PAIS")
    private Integer idPais;

    //-------------------------------COLUMNAS------------------------------------------
    @Column(name = "Nom_Pais", length = 50, nullable = false, unique = true)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO",nullable = false)
    private EstadoGeneral estado;

    //-------------------------------RELACIONES------------------------------------------
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pais")
    private List<Equipo> equipos;

    //-------------------------------CONSTRUCTORES------------------------------------------

    public Pais() {
    }

    public Pais(String nombre, EstadoGeneral estado) {
        this.nombre = nombre;
        this.estado = estado;
    }

    //-------------------------------GETTES AND SETTERS------------------------------------------


    public Integer getIdPais() {
        return idPais;
    }

    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
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

}