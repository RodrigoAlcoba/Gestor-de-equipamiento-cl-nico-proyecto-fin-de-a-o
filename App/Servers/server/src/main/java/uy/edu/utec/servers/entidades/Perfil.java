package uy.edu.utec.servers.entidades;

import jakarta.persistence.*;
import uy.edu.utec.servers.enums.EstadoGeneral;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Perfiles")
public class Perfil implements Serializable {
    //-------------------------------ID.SECUENCIA------------------------------------------
    @Id
    @SequenceGenerator(name = "PERFILES_ID_SEQ", sequenceName = "PERFILES_ID_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERFILES_ID_SEQ")
    @Column(name = "ID_PERFIL")
    private Integer idPerfil;

    //-------------------------------RELACIONES------------------------------------------
    @OneToMany(mappedBy = "perfilUsuario")
    private List<Usuario> usuarios;
    @ManyToMany
    @JoinTable(name = "PERFIL_FUNCIONALIDAD",
            joinColumns = @JoinColumn(name = "ID_FUNCIONALIDAD"),
            inverseJoinColumns = @JoinColumn(name = "ID_PERFIL"))
    private List<Funcionalidad> funcionalidades;

    //-------------------------------COLUMNAS------------------------------------------
    @Column(name = "NOM_PERFIL", length = 40, nullable = false, unique = true)
    private String Nom_Perfil;

    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO", nullable = false)
    private EstadoGeneral estado;

    //------------------------------CONSTRUCTORES------------------------------------------
    public Perfil() {

    };

    public Perfil( List<Usuario> usuarios, String nom_Perfil, EstadoGeneral estado, List<Funcionalidad> funcionalidades) {
        this.usuarios = usuarios;
        Nom_Perfil = nom_Perfil;
        this.estado = estado;
        this.funcionalidades = funcionalidades;
    }

    //-------------------------------GETTERS AND SETTERS------------------------------------------

    public Integer getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    public List<Usuario> getUsuario() {
        return usuarios;
    }

    public void setUsuario(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public String getNom_Perfil() {
        return Nom_Perfil;
    }

    public void setNom_Perfil(String nom_Perfil) {
        Nom_Perfil = nom_Perfil;
    }

    public EstadoGeneral getEstado() {
        return estado;
    }

    public void setEstado(EstadoGeneral estado) {
        this.estado = estado;
    }

    public List<Funcionalidad> getFuncionalidades() {
        return funcionalidades;
    }

    public void setFuncionalidades(List<Funcionalidad> funcionalidades) {
        this.funcionalidades = funcionalidades;
    }

    //-------------------------------TOSTRING------------------------------------------
    @Override
    public String toString() {
        return "Perfil{" +
                "idPerfil=" + idPerfil +
                ", usuarios=" + usuarios +
                ", funcionalidades=" + funcionalidades +
                ", Nom_Perfil='" + Nom_Perfil + '\'' +
                ", estado=" + estado +
                '}';
    }
}
