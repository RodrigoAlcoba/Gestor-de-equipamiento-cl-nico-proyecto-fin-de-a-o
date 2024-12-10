package uy.edu.utec.servers.beans.remote.interfaces;

import jakarta.ejb.Remote;
import uy.edu.utec.servers.dtos.MarcaDTO;
import uy.edu.utec.servers.entidades.Marca;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;

@Remote
public interface MarcaRemote {

    void agregarMarca(MarcaDTO marca) throws ServiciosException;

    void eliminarMarca(Integer id) throws ServiciosException;

    void activarMarca(Integer id) throws ServiciosException;

    List<MarcaDTO> obtenerTodasLasMarcas();

    List<MarcaDTO> obtenerTodasLasMarcasActivas();

    List<MarcaDTO> obtenerTodasLasMarcasEliminadas();

    List<MarcaDTO> obtenerTodasLasMarcasPorNombre(String inputNombre);

    MarcaDTO buscarPorId(Integer id);
    MarcaDTO obtenerMarcaPorNombre(String nombre);
}

