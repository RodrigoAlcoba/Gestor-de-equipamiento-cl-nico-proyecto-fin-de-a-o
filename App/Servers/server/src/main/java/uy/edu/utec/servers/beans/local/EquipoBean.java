package uy.edu.utec.servers.beans.local;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import org.glassfish.jaxb.core.v2.model.core.ID;
import uy.edu.utec.servers.beans.local.interfaces.IEquipoBean;
import uy.edu.utec.servers.beans.local.interfaces.IMovEquipoBean;
import uy.edu.utec.servers.dao.*;
import uy.edu.utec.servers.dtos.EquipoDTO;
import uy.edu.utec.servers.dtos.MovEquipoDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearEquipoDTO;
import uy.edu.utec.servers.entidades.Equipo;
import uy.edu.utec.servers.entidades.MovEquipo;
import uy.edu.utec.servers.enums.EstadoGeneral;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.servers.mappers.EquipoMapper;
import uy.edu.utec.servers.mappers.MovEquipoMapper;
import uy.edu.utec.servers.utils.Base64Utils;
import uy.edu.utec.servers.utils.DateMapper;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Stateless
public class EquipoBean implements IEquipoBean {
    @EJB
    EquipoDAO equipoDAO;
    @EJB
    PaisDAO paisDAO;
    @EJB
    ProveedorDAO proveedorDAO;
    @EJB
    ModeloDAO modeloDAO;
    @EJB
    MarcaDAO marcaDAO;
    @EJB
    TipoEquipoDAO tipoEquipoDAO;
    @EJB
    MovEquipoDAO movEquipoDAO;

    @EJB
    IMovEquipoBean movEquipoBean;

    @Override
    public void agregarEquipo(CrearEquipoDTO newEquipo) throws ServiciosException, SQLException {
        Equipo equipo = EquipoMapper.toEntity(newEquipo);

        equipo.setPais(paisDAO.buscarPorId(newEquipo.getPaisId()));

        equipo.setTipoEquipo(tipoEquipoDAO.buscarPorId(newEquipo.getTipoEquipoId()));
        equipo.setProveedor(proveedorDAO.buscarPorId(newEquipo.getProveedorId()));
        equipo.setModelo(modeloDAO.buscarPorId(newEquipo.getModeloId()));
        //equipo.setMarca(marcaDAO.buscarPorId(newEquipo.getMarcaId()));
        //equipo.setImagen(Base64Utils.base64ToBlob(newEquipo.getImagen()));
        equipo.setEstado(EstadoGeneral.ACTIVO);

        equipoDAO.agregarEquipo(equipo);

        /*equipo = equipoDAO.buscarEquipoPoridInternaI(newEquipo.getIdInterna());

        newEquipo.getCrearMovEquipoDTO().setEquipoId(equipo.getIdEquipo());
        movEquipoBean.crearMovEquipo(newEquipo.getCrearMovEquipoDTO());*/
    }

    @Override
    public void actualizarEquipo(EquipoDTO equipo) throws ServiciosException, SQLException {
        Equipo updateEquipo = equipoDAO.buscarPorId(equipo.getIdEquipo());
        System.out.println(equipo.getIdEquipo());
        updateEquipo.setNomEquipo(equipo.getNomEquipo());
        //updateEquipo.setTipoEquipo(tipoEquipoDAO.buscarPorId(equipo.getTipoEquipoId()));
        updateEquipo.setModelo(modeloDAO.buscarPorId(equipo.getModeloId()));
        updateEquipo.setNumSerie(equipo.getNumSerie());
        updateEquipo.setGarantia(equipo.getGarantia());
        updateEquipo.setPais(paisDAO.buscarPorId(equipo.getPaisId()));
        updateEquipo.setProveedor(proveedorDAO.buscarPorId(equipo.getProveedorId()));
        updateEquipo.setFecAdquisicion(equipo.getFecAdquisicion());
        updateEquipo.setIdInterna(equipo.getIdInterna());
        updateEquipo.getModelo().getMarca().setIdMarca(equipo.getMarcaId());
        updateEquipo.setTipoEquipo(tipoEquipoDAO.buscarPorId(equipo.getTipoEquipoId()));
        //updateEquipo.setTipoEquipo(tipoEquipoDAO.buscarPorId(1));
        //System.out.println("ID TIPO EQUIPO: " + updateEquipo.getTipoEquipo().getNombre());
        //ubicación aparte?
        updateEquipo.setImagen(Base64Utils.base64ToBlob(equipo.getImagen()));
        updateEquipo.setIdEquipo(equipo.getIdEquipo());
        equipoDAO.actualizarEquipo(updateEquipo);
    }

    @Override
    public EquipoDTO buscarPorId(Integer id) throws SQLException {
        Equipo equipo = equipoDAO.buscarPorId(id);
        EquipoDTO equipoDTO = EquipoMapper.toDTO(equipo);
        equipoDTO.setPaisId(equipo.getPais().getIdPais());
        equipoDTO.setModeloId(equipo.getModelo().getIdModelo());
        equipoDTO.setProveedorId(equipo.getProveedor().getIdProveedor());
        equipoDTO.setTipoEquipoId(equipo.getTipoEquipo().getIdTipoEquipo());
        equipoDTO.setMarcaId(equipo.getModelo().getMarca().getIdMarca());
       /* MovEquipo movEquipo = movEquipoDAO.buscarUbicacionActualByEquipoId(id);

        MovEquipoDTO movEquipoDTO;
        if (movEquipo != null) {
            movEquipoDTO = MovEquipoMapper.toDTO(movEquipo);
            equipoDTO.setUbicacionActual(movEquipoDTO);
        }*/

        return equipoDTO;

    }

    @Override
    public List<EquipoDTO> obtenerTodosLosEquipos() throws SQLException {
        List<EquipoDTO> equipoDTOs = EquipoMapper.toDTO(equipoDAO.obtenerTodosLosEquipos());
        /*for (EquipoDTO equipoDTO: equipoDTOs) {
            MovEquipo movEquipo = movEquipoDAO.buscarUbicacionActualByEquipoId(equipoDTO.getIdEquipo());

            MovEquipoDTO movEquipoDTO;
            if (movEquipo != null) {
                movEquipoDTO = MovEquipoMapper.toDTO(movEquipo);
                equipoDTO.setUbicacionActual(movEquipoDTO);
            }
        }*/
        return equipoDTOs;
    }

    @Override
    public void bajaEquipo(Integer id) throws ServiciosException, SQLException {
        Equipo equipo = equipoDAO.buscarPorId(id);
        equipo.setEstado(EstadoGeneral.ELIMINADO);
        equipoDAO.actualizarEquipo(equipo);
    }

    @Override
    public void activarEquipo(Integer id) throws ServiciosException {
        Equipo equipo = equipoDAO.buscarPorId(id);
        equipo.setEstado(EstadoGeneral.ACTIVO);
        equipoDAO.actualizarEquipo(equipo);
    }

    @Override
    public EquipoDTO buscarPorNombre(String nombre) throws SQLException {
        return EquipoMapper.toDTO(equipoDAO.buscarEquipoPorNombre(nombre));
    }


        public EquipoDTO buscarEquipoPoridInterna (Integer idInterna) throws SQLException {
            return EquipoMapper.toDTO(equipoDAO.buscarEquipoPoridInternaI(idInterna));
        }

    @Override
    public List<EquipoDTO> buscarTodosLosEquiposPorNombre(String inputNombre) throws SQLException {
        return EquipoMapper.toDTO(equipoDAO.buscarTodosLosEquiposPorNombre(inputNombre));
    }

    @Override
    public List<EquipoDTO> buscarTodosLosEquiposActivosSinValidar() throws SQLException {
        return EquipoMapper.toDTO(equipoDAO.obtenerTodosLosEquiposActivosSinValidar());
    }

    @Override
    public List<EquipoDTO> obtenerEquiposPorTipoEquipo(Integer idTipoEquipo) throws SQLException {
        return EquipoMapper.toDTO(equipoDAO.obtenerEquiposPorTipoEquipo(idTipoEquipo));
    }

    @Override
    public List<EquipoDTO> obtenerEquiposPorMarca(Integer idMarca) throws SQLException {
        return EquipoMapper.toDTO(equipoDAO.obtenerEquiposPorMarca(idMarca));
    }

    @Override
    public List<EquipoDTO> obtenerEquiposPorModelo(Integer idModelo) throws SQLException {
        return EquipoMapper.toDTO(equipoDAO.obtenerEquiposPorModelo(idModelo));
    }

    @Override
    public List<EquipoDTO> obtenerEquiposPorNumeroSerie(Integer numSerie) throws SQLException {
        return EquipoMapper.toDTO(equipoDAO.obtenerEquiposPorNumeroSerie(numSerie));
    }

    @Override
    public List<EquipoDTO> obtenerEquiposPorPais(Integer idPais) throws SQLException {
        return EquipoMapper.toDTO(equipoDAO.obtenerEquiposPorPais(idPais));
    }

    @Override
    public List<EquipoDTO> obtenerEquiposPorProveedor(Integer idProveedor) throws SQLException {
        return EquipoMapper.toDTO(equipoDAO.obtenerEquiposPorProveedor(idProveedor));
    }

    @Override
    public List<EquipoDTO> obtenerEquiposPorFecAdquisicion(Date fecAdquisicion) throws SQLException {
        return EquipoMapper.toDTO(equipoDAO.obtenerEquiposPorFecAdquisicion(DateMapper.convertDateToLocalDate(fecAdquisicion)));
    }

    @Override
    public List<EquipoDTO> obtenerEquiposPorUbicacion(Integer idUbicacion) throws SQLException {
        return EquipoMapper.toDTO(equipoDAO.obtenerEquiposPorUbicacion(idUbicacion));
    }

    @Override
    public List<EquipoDTO> buscarEquiposPoridInterna(Integer inputIdInterna) throws SQLException {
        return EquipoMapper.toDTO(equipoDAO.buscarEquiposPoridInterna(inputIdInterna));
    }
};
