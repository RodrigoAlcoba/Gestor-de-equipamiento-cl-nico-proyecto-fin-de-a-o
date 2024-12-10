package uy.edu.utec.servers.entidades.PK_Compuestas;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import uy.edu.utec.servers.entidades.Equipo;
import uy.edu.utec.servers.entidades.Usuario;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class BajaUsuarioEquipoPK implements Serializable {

    //-------------------------------RELACIONES-PK------------------------------------------
    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    private Usuario usuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_Equipo", nullable = false)
    private Equipo equipo;

    //-------------------------------COLUMNA-PK------------------------------------------
    @Column(name = "FEC_BAJA", nullable = false)
    private LocalDate fechaBaja;

    //-------------------------------CONSTRUCTORES------------------------------------------
    public BajaUsuarioEquipoPK() {
    }

    public BajaUsuarioEquipoPK(Usuario usuario, Equipo equipo, LocalDate fechaBaja) {
        this.usuario = usuario;
        this.equipo = equipo;
        this.fechaBaja = fechaBaja;
    }

    //-------------------------------GETTERS AND SETTERS------------------------------------------
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public LocalDate getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(LocalDate fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BajaUsuarioEquipoPK that = (BajaUsuarioEquipoPK) o;
        return Objects.equals(usuario, that.usuario) && Objects.equals(equipo, that.equipo) && Objects.equals(fechaBaja, that.fechaBaja);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, equipo, fechaBaja);
    }
}
