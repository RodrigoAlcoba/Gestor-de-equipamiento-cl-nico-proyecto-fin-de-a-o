package uy.edu.utec.servers.beans.remote;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import uy.edu.utec.servers.beans.local.interfaces.IBajaUsuarioEquipoBean;
import uy.edu.utec.servers.beans.remote.interfaces.IBajaUsuarioEquipoRemote;
import uy.edu.utec.servers.dtos.BajaUsuarioEquipoDTO;
import uy.edu.utec.servers.dtos.UsuarioDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Stateless
public class BajaUsuarioEquipoBeanImpl implements IBajaUsuarioEquipoRemote {
    @Inject
    private Validator validator;
    @EJB
    IBajaUsuarioEquipoBean bean;
    @Override
    public void crearBajaEquipo(BajaUsuarioEquipoDTO bajaEquipo) throws ServiciosException, SQLException {
        Set<ConstraintViolation<BajaUsuarioEquipoDTO>> violations = validator.validate(bajaEquipo);
        String errores = "";
        for(ConstraintViolation<BajaUsuarioEquipoDTO> violation : violations){
            System.out.println(violation.getMessage());
            errores+=violation.getMessage() + "\n";
        }
        if(!errores.isEmpty())
            throw new ServiciosException(errores);
        bean.crearBajaEquipo(bajaEquipo);
    }

    @Override
    public void actualizarBajaEquipo(BajaUsuarioEquipoDTO bajaEquipo) throws ServiciosException {
        Set<ConstraintViolation<BajaUsuarioEquipoDTO>> violations = validator.validate(bajaEquipo);
        String errores = "";
        for(ConstraintViolation<BajaUsuarioEquipoDTO> violation : violations){
            System.out.println(violation.getMessage());
            errores+=violation.getMessage() + "\n";
        }
        if(!errores.isEmpty())
            throw new ServiciosException(errores);
        bean.actualizarBajaEquipo(bajaEquipo);
    }

    @Override
    public BajaUsuarioEquipoDTO buscarPorClaveCompuesta(Integer usuarioid, Integer equipoId, LocalDate fecbaja) throws SQLException {
        return bean.buscarPorClaveCompuesta(usuarioid, equipoId, fecbaja);
    }

    @Override
    public List<BajaUsuarioEquipoDTO> obtenertodasLasBajasEquipo() {
        return bean.obtenertodasLasBajasEquipo();
    }

    @Override
    public BajaUsuarioEquipoDTO buscarBajaPorEquipoId(Integer id) throws SQLException {
        return bean.buscarBajaPorEquipoId(id);
    }

    @Override
    public List<BajaUsuarioEquipoDTO> buscarBajasPorUsuarioId(Integer id) {
        return bean.buscarBajasPorUsuarioId(id);
    }
}
