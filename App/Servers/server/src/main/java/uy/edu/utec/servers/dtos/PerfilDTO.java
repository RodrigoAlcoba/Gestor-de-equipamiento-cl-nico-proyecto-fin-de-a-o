package uy.edu.utec.servers.dtos;

import jakarta.validation.constraints.*;
import uy.edu.utec.servers.enums.EstadoGeneral;

import java.io.Serializable;

public class PerfilDTO implements Serializable {
    private Integer idPerfil;

    @Size(min = 3, max = 40, message = "El nombre de perfil debe tener entre 3 y 40 caracteres")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "En el nombre del perfil no se admiten numeros ni espacios.")
    private String nomPerfil;

    @NotNull(message = "El estado del perfil no puede ser nulo")
    private EstadoGeneral estado;

    //--------------CONSTRUCTOR--------------------------

    public PerfilDTO() {
    }

    //--------------GETTERS AND SETTERS------------------

    public Integer getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getNomPerfil() {
        return nomPerfil;
    }

    public void setNomPerfil(String Nom_Perfil) {
        this.nomPerfil = Nom_Perfil;
    }

    public EstadoGeneral getEstado() {
        return estado;
    }

    public void setEstado(EstadoGeneral estado) {
        this.estado = estado;
    }
}
