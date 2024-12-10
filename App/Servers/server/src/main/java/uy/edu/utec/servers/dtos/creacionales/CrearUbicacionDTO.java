package uy.edu.utec.servers.dtos.creacionales;

import jakarta.validation.constraints.*;

import java.io.Serializable;

public class CrearUbicacionDTO implements Serializable {

    @NotBlank(message = "El nombre de la ubicación no debe estar vacío")
    @Size(min = 2, max = 80, message="El nombre dde la ubicación debe tener entre 2 y 80 caracteres")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "No se pueden ingresar numeros en nombre")
    private String nombre;

    @NotNull(message = "El número de la ubicación no debe estar vacío")
    @Min(value = 1, message = "El número de la ubicación debe ser mayor a 1")
    @Max(value = 9999, message = "El número de la ubicación debe ser menor a 9999")
    private Integer numero;

    @NotBlank(message = "El campo piso no debe estar vacío")
    @Size(min = 1, max = 50, message="Piso debe tener entre 5 y 50 caracteres")
    private String piso;

    //La cama es opcional
    @Min(value = 1, message = "Cama debe ser mayor a 1")
    @Max(value=1000, message="Cama debe ser menor a 1000")
    private Integer cama;


    @Min(value = 1)
    private Integer institucionId;

    @Min(value = 1)
    private Integer sectorId;

    public CrearUbicacionDTO() {
    }

    public CrearUbicacionDTO(
            String nombre,
            Integer numero,
            String piso,
            Integer cama,
            Integer institucionId,
            Integer sectorId) {
        this.nombre = nombre;
        this.numero = numero;
        this.piso = piso;
        this.cama = cama;
        this.institucionId = institucionId;
        this.sectorId = sectorId;
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
