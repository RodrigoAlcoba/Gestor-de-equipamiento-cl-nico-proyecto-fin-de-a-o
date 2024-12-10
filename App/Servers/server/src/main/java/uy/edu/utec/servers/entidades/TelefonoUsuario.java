package uy.edu.utec.servers.entidades;


import jakarta.persistence.*;
import uy.edu.utec.servers.entidades.PK_Compuestas.TelefonoUsuario_PK;

import java.io.Serializable;

@Entity
@Table(name = "telefonos_usuarios")
public class TelefonoUsuario implements Serializable {

    //-------------------------------PKCOMPUESTA------------------------------------------
    @EmbeddedId
    TelefonoUsuario_PK PK_TEL;
    @ManyToOne
    @JoinColumn(name = "ID_Usuario", insertable = false, updatable = false)
    private Usuario usuario;

    //-------------------------------CONSTRUCTORES------------------------------------------

    public TelefonoUsuario(){};

    public TelefonoUsuario(TelefonoUsuario_PK PK_TEL, Usuario usuario) {
        this.PK_TEL = PK_TEL;
    }

    //-------------------------------GETTERS AND SETTERS------------------------------------------

    public TelefonoUsuario_PK getPK_TEL() {
        return PK_TEL;
    }

    public void setPK_TEL(TelefonoUsuario_PK PK_TEL) {
        this.PK_TEL = PK_TEL;
    }
}

