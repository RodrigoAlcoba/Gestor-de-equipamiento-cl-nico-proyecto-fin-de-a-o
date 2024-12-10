package uy.edu.utec.servers.beans.remote;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import uy.edu.utec.servers.beans.local.interfaces.IMovEquipoBean;
import uy.edu.utec.servers.beans.remote.interfaces.IMovEquipoRemote;
import uy.edu.utec.servers.dtos.EquipoDTO;
import uy.edu.utec.servers.dtos.MovEquipoDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearMovEquipoDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Stateless
public class MovEquipoRemoteImpl implements IMovEquipoRemote {
    @EJB
    IMovEquipoBean bean;
    @Inject
    private Validator validator;
    @Override
    public void crearMovEquipo(CrearMovEquipoDTO movEquipo) throws ServiciosException {
        Set<ConstraintViolation<CrearMovEquipoDTO>> violations = validator.validate(movEquipo);
        String errores = "";
        for(ConstraintViolation<CrearMovEquipoDTO> violation : violations){
            System.out.println(violation.getMessage());
            errores+=violation.getMessage() + "\n";
        }
        if(!errores.isEmpty())
            throw new ServiciosException(errores);
        bean.crearMovEquipo(movEquipo);
    }

    @Override
    public void actualizarMovEquipo(MovEquipoDTO movEquipo) throws ServiciosException {
        bean.actualizarMovEquipo(movEquipo);
    }

    @Override
    public MovEquipoDTO buscarPorId(Integer id) {
        return bean.buscarPorId(id);
    }

    @Override
    public List<MovEquipoDTO> obtenerTodosMovDeEquipo() {
        return bean.obtenerTodosMovDeEquipo();
    }

    @Override
    public MovEquipoDTO buscarUbicacionActualByEquipoId(Integer equipoId) {
        return bean.buscarUbicacionActualByEquipoId(equipoId);
    }

    @Override
    public List<MovEquipoDTO> obtenerTodosMovDeEquipoByEuipoId(Integer equipoId) {
        return bean.obtenerTodosMovDeEquipoByEuipoId(equipoId);
    }

    @Override
    public List<MovEquipoDTO> obtenerTodosMovDeEquipoPorInstitucion(Integer InstitucionId) {
        return bean.obtenerTodosMovDeEquipoPorInstitucion(InstitucionId);
    }

    @Override
    public List<MovEquipoDTO> obtenerTodosMovDeEquipoPorSector(Integer idSector) {
        return bean.obtenerTodosMovDeEquipoPorSector(idSector);
    }

    @Override
    public List<MovEquipoDTO> obtenerTodosMovDeEquipoPorUsuario(Integer idUsuario) {
        return bean.obtenerTodosMovDeEquipoPorUsuario(idUsuario);
    }

    @Override
    public List<MovEquipoDTO> obtenerTodosMovDeEquipoPorFecDesde(LocalDate fecEntrada) {
        return bean.obtenerTodosMovDeEquipoPorFecDesde(fecEntrada);
    }

    @Override
    public List<MovEquipoDTO> obtenerTodosMovDeEquipoPorFechaHasta(LocalDate fecHasta) {
        return bean.obtenerTodosMovDeEquipoPorFechaHasta(fecHasta);
    }
}
