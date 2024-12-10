package uy.edu.utec.servers.dtos.creacionales;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.validation.constraints.*;
import uy.edu.utec.servers.validaciones.anotacionesPersonalizadas.FechaGarantiaValida;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import uy.edu.utec.servers.dtos.MovEquipoDTO;

import java.io.Serializable;
import java.time.LocalDate;

@FechaGarantiaValida
public class CrearEquipoDTO implements Serializable {

    @NotBlank(message = "No puede dejar el nombre del equipo vacío")
    @Size(min = 2, max = 80, message = "El nombre del equipo debe tener entre 2 y 80 caracteres")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "En el nombre del equipo no se admiten caracteres especiales.")
    private String nomEquipo;


    private Integer tipoEquipoId;


    private Integer modeloId;

    @NotNull(message = "El campo numero de serie no puede estar vacío")
    @Min(value = 1)
    private Integer numSerie;

    @NotNull(message = "La garantía no debe estar vacía")
    @JsonbDateFormat("yyyy-MM-dd")
    private LocalDate garantia;


    private Integer paisId;


    private Integer proveedorId;

    @NotNull(message = "El campo fecha de adquisción no puede estar vacío")
    @JsonbDateFormat("yyyy-MM-dd")
    @PastOrPresent(message = "Fecha de adquisición inválida")
    private LocalDate fecAdquisicion;

    @NotNull(message = "La identificación interna no debe estar vacía")
    @Min(value = 0, message = "La identificación interna debe ser mayor o igual a 0")
    private Integer idInterna;


    private Integer ubicacionId;

    @NotBlank(message = "La imágen no puede estar vacía")
    private String imagen;


    private Integer marcaId;

   // private CrearMovEquipoDTO crearMovEquipoDTO;

    public CrearEquipoDTO() {
    }

    public CrearEquipoDTO(String nomEquipo, Integer tipoEquipoId, Integer modeloId,
                          Integer numSerie, LocalDate garantia, Integer paisId,
                          Integer proveedorId, LocalDate fecAdquisicion, Integer idInterna,
                          String imagen, Integer ubicacionId) {
        this.nomEquipo = nomEquipo;
        this.tipoEquipoId = tipoEquipoId;
        this.modeloId = modeloId;
        this.numSerie = numSerie;
        this.garantia = garantia;
        this.paisId = paisId;
        this.proveedorId = proveedorId;
        this.fecAdquisicion = fecAdquisicion;
        this.idInterna = idInterna;
        this.imagen = imagen;
        this.ubicacionId = ubicacionId;
    }

    public Integer getIdInterna() {
        return idInterna;
    }

    public void setIdInterna(Integer idInterna) {
        this.idInterna = idInterna;
    }

    public String getNomEquipo() {
        return nomEquipo;
    }

    public void setNomEquipo(String nomEquipo) {
        this.nomEquipo = nomEquipo;
    }

    public Integer getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(Integer numSerie) {
        this.numSerie = numSerie;
    }

    public LocalDate getFecAdquisicion() {
        return fecAdquisicion;
    }

    public void setFecAdquisicion(LocalDate fecAdquisicion) {
        this.fecAdquisicion = fecAdquisicion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public LocalDate getGarantia() {
        return garantia;
    }

    public void setGarantia(LocalDate garantia) {
        this.garantia = garantia;
    }
    public Integer getPaisId() {
        return paisId;
    }

    public void setPaisId(Integer paisId) {
        this.paisId = paisId;
    }

    public Integer getTipoEquipoId() {
        return tipoEquipoId;
    }

    public void setTipoEquipoId(Integer tipoEquipoId) {
        this.tipoEquipoId = tipoEquipoId;
    }

    public Integer getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Integer proveedorId) {
        this.proveedorId = proveedorId;
    }

    public Integer getModeloId() {
        return modeloId;
    }

    public void setModeloId(Integer modeloId) {
        this.modeloId = modeloId;

    }

    public Integer getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(Integer marcaId) {
        this.marcaId = marcaId;
    }

    public Integer getUbicacionId() {
        return ubicacionId;
    }

    public void setUbicacionId(Integer ubicacionId) {
        this.ubicacionId = ubicacionId;
    }

    /*public CrearMovEquipoDTO getCrearMovEquipoDTO() {
        return crearMovEquipoDTO;
    }

    public void setCrearMovEquipoDTO(CrearMovEquipoDTO crearMovEquipoDTO) {
        this.crearMovEquipoDTO = crearMovEquipoDTO;
    }*/

    @Override
    public String toString() {
        return "CrearEquipoDTO{" +
                "idInterna=" + idInterna +
                ", nomEquipo='" + nomEquipo + '\'' +
                ", numSerie=" + numSerie +
                ", fecAdquisicion=" + fecAdquisicion +
                ", imagen='" + imagen + '\'' +
                ", garantia=" + garantia +
                ", paisId=" + paisId +
                ", tipoEquipoId=" + tipoEquipoId +
                ", proveedorId=" + proveedorId +
                ", modeloId=" + modeloId +
                '}';
    }


}
