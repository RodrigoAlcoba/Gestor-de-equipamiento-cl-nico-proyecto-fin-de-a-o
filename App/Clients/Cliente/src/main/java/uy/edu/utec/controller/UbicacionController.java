package uy.edu.utec.controller;

import uy.edu.utec.daos.UbicacionDAOCli;
import uy.edu.utec.servers.dtos.BajaUsuarioUbicacionDTO;
import uy.edu.utec.servers.dtos.UbicacionDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearUbicacionDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;

public class UbicacionController {
    private ControllerGeneral controllerGeneral;

    public UbicacionController(ControllerGeneral controllerGeneral){
        this.controllerGeneral = controllerGeneral;
    }

    public void agregarUbicacion(CrearUbicacionDTO ubicacion) throws ServiciosException {
        UbicacionDAOCli.agregarUbicacion(ubicacion);
    }

    public UbicacionDTO obtenerUbicacionPorNombre(String nombre){
        return UbicacionDAOCli.obtenerUbicacionPorNombre(nombre);
    }

    public List<UbicacionDTO> obtenerTodasLasUbicaciones() throws ServiciosException {
        return UbicacionDAOCli.obtenerTodasLasUbicaciones();
    }
    public UbicacionDTO buscarUbicacionPorNombre(String inputNombre){
        return UbicacionDAOCli.obtenerUbicacionPorNombre(inputNombre);
    }

    public UbicacionDTO obtenerUbicacionPorId(Integer id) throws ServiciosException {
        return UbicacionDAOCli.buscarUbicacionPorId(id);
    }

    public  UbicacionDTO obtenerUbicacionPorIdEquipo(Integer id){
        return UbicacionDAOCli.obtenerUbicacionPorIdEquipo(id);
    }

    public List<UbicacionDTO> obtenerTodasLasUbicacionesActivas() throws ServiciosException {
        return UbicacionDAOCli.obtenerTodasLasUbicacionesActivas();
    }

    public void darDeBajaUbicacion(BajaUsuarioUbicacionDTO bajaUsuarioUbicacionDTO) throws ServiciosException {
        UbicacionDAOCli.bajaUbicacion(bajaUsuarioUbicacionDTO);
    }


    public void modificarUbicacion(UbicacionDTO ubicacionDTO) throws ServiciosException {
        UbicacionDAOCli.modificarUbicacion(ubicacionDTO);
    }

}
