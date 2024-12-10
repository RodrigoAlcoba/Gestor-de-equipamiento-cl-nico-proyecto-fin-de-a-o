package uy.edu.utec.servers.dtos;

import jakarta.validation.constraints.NotNull;
import uy.edu.utec.servers.entidades.Equipo;
import uy.edu.utec.servers.enums.EstadoGeneral;

import java.io.Serializable;
import java.util.List;

public class PaisDTO implements Serializable {
    private Integer ID_Pais;
    private String nombre;
    private EstadoGeneral estado;

    @NotNull
    private Integer equipoId;

    private List<Equipo> equipoList;

    //--------------------CONSTRUCTOR--------------------

    public PaisDTO() {
    }

    //--------------------GETTERS AND SETTERS--------------------

    public Integer getID_Pais() {
        return ID_Pais;
    }

    public void setID_Pais(Integer ID_Pais) {
        this.ID_Pais = ID_Pais;
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

    public Integer getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(Integer equipoId) {
        this.equipoId = equipoId;
    }

    public List<Equipo> getEquipoList() {
        return equipoList;
    }

    public void setEquipoList(List<Equipo> equipoList) {
        this.equipoList = equipoList;
    }
}
