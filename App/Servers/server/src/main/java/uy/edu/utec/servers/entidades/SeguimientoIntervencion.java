package uy.edu.utec.servers.entidades;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "SEGUIMIENTO_INTERVENCION")
public class SeguimientoIntervencion {
    //-------------------------------ID-SECUENCIA------------------------------------------
    @Id
    @SequenceGenerator(name = "SEGUIMIENTO_INTERVENCION_ID_SEQ", sequenceName = "SEGUIMIENTO_INTERVENCION_ID_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEGUIMIENTO_INTERVENCION_ID_SEQ")
    @Column(name = "ID_Seguimiento_Intervencion")
    private Integer idSeguimientoIntervencion;

    //-------------------------------RELACIONES------------------------------------------
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_INTERVENCION")
    private Intervencion intervencion;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Tipo_Intervencion")
    private TipoIntervencion tipoIntervencion;

    //-------------------------------COLUMNA------------------------------------------
    @Column(name = "FECHA_HORA")
    private LocalDate fechaHora;

    @Column(name = "MOTIVO", nullable = true, length = 50)
    private String motivo;

    @Column(name = "OBSERVACIONES", nullable = true, length = 200)
    private String observaciones;

    public SeguimientoIntervencion() {
    }

    public SeguimientoIntervencion(Integer idSeguimientoIntervencion, Intervencion intervencion, LocalDate fechaHora, String motivo, String observaciones) {
        this.idSeguimientoIntervencion = idSeguimientoIntervencion;
        this.intervencion = intervencion;
        this.tipoIntervencion = tipoIntervencion;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.observaciones = observaciones;
    }

    public Integer getIdSeguimientoIntervencion() {
        return idSeguimientoIntervencion;
    }

    public void setIdSeguimientoIntervencion(Integer idSeguimientoIntervencion) {
        this.idSeguimientoIntervencion = idSeguimientoIntervencion;
    }

    public TipoIntervencion getTipoIntervencion() {
        return tipoIntervencion;
    }

    public void setTipoIntervencion(TipoIntervencion tipoIntervencion) {
        this.tipoIntervencion = tipoIntervencion;
    }

    public Intervencion getIntervencion() {
        return intervencion;
    }

    public void setIntervencion(Intervencion intervencion) {
        this.intervencion = intervencion;
    }

    public LocalDate getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDate fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
