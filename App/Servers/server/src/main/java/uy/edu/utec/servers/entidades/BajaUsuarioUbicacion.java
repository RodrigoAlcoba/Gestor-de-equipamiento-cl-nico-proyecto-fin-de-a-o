package uy.edu.utec.servers.entidades;

import jakarta.persistence.*;
import uy.edu.utec.servers.entidades.PK_Compuestas.BajaUsuarioUbicacion_PK;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "BAJA_USUARIO_UBICACION")
public class BajaUsuarioUbicacion implements Serializable {
    //-------------------------------PKCOMPUESTA------------------------------------------
    @EmbeddedId
    BajaUsuarioUbicacion_PK bajaUsuarioUbicacionPk;

    //-------------------------------COLUMNAS------------------------------------------
    @Column(name = "RAZON", length = 80, nullable = false)
    private String razon;
    @Column(name = "COMENTARIO", length = 200, nullable = false)
    private String comentario;

    //-------------------------------RELACIONES------------------------------------------
    @JoinColumn(name = "ID_Ubicacion", nullable = false, insertable = false, updatable = false)
    @ManyToOne
    private Ubicacion ubicacionBajaUbicacion;

    @ManyToOne
    @JoinColumn(name = "ID_Usuario", nullable = false, insertable = false, updatable = false)
    private Usuario usuarioBajaUbicacion;

    //-------------------------------CONSTRUCTORES------------------------------------------
    public BajaUsuarioUbicacion(
            BajaUsuarioUbicacion_PK bajaUsuarioUbicacionPk,
            Date fec_Baja, String razon,
            String comentario,
            Ubicacion ubicacionBajaUbicacion,
            Usuario usuarioBajaUbicacion)
    {
        this.bajaUsuarioUbicacionPk = new BajaUsuarioUbicacion_PK(usuarioBajaUbicacion, ubicacionBajaUbicacion, fec_Baja);
        this.razon = razon;
        this.comentario = comentario;
        this.ubicacionBajaUbicacion = ubicacionBajaUbicacion;
        this.usuarioBajaUbicacion = usuarioBajaUbicacion;
    }

    public BajaUsuarioUbicacion() {

    }

    //-------------------------------GETTERS AND SETTERS------------------------------------------

    public BajaUsuarioUbicacion_PK getBajaUsuarioUbicacionPk() {
        return bajaUsuarioUbicacionPk;
    }

    public void setBajaUsuarioUbicacionPk(BajaUsuarioUbicacion_PK bajaUsuarioUbicacionPk) {
        this.bajaUsuarioUbicacionPk = bajaUsuarioUbicacionPk;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Ubicacion getUbicacionBajaUbicacion() {
        return ubicacionBajaUbicacion;
    }

    public void setUbicacionBajaUbicacion(Ubicacion ubicacionBajaUbicacion) {
        this.ubicacionBajaUbicacion = ubicacionBajaUbicacion;
    }

    public Usuario getUsuarioBajaUbicacion() {
        return usuarioBajaUbicacion;
    }

    public void setUsuarioBajaUbicacion(Usuario usuarioBajaUbicacion) {
        this.usuarioBajaUbicacion = usuarioBajaUbicacion;
    }
    //-------------------------------TOSTRING------------------------------------------

    @Override
    public String toString() {
        return "BajaUsuarioUbicacion{" +
                "bajaUsuarioUbicacionPk=" + bajaUsuarioUbicacionPk +
                ", razon='" + razon + '\'' +
                ", comentario='" + comentario + '\'' +
                ", ubicacionBajaUbicacion=" + ubicacionBajaUbicacion +
                ", usuarioBajaUbicacion=" + usuarioBajaUbicacion +
                '}';
    }
}