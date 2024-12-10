package uy.edu.utec.servers.entidades.PK_Compuestas;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import uy.edu.utec.servers.entidades.Usuario;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TelefonoUsuario_PK implements Serializable {

    //@Column(name = "telefono", nullable = false, unique = true, length = 20)
    private String telefono;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    private Usuario usuario;

    //getters y setters

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


//constru

    public TelefonoUsuario_PK() {
    }

    public TelefonoUsuario_PK(String telefono, Usuario usuario) {
        this.telefono = telefono;
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TelefonoUsuario_PK that = (TelefonoUsuario_PK) o;
        return Objects.equals(usuario, that.usuario) && Objects.equals(telefono, that.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, telefono);
    }

    @Override
    public String toString() {
        return "TelefonoUsuario_PK{" +
                "telefono='" + telefono + '\'' +
                ", usuario=" + usuario +
                '}';
    }
}


