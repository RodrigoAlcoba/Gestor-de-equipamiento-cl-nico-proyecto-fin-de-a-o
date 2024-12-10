package uy.edu.utec.servers.beans.remote.interfaces;

import jakarta.ejb.Remote;
import uy.edu.utec.servers.dtos.BajaUsuarioUbicacionDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

@Remote
public interface IBajaUbicacionUsuarioRemote {

    void usuarioBajaUbicacion(BajaUsuarioUbicacionDTO bajaUbicacion) throws ServiciosException;
}
