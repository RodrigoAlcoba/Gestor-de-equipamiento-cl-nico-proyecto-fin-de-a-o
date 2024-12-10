package uy.edu.utec.servers.mappers;

import uy.edu.utec.servers.dtos.EquipoDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearEquipoDTO;
import uy.edu.utec.servers.entidades.Equipo;
import uy.edu.utec.servers.utils.Base64Utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipoMapper {
    public static Equipo toEntity(CrearEquipoDTO newEquipo) throws SQLException {
        Equipo equipo = new Equipo();
        equipo.setIdInterna(newEquipo.getIdInterna());
        equipo.setNomEquipo(newEquipo.getNomEquipo());
        equipo.setNumSerie(newEquipo.getNumSerie());
        equipo.setFecAdquisicion(newEquipo.getFecAdquisicion());

        equipo.setImagen(Base64Utils.base64ToBlob(newEquipo.getImagen()));

        equipo.setGarantia(newEquipo.getGarantia());
        //SETEADOS POR FUERA DEL DTO (bean local)
        //setear el estado desde el bean
//        equipo.setPais();
//        equipo.setTipoEquipo();
//        equipo.setProveedor();
//        equipo.setModelo();
        return equipo;
    }
    public static Equipo toEntity(EquipoDTO equipoDTO) throws SQLException {
        Equipo equipo = new Equipo();
        equipo.setIdInterna(equipoDTO.getIdInterna());
        equipo.setNomEquipo(equipoDTO.getNomEquipo());
        equipo.setNumSerie(equipoDTO.getNumSerie());
        equipo.setFecAdquisicion(equipoDTO.getFecAdquisicion());

        equipo.setImagen(Base64Utils.base64ToBlob(equipoDTO.getImagen()));

        equipo.setGarantia(equipoDTO.getGarantia());
        equipo.setEstado(equipoDTO.getEstado());
        equipo.setIdEquipo(equipoDTO.getIdEquipo());
        //SETEADOS POR FUERA DEL DTO (bean local)
//        equipo.setPais();
//        equipo.setTipoEquipo();
//        equipo.setProveedor();
//        equipo.setModelo();
        return equipo;
    }
    public static EquipoDTO toDTO(Equipo equipo) throws SQLException {
        EquipoDTO equipoDTO = new EquipoDTO();
        equipoDTO.setIdEquipo(equipo.getIdEquipo());
        equipoDTO.setIdInterna(equipo.getIdInterna());
        equipoDTO.setNomEquipo(equipo.getNomEquipo());
        equipoDTO.setNumSerie(equipo.getNumSerie());
        equipoDTO.setFecAdquisicion(equipo.getFecAdquisicion());

        equipoDTO.setImagen(Base64Utils.blobToBase64(equipo.getImagen()));

        equipoDTO.setGarantia(equipo.getGarantia());
        equipoDTO.setEstado(equipo.getEstado());
        equipoDTO.setIdEquipo(equipo.getIdEquipo());

        equipoDTO.setPaisId(equipo.getPais().getIdPais());
        equipoDTO.setTipoEquipoId(equipo.getTipoEquipo().getIdTipoEquipo());
        System.out.println(equipo.getTipoEquipo().getIdTipoEquipo());
        equipoDTO.setProveedorId(equipo.getProveedor().getIdProveedor());
        equipoDTO.setModeloId(equipo.getModelo().getIdModelo());
        return equipoDTO;
    }
    public static List<EquipoDTO> toDTO(List<Equipo> equipos) throws SQLException {
        List<EquipoDTO> equipoDTOList = new ArrayList<>();
        for (Equipo equipo : equipos) {
            equipoDTOList.add(toDTO(equipo));
        }
        return equipoDTOList;
    }
}
