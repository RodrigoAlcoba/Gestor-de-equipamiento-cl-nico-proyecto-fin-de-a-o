package uy.edu.utec.servers.entidades;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import uy.edu.utec.servers.enums.EstadoGeneral;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MARCAS")
public class Marca implements Serializable {
    //-------------------------------ID-SECUENCIAS------------------------------------------
    @Id
    @SequenceGenerator(name = "MARCAS_ID_SEQ", sequenceName = "MARCAS_ID_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MARCAS_ID_SEQ")
    @Column(name = "ID_Marca")
    private Integer idMarca;

    //-------------------------------COLUMNAS------------------------------------------
    @Column(name = "NOM_MARCA", length = 50, nullable = false, unique = true)
    private String nombreMarca;

    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO", nullable = false)
    private EstadoGeneral estado;

    //-------------------------------RELACIONES------------------------------------------
    @OneToMany(mappedBy = "marca", fetch = FetchType.EAGER)
    @JsonbTransient
    private List<Modelo> modelos = new ArrayList<>();

    //-------------------------------CONSTRUCTORES------------------------------------------

    public Marca(){};

    public Marca(Integer idMarca, String nombreMarca, EstadoGeneral estado, List<Modelo> modelos) {
        this.idMarca = idMarca;
        this.nombreMarca = nombreMarca;
        this.estado = estado;
        this.modelos = modelos;
    }

    //-------------------------------GETTERS AND SETTERS------------------------------------------

    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    public EstadoGeneral getEstado() {
        return estado;
    }

    public void setEstado(EstadoGeneral estado) {
        this.estado = estado;
    }

    public List<Modelo> getModelos() {
        return modelos;
    }

    public void setModelos(List<Modelo> modelos) {
        this.modelos = modelos;
    }

}