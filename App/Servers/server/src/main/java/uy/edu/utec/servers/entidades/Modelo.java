package uy.edu.utec.servers.entidades;

import jakarta.persistence.*;
import uy.edu.utec.servers.enums.EstadoGeneral;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "MODELOS")
public class Modelo implements Serializable {

    //-------------------------------ID-SECUENCIA------------------------------------------
    @Id
    @SequenceGenerator(name = "MODELOS_ID_SEQ", sequenceName = "MODELOS_ID_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MODELOS_ID_SEQ")
    @Column(name = "ID_Modelo")
    private Integer idModelo;

    //-------------------------------COLUMNAS------------------------------------------
    @Column(name = "NOM_MODELO", length = 50, nullable = false, unique = true)
    private String nombreModelo;

    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO", nullable = false)
    private EstadoGeneral estado;

    //-------------------------------RELACIONES------------------------------------------
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_Marca", nullable = false, insertable = false, updatable = false)
    private Marca marca;
    @OneToMany( mappedBy = "modelo")
    private List<Equipo> equipos;

    //-------------------------------CONSTRUCTORES------------------------------------------

    public Modelo(){}

    public Modelo(String nombreModelo, EstadoGeneral estado, Marca marca) {
        this.nombreModelo = nombreModelo;
        this.estado = estado;
        this.marca = marca;
    }

    //-------------------------------GETTERS AND SETTERS------------------------------------------
    public Integer getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Integer idModelo) {
        this.idModelo = idModelo;
    }

    public String getNombreModelo() {
        return nombreModelo;
    }

    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }

    public EstadoGeneral getEstado() {
        return estado;
    }

    public void setEstado(EstadoGeneral estado) {
        this.estado = estado;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

}