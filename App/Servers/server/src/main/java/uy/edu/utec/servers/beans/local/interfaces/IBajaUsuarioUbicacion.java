package uy.edu.utec.servers.beans.local.interfaces;

import jakarta.ejb.LocalBean;
import uy.edu.utec.servers.dtos.BajaUsuarioUbicacionDTO;
import uy.edu.utec.servers.entidades.BajaUsuarioUbicacion;
import uy.edu.utec.servers.excepciones.ServiciosException;

@LocalBean
public interface IBajaUsuarioUbicacion {

    void usuarioBajaUbicacion(BajaUsuarioUbicacionDTO bajaUbicacion) throws ServiciosException;


}
