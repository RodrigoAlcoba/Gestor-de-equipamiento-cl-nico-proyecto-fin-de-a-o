package uy.edu.utec.servers.entidades;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "INTERVENCIONES")
public class Intervencion {
    //-------------------------------ID-SECUENCIA------------------------------------------
    @Id
    @SequenceGenerator(name = "INTERVENCIONES_ID_SEQ", sequenceName = "INTERVENCIONES_ID_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INTERVENCIONES_ID_SEQ")
    @Column(name = "ID_Intervencion")
    private Integer idIntervencion;

    //-------------------------------RELACIONES------------------------------------------
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_EQUIPO")
    private Equipo equipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TIPO_INTERVENCION")
    private TipoIntervencion tipoIntervencion;

    @OneToMany(mappedBy = "intervencion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SeguimientoIntervencion> trabajarIntervencionList;

    //-------------------------------COLUMNAS------------------------------------------
    @Column(name = "MOTIVO", nullable = false, length = 50)
    private String motivo;

    @Column(name = "COMENTARIO", length = 200)
    private String comentario;

    @Column(name = "FECHA_HORA", nullable = false, unique = true)
    private LocalDate fechaHora;

    //-------------------------------CONSTRUCTORES-------------------------------------------
    public Intervencion() {
    }

    public Intervencion(Integer idIntervencion, Equipo equipo, Usuario usuario, TipoIntervencion tipoIntervencion, List<SeguimientoIntervencion> trabajarIntervencionList, String motivo, String comentario, LocalDate fechaHora) {
        this.idIntervencion = idIntervencion;
        this.equipo = equipo;
        this.usuario = usuario;
        this.tipoIntervencion = tipoIntervencion;
        this.trabajarIntervencionList = trabajarIntervencionList;
        this.motivo = motivo;
        this.comentario = comentario;
        this.fechaHora = fechaHora;
    }

    public Integer getIdIntervencion() {
        return idIntervencion;
    }

    public void setIdIntervencion(Integer idIntervencion) {
        this.idIntervencion = idIntervencion;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TipoIntervencion getTipoIntervencion() {
        return tipoIntervencion;
    }

    public void setTipoIntervencion(TipoIntervencion tipoIntervencion) {
        this.tipoIntervencion = tipoIntervencion;
    }

    public List<SeguimientoIntervencion> getTrabajarIntervencionList() {
        return trabajarIntervencionList;
    }

    public void setTrabajarIntervencionList(List<SeguimientoIntervencion> trabajarIntervencionList) {
        this.trabajarIntervencionList = trabajarIntervencionList;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDate fechaHora) {
        this.fechaHora = fechaHora;
    }
}