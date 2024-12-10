package uy.edu.utec.servers.entidades;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "UBICACIONES")
public class Ubicacion implements Serializable {
    //-------------------------------ID-SECUENCIA------------------------------------------
    @Id
    @SequenceGenerator(name = "UBICACIONES_ID_SEQ", sequenceName = "UBICACIONES_ID_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UBICACIONES_ID_SEQ")
    @Column(name = "ID_UBICACION")
    private Integer idUbicacion;

    //-------------------------------COLUMNAS------------------------------------------
    @Column(name = "NOM_UBICACION", length = 80, nullable = false)
    private String nombre;

    @Column(name = "NUMERO", nullable = false)
    private Integer numero;

    @Column(name = "PISO", length = 50, nullable = false)
    private String piso;

    @Column(name = "CAMA")
    private Integer cama;

    //-------------------------------RELACIONES------------------------------------------
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_INSTITUCION", nullable = false)
    private Institucion institucion;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SECTOR", nullable = false)
    private Sector sector;

    //-------------------------------CONSTRUCTORES------------------------------------------

    public Ubicacion() {
    }

    public Ubicacion(String nombre, Integer numero, String piso, Integer cama, Institucion institucion, Sector sector) {
        this.nombre = nombre;
        this.numero = numero;
        this.piso = piso;
        this.cama = cama;
        this.institucion = institucion;
        this.sector = sector;
    }

    //-------------------------------GETTERS AND SETTERS------------------------------------------


    public Integer getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(Integer idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public Integer getCama() {
        return cama;
    }

    public void setCama(Integer cama) {
        this.cama = cama;
    }

    public Institucion getInstitucion() {
        return institucion;
    }

    public void setInstitucion(Institucion institucion) {
        this.institucion = institucion;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }


}