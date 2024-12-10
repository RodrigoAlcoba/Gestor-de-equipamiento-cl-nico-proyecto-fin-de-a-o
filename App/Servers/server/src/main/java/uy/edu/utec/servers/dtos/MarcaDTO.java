package uy.edu.utec.servers.dtos;

import uy.edu.utec.servers.enums.EstadoGeneral;

import java.io.Serializable;

public class MarcaDTO implements Serializable {

    private Integer idMarca;

    private String nombreMarca;

    private EstadoGeneral estado;

    public MarcaDTO() {
    }

    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    public EstadoGeneral getEstado() {
        return estado;
    }

    public void setEstado(EstadoGeneral estado) {
        this.estado = estado;
    }
}
