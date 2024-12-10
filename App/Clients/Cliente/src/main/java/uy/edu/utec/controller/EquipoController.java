package uy.edu.utec.controller;

import uy.edu.utec.daos.EquipoDAOCli;
import uy.edu.utec.servers.dtos.EquipoDTO;
import uy.edu.utec.daos.EquipoDAOCli;
import uy.edu.utec.daos.MovEquipoDAOCli;
import uy.edu.utec.daos.UsuarioDAOCliNEW;
import uy.edu.utec.servers.dao.EquipoDAO;
import uy.edu.utec.servers.dtos.EquipoDTO;
import uy.edu.utec.servers.dtos.MovEquipoDTO;
import uy.edu.utec.servers.dtos.UbicacionDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearEquipoDTO;
import uy.edu.utec.servers.entidades.Equipo;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.view.Interfaces.Equipo.IngresoEquipo;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class EquipoController {

    private IngresoEquipo interfazIngresoEquipo;
    //private EquipoDAOCli equipoDAOCli = EquipoDAOCli;

    public EquipoController(ControllerGeneral controllerGeneral) throws ServiciosException {
        this.interfazIngresoEquipo = new IngresoEquipo(controllerGeneral);
    }

    public void crearEquipo(CrearEquipoDTO equipo) throws ServiciosException, SQLException { //
        EquipoDAOCli.equipoRemote.agregarEquipo(equipo);
    }

    public EquipoDTO obtenerPorNombre(String nombre) throws SQLException{
        return EquipoDAOCli.obtenerEquipoPorNombre(nombre);
    }

    public void modificarEquipo(EquipoDTO equipoDTO) throws ServiciosException, SQLException {
        EquipoDAOCli.modificarEquipo(equipoDTO);
    }

    public  EquipoDTO obtenerEquipoPorIdInterno(Integer idInterna) throws SQLException {
       return EquipoDAOCli.obtenerEquipoPorIdInteno(idInterna);
    }

    public  EquipoDTO obtenerEquipoPorId(Integer id) throws SQLException {
        return EquipoDAOCli.obtenerEquipoPorId(id);
    }

    public  List<EquipoDTO> obtenerEquiposPorNombre(String nombre) throws SQLException{
        return EquipoDAOCli.obtenerEquiposPorNombre(nombre);
    }

    public List<EquipoDTO> obtenerEquiposPorTipoDeEquipo(Integer tipoEquipo) throws SQLException{
        return EquipoDAOCli.obtenerEquiposTipoEquipo(tipoEquipo);
    }

    public List<EquipoDTO> obtenerEquiposPorMarca(Integer idMarca) throws SQLException {
        return EquipoDAOCli.obtenerEquiposPorMarca(idMarca);
    }

    public List<EquipoDTO> obtenerEquiposPorModelo(Integer idModelo) throws SQLException {
        return EquipoDAOCli.obtenerEquiposPorModelo(idModelo);
    }

    public  List<EquipoDTO> obtenerEquiposPorNumeroSerie(Integer numSerie) throws SQLException {
        return EquipoDAOCli.obtenerEquiposPorNumeroSerie(numSerie);
    }

    public  List<EquipoDTO> obtenerEquiposPorPais(Integer idPais) throws SQLException {
        return  EquipoDAOCli.obtenerEquiposPorPais(idPais);
    }

    public List<EquipoDTO> obtenerEquiposPorProveedor(Integer idProveedor) throws SQLException {
        return  EquipoDAOCli.obtenerEquiposPorProveedor(idProveedor);
    }

    public List<EquipoDTO> obtenerEquiposPorFecAdquisicion(Date fecAdquisicion) throws SQLException {
        return  EquipoDAOCli.obtenerEquiposPorFecAdquisicion(fecAdquisicion);
    }

    public List<EquipoDTO> obtenerEquiposPorUbicacion(Integer idUbicacion) throws SQLException {
        return EquipoDAOCli.obtenerEquiposPorUbicacion(idUbicacion);
    }

    public  List<EquipoDTO> buscarEquiposPoridInterna(Integer inputIdInterna) throws SQLException {
        return  EquipoDAOCli.buscarEquiposPoridInterna(inputIdInterna);
    }
    public void bajaEquipo(Integer id) throws ServiciosException, SQLException {
        EquipoDAOCli.bajarEquipo(id);
    }

    public void reactivarEquipo(Integer id) throws ServiciosException {
        EquipoDAOCli.reactivarEquipo(id);
    }

    public List<EquipoDTO> obtenerTodosLosEquipos() throws SQLException {
        return EquipoDAOCli.obtenerTodosLosEquipos();
    }
    public  EquipoDTO buscarPorId(Integer id) throws SQLException{
        return EquipoDAOCli.obtenerEquipoPorId(id);
    }
}
