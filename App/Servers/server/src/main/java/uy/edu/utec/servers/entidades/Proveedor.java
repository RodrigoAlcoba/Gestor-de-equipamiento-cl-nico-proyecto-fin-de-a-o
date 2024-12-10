package uy.edu.utec.servers.entidades;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import uy.edu.utec.servers.enums.EstadoGeneral;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "proveedores")
public class Proveedor implements Serializable {
    //-------------------------------ID-SECUENCIA------------------------------------------
    @Id
    @SequenceGenerator(name = "PROVEEDORES_ID_SEQ", sequenceName = "PROVEEDORES_ID_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROVEEDORES_ID_SEQ")
    @Column(name = "ID_PROVEEDOR")
    private Integer idProveedor;

    //-------------------------------COLUMNAS------------------------------------------
    @Column(name = "NOM_PROVEEDOR", length = 50, nullable = false, unique = true)
    private String nomProveedor;

    @Column(name = "EMAIL", length = 50, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO", nullable = false)
    private EstadoGeneral estado;

    @Column(name = "SITIO", length = 80, nullable = false)
    private String sitio;


    //-------------------------------RELACIONES------------------------------------------
    @JsonbTransient
    @OneToMany(mappedBy = "telefonoProveedor")
    private List<TelefonoProveedor> telefonoProveedor;

    @OneToMany(mappedBy="proveedor")
    private List<Equipo> equipos;

    //-------------------------------CONSTRUCTORES------------------------------------------
    public Proveedor(){}

    public Proveedor(String nomProveedor, String email, EstadoGeneral estado, String sitio, List<TelefonoProveedor> telefonoProveedor, List<Equipo> equipos) {
        this.nomProveedor = nomProveedor;
        this.email = email;
        this.estado = estado;
        this.sitio = sitio;
        this.telefonoProveedor = telefonoProveedor;
        this.equipos = equipos;
    }

    //-------------------------------GETTERS AND SETTERS------------------------------------------


    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNomProveedor() {
        return nomProveedor;
    }

    public void setNomProveedor(String nomProveedor) {
        this.nomProveedor = nomProveedor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EstadoGeneral getEstado() {
        return estado;
    }

    public void setEstado(EstadoGeneral estado) {
        this.estado = estado;
    }

    public String getSitio() {
        return sitio;
    }

    public void setSitio(String sitio) {
        this.sitio = sitio;
    }

    public List<TelefonoProveedor> getTelefonoProveedor() {
        return telefonoProveedor;
    }

    public void setTelefonoProveedor(List<TelefonoProveedor> telefonoProveedor) {
        this.telefonoProveedor = telefonoProveedor;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    //-------------------------------TOSTRING------------------------------------------
    @Override
    public String toString() {
        return "Proveedor{" +
                "idProveedor=" + idProveedor +
                ", nomProveedor='" + nomProveedor + '\'' +
                ", email='" + email + '\'' +
                ", estado=" + estado +
                ", sitio='" + sitio + '\'' +
                ", telefonoProveedor=" + telefonoProveedor +
                '}';
    }

    public void addTelefonoProveedor(TelefonoProveedor telefonoProveedor) {
        this.telefonoProveedor.add(telefonoProveedor);
    }
}