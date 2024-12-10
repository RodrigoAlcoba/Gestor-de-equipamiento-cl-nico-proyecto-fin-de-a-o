package uy.edu.utec.servers.dtos;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;

public class MovEquipoDTO implements Serializable {

    private Integer idMovEquipo;
    @JsonbDateFormat("yyyy-MM-dd")
    private LocalDate fecEntrada;
    @JsonbDateFormat("yyyy-MM-dd")
    private LocalDate fecSalida;
    @Size(min = 2, max = 80, message = "Las observaciones deben tener entre 2 y 80 caracteres")
    private String observaciones;
    @NotNull
    private Integer equipoId;
    @NotNull
    private Integer ubicacionId;
    @NotNull
    private Integer usuarioId;

    public MovEquipoDTO() {
    }

    public MovEquipoDTO(
            Integer idMovEquipo,
            LocalDate fecEntrada,
            LocalDate fecSalida,
            String observaciones,
            Integer equipoId,
            Integer ubicacionId,
            Integer usuarioId) {
        this.idMovEquipo = idMovEquipo;
        this.fecEntrada = fecEntrada;
        this.fecSalida = fecSalida;
        this.observaciones = observaciones;
        this.equipoId = equipoId;
        this.ubicacionId = ubicacionId;
        this.usuarioId = usuarioId;
    }

    public Integer getIdMovEquipo() {
        return idMovEquipo;
    }

    public void setIdMovEquipo(Integer idMovEquipo) {
        this.idMovEquipo = idMovEquipo;
    }

    public LocalDate getFecEntrada() {
        return fecEntrada;
    }

    public void setFecEntrada(LocalDate fecEntrada) {
        this.fecEntrada = fecEntrada;
    }

    public LocalDate getFecSalida() {
        return fecSalida;
    }

    public void setFecSalida(LocalDate fecSalida) {
        this.fecSalida = fecSalida;
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
