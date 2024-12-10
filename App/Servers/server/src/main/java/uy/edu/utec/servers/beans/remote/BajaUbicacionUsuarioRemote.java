package uy.edu.utec.servers.beans.remote;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import uy.edu.utec.servers.beans.local.interfaces.IBajaUsuarioUbicacion;
import uy.edu.utec.servers.beans.remote.interfaces.IBajaUbicacionUsuarioRemote;
import uy.edu.utec.servers.dtos.BajaUsuarioUbicacionDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.Set;

@Stateless
public class BajaUbicacionUsuarioRemote implements IBajaUbicacionUsuarioRemote {

    @Inject
    private Validator validator;
    @EJB
    IBajaUsuarioUbicacion iBajaUsuarioUbicacion;

    @Override
    public void usuarioBajaUbicacion(BajaUsuarioUbicacionDTO bajaUbicacion) throws ServiciosException {
        Set<ConstraintViolation<BajaUsuarioUbicacionDTO>> violations = validator.validate(bajaUbicacion);
        String errores = "";
        for(ConstraintViolation<BajaUsuarioUbicacionDTO> violation : violations){
            System.out.println(violation.getMessage());
            errores+=violation.getMessage() + "\n";
        }
        if(!errores.isEmpty()) {
            throw new ServiciosException(errores);
        }
        iBajaUsuarioUbicacion.usuarioBajaUbicacion(bajaUbicacion);
    }
}
