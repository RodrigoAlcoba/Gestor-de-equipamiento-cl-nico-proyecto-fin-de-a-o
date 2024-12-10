package uy.edu.utec.servers.beans.local.interfaces;

import jakarta.ejb.LocalBean;
import uy.edu.utec.servers.dtos.ProveedorDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearProveedorDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;

@LocalBean
public interface IProveedorBean {
    void agregarProveedor(CrearProveedorDTO proveedor) throws ServiciosException;

    void bajaProveedor(Integer id) throws ServiciosException;
    void activarProveedor(Integer id) throws ServiciosException;

    ProveedorDTO buscarPorId(Integer id);
    ProveedorDTO buscarPorNombre(String nombre);

    List<ProveedorDTO> obtenerTodosLosProveedores();

    List<ProveedorDTO> obtenerTodosLosProveedoresActivos();
    List<ProveedorDTO> obtenerTodosLosProveedoresEliminados();
}
