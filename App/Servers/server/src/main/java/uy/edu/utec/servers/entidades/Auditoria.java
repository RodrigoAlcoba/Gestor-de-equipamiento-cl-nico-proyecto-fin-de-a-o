package uy.edu.utec.servers.entidades;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "AUDITORIAS")
public class Auditoria {

    //-------------------------------ID-SECUENCIA------------------------------------------
    @Id
    @SequenceGenerator(name = "AUDITORIAS_ID_SEQ", sequenceName = "AUDITORIAS_ID_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUDITORIAS_ID_SEQ")
    @Column(name = "ID_Auditoria")
    private Integer idAuditoria;

    //-------------------------------COLUMNAS------------------------------------------
    @Column(name = "Fecha", nullable = false)
    private LocalDate fecha;

    //-------------------------------RELACIONES------------------------------------------
    @ManyToOne
    @JoinColumn(name = "ID_Usuario", nullable = false, insertable = false, updatable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "ID_Operacion", nullable = false, insertable = false, updatable = false)
    private Operacion operacion;

    //-------------------------------CONSTRUCTORES------------------------------------------
    public Auditoria() {
    }

    public Auditoria(LocalDate fecha, Usuario usuario, Operacion operacion) {
        this.fecha = fecha;
        this.usuario = usuario;
        this.operacion = operacion;
    }

    //-------------------------------GETTERS AND SETTERS------------------------------------------
    public Integer getIdAuditoria() {
        return idAuditoria;
    }

    public void setIdAuditoria(Integer idAuditoria) {
        this.idAuditoria = idAuditoria;
    }
    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Operacion getOperacion() {
        return operacion;
    }

    public void setOperacion(Operacion operacion) {
        this.operacion = operacion;
    }

    //-------------------------------TOSTRING------------------------------------------

    @Override
    public String toString() {
        return "Auditoria{" +
                "idAuditoria=" + idAuditoria +
                ", fecha=" + fecha +
                ", usuario=" + usuario +
                ", operacion=" + operacion +
                '}';
    }
}