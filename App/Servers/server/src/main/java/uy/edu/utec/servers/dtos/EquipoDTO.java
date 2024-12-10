package uy.edu.utec.servers.dtos;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.validation.constraints.*;
import uy.edu.utec.servers.enums.EstadoGeneral;
import uy.edu.utec.servers.validaciones.anotacionesPersonalizadas.FechaGarantiaValidaMod;

import java.io.Serializable;
import java.time.LocalDate;

@FechaGarantiaValidaMod
public class EquipoDTO implements Serializable {

    private Integer idEquipo;

    @NotNull(message = "La identificación interna no debe estar vacía")
    private Integer idInterna;

    @NotBlank(message = "No puede dejar el nombre del equipo vacío")
    @Size(min = 2, max = 80, message = "El nombre del equipo debe tener entre 2 y 80 caracteres")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "En el nombre del equipo no se admiten caracteres especiales.")
    private String nomEquipo;

    @NotNull(message = "El campo numero de serie no puede estar vacío")
    @Min(value = 1)
    private Integer numSerie;

    @NotNull(message = "El campo fecha de adquisción no puede estar vacío")
    @JsonbDateFormat("yyyy-MM-dd")
    @PastOrPresent(message = "Fecha de adquisición inválida")
    private LocalDate fecAdquisicion;

    @NotBlank(message = "La imágen no puede estar vacía")
    private String imagen;
    @NotNull(message = "La garantía no debe estar vacía")
    @JsonbDateFormat("yyyy-MM-dd")
    private LocalDate garantia;
    private EstadoGeneral estado;
    @NotNull(message = "País no debe estar vacío")
    private Integer paisId;
    @NotNull(message = "El tipo de equipo no puede ser nulo")
    private Integer tipoEquipoId;
    @NotNull(message = "Proveedor no debe estar vacío")
    private Integer proveedorId;
    @NotNull(message = "Modelo no debe estar vacío")
    private Integer modeloId;
    @NotNull(message = "Marca no debe estar vacío")
    private Integer marcaId;

    /*@NotNull(message = "La ubicación no debe estar vacía")
    private Integer ubicacionId;*/
//    private List<Intervencion> intervenciones;
   // private MovEquipoDTO ubicacionActual;
//    private List<BajaUsuarioEquipo> bajaUsuarioEquipos;

    public EquipoDTO() {
    }

    public Integer getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Integer idEquipo) {
        this.idEquipo = idEquipo;
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

    public EstadoGeneral getEstado() {
        return estado;
    }

    public void setEstado(EstadoGeneral estado) {
        this.estado = estado;
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

    /*public MovEquipoDTO getUbicacionActual() {
        return ubicacionActual;
    }

    public void setUbicacionActual(MovEquipoDTO ubicacionActual) {
        this.ubicacionActual = ubicacionActual;
    }*/

   /* public Integer getUbicacionId() {
        return ubicacionId;
    }

    public void setUbicacionId(Integer ubicacionId) {
        this.ubicacionId = ubicacionId;
    }*/

    public Integer getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(Integer marcaId) {
        this.marcaId = marcaId;
    }

    @Override
    public String toString() {
        return "EquipoDTO{" +
                "idEquipo=" + idEquipo +
                ", idInterna=" + idInterna +
                ", nomEquipo='" + nomEquipo + '\'' +
                ", numSerie=" + numSerie +
                ", fecAdquisicion=" + fecAdquisicion +
                ", imagen='" + imagen + '\'' +
                ", garantia=" + garantia +
                ", estado=" + estado +
                ", paisId=" + paisId +
                ", tipoEquipoId=" + tipoEquipoId +
                ", proveedorId=" + proveedorId +
                ", modeloId=" + modeloId +
                "marca" +marcaId
        +'}';
    }
}
