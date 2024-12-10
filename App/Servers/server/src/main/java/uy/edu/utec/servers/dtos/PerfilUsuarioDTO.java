package uy.edu.utec.servers.dtos;

import java.io.Serializable;

public class PerfilUsuarioDTO implements Serializable {
    private Integer idPerfil;
    private String nomPerfil;

    public PerfilUsuarioDTO() {
    }

    public Integer getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getNomPerfil() {
        return nomPerfil;
    }

    public void setNomPerfil(String nomPerfil) {
        this.nomPerfil = nomPerfil;
    }
}
