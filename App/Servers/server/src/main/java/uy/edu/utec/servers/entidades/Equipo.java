package uy.edu.utec.servers.entidades;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import uy.edu.utec.servers.enums.EstadoGeneral;

import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "EQUIPOS")
public class Equipo implements Serializable {
    //-------------------------------ID-SECUENCIA------------------------------------------
    @Id
    @SequenceGenerator(name = "EQUIPOS_ID_SEQ", sequenceName = "EQUIPOS_ID_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EQUIPOS_ID_SEQ")
    @Column(name = "ID_Equipo")
    private Integer idEquipo;

    ////-------------------------------COLUMNAS------------------------------------------
    @Column(name = "IDE_INTERNA", nullable = false, unique = true)
    private Integer idInterna;
    @Column(name = "NOM_EQUIPO", nullable = false, length = 80)
    private String nomEquipo;

    @Column(name = "NUM_SERIE", nullable = false, length = 10)
    private Integer numSerie;

    @Column(name = "FEC_ADQUISICION", nullable = false)
    @JsonbDateFormat("dd/MM/yyyy HH:mm:ss")
    private LocalDate fecAdquisicion;

    @Lob
    @Column(name = "IMAGEN")
    private Blob imagen;

    @Column(name = "GARANTIA", nullable = false)
    @JsonbDateFormat("dd/MM/yyyy HH:mm:ss")
    private LocalDate garantia;

    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO", nullable = false, length = 10)
    private EstadoGeneral estado;

    //-------------------------------RELACIONES------------------------------------------
    @ManyToOne
   // @JsonbTransient
    @JoinColumn(name = "ID_PAIS", nullable = false)
    private Pais pais;

    @ManyToOne
    @JsonbTransient
    @JoinColumn(name = "ID_TIPO_EQUIPO", nullable = false)
    private TipoEquipo tipoEquipo;

    @ManyToOne
    @JsonbTransient
    @JoinColumn(name = "ID_PROVEEDOR", nullable = false)
    private Proveedor proveedor;

    @ManyToOne
    @JsonbTransient
    @JoinColumn(name = "ID_MODELO", nullable = false)
    private Modelo modelo;

    @OneToMany( mappedBy = "equipo")
    @JsonbTransient
    private List<Intervencion> intervenciones;

    @OneToMany( mappedBy = "equipoMovEquipo")
    @JsonbTransient
    private List<MovEquipo> movEquipos;

    @OneToMany(mappedBy = "usuarioEquipoPK.equipo")
    @JsonbTransient
    private List<BajaUsuarioEquipo> bajaUsuarioEquipos;

    //-------------------------------CONSTRUCTORES------------------------------------------
    public Equipo() {
    }

    public Equipo(
            Integer idInterna,
            String nomEquipo,
            Integer numSerie,
            LocalDate fecAdquisicion,
            Blob imagen,
            LocalDate garantia,
            EstadoGeneral estado,
            Pais pais,
            TipoEquipo tipoEquipo,
            Proveedor proveedor,
            Modelo modelo,
            List<Intervencion> intervenciones,
            List<MovEquipo> movEquipos,
            List<BajaUsuarioEquipo> bajaUsuarioEquipos)
    {
        this.idInterna = idInterna;
        this.nomEquipo = nomEquipo;
        this.numSerie = numSerie;
        this.fecAdquisicion = fecAdquisicion;
        this.imagen = imagen;
        this.garantia = garantia;
        this.estado = estado;
        this.pais = pais;
        this.tipoEquipo = tipoEquipo;
        this.proveedor = proveedor;
        this.modelo = modelo;
        this.intervenciones = intervenciones;
        this.movEquipos = movEquipos;
        this.bajaUsuarioEquipos = bajaUsuarioEquipos;
    }

    //-------------------------------GETTERS AND SETTERS------------------------------------------
    public Integer getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Integer idEquipo) {
        this.idEquipo = idEquipo;
    }

    public Integer getIdInterna() {
        return idInterna;
    }

    public void setIdInterna(Integer idInterna) {
        this.idInterna = idInterna;
    }

    public String getNomEquipo() {
        return nomEquipo;
    }

    public void setNomEquipo(String nomEquipo) {
        this.nomEquipo = nomEquipo;
    }

    public Integer getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(Integer numSerie) {
        this.numSerie = numSerie;
    }

    public LocalDate getFecAdquisicion() {
        return fecAdquisicion;
    }

    public void setFecAdquisicion(LocalDate fecAdquisicion) {
        this.fecAdquisicion = fecAdquisicion;
    }

    public Blob getImagen() {
        return imagen;
    }

    public void setImagen(Blob imagen) {
        this.imagen = imagen;
    }

    public LocalDate getGarantia() {
        return garantia;
    }

    public void setGarantia(LocalDate garantia) {
        this.garantia = garantia;
    }

    public EstadoGeneral getEstado() {
        return estado;
    }

    public void setEstado(EstadoGeneral estado) {
        this.estado = estado;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public TipoEquipo getTipoEquipo() {
        return tipoEquipo;
    }

    public void setTipoEquipo(TipoEquipo tipoEquipo) {
        this.tipoEquipo = tipoEquipo;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public List<Intervencion> getIntervenciones() {
        return intervenciones;
    }

    public void setIntervenciones(List<Intervencion> intervenciones) {
        this.intervenciones = intervenciones;
    }

    public List<MovEquipo> getMovEquipos() {
        return movEquipos;
    }

    public void setMovEquipos(List<MovEquipo> movEquipos) {
        this.movEquipos = movEquipos;
    }

    public List<BajaUsuarioEquipo> getBajaUsuarioEquipos() {
        return bajaUsuarioEquipos;
    }

    public void setBajaUsuarioEquipos(List<BajaUsuarioEquipo> bajaUsuarioEquipos) {
        this.bajaUsuarioEquipos = bajaUsuarioEquipos;
    }
}