package uy.edu.utec.servers.mappers;

import uy.edu.utec.servers.dtos.ProveedorDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearProveedorDTO;
import uy.edu.utec.servers.entidades.Proveedor;
import uy.edu.utec.servers.entidades.TelefonoProveedor;

import java.util.ArrayList;
import java.util.List;

public class ProveedorMapper {
    public static Proveedor toEntity(CrearProveedorDTO newProveedor) {
        Proveedor proveedor = new Proveedor();
        proveedor.setNomProveedor(newProveedor.getNomProveedor());
        proveedor.setEmail(newProveedor.getEmail());
        proveedor.setSitio(newProveedor.getSitio());
        //setear los telefonos por fuera
        return proveedor;
    }
    public static ProveedorDTO toDTO(Proveedor proveedor) {
        ProveedorDTO proveedorDTO = new ProveedorDTO();
        proveedorDTO.setIdProveedor(proveedor.getIdProveedor());
        proveedorDTO.setNomProveedor(proveedor.getNomProveedor());
        proveedorDTO.setEmail(proveedor.getEmail());
        proveedorDTO.setSitio(proveedor.getSitio());
        proveedorDTO.setTelefonos(telefonosToStingList(proveedor.getTelefonoProveedor()));
        return proveedorDTO;
    }
//    public static Proveedor toEntity(ProveedorDTO proveedorDTO) {
//        Proveedor proveedor = new Proveedor();
//
//
//        return proveedor;
//    }
    public static List<ProveedorDTO> toDTOList(List<Proveedor> proveedores) {
        List<ProveedorDTO> proveedorDTOList = new ArrayList<>();
        for (Proveedor proveedor : proveedores) {
            ProveedorDTO proveedorDTO = new ProveedorDTO();
            proveedorDTO.setIdProveedor(proveedor.getIdProveedor());
            proveedorDTO.setNomProveedor(proveedor.getNomProveedor());
            proveedorDTO.setEmail(proveedor.getEmail());
            proveedorDTO.setSitio(proveedor.getSitio());
            proveedorDTO.setTelefonos(telefonosToStingList(proveedor.getTelefonoProveedor()));
            proveedorDTOList.add(proveedorDTO);
        }
        return proveedorDTOList;
    }
    private static List<String> telefonosToStingList(List<TelefonoProveedor> telefonos) {
        List<String> telefonosList = new ArrayList<>();
        for (TelefonoProveedor telefono : telefonos) {
            telefonosList.add(telefono.getPK_TEL_PROVE().getTelefono());
        }
        return telefonosList;
    }
}
