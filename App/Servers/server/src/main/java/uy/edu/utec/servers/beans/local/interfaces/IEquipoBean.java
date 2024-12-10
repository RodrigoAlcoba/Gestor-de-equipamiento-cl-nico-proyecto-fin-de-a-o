package uy.edu.utec.servers.beans.local.interfaces;

import jakarta.ejb.LocalBean;
import uy.edu.utec.servers.dtos.EquipoDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearEquipoDTO;
import uy.edu.utec.servers.entidades.Equipo;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@LocalBean
public interface IEquipoBean {

    void agregarEquipo(CrearEquipoDTO equipo) throws ServiciosException, SQLException;

    void actualizarEquipo(EquipoDTO equipo) throws ServiciosException, SQLException;

    EquipoDTO buscarPorId(Integer id) throws SQLException;

    List<EquipoDTO> obtenerTodosLosEquipos() throws SQLException;

    void bajaEquipo(Integer id) throws ServiciosException, SQLException;
    void activarEquipo(Integer id) throws ServiciosException;

    EquipoDTO buscarPorNombre(String nombre) throws SQLException;

    EquipoDTO buscarEquipoPoridInterna(Integer idInterna) throws SQLException;

    List<EquipoDTO> buscarTodosLosEquiposPorNombre(String inputNombre) throws SQLException;
    List<EquipoDTO> buscarTodosLosEquiposActivosSinValidar() throws SQLException;

   List<EquipoDTO> obtenerEquiposPorTipoEquipo(Integer idTipoEquipo) throws SQLException;

    List<EquipoDTO> obtenerEquiposPorMarca(Integer idMarca) throws SQLException;

    List<EquipoDTO> obtenerEquiposPorModelo(Integer idModelo) throws SQLException;

    List<EquipoDTO> obtenerEquiposPorNumeroSerie(Integer numSerie) throws SQLException;

    List<EquipoDTO> obtenerEquiposPorPais(Integer idPais) throws SQLException;

    List<EquipoDTO> obtenerEquiposPorProveedor(Integer idProveedor) throws SQLException;

    List<EquipoDTO> obtenerEquiposPorFecAdquisicion(Date fecAdquisicion) throws SQLException;

    List<EquipoDTO> obtenerEquiposPorUbicacion(Integer idUbicacion) throws SQLException;

    List<EquipoDTO> buscarEquiposPoridInterna(Integer inputIdInterna) throws SQLException;

}