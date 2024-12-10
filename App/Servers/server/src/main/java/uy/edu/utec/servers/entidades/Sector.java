package uy.edu.utec.servers.entidades;

import jakarta.persistence.*;
import uy.edu.utec.servers.enums.NombreSector;

import java.util.List;

@Entity
@Table(name = "Sectores")
public class Sector {
    //-------------------------------ID-SECUENCIA------------------------------------------
    @Id
    @SequenceGenerator(name = "SECTORES_ID_SEQ", sequenceName = "SECTORES_ID_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SECTORES_ID_SEQ")
    @Column(name = "ID_SECTOR")
    private Integer idSector;

    //-------------------------------COLUMNAS------------------------------------------
    @Enumerated(EnumType.STRING)
    @Column(name = "NOMBRE",nullable = false, unique = true)
    private NombreSector nombreSector;

    //-------------------------------RELACIONES------------------------------------------
    @OneToMany(mappedBy = "sector")
    private List<Ubicacion> ubicaciones;

    //-------------------------------CONSTRUCTORES------------------------------------------

    public Sector() {
    }

    public Sector(NombreSector nombreSector, List<Ubicacion> ubicaciones) {
        this.nombreSector = nombreSector;
        this.ubicaciones = ubicaciones;
    }
    //-------------------------------GETTERS AND SETTERS------------------------------------------


    public Integer getIdSector() {
        return idSector;
    }

    public void setIdSector(Integer idSector) {
        this.idSector = idSector;
    }

    public NombreSector getNombreSector() {
        return nombreSector;
    }

    public void setNombreSector(NombreSector nombreSector) {
        this.nombreSector = nombreSector;
    }

    public List<Ubicacion> getUbicaciones() {
        return ubicaciones;
    }

    public void setUbicaciones(List<Ubicacion> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }

    //-------------------------------TOSTRING------------------------------------------

    @Override
    public String toString() {
        return "Sector{" +
                "idSector=" + idSector +
                ", nombreSector=" + nombreSector +
                ", ubicaciones=" + ubicaciones +
                '}';
    }
}