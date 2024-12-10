package uy.edu.utec.servers.dtos;

import uy.edu.utec.servers.enums.EstadoGeneral;

import java.io.Serializable;

public class TipoEquipoDTO implements Serializable {
    private Integer id;
    private String nomTipo;
    private EstadoGeneral estado;
    private Integer tipoEquipo;

    public TipoEquipoDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTipoEquipo() {
        return tipoEquipo;
    }

    public void setTipoEquipo(Integer tipoEquipo) {
        this.tipoEquipo = tipoEquipo;
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

    @Override
    public String toString() {
        return "TipoEquipoDTO{" +
                "idEquipo=" + id +
                ", nomTipo='" + nomTipo + '\'' +
                ", estado=" + estado +
                '}';
    }
}
