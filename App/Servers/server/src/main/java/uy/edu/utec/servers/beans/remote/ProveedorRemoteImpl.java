package uy.edu.utec.servers.beans.remote;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import uy.edu.utec.servers.beans.local.interfaces.IProveedorBean;
import uy.edu.utec.servers.beans.remote.interfaces.ProveedorRemote;
import uy.edu.utec.servers.dtos.ProveedorDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearProveedorDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;

@Stateless
public class ProveedorRemoteImpl implements ProveedorRemote {
    @EJB
    private IProveedorBean proveedorBean;

    @Override
    public void agregarProveedor(CrearProveedorDTO proveedor) throws ServiciosException {
        proveedorBean.agregarProveedor(proveedor);
    }

    @Override
    public void bajaProveedor(Integer id) throws ServiciosException {
        proveedorBean.bajaProveedor(id);
    }

    @Override
    public void activarProveedor(Integer id) throws ServiciosException {
        proveedorBean.activarProveedor(id);
    }

    @Override
    public ProveedorDTO buscarPorId(Integer id) {
        return proveedorBean.buscarPorId(id);
    }

    @Override
    public ProveedorDTO buscarPorNombre(String nombre) {
        return proveedorBean.buscarPorNombre(nombre);
    }

    @Override
    public List<ProveedorDTO> obtenerTodosLosProveedores() {
        return proveedorBean.obtenerTodosLosProveedores();
    }

    @Override
    public List<ProveedorDTO> obtenerTodosLosProveedoresActivos() {
        return proveedorBean.obtenerTodosLosProveedoresActivos();
    }

    @Override
    public List<ProveedorDTO> obtenerTodosLosProveedoresEliminados() {
        return proveedorBean.obtenerTodosLosProveedoresEliminados();
    }
}
