package uy.edu.utec.servers.dtos;

import uy.edu.utec.servers.enums.EstadoGeneral;

import java.io.Serializable;

public class ModeloDTO implements Serializable {
    private Integer idModelo;
    private String nombreModelo;
    private EstadoGeneral estado;

    public ModeloDTO() {
    }

    public Integer getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Integer idModelo) {
        this.idModelo = idModelo;
    }

    public String getNombreModelo() {
        return nombreModelo;
    }

    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }

    public EstadoGeneral getEstado() {
        return estado;
    }

    public void setEstado(EstadoGeneral estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "ModeloDTO{" +
                "idMODELO=" + idModelo +
                ", nombreModelo='" + nombreModelo + '\'' +
                ", estado=" + estado +
                '}';
    }
}
