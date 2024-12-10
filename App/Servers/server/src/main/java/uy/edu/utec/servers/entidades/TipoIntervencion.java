package uy.edu.utec.servers.entidades;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import uy.edu.utec.servers.enums.EstadoGeneral;

import java.util.List;

@Entity
@Table(name = "TIPO_INTERVENCION")
public class TipoIntervencion {
    //-------------------------------ID-SECUENCIA------------------------------------------
    @Id
    @SequenceGenerator(name = "TIPO_INTERVENCIONES_ID_SEQ", sequenceName = "TIPO_INTERVENCIONES_ID_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TIPO_INTERVENCIONES_ID_SEQ")
    @Column(name = "ID_TIPO_INTERVENCION")
    private Integer idTipoIntervencion;

    //-------------------------------COLUMNA------------------------------------------
    @Column(name = "NOM_TIPO", nullable = false, length = 50, unique = true)
    private String nomTipo;
    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO", nullable = false)
    private EstadoGeneral estado;

    //-------------------------------RELACIONES------------------------------------------
    @JsonbTransient
    @OneToMany(mappedBy = "tipoIntervencion", fetch = FetchType.LAZY)
    private List<Intervencion> intervenciones;

    @OneToMany(mappedBy = "tipoIntervencion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SeguimientoIntervencion> seguimientoIntervencionList;

    //-------------------------------CONSTRUCTORES------------------------------------------
    public TipoIntervencion() {
    }

    public TipoIntervencion(Integer idTipoIntervencion, String nomTipo, EstadoGeneral estado, List<Intervencion> intervenciones, List<SeguimientoIntervencion> trabajarIntervencionList) {
        this.idTipoIntervencion = idTipoIntervencion;
        this.nomTipo = nomTipo;
        this.estado = estado;
        this.intervenciones = intervenciones;
        this.seguimientoIntervencionList = trabajarIntervencionList;
    }

    //-------------------------------GETTERS AND SETTERS------------------------------------------
    public Integer getIdTipoIntervencion() {
        return idTipoIntervencion;
    }

    public void setIdTipoIntervencion(Integer idTipoIntervencion) {
        this.idTipoIntervencion = idTipoIntervencion;
    }

    public String getNomTipo() {
        return nomTipo;
    }

    public void setNomTipo(String nomTipo) {
        this.nomTipo = nomTipo;
    }

    public EstadoGeneral getEstado() {
        return estado;
    }

    public void setEstado(EstadoGeneral estado) {
        this.estado = estado;
    }

    public List<Intervencion> getIntervenciones() {
        return intervenciones;
    }

    public void setIntervenciones(List<Intervencion> intervenciones) {
        this.intervenciones = intervenciones;
    }

    public List<SeguimientoIntervencion> getSeguimientoIntervencionList() {
        return seguimientoIntervencionList;
    }

    public void setSeguimientoIntervencionList(List<SeguimientoIntervencion> seguimientoIntervencionList) {
        this.seguimientoIntervencionList = seguimientoIntervencionList;
    }
}