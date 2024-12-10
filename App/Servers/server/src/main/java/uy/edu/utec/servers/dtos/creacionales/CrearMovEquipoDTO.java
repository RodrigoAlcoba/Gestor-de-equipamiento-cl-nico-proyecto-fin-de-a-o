package uy.edu.utec.servers.dtos.creacionales;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;

public class CrearMovEquipoDTO implements Serializable {

    @NotNull
    @PastOrPresent(message = "La fecha no puede ser mayor a la actual")
    private LocalDate fecEntrada;

    @NotBlank(message = "Observaciones no puede estar vacío")
    @Size(min = 6, max = 150, message = "Observaciones mínimo 6 máximo 150 caracteres")
    private String observaciones;

    @NotNull
    private Integer equipoId;
    @NotNull
    private Integer ubicacionId;
    @NotNull
    private Integer usuarioId;

    public CrearMovEquipoDTO() {
    }

    public CrearMovEquipoDTO(LocalDate fecEntrada, String observaciones, Integer equipoId, Integer ubicacionId, Integer usuarioId) {
        this.fecEntrada = fecEntrada;
        this.observaciones = observaciones;
        this.equipoId = equipoId;
        this.ubicacionId = ubicacionId;
        this.usuarioId = usuarioId;
    }

    public LocalDate getFecEntrada() {
        return fecEntrada;
    }

    public void setFecEntrada(LocalDate fecEntrada) {
        this.fecEntrada = fecEntrada;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(Integer equipoId) {
        this.equipoId = equipoId;
    }

    public Integer getUbicacionId() {
        return ubicacionId;
    }

    public void setUbicacionId(Integer ubicacionId) {
        this.ubicacionId = ubicacionId;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }
}
