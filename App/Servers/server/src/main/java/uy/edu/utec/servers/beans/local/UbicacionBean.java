package uy.edu.utec.servers.beans.local;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import uy.edu.utec.servers.beans.local.interfaces.IUbicacionBean;
import uy.edu.utec.servers.dao.InstitucionDAO;
import uy.edu.utec.servers.dao.SectorDAO;
import uy.edu.utec.servers.dao.UbicacionDAO;
import uy.edu.utec.servers.dtos.UbicacionDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearUbicacionDTO;
import uy.edu.utec.servers.entidades.Sector;
import uy.edu.utec.servers.entidades.Ubicacion;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.servers.mappers.UbicacionMapper;

import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class UbicacionBean implements IUbicacionBean {
    @EJB
    private UbicacionDAO ubicacionDAO;
    @EJB
    private SectorDAO sectorDAO;
    @EJB
    private InstitucionDAO institucionDAO;
    @Override
    public void agregarUbicacion(CrearUbicacionDTO newUbicacion) throws ServiciosException {
        Ubicacion ubicacion = UbicacionMapper.toEntity(newUbicacion);
        ubicacion.setInstitucion(institucionDAO.buscarPorId(newUbicacion.getInstitucionId()));
        ubicacion.setSector(sectorDAO.buscarPorId(newUbicacion.getSectorId()));
        ubicacion.setIdUbicacion(ubicacion.getIdUbicacion());
        ubicacionDAO.agregarUbicacion(ubicacion);
    }
    @Override
    public UbicacionDTO buscarPorId(Integer id) {
        return UbicacionMapper.toDTO(ubicacionDAO.buscarPorId(id));
    }

    @Override
    public List<UbicacionDTO> obtenerTodasLasUbicaciones() {
        return ubicacionDAO.obtenerTodasLasUbicaciones()
                .stream()
                .map(UbicacionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void actualizarUbicacion(UbicacionDTO ubicacionDTO) throws ServiciosException {
        Ubicacion ubicacion = ubicacionDAO.buscarPorId(ubicacionDTO.getIdUbicacion());

        //Actualizables
        Sector sector = sectorDAO.buscarPorId(ubicacionDTO.getSectorId());
        ubicacion.setNumero(ubicacionDTO.getNumero());
        ubicacion.setNombre(ubicacionDTO.getNombre());
        ubicacion.setPiso(ubicacionDTO.getPiso());
        ubicacion.setCama(ubicacionDTO.getCama());
        ubicacion.setSector(sector);

        ubicacionDAO.actualizarUbicacion(ubicacion);

    }

    public void bajarUbicacion(Integer id) throws ServiciosException {
        ubicacionDAO.eliminarUbicacion(id);
    }

    @Override
    public UbicacionDTO buscarUbicacionPorNombre(String nombre) {
        return UbicacionMapper.toDTO(ubicacionDAO.buscarUbicacionPorNombre(nombre));
    }

    @Override
    public List<UbicacionDTO> obtenerTodasLasUbicacionesActivas() {
        return UbicacionMapper.toDTO(ubicacionDAO.obtenerTodasLasUbicacionesActivas());
    }

}
