package uy.edu.utec.servers.dtos;


import jakarta.validation.constraints.*;

import java.io.Serializable;

public class UbicacionDTO implements Serializable {
    @NotNull(message = "Ubicación no puede ser nulo.")
    private Integer idUbicacion;

    @NotBlank(message = "El nombre de la ubicación no debe estar vacío.")
    @Size(min = 2, max = 80, message="El nombre dde la ubicación debe tener entre 2 y 80 caracteres.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "No se pueden ingresar numeros en nombre.")
    private String nombre;

    @Min(value = 1, message = "El número de la ubicación debe ser mayor a 1.")
    @Max(value = 9999, message = "El número de la ubicación debe ser menor a 9999.")
    private Integer numero;

    @NotBlank(message = "El campo piso no debe estar vacío.")
    @Size(min = 1, max = 2, message="El valor del campo Piso debe ser un número entre 1 y 10.")
    private String piso;

    @Min(value = 1, message = "Cama debe ser mayor a 0.")
    @Max(value=1000, message="Cama debe ser menor a 1000.")
    private Integer cama;

    @NotNull
    @Min(value = 1)
    private Integer institucionId;

    @NotNull
    @Min(value = 1)
    private Integer sectorId;

    public UbicacionDTO() {
    }

    public UbicacionDTO(
            Integer idUbicacion,
            String nombre,
            Integer numero,
            String piso,
            Integer cama,
            Integer institucionId,
            Integer sectorId) {
        this.idUbicacion = idUbicacion;
        this.nombre = nombre;
        this.numero = numero;
        this.piso = piso;
        this.cama = cama;
        this.institucionId = institucionId;
        this.sectorId = sectorId;
    }

    public Integer getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(Integer idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public Integer getCama() {
        return cama;
    }

    public void setCama(Integer cama) {
        this.cama = cama;
    }

    public Integer getInstitucionId() {
        return institucionId;
    }

    public void setInstitucionId(Integer institucionId) {
        this.institucionId = institucionId;
    }

    public Integer getSectorId() {
        return sectorId;
    }

    public void setSectorId(Integer sectorId) {
        this.sectorId = sectorId;
    }
}
