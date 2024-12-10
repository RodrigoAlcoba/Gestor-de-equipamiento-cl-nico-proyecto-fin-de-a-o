package uy.edu.utec.servers.entidades;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "MOV_EQUIPO")
public class MovEquipo implements Serializable {

    //-------------------------------ID-SECUENCIA------------------------------------------
    @Id
    @SequenceGenerator(name = "MOV_EQUIPO_SEQ", sequenceName = "MOV_EQUIPO_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MOV_EQUIPO_SEQ")
    @Column(name = "ID_Mov_Equipo")
    private Integer idMovEquipo;

    //-------------------------------COLUMNAS------------------------------------------
    @Column(name = "Fec_Entrada", nullable = false)
    private LocalDate fecEntrada;

    @Column(name = "Fec_Salida", nullable = true)
    private LocalDate fecSalida;

    @Column(name = "Observaciones", nullable = true, length = 150)
    private String observaciones;

    //-------------------------------RELACIONES------------------------------------------

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_Equipo", updatable = false)
    private Equipo equipoMovEquipo;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_Ubicacion", updatable = false)
    private Ubicacion ubicacionMovEquipo;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_Usuario", updatable = false)
    private Usuario usuarioMovEquipo;


    //-------------------------------CONSTRUCTORES------------------------------------------

    public MovEquipo(){}

    public MovEquipo(LocalDate fecEntrada,
                     LocalDate fecSalida,
                     String observaciones,
                     Equipo equipoMovEquipo,
                     Ubicacion ubicacionMovEquipo,
                     Usuario usuarioMovEquipo) {
        this.fecEntrada = fecEntrada;
        this.fecSalida = fecSalida;
        this.observaciones = observaciones;
        this.equipoMovEquipo = equipoMovEquipo;
        this.ubicacionMovEquipo = ubicacionMovEquipo;
        this.usuarioMovEquipo = usuarioMovEquipo;
    }

    //-------------------------------GETTERS AND SETTERS------------------------------------------

    public Integer getIdMovEquipo() {
        return idMovEquipo;
    }

    public void setIdMovEquipo(Integer idMovEquipo) {
        this.idMovEquipo = idMovEquipo;
    }

    public LocalDate getFecEntrada() {
        return fecEntrada;
    }

    public void setFecEntrada(LocalDate fecEntrada) {
        this.fecEntrada = fecEntrada;
    }

    public LocalDate getFecSalida() {
        return fecSalida;
    }

    public void setFecSalida(LocalDate fecSalida) {
        this.fecSalida = fecSalida;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Equipo getEquipoMovEquipo() {
        return equipoMovEquipo;
    }

    public void setEquipoMovEquipo(Equipo equipoMovEquipo) {
        this.equipoMovEquipo = equipoMovEquipo;
    }

    public Ubicacion getUbicacionMovEquipo() {
        return ubicacionMovEquipo;
    }

    public void setUbicacionMovEquipo(Ubicacion ubicacionMovEquipo) {
        this.ubicacionMovEquipo = ubicacionMovEquipo;
    }

    public Usuario getUsuarioMovEquipo() {
        return usuarioMovEquipo;
    }

    public void setUsuarioMovEquipo(Usuario usuarioMovEquipo) {
        this.usuarioMovEquipo = usuarioMovEquipo;
    }

    //-------------------------------TOSTRING------------------------------------------
    @Override
    public String toString() {
        return "MovEquipo{" +
                "idMovEquipo=" + idMovEquipo +
                ", fecEntrada=" + fecEntrada +
                ", fecSalida=" + fecSalida +
                ", observaciones='" + observaciones + '\'' +
                ", equipoMovEquipo=" + equipoMovEquipo +
                ", ubicacionMovEquipo=" + ubicacionMovEquipo +
                ", usuarioMovEquipo=" + usuarioMovEquipo +
                '}';
    }
}