package uy.edu.utec.servers.beans.local.interfaces;

import jakarta.ejb.LocalBean;
import uy.edu.utec.servers.dtos.MovEquipoDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearMovEquipoDTO;
import uy.edu.utec.servers.entidades.MovEquipo;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@LocalBean
public interface IMovEquipoBean {
    void crearMovEquipo(CrearMovEquipoDTO movEquipo) throws ServiciosException;
    void actualizarMovEquipo(MovEquipoDTO movEquipo) throws ServiciosException;
    MovEquipoDTO buscarPorId(Integer id);
    List<MovEquipoDTO> obtenerTodosMovDeEquipo();
    MovEquipoDTO buscarUbicacionActualByEquipoId(Integer equipoId);
    List<MovEquipoDTO> obtenerTodosMovDeEquipoByEuipoId(Integer equipoId);

    List<MovEquipoDTO> obtenerTodosMovDeEquipoPorInstitucion(Integer InstitucionId);

    List<MovEquipoDTO> obtenerTodosMovDeEquipoPorSector(Integer idSector);

    List<MovEquipoDTO> obtenerTodosMovDeEquipoPorUsuario(Integer idUsuario);

    List<MovEquipoDTO> obtenerTodosMovDeEquipoPorFecDesde(LocalDate fecEntrada);

    List<MovEquipoDTO> obtenerTodosMovDeEquipoPorFechaHasta(LocalDate fecHasta);
}
