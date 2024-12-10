package uy.edu.utec.servers.beans.local.interfaces;

import jakarta.ejb.LocalBean;
import uy.edu.utec.servers.dtos.BajaUsuarioEquipoDTO;
import uy.edu.utec.servers.entidades.BajaUsuarioEquipo;
import uy.edu.utec.servers.entidades.PK_Compuestas.BajaUsuarioEquipoPK;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
@LocalBean
public interface IBajaUsuarioEquipoBean {
    void crearBajaEquipo(BajaUsuarioEquipoDTO bajaEquipo) throws ServiciosException, SQLException;
    void actualizarBajaEquipo(BajaUsuarioEquipoDTO bajaEquipo) throws ServiciosException;
    BajaUsuarioEquipoDTO buscarPorClaveCompuesta(Integer usuarioid, Integer equipoId, LocalDate fecbaja) throws SQLException;
    List<BajaUsuarioEquipoDTO> obtenertodasLasBajasEquipo();
    BajaUsuarioEquipoDTO buscarBajaPorEquipoId(Integer id) throws SQLException;
    List<BajaUsuarioEquipoDTO> buscarBajasPorUsuarioId(Integer id);
}
