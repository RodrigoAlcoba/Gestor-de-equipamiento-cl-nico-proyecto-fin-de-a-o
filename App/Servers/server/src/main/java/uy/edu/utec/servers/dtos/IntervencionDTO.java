package uy.edu.utec.servers.dtos;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;

public class IntervencionDTO implements Serializable {
    private Integer idIntervencion;
    @Size(max = 50, message = "El máximo para el motivo es 50 carácteres")
    @NotBlank(message = "Debe ingresar un motivo.")
    private String motivo;
    @Size(max = 150)
    private String comentario;

    private TipoIntervencionDTO tipoIntervencion;
    @JsonbDateFormat("yyyy-MM-dd")
    private LocalDate fechaHora;
    private Integer equipoId;
    private Integer usuarioId;
    private String emailUsuario;



    public IntervencionDTO() {
    }
    public Integer getIdIntervencion() {
        return idIntervencion;
    }

    public void setIdIntervencion(Integer idIntervencion) {
        this.idIntervencion = idIntervencion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDate fechaHora) {
        this.fechaHora = fechaHora;
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

    public TipoIntervencionDTO getTipoIntervencion() {
        return tipoIntervencion;
    }

    public void setTipoIntervencion(TipoIntervencionDTO tipoIntervencion) {
        this.tipoIntervencion = tipoIntervencion;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

}
