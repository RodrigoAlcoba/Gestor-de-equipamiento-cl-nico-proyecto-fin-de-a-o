package uy.edu.utec.view.Interfaces.Usuario;

import uy.edu.utec.controller.ControllerGeneral;
import uy.edu.utec.controller.EquipoController;
import uy.edu.utec.controller.UsuarioController;
import uy.edu.utec.servers.dtos.EquipoDTO;
import uy.edu.utec.servers.dtos.UsuarioDTO;
import uy.edu.utec.servers.enums.EstadoGeneral;
import uy.edu.utec.servers.enums.EstadoUsuario;
import uy.edu.utec.servers.excepciones.ServiciosException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class ActivarUsuarioInterfaz {
    private JButton activarButton;
    private JButton cancelarButton;
    private JPanel panel;
    private JComboBox comboBoxUsuarios;
    ControllerGeneral controllerGeneral;

    public ActivarUsuarioInterfaz(ControllerGeneral controllerGeneral){
        this.controllerGeneral = controllerGeneral;
        UsuarioController usuarioController = new UsuarioController(controllerGeneral);

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerGeneral.mostrarMenuInterfaz(true);
                //mostrarMensaje("Activación cancelada");
                panel.setVisible(false);
            }
        });
        activarButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!usuarioController.getUsuariosSinValidarYEliminados().isEmpty()){
                        try {
                            controllerGeneral.getUsuarioController().activarUsuario(usuarioController.buscarPorEmail((String) comboBoxUsuarios.getSelectedItem()).getID_Usuario());
                            actualizarComboBox();
                            mostrarMensaje("Usuario activado correctamente");
                            volverPrincipal();
                        } catch (ServiciosException | SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Seleccione un usuario para activar.");
                    }
                }
            }
        );

    }

    public void actualizarComboBox() throws SQLException, ServiciosException {
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
        UsuarioController usuarioController = new UsuarioController(controllerGeneral);
        List<UsuarioDTO> usuarios = usuarioController.getUsuariosSinValidarYEliminados();
        for(UsuarioDTO usuarioDTO : usuarios){
                comboBoxModel.addElement(String.valueOf(usuarioDTO.getEmail()));

        }
        comboBoxUsuarios.setModel(comboBoxModel);
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
}
