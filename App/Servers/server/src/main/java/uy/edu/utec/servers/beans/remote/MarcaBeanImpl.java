package uy.edu.utec.servers.beans.remote;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import uy.edu.utec.servers.beans.remote.interfaces.MarcaRemote;
import uy.edu.utec.servers.dtos.MarcaDTO;
import uy.edu.utec.servers.entidades.Marca;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.servers.beans.local.interfaces.IMarcaBean;

import java.util.List;

@Stateless
public class MarcaBeanImpl implements MarcaRemote {
    @EJB
    private IMarcaBean iMarcaBean;

    // CREAR
    @Override
    public void agregarMarca(MarcaDTO marca) throws ServiciosException {
        iMarcaBean.agregarMarca(marca);
    }

    //ELIMINAR
    @Override
    public void eliminarMarca(Integer id) throws ServiciosException{
        iMarcaBean.eliminarMarca(id);
    }

    // MODIFICAR
    @Override
    public void activarMarca(Integer id) throws ServiciosException {
        iMarcaBean.activarMarca(id);
    }

    // LISTADOS (FILTROS)
    @Override
    public List<MarcaDTO> obtenerTodasLasMarcas() {
        return iMarcaBean.obtenerTodasLasMarcas();
    }

    @Override
    public List<MarcaDTO> obtenerTodasLasMarcasActivas() {
        return iMarcaBean.obtenerTodasLasMarcasActivas();
    }

    @Override
    public List<MarcaDTO> obtenerTodasLasMarcasEliminadas() {
        return iMarcaBean.obtenerTodasLasMarcasEliminadas();
    }

    @Override
    public List<MarcaDTO> obtenerTodasLasMarcasPorNombre(String inputNombre) {
        return iMarcaBean.obtenerTodasLasMarcasPorNombre(inputNombre);
    }
    @Override
    public MarcaDTO buscarPorId(Integer id) {
        return iMarcaBean.buscarPorId(id);
    }

    @Override
    public MarcaDTO obtenerMarcaPorNombre(String nombre){
        return iMarcaBean.obtenerMarcaPorNombre(nombre);
    }

}
