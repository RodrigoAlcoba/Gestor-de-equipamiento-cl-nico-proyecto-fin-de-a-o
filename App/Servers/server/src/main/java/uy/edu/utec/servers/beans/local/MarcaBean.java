package uy.edu.utec.servers.beans.local;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import uy.edu.utec.servers.dao.MarcaDAO;
import uy.edu.utec.servers.dtos.MarcaDTO;
import uy.edu.utec.servers.entidades.Marca;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.servers.beans.local.interfaces.IMarcaBean;
import uy.edu.utec.servers.mappers.MarcaMapper;

import java.util.List;

@Stateless
public class MarcaBean implements IMarcaBean {
    @EJB
    private MarcaDAO marcaDAO;


    @Override
    public void agregarMarca(MarcaDTO marca) throws ServiciosException {
        marcaDAO.agregarMarca(MarcaMapper.toMarca(marca));
    }

    @Override
    public void eliminarMarca(Integer id) throws ServiciosException {
        marcaDAO.eliminarMarca(id);
    }

    @Override
    public void activarMarca(Integer id) throws ServiciosException {
        marcaDAO.eliminarMarca(id);
    }

    @Override
    public List<MarcaDTO> obtenerTodasLasMarcas() {
        return MarcaMapper.toDTOList(marcaDAO.obtenerTodasLasMarcas());
    }

    @Override
    public List<MarcaDTO> obtenerTodasLasMarcasActivas() {
        return MarcaMapper.toDTOList(marcaDAO.obtenerTodasLasMarcasActivas());
    }

    @Override
    public List<MarcaDTO> obtenerTodasLasMarcasEliminadas() {
        return MarcaMapper.toDTOList(marcaDAO.obtenerTodasLasMarcasEliminadas());
    }

    @Override
    public List<MarcaDTO> obtenerTodasLasMarcasPorNombre(String inputNombre) {
        return MarcaMapper.toDTOList(marcaDAO.obtenerTodosLasMarcasPorNombre(inputNombre));
    }

    @Override
    public MarcaDTO buscarPorId(Integer id) {
        return MarcaMapper.toDTO(marcaDAO.buscarPorId(id));
    }

    @Override
    public MarcaDTO obtenerMarcaPorNombre(String nombre) {
        return MarcaMapper.toDTO(marcaDAO.obtenerMarcaPorNombre(nombre));
    }


}
