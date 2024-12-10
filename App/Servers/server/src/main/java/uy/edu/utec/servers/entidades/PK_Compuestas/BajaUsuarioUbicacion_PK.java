package uy.edu.utec.servers.entidades.PK_Compuestas;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import uy.edu.utec.servers.entidades.Ubicacion;
import uy.edu.utec.servers.entidades.Usuario;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Embeddable
public class BajaUsuarioUbicacion_PK implements Serializable {
    //-------------------------------RELACIONES-PK------------------------------------------
    @ManyToOne
    @JoinColumn(name = "ID_Usuario")
    private Usuario usuarioBajaUbicacion_id;
    @ManyToOne
    @JoinColumn(name = "ID_Ubicacion")
    private Ubicacion ubicacionBajaUbicacion_id;

    //-------------------------------COLUMNA-PK------------------------------------------
    @Column(name = "FEC_BAJA", nullable = false)
    private Date fec_baja;

    //-------------------------------CONSTRUCTORES------------------------------------------

    public BajaUsuarioUbicacion_PK(){};

    public BajaUsuarioUbicacion_PK(Usuario usuarioBajaUbicacion_id, Ubicacion ubicacionBajaUbicacion_id, Date fec_baja) {
        this.usuarioBajaUbicacion_id = usuarioBajaUbicacion_id;
        this.ubicacionBajaUbicacion_id = ubicacionBajaUbicacion_id;
        this.fec_baja = fec_baja;
    }

    //-------------------------------GETTERS AND SETTERS------------------------------------------
    public Usuario getUsuarioBajaUbicacion_id() {
        return usuarioBajaUbicacion_id;
    }

    public void setUsuarioBajaUbicacion_id(Usuario usuarioBajaUbicacion_id) {
        this.usuarioBajaUbicacion_id = usuarioBajaUbicacion_id;
    }

    public Ubicacion getUbicacionBajaUbicacion_id() {
        return ubicacionBajaUbicacion_id;
    }

    public void setUbicacionBajaUbicacion_id(Ubicacion ubicacionBajaUbicacion_id) {
        this.ubicacionBajaUbicacion_id = ubicacionBajaUbicacion_id;
    }

    public Date getFec_baja() {
        return fec_baja;
    }

    public void setFec_baja(Date fec_baja) {
        this.fec_baja = fec_baja;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BajaUsuarioUbicacion_PK that = (BajaUsuarioUbicacion_PK) o;
        return Objects.equals(usuarioBajaUbicacion_id, that.usuarioBajaUbicacion_id) && Objects.equals(ubicacionBajaUbicacion_id, that.ubicacionBajaUbicacion_id) && Objects.equals(fec_baja, that.fec_baja);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioBajaUbicacion_id, ubicacionBajaUbicacion_id, fec_baja);
    }
}
