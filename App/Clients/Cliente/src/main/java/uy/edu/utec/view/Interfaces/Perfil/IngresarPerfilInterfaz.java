package uy.edu.utec.view.Interfaces.Perfil;

import uy.edu.utec.controller.ControllerGeneral;
import uy.edu.utec.servers.dtos.PerfilDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearPerfilDTO;
import uy.edu.utec.servers.enums.EstadoGeneral;
import uy.edu.utec.servers.excepciones.ServiciosException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IngresarPerfilInterfaz {
    private JTextField NomPerfilInput;
    private JButton cancelarButton;
    private JButton ingresarButton;
    private JPanel panel;

    ControllerGeneral controllerGeneral;

    public IngresarPerfilInterfaz(ControllerGeneral controllerGeneral){
        this.controllerGeneral = controllerGeneral;
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverPrincipal();
            }
        });
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    agregarPerfil();

                } catch (ServiciosException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    //cardlayout
    public JPanel getPanel() {
        return panel;
    }
    public void setVisible(boolean visible) {
        panel.setVisible(visible);
    }

    public void agregarPerfil() throws ServiciosException {
        CrearPerfilDTO perfilDTO = new CrearPerfilDTO();
        perfilDTO.setNomPerfil(NomPerfilInput.getText().trim().toUpperCase());
        if (mensajeConfirmacion()){
            try{
                controllerGeneral.getPerfilController().agregarPerfil(perfilDTO);
                mostrarMensaje("Perfil agregado correctamente.");
                volverPrincipal();
                }catch (ServiciosException ex){
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error al ingresar un perfil: \n" + ex.getMessage());
            }
        }else{
            volverPrincipal();
        }

        }


    public void volverPrincipal(){
        controllerGeneral.mostrarMenuInterfaz(true);
        panel.setVisible(false);
    }

    public void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public Boolean mensajeConfirmacion() {
        Object[] opciones = {"Confirmar", "Cancelar"};
        int respuesta = JOptionPane.showOptionDialog(null, "¿Desea confirmar la acción?", "Confirmación", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        return respuesta == JOptionPane.YES_OPTION;
    }

}
