package uy.edu.utec.servers.dtos;

import jakarta.validation.constraints.NotNull;
import uy.edu.utec.servers.enums.NombreSector;

import java.io.Serializable;

public class SectorDTO implements Serializable {
    @NotNull
    private Integer id;
    private NombreSector nombre;

    public SectorDTO(Integer id, NombreSector nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public SectorDTO() {
    }

    public Integer getId() {
        return id;
    }

    public NombreSector getNombre() {
        return nombre;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(NombreSector nombre) {
        this.nombre = nombre;
    }
}