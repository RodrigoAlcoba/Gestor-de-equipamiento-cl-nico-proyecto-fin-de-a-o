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

public class BajaUsuarioInterfaz {
    private JButton darDeBajaButton;
    private JButton cancelarButton;
    private JPanel panel;
    private JComboBox comboBoxUsuarios;

    private ControllerGeneral controllerGeneral;
    private JFrame frame;

    public BajaUsuarioInterfaz(ControllerGeneral controllerGeneral) {
        this.controllerGeneral = controllerGeneral;
        frame = new JFrame("Baja usuario");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 500);


        UsuarioController usuarioController = new UsuarioController(controllerGeneral);


        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerGeneral.mostrarMenuInterfaz(true);
                //mostrarMensaje("Baja cancelada.");
                panel.setVisible(false);
            }
        });
        darDeBajaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!usuarioController.obtenerUsuariosActivos().isEmpty()) {
                    String usuarioSeleccionadoEmail = comboBoxUsuarios.getSelectedItem().toString();
                    Integer idUsuario = controllerGeneral.getUsuarioController().buscarPorEmail(usuarioSeleccionadoEmail).getID_Usuario();
                    try {
                        if(mensajeConfirmacion()){
                            controllerGeneral.getUsuarioController().bajaUsuarioLogica(idUsuario);
                            actualizarComboBox();
                            mostrarMensaje("¡Usuario dado de baja correctamente!");
                            volverPrincipal();
                        }//else{
                            //mostrarMensaje("Baja cancelada.");
                        //}
                    } catch (ServiciosException | SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error al dar de baja el usuario: \n" + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un usuario para dar de baja.");
                }
            }
        });

    }


    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void volverPrincipal() {
        controllerGeneral.mostrarMenuInterfaz(true);
        panel.setVisible(false);
    }

    public void actualizarComboBox() throws SQLException, ServiciosException {
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
        UsuarioController usuarioController = new UsuarioController(controllerGeneral);
        List<UsuarioDTO> usuarios = usuarioController.obtenerTodosLosUsuarios();
        for(UsuarioDTO usuarioDTO : usuarios){
            if (usuarioDTO.getEstado() == EstadoUsuario.ACTIVO){
                comboBoxModel.addElement(String.valueOf(usuarioDTO.getEmail()));
            }
        }
        comboBoxUsuarios.setModel(comboBoxModel);
    }

    public Boolean mensajeConfirmacion() {
        Object[] opciones = {"Confirmar", "Cancelar"};
        int respuesta = JOptionPane.showOptionDialog(null, "¿Desea confirmar la acción?", "Confirmación", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        return respuesta == JOptionPane.YES_OPTION;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
}

