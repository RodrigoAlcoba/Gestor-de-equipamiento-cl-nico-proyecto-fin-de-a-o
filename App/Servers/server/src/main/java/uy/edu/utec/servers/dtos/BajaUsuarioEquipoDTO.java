package uy.edu.utec.servers.dtos;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;

public class BajaUsuarioEquipoDTO implements Serializable {
    private Integer bajaEquipoId;
    private Integer equipoId;
    private Integer usuarioId;
    @Size(min = 3, max = 80, message = "Razón debe tener entre 3 y 80 caracteres")
    private String razon;
    @Size(min = 6, max = 200, message = "Comentario debe tener entre 6 y 200 caracteres")
    private String comentario;
    @JsonbDateFormat("yyyy-MM-dd")
    private LocalDate fechaBaja;

    public BajaUsuarioEquipoDTO() {
    }

    public Integer getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(Integer equipoId) {
        this.equipoId = equipoId;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(LocalDate fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public Integer getBajaEquipoId() {
        return bajaEquipoId;
    }

    public void setBajaEquipoId(Integer bajaEquipoId) {
        this.bajaEquipoId = bajaEquipoId;
    }
}


