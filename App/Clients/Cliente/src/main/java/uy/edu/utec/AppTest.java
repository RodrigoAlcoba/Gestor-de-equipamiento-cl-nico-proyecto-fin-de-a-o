package uy.edu.utec;

import uy.edu.utec.daos.*;
import uy.edu.utec.servers.beans.remote.interfaces.*;
import uy.edu.utec.servers.dtos.creacionales.CrearUbicacionDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.sql.SQLException;

public class AppTest {
    public static void main(String[] args) throws ServiciosException, SQLException {
        //-------------------Extración de datos de Usuario-------------------
        IUsuarioRemoteNew usuarioRemote = UsuarioDAOCliNEW.usuarioRemote;
        System.out.println("--------USUARIOS----------");
        usuarioRemote.obtenerTodosLosUsuarios()
                .forEach(usuarioDTO ->
                        System.out.println(
                                usuarioDTO.getNom_usuario()
                        )
                );
        //Funciona pueden probarlo :D
//        System.out.println("--------USUARIOS POR MEDIO DEL LOGIN----------");
//        UsuarioDTO usuarioLogin = usuarioRemote.login(new LoginDTO(
//                "test4@gmail.com",//email de un usuario de tu bd
//                      "123456789" //contraseña
//        ));
//        System.out.println(usuarioLogin.toString());


        //-------------------Extración de datos de Institucion-------------------
        InstitucionRemote institucionRemote = InstitucionDAOCliNEW.institucionRemote;
        System.out.println("--------INSTITUCIONES----------");
        institucionRemote.obtenerTodasLasInstituciones()
                .forEach(institucion ->
                        System.out.println(
                                institucion.getNom_Institucion()
                        )
                );

        //-------------------Extración de datos de Institucion-------------------
        IntervencionRemote intervencionRemote = IntervencionDAOCli.intervencionRemote;
        System.out.println("--------INTERVENCION----------");
        intervencionRemote.obtenerTodasLasIntervenciones()
                .forEach(intervencion ->
                        System.out.println(
                                intervencion.getIdIntervencion()
                        )
                );

        //-------------------Extración de datos de Perfil-------------------
        PerfilRemote perfilRemote = PerfilDAOCliNEW.perfilRemote;
        System.out.println("--------PERFILES----------");
        perfilRemote.obtenerTodosLosPerfiles()
                .forEach(perfil ->
                        System.out.println(
                                perfil.getNomPerfil()
                        )
                );
        System.out.println("--------PERFILES POR NOMBRE----------");
        perfilRemote.obtenerTodosLosPerfilesPorNombre("VET")
                .forEach(perfil ->
                        System.out.println(
                                perfil.getNomPerfil()
                        )
                );
        //-------------------Extración de datos de Proveedor-------------------
        ProveedorRemote proveedorRemote = ProveedorDAOCli.proveedorRemote;
        System.out.println("--------PROVEEDORES----------");
        proveedorRemote.obtenerTodosLosProveedores()
                .forEach(proveedor ->
                        System.out.println(
                                proveedor.getNomProveedor()
                        )
                );

        //-------------------Extración de datos de Sectores-------------------
        SectorRemote sectorRemote = SectorDAOCli.sectorRemote;
        System.out.println("--------SECTORES----------");
        sectorRemote.obtenerTodosLosSectores()
                .forEach(sector ->
                        System.out.println(
                                sector.getNombre().toString()//es un enum
                        )
                );

        //-------------------Extración de datos de Ubicaciones-------------------
        UbicacionRemote ubicacionRemote = UbicacionDAOCli.ubicacionRemote;
        System.out.println("--------Ubicaciones----------");
        ubicacionRemote.obtenerTodasLasUbicaciones()
                .forEach(ubicacionDTO ->
                        System.out.println(
                                ubicacionDTO.getNombre()
                        )
                );

        //PRUEBA DE VALIDACIÓN CON DATOS MALOS (Setear según los datos que se tengan)

/*        CrearUbicacionDTO crearUbicacionDTO = new CrearUbicacionDTO();

        crearUbicacionDTO.setNombre("t");
        crearUbicacionDTO.setCama(3);
        crearUbicacionDTO.setPiso("3");
        crearUbicacionDTO.setInstitucionId(1);
        crearUbicacionDTO.setSectorId(4);
        crearUbicacionDTO.setNumero(1234);

        ubicacionRemote.agregarUbicacion(crearUbicacionDTO);*/

        //-------------------Extración de datos de MovEquipo-------------------
        IMovEquipoRemote movEquipoRemote = MovEquipoDAOCli.movEquipoRemote;
        System.out.println("--------MovEquipo----------");
        movEquipoRemote.obtenerTodosMovDeEquipo()
                .forEach(movEquipoDTO ->
                        System.out.println(
                                "Observaciones: " + movEquipoDTO.getObservaciones()
                        )
                );

        //-------------------Extración de datos de EQUIPO-------------------
        EquipoRemote equipoRemote = EquipoDAOCli.equipoRemote;
        System.out.println("--------EQUIPO----------");
        equipoRemote.obtenerTodosLosEquipos()
                .forEach(equipoDTO ->
                        System.out.println(
                                equipoDTO.toString()
                        )
                );



    }
}
