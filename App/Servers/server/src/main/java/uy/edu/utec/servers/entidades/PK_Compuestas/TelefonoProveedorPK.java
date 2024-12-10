package uy.edu.utec.servers.entidades.PK_Compuestas;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import uy.edu.utec.servers.entidades.Proveedor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TelefonoProveedorPK implements Serializable {
    @Column(name = "TELEFONO", nullable = false, unique = true, length = 20)
    private String telefono;

    //-------------------------------RELACIONES-PK------------------------------------------
    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_PROVEEDOR", nullable = false)
    private Proveedor proveedor;

    //-------------------------------CONSTRUCTORES------------------------------------------
    public TelefonoProveedorPK() {
    }

    public TelefonoProveedorPK(String telefono, Proveedor proveedor) {
        this.telefono = telefono;
        this.proveedor = proveedor;
    }
    //-------------------------------GETTERS AND SETTERS------------------------------------------
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TelefonoProveedorPK that = (TelefonoProveedorPK) o;
        return Objects.equals(telefono, that.telefono) && Objects.equals(proveedor, that.proveedor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(telefono, proveedor);
    }
}
