package uy.edu.utec.view.Interfaces.TipoIntervencion;

import uy.edu.utec.controller.ControllerGeneral;
import uy.edu.utec.controller.TipoIntervencionController;
import uy.edu.utec.servers.dtos.TipoIntervencionDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class IngresoTipoIntervencionInterfaz {
    private JTextField TipoIntervencionInput;
    private JButton guardarButton;
    private JButton cancelarButton;
    private JPanel panel;

    //
    ControllerGeneral controllerGeneral;

    public IngresoTipoIntervencionInterfaz(ControllerGeneral controllerGeneral){
        this.controllerGeneral = controllerGeneral;
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    controllerGeneral.mostrarMenuInterfaz(true);
                    panel.setVisible(false);
            }
        });
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    try {
                        agregarTipoIntervencion();

                    } catch (ServiciosException ex) {
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error al crear el tipo de intervención: \n" + ex.getMessage());
                    }
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }

    public void volverPrincipal(){
        controllerGeneral.mostrarMenuInterfaz(true);
        panel.setVisible(false);
    }

    public void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public void agregarTipoIntervencion()throws ServiciosException {

        if (mensajeConfirmacion()){
            controllerGeneral.mostrarMenuInterfaz(true);
        }//else {
//            //mostrarMensaje("Se ha cancelado el ingreso de tipo intervención con éxito.");
//        }

        if (TipoIntervencionInput.getText().length() < 3) {
            mostrarMensaje("El tipo de intervención debe tener entre 3 y 25 caracteres.");
            return;
        }
        if (TipoIntervencionInput.getText().length() > 25){
            mostrarMensaje("El tipo de intervención debe tener entre 3 y 25 caracteres.");
            return;
        }

        if (!TipoIntervencionInput.getText().matches("^[a-zA-Z\\s]+$")) {
            mostrarMensaje("El tipo de intervención no puede contener números ni caracteres especiales.");
            return;
        }
        if (TipoIntervencionInput.getText().isEmpty() || TipoIntervencionInput.getText().trim().isEmpty()) {
            mostrarMensaje("El tipo de intervención no puede estar vacío.");
            return;
        }
        TipoIntervencionController tipoIntervencionController = new TipoIntervencionController(controllerGeneral);

        List<TipoIntervencionDTO> existentes = tipoIntervencionController.obtenerTodosLosTiposIntervencion();

        for (TipoIntervencionDTO tiposIntervencionActuales :existentes  ) {
            if (tiposIntervencionActuales.getNomTipoIntervencion().equalsIgnoreCase(TipoIntervencionInput.getText().trim())) {
                mostrarMensaje("Ya existe un tipo de intervención con ese nombre.");
                return;
            }
        }

        TipoIntervencionDTO tipoIntervencionDTO = new TipoIntervencionDTO();
        tipoIntervencionDTO.setNomTipoIntervencion(TipoIntervencionInput.getText());
        TipoIntervencionController intervencion = new TipoIntervencionController(controllerGeneral);
        intervencion.agregarTipoIntervencion(tipoIntervencionDTO);
        mostrarMensaje("Tipo intervención agregada con éxito");
        volverPrincipal();
    }
    public Boolean mensajeConfirmacion() {
        Object[] opciones = {"Confirmar", "Cancelar"};
        int respuesta = JOptionPane.showOptionDialog(null, "¿Desea confirmar la acción?", "Confirmación", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        return respuesta == JOptionPane.YES_OPTION;
    }


}

