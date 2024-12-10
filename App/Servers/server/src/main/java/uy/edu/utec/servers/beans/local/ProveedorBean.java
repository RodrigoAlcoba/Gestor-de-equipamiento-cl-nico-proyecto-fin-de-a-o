package uy.edu.utec.servers.beans.local;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import uy.edu.utec.servers.beans.local.interfaces.IProveedorBean;
import uy.edu.utec.servers.dao.ProveedorDAO;
import uy.edu.utec.servers.dao.TelefonoProveedorDAO;
import uy.edu.utec.servers.dtos.ProveedorDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearProveedorDTO;
import uy.edu.utec.servers.entidades.PK_Compuestas.TelefonoProveedorPK;
import uy.edu.utec.servers.entidades.Proveedor;
import uy.edu.utec.servers.entidades.TelefonoProveedor;
import uy.edu.utec.servers.enums.EstadoGeneral;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.servers.mappers.ProveedorMapper;

import java.util.List;

@Stateless
public class ProveedorBean implements IProveedorBean {
    @EJB
    private ProveedorDAO proveedorDAO;
    @EJB
    private TelefonoProveedorDAO telefonoProveedorDAO;

    @Override
    public void agregarProveedor(CrearProveedorDTO newProveedor) throws ServiciosException {

        Proveedor proveedor = ProveedorMapper.toEntity(newProveedor);
        proveedor.setEstado(EstadoGeneral.ACTIVO);

        //Se persiste el nuevo proveedor
        proveedorDAO.agregarProveedor(proveedor);

        //Se crean las entidades telefono para proveedor
        for (String telefono : newProveedor.getTelefonos()) {
            Proveedor newProveedorEntity = proveedorDAO.buscarPorNombre(newProveedor.getNomProveedor());
            TelefonoProveedor telefonoProveedor = new TelefonoProveedor();
            TelefonoProveedorPK telefonoProveedorPK = new TelefonoProveedorPK();
            telefonoProveedorPK.setProveedor(newProveedorEntity);
            telefonoProveedorPK.setTelefono(telefono);
            telefonoProveedor.setPK_TEL_PROVE(telefonoProveedorPK);
            telefonoProveedorDAO.agregarTelefono(telefonoProveedor);
        }
    }

    @Override
    public void bajaProveedor(Integer id) throws ServiciosException {
        Proveedor proveedor = proveedorDAO.buscarPorId(id);
        proveedor.setEstado(EstadoGeneral.ELIMINADO);
    }

    @Override
    public void activarProveedor(Integer id) throws ServiciosException {
        Proveedor proveedor = proveedorDAO.buscarPorId(id);
        proveedor.setEstado(EstadoGeneral.ACTIVO);
    }

    @Override
    public ProveedorDTO buscarPorId(Integer id) {
        return ProveedorMapper.toDTO(proveedorDAO.buscarPorId(id));
    }

    @Override
    public ProveedorDTO buscarPorNombre(String nombre) {
        return ProveedorMapper.toDTO(proveedorDAO.buscarPorNombre(nombre));
    }

    @Override
    public List<ProveedorDTO> obtenerTodosLosProveedores() {
        return ProveedorMapper.toDTOList(proveedorDAO.obtenerTodosLosProveedores());
    }

    @Override
    public List<ProveedorDTO> obtenerTodosLosProveedoresActivos() {
        return ProveedorMapper.toDTOList(proveedorDAO.obtenerTodosLosProveedoresActivos());
    }

    @Override
    public List<ProveedorDTO> obtenerTodosLosProveedoresEliminados() {
        return ProveedorMapper.toDTOList(proveedorDAO.obtenerTodosLosProveedoresEliminados());
    }
}
