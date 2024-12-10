package uy.edu.utec.servers.dtos;

import java.io.Serializable;

public class InstitucionDTO implements Serializable {
    private Integer id;

    private String nom_Institucion;

    public InstitucionDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom_Institucion() {
        return nom_Institucion;
    }

    public void setNom_Institucion(String nom_Institucion) {
        this.nom_Institucion = nom_Institucion;
    }
}
