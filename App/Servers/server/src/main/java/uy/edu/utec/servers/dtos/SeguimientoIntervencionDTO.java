package uy.edu.utec.servers.dtos;


import jakarta.json.bind.annotation.JsonbDateFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class SeguimientoIntervencionDTO implements Serializable {
    private Integer seguimientoIntervencionId;
    private Integer intervencionId;
    private String tipoIntervencion;
    private String motivo;
    private String observaciones;
    @JsonbDateFormat("yyyy-MM-dd")
    private LocalDate fechaHora;
    public SeguimientoIntervencionDTO() {
    }

    public SeguimientoIntervencionDTO(Integer seguimientoIntervencionId, Integer intervencionId, String tipoIntervencion, String motivo, String observaciones, LocalDate fechaHora) {
        this.seguimientoIntervencionId = seguimientoIntervencionId;
        this.intervencionId = intervencionId;
        this.tipoIntervencion = tipoIntervencion;
        this.motivo = motivo;
        this.observaciones = observaciones;
        this.fechaHora = fechaHora;
    }

    //----------------Getters y Setters----------------------


    public Integer getSeguimientoIntervencionId() {
        return seguimientoIntervencionId;
    }

    public void setSeguimientoIntervencionId(Integer seguimientoIntervencionId) {
        this.seguimientoIntervencionId = seguimientoIntervencionId;
    }

    public Integer getIntervencionId() {
        return intervencionId;
    }

    public void setIntervencionId(Integer intervencionId) {
        this.intervencionId = intervencionId;
    }

    public String getTipoIntervencion() {
        return tipoIntervencion;
    }

    public void setTipoIntervencion(String tipoIntervencion) {
        this.tipoIntervencion = tipoIntervencion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public LocalDate getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDate fechaHora) {
        this.fechaHora = fechaHora;
    }

    @Override
    public String toString() {
        return tipoIntervencion;
    }
}

