package uy.edu.utec.servers.beans.local;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import uy.edu.utec.servers.beans.local.interfaces.IBajaUsuarioEquipoBean;
import uy.edu.utec.servers.beans.local.interfaces.IEquipoBean;
import uy.edu.utec.servers.dao.BajaUsuarioEquipoDAO;
import uy.edu.utec.servers.dao.EquipoDAO;
import uy.edu.utec.servers.dao.UsuarioDAOnew;
import uy.edu.utec.servers.dtos.BajaUsuarioEquipoDTO;
import uy.edu.utec.servers.entidades.BajaUsuarioEquipo;
import uy.edu.utec.servers.entidades.Equipo;
import uy.edu.utec.servers.entidades.PK_Compuestas.BajaUsuarioEquipoPK;
import uy.edu.utec.servers.entidades.Usuario;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.servers.mappers.BajaUsuarioEquipoMapper;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Stateless
public class BajaUsuarioEquipoBean implements IBajaUsuarioEquipoBean {
    @EJB
    BajaUsuarioEquipoDAO bajaEquipodao;
    @EJB
    UsuarioDAOnew usuarioDao;
    @EJB
    EquipoDAO equipoDao;
    @EJB
    IEquipoBean equipoBean;
    @Override
    public void crearBajaEquipo(BajaUsuarioEquipoDTO bajaEquipoDTO) throws ServiciosException, SQLException {
        Usuario usuario = usuarioDao.buscarPorId(bajaEquipoDTO.getUsuarioId());
        Equipo equipo = equipoDao.buscarPorId(bajaEquipoDTO.getEquipoId());

        BajaUsuarioEquipo bajaUsuarioEquipo = new BajaUsuarioEquipo(
                usuario,
                equipo,
                bajaEquipoDTO.getFechaBaja(),
                bajaEquipoDTO.getRazon(),
                bajaEquipoDTO.getComentario()
        );
        bajaUsuarioEquipo.setEquipo(equipo);
        bajaUsuarioEquipo.setUsuario(usuario);

        //SE CREA LA BAJA DEL EQUIPO
        bajaEquipodao.crearBajaEquipo(bajaUsuarioEquipo);

        //ACTUALIZO EL EQUIPO Y LO DOY DE BAJA
        equipoBean.bajaEquipo(bajaEquipoDTO.getEquipoId());
    }

    @Override
    public void actualizarBajaEquipo(BajaUsuarioEquipoDTO bajaEquipoDTO) throws ServiciosException {
        Usuario usuario = usuarioDao.buscarPorId(bajaEquipoDTO.getUsuarioId());
        Equipo equipo = equipoDao.buscarPorId(bajaEquipoDTO.getEquipoId());

        BajaUsuarioEquipo bajaUsuarioEquipo = new BajaUsuarioEquipo(
                usuario,
                equipo,
                bajaEquipoDTO.getFechaBaja(),
                bajaEquipoDTO.getRazon(),
                bajaEquipoDTO.getComentario()
        );
        bajaUsuarioEquipo.setEquipo(equipo);
        bajaUsuarioEquipo.setUsuario(usuario);

        bajaEquipodao.actualizarBajaEquipo(bajaUsuarioEquipo);
    }

    @Override
    public BajaUsuarioEquipoDTO buscarPorClaveCompuesta(Integer usuarioid, Integer equipoId, LocalDate fecbaja) throws SQLException {
        BajaUsuarioEquipoPK bajaUsuarioEquipoPK = new BajaUsuarioEquipoPK(
                usuarioDao.buscarPorId(usuarioid),
                equipoDao.buscarPorId(equipoId),
                fecbaja
        );
        return BajaUsuarioEquipoMapper.toDTO(bajaEquipodao.buscarPorClaveCompuesta(bajaUsuarioEquipoPK));
    }

    @Override
    public List<BajaUsuarioEquipoDTO> obtenertodasLasBajasEquipo() {
        return BajaUsuarioEquipoMapper.toDTOList(bajaEquipodao.obtenertodasLasBajasEquipo());
    }

    @Override
    public BajaUsuarioEquipoDTO buscarBajaPorEquipoId(Integer equipoId) throws SQLException {
        return BajaUsuarioEquipoMapper.toDTO(bajaEquipodao.buscarBajaPorEquipoId(equipoId));
    }

    @Override
    public List<BajaUsuarioEquipoDTO> buscarBajasPorUsuarioId(Integer id) {
        return BajaUsuarioEquipoMapper.toDTOList(bajaEquipodao.buscarBajasPorUsuarioId(id));
    }
}
