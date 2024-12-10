package uy.edu.utec.servers.entidades;

import jakarta.persistence.*;
import uy.edu.utec.servers.entidades.PK_Compuestas.TelefonoProveedorPK;

@Entity
@Table(name = "TELEFONOS_PROVEEDOR")
public class TelefonoProveedor {
    //-------------------------------PKCOMPUESTA------------------------------------------
    @EmbeddedId
    private TelefonoProveedorPK PK_TEL_PROVE;

    //-------------------------------CONSTRUCTORES------------------------------------------
    public TelefonoProveedor() {
    }
    public TelefonoProveedor(String telefono, Proveedor proveedor) {
        this.PK_TEL_PROVE = new TelefonoProveedorPK(telefono, proveedor);
    }

    //-------------------------------RELACIONES------------------------------------------

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_PROVEEDOR", insertable = false, updatable = false)
    private Proveedor telefonoProveedor;

    //-------------------------------GETTERS AND SETTERS------------------------------------------

    public TelefonoProveedorPK getPK_TEL_PROVE() {
        return PK_TEL_PROVE;
    }

    public void setPK_TEL_PROVE(TelefonoProveedorPK PK_TEL_PROVE) {
        this.PK_TEL_PROVE = PK_TEL_PROVE;
    }

    public Proveedor getTelefonoProveedor() {
        return telefonoProveedor;
    }

    public void setTelefonoProveedor(Proveedor telefonoProveedor) {
        this.telefonoProveedor = telefonoProveedor;
    }
}