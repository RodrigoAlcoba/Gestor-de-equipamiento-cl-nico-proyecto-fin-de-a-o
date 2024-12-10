package uy.edu.utec.servers.beans.remote;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import uy.edu.utec.servers.beans.local.interfaces.IIntervencionBean;
import uy.edu.utec.servers.beans.remote.interfaces.IntervencionRemote;
import uy.edu.utec.servers.dtos.IntervencionDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Stateless
public class IntervencionBEANImpl implements IntervencionRemote {
    @Inject
    private Validator validator;
    @EJB
    private IIntervencionBean intervencionBean;

    @Override
    public void agregarIntervencion(IntervencionDTO intervencion) throws ServiciosException {
        Set<ConstraintViolation<IntervencionDTO>> violations = validator.validate(intervencion);
        String errores = "";
        for(ConstraintViolation<IntervencionDTO> violation : violations){
            System.out.println(violation.getMessage());
            errores+=violation.getMessage() + "\n";
        }
        if(!errores.isEmpty())
            throw new ServiciosException(errores);
        intervencionBean.agregarIntervencion(intervencion);
    }

    @Override
    public IntervencionDTO buscarPorId(Integer id) {
        return intervencionBean.buscarPorId(id);
    }

    @Override
    public List<IntervencionDTO> obtenerTodasLasIntervenciones() {
        return intervencionBean.obtenerTodasLasIntervenciones();
    }

    @Override
    public List<IntervencionDTO> obtenerIntervencionesPorTipoYFechas(Integer tipoIntervencionID, LocalDate fechaDesde, LocalDate fechaHasta) {
        return intervencionBean.obtenerIntervencionesPorTipoYFechas(tipoIntervencionID,fechaDesde,fechaHasta);
    }

    @Override
    public List<IntervencionDTO> obtenerIntervencionesPorEquipoID(Integer equipoId, LocalDate fechaDesde, LocalDate fechaHasta) {
        return intervencionBean.obtenerIntervencionesPorEquipoID(equipoId, fechaDesde, fechaHasta);
    }

    public List<IntervencionDTO> obtenerIntervencionesPorTipo(String tipoIntervencion){
        return intervencionBean.obtenerIntervencionesPorTipo(tipoIntervencion);
    }
}
