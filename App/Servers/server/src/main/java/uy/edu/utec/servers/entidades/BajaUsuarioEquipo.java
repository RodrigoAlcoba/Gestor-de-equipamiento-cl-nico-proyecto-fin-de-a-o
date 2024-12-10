package uy.edu.utec.servers.entidades;

import jakarta.persistence.*;
import uy.edu.utec.servers.entidades.PK_Compuestas.BajaUsuarioEquipoPK;

import java.time.LocalDate;


@Entity
@Table(name = "BAJA_USUARIO_EQUIPO")
public class BajaUsuarioEquipo {

    //-------------------------------PKCOMPUESTA------------------------------------------
    @EmbeddedId
    private BajaUsuarioEquipoPK usuarioEquipoPK;

    //-------------------------------COLUMNAS------------------------------------------
    @Column(name = "RAZON", nullable = false, length = 80)
    private String razon;

    @Column(name = "COMENTARIO", nullable = false, length = 200)
    private String comentario;

    //-------------------------------RELACIONES------------------------------------------
    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_Equipo", insertable = false, updatable = false)
    private Equipo equipo;

    //-------------------------------CONSTRUCTORES------------------------------------------
    public BajaUsuarioEquipo() {
    }

    public BajaUsuarioEquipo(Usuario usuario, Equipo equipo, LocalDate fecBaja, String razon, String comentario) {
        this.usuarioEquipoPK = new BajaUsuarioEquipoPK(usuario,equipo,fecBaja);
        this.razon = razon;
        this.comentario = comentario;
    }

    //-------------------------------GETTERS AND SETTERS------------------------------------------
   public Usuario getUsuario(){
        return usuarioEquipoPK.getUsuario();
   }

   public void setUsuario(Usuario usuario){
        usuarioEquipoPK.setUsuario(usuario);
   }

   public Equipo getEquipo(){
        return usuarioEquipoPK.getEquipo();
   }

   public void setEquipo( Equipo equipo){
        usuarioEquipoPK.setEquipo(equipo);
   }

   public LocalDate getFecBaja(){
        return usuarioEquipoPK.getFechaBaja();
   }

   public void setFecBaja(LocalDate fecBaja){
        usuarioEquipoPK.setFechaBaja(fecBaja);
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

    public BajaUsuarioEquipoPK getUsuarioEquipoPK() {
        return usuarioEquipoPK;
    }

    public void setUsuarioEquipoPK(BajaUsuarioEquipoPK usuarioEquipoPK) {
        this.usuarioEquipoPK = usuarioEquipoPK;
    }
    //-------------------------------TOSTRING------------------------------------------

    @Override
    public String toString() {
        return "BajaUsuarioEquipo{" +
                "usuarioEquipoPK=" + usuarioEquipoPK +
                ", razon='" + razon + '\'' +
                ", comentario='" + comentario + '\'' +
                ", equipo=" + equipo +
                '}';
    }
}