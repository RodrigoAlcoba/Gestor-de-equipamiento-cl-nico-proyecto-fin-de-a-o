package uy.edu.utec.servers.dtos;

import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.util.List;

public class ProveedorDTO implements Serializable {
    @NotNull
    private Integer idProveedor;
    @NotNull
    @NotBlank
    @Size(min = 3, max = 50)
    private String nomProveedor;
    @NotNull
    @NotBlank
    @Email
    private String email;
    @NotNull
    @NotBlank
    @Size(min = 3, max = 50)
    private String sitio;

    //todo refactor usando @GroupSequence({Default.class, ValidTelefonos.class})
    @NotEmpty.List({
            @NotEmpty
    })
    @Size.List({
        @Size(min = 5, max = 10)
    })
    @NotBlank.List({
            @NotBlank
    })
    private List<String> telefonos;

    public ProveedorDTO() {
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNomProveedor() {
        return nomProveedor;
    }

    public void setNomProveedor(String nomProveedor) {
        this.nomProveedor = nomProveedor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSitio() {
        return sitio;
    }

    public void setSitio(String sitio) {
        this.sitio = sitio;
    }

    public List<String> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<String> telefonos) {
        this.telefonos = telefonos;
    }
}
