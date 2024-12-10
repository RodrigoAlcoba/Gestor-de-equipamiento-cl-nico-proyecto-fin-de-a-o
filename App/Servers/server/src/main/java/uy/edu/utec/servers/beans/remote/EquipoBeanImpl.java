package uy.edu.utec.servers.beans.remote;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import uy.edu.utec.servers.beans.local.interfaces.IEquipoBean;
import uy.edu.utec.servers.beans.remote.interfaces.EquipoRemote;
import uy.edu.utec.servers.dtos.EquipoDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearEquipoDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Stateless
public class EquipoBeanImpl implements EquipoRemote {
    @Inject
    private Validator validator;

    @EJB
    private IEquipoBean equipoBean;


    @Override
    public void agregarEquipo(CrearEquipoDTO equipo) throws ServiciosException, SQLException {
        Set<ConstraintViolation<CrearEquipoDTO>> violations = validator.validate(equipo);
        String errores = "";
        for(ConstraintViolation<CrearEquipoDTO> violation : violations){
            System.out.println(violation.getMessage());
            errores+=violation.getMessage() + "\n";
        }
        if(!errores.isEmpty())
            throw new ServiciosException(errores);
        equipoBean.agregarEquipo(equipo);
    }

    @Override
    public void actualizarEquipo(EquipoDTO equipo) throws ServiciosException, SQLException {
        Set<ConstraintViolation<EquipoDTO>> violations = validator.validate(equipo);
        String errores = "";
        for(ConstraintViolation<EquipoDTO> violation : violations){
            System.out.println(violation.getMessage());
            errores+=violation.getMessage() + "\n";
        }
        if(!errores.isEmpty())
            throw new ServiciosException(errores);
        equipoBean.actualizarEquipo(equipo);
    }

    @Override
    public EquipoDTO buscarPorId(Integer id) throws SQLException {
        return equipoBean.buscarPorId(id);
    }

    @Override
    public List<EquipoDTO> obtenerTodosLosEquipos() throws SQLException {
        return equipoBean.obtenerTodosLosEquipos();
    }

    @Override
    public void bajaEquipo(Integer id) throws ServiciosException, SQLException {
        equipoBean.bajaEquipo(id);
    }

    @Override
    public void activarEquipo(Integer id) throws ServiciosException {
        equipoBean.activarEquipo(id);
    }

    @Override

    public EquipoDTO obtenerPorNombre(String nombre) throws SQLException {
        return equipoBean.buscarPorNombre(nombre);
    }

    @Override
    public List<EquipoDTO> buscarTodosLosEquiposPorNombre(String inputNombre) throws SQLException {
        return equipoBean.buscarTodosLosEquiposPorNombre(inputNombre);
    }

    @Override
    public EquipoDTO buscarEquipoPoridInterna(Integer idInterna) throws SQLException {
        return equipoBean.buscarEquipoPoridInterna(idInterna);
    }

    @Override
    public List<EquipoDTO> obtenerEquiposPorTipoEquipo(Integer idTipoEquipo) throws SQLException{
        return equipoBean.obtenerEquiposPorTipoEquipo(idTipoEquipo);
    }

    @Override
    public List<EquipoDTO> obtenerEquiposPorMarca(Integer idMarca) throws SQLException {
        return equipoBean.obtenerEquiposPorMarca(idMarca);
    }

    @Override
    public List<EquipoDTO> obtenerEquiposPorModelo(Integer idModelo) throws SQLException {
        return equipoBean.obtenerEquiposPorModelo(idModelo);
    }

    @Override
    public List<EquipoDTO> obtenerEquiposPorNumeroSerie(Integer numSerie) throws SQLException {
         return equipoBean.obtenerEquiposPorNumeroSerie(numSerie);
    }

    @Override
    public List<EquipoDTO> obtenerEquiposPorPais(Integer idPais) throws SQLException {
        return equipoBean.obtenerEquiposPorPais(idPais);
    }

    @Override
    public List<EquipoDTO> obtenerEquiposPorProveedor(Integer idProveedor) throws SQLException {
        return equipoBean.obtenerEquiposPorProveedor(idProveedor);
    }

    @Override
    public List<EquipoDTO> obtenerEquiposPorFecAdquisicion(Date fecAdquisicion) throws SQLException {
        return equipoBean.obtenerEquiposPorFecAdquisicion(fecAdquisicion);
    }

    @Override
    public List<EquipoDTO> obtenerEquiposPorUbicacion(Integer idUbicacion) throws SQLException {
        return equipoBean.obtenerEquiposPorUbicacion(idUbicacion);
    }

    @Override
    public List<EquipoDTO> buscarEquiposPoridInterna(Integer inputIdInterna) throws SQLException {
        return equipoBean.buscarEquiposPoridInterna(inputIdInterna);
    }
}