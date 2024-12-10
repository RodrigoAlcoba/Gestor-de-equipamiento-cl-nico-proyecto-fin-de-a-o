package uy.edu.utec.servers.dtos;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.validation.constraints.*;
import uy.edu.utec.servers.entidades.BajaUsuarioUbicacion;

import java.io.Serializable;
import java.time.LocalDate;

public class BajaUsuarioUbicacionDTO implements Serializable {

    private Integer usuarioId;
    private Integer ubicacionId;
    @NotNull(message = "El campo fecha no puede estar vacío")
    @PastOrPresent(message = "La fecha no puede ser mayor a la actual")
    @JsonbDateFormat("yyyy-MM-dd")
    private LocalDate fec_baja;
    @NotBlank(message = "El campo razón no puede estar vacío")
    @Size(min = 6, max = 80, message = "La razón debe tener entre 6 y 80 caracteres")
    private String razon;

    @NotBlank(message = "El campo comentario no puede estar vacío")
    @Size(min = 6, max = 80, message = "El comentario debe tener entre 6 y 200 caracteres")
    private String comentario;

    public BajaUsuarioUbicacionDTO(){}

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Integer getUbicacionId() {
        return ubicacionId;
    }

    public void setUbicacionId(Integer ubicacionId) {
        this.ubicacionId = ubicacionId;
    }

    public LocalDate getFec_baja() {
        return fec_baja;
    }

    public void setFec_baja(LocalDate fec_baja) {
        this.fec_baja = fec_baja;
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
}
