package uy.edu.utec.servers.dtos.creacionales;

import java.io.Serializable;

public class CrearInstitucionDTO implements Serializable {
    private String nombre;

    public CrearInstitucionDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
