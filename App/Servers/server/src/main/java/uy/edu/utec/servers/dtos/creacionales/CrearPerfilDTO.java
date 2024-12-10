package uy.edu.utec.servers.dtos.creacionales;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class CrearPerfilDTO implements Serializable {

    @Size(min = 3, max = 40, message = "El nombre de perfil debe tener entre 3 y 40 caracteres")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "En el nombre del perfil no se admiten numeros ni espacios.")
    private String nomPerfil;

    //--------------CONSTRUCTOR--------------------------

    public CrearPerfilDTO() {
    }

    //--------------GETTERS AND SETTERS------------------

    public String getNomPerfil() {
        return nomPerfil;
    }

    public void setNomPerfil(String nomPerfil) {
        this.nomPerfil = nomPerfil;
    }
}
