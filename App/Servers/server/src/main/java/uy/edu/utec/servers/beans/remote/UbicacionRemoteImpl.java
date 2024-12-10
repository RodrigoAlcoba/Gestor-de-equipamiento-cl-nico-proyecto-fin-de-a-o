package uy.edu.utec.servers.beans.remote;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import uy.edu.utec.servers.beans.local.interfaces.IUbicacionBean;
import uy.edu.utec.servers.beans.remote.interfaces.UbicacionRemote;
import uy.edu.utec.servers.dtos.UbicacionDTO;
import uy.edu.utec.servers.dtos.UsuarioDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearUbicacionDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Stateless
public class UbicacionRemoteImpl implements UbicacionRemote, Serializable {

    @Inject
    private Validator validator;

    @EJB
    private IUbicacionBean ubicacionBean;

    @Override
    public void agregarUbicacion(CrearUbicacionDTO ubicacion) throws ServiciosException {
        Set<ConstraintViolation<CrearUbicacionDTO>> violations = validator.validate(ubicacion);
        String errores = "";
        for(ConstraintViolation<CrearUbicacionDTO> violation : violations){
            System.out.println(violation.getMessage());
            errores+=violation.getMessage() + "\n";
        }
        if(!errores.isEmpty()) {
            throw new ServiciosException(errores);
        }
        ubicacionBean.agregarUbicacion(ubicacion);
    }
    @Override
    public UbicacionDTO buscarPorId(Integer id) {
        return ubicacionBean.buscarPorId(id);
    }

    @Override
    public List<UbicacionDTO> obtenerTodasLasUbicaciones() {
        return ubicacionBean.obtenerTodasLasUbicaciones();
    }

    @Override
    public void actualizarUbicacion(UbicacionDTO ubicacionDTO) throws ServiciosException {
        Set<ConstraintViolation<UbicacionDTO>> violations = validator.validate(ubicacionDTO);
        String errores = "";
        for(ConstraintViolation<UbicacionDTO> violation : violations){
            System.out.println(violation.getMessage());
            errores+=violation.getMessage() + "\n";
        }
        if(!errores.isEmpty())
            throw new ServiciosException(errores);
        ubicacionBean.actualizarUbicacion(ubicacionDTO);
    }

    @Override
    public void bajarUbicacion(Integer id) throws ServiciosException {
        ubicacionBean.bajarUbicacion(id);
    }

    @Override
    public UbicacionDTO buscarUbicacionPorNombre(String nombre) {
        return ubicacionBean.buscarUbicacionPorNombre(nombre);
    }

    @Override
    public List<UbicacionDTO> obtenerTodasLasUbicacionesActivas() {
        return ubicacionBean.obtenerTodasLasUbicacionesActivas();
    }


}
