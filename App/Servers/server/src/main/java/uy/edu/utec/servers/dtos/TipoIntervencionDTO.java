package uy.edu.utec.servers.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import uy.edu.utec.servers.enums.EstadoGeneral;

import java.io.Serializable;

public class TipoIntervencionDTO implements Serializable {
    private Integer idTipointervencion;


    private String nomTipoIntervencion;
    private EstadoGeneral estado;

    //--------------CONSTRUCTOR--------------------------
    public TipoIntervencionDTO() {
    }
    public TipoIntervencionDTO(Integer idTipointervencion) {
        this.idTipointervencion = idTipointervencion;
    }
    //--------------GETTERS AND SETTERS------------------

    public @NotNull Integer getIdTipointervencion() {
        return idTipointervencion;
    }

    public void setIdTipointervencion(Integer idTipointervencion) {
        this.idTipointervencion = idTipointervencion;
    }

    public String getNomTipoIntervencion() {
        return nomTipoIntervencion;
    }

    public void setNomTipoIntervencion(String nomTipoIntervencion) {
        this.nomTipoIntervencion = nomTipoIntervencion;
    }

    public EstadoGeneral getEstado() {
        return estado;
    }

    public void setEstado(EstadoGeneral estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return getNomTipoIntervencion();
    }
}

