package uy.edu.utec.servers.entidades;

import jakarta.persistence.*;
import uy.edu.utec.servers.enums.EstadoUsuario;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Usuarios")
public class Usuario implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    //-------------------------------ID-SECUENCIA------------------------------------------
    @Id
    @SequenceGenerator(name = "USUARIOS_ID_SEQ", sequenceName = "USUARIOS_ID_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIOS_ID_SEQ")
    @Column(name = "ID_Usuario")
    private Integer idUsuario;

    //-------------------------------COLUMNAS------------------------------------------
    @Column(name= "CEDULA", length = 9, nullable = false, unique = true)
    private String cedula;

    @Column(name = "EMAIL", length = 50, nullable = false, unique = true)
    private String email;

    @Column(name = "NOMBRE", length = 20, nullable = false)
    private String nombre;

    @Column(name = "NOMBRE2", length = 20, nullable = true)
    private String nombre2;

    @Column(name = "APELLIDO", length = 21, nullable = false)
    private String apellido;

    @Column(name = "APELLIDO2", length = 21, nullable = false)
    private String apellido2;

    @Column(name = "FEC_NAC", nullable = false)
    private LocalDate fec_nac;

    @Column(name = "NOM_USUARIO", length = 51, nullable = false)
    private String nom_usuario;

    @Column(name = "CONTRASENIA", length = 50, nullable = false)
    private String contrasenia;
    @Transient
    private String telefono;
    @OneToMany(mappedBy = "usuario")
    private List<TelefonoUsuario> telefonos;

    @Enumerated(EnumType.STRING)
    @Column(name = "Estado", nullable = false)
    private EstadoUsuario estado;

    //-------------------------------RELACIONES------------------------------------------
    @ManyToOne(cascade ={ CascadeType.ALL})
    @JoinColumn(name = "ID_PERFIL")
    private Perfil perfilUsuario;

    @ManyToOne(cascade ={ CascadeType.ALL})
    @JoinColumn(name = "ID_Institucion")
    private Institucion institucion;

    @OneToMany(mappedBy = "usuarioMovEquipo")
    private List<MovEquipo> movEquipos;

    @OneToMany(mappedBy = "usuarioBajaUbicacion")
    private List<BajaUsuarioUbicacion> bajaUsuarioUbicaciones;

    @OneToMany(mappedBy = "usuarioEquipoPK.usuario")
    private List<BajaUsuarioEquipo> bajasEquipos;

    //-------------------------------CONSTRUCTORES------------------------------------------
    public Usuario(){}

    public Usuario(
            String cedula,
            String email,
            String nombre,
            String nombre2,
            String apellido,
            String apellido2,
            LocalDate fec_nac,
            String nom_usuario,
            String contrasenia,
            String telefono,
            EstadoUsuario estado,
            Perfil perfilUsuario,
            Institucion institucion,
            List<MovEquipo> movEquipos,
            List<BajaUsuarioUbicacion> bajaUsuarioUbicaciones,
            List<BajaUsuarioEquipo> bajasEquipos) {
        this.cedula = cedula;
        this.email = email;
        this.nombre = nombre;
        this.nombre2 = nombre2;
        this.apellido = apellido;
        this.apellido2 = apellido2;
        this.fec_nac = fec_nac;
        this.nom_usuario = nom_usuario;
        this.contrasenia = contrasenia;
        this.telefono = telefono;
        this.estado = estado;
        this.perfilUsuario = perfilUsuario;
        this.institucion = institucion;
        this.movEquipos = movEquipos;
        this.bajaUsuarioUbicaciones = bajaUsuarioUbicaciones;
        this.bajasEquipos = bajasEquipos;
    }

    //-------------------------------GETTERS AND SETTERS------------------------------------------

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Perfil getPerfilUsuario() {
        return perfilUsuario;
    }

    public void setPerfilUsuario(Perfil perfilUsuario) {
        this.perfilUsuario = perfilUsuario;
    }

    public List<MovEquipo> getMovEquipos() {
        return movEquipos;
    }

    public void setMovEquipos(List<MovEquipo> movEquipos) {
        this.movEquipos = movEquipos;
    }

    public List<BajaUsuarioUbicacion> getBajaUsuarioUbicaciones() {
        return bajaUsuarioUbicaciones;
    }

    public void setBajaUsuarioUbicaciones(List<BajaUsuarioUbicacion> bajaUsuarioUbicaciones) {
        this.bajaUsuarioUbicaciones = bajaUsuarioUbicaciones;
    }

    public List<BajaUsuarioEquipo> getBajasEquipos() {
        return bajasEquipos;
    }

    public void setBajasEquipos(List<BajaUsuarioEquipo> bajasEquipos) {
        this.bajasEquipos = bajasEquipos;
    }


    public Institucion getInstitucion() {
        return institucion;
    }

    public void setInstitucion(Institucion institucion) {
        this.institucion = institucion;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public LocalDate getFec_nac() {
        return fec_nac;
    }

    public void setFec_nac(LocalDate fec_nac) {
        this.fec_nac = fec_nac;
    }

    public String getNom_usuario() {
        return nom_usuario;
    }

    public void setNom_usuario(String nom_usuario) {
        this.nom_usuario = nom_usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public EstadoUsuario getEstado() {
        return estado;
    }

    public void setEstado(EstadoUsuario estado) {
        this.estado = estado;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public List<TelefonoUsuario> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<TelefonoUsuario> telefonos) {
        this.telefonos = telefonos;
    }
}