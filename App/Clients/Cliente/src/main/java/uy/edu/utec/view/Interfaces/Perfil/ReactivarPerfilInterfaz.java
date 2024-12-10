package uy.edu.utec.view.Interfaces.Perfil;

import uy.edu.utec.controller.ControllerGeneral;
import uy.edu.utec.controller.PerfilController;
import uy.edu.utec.servers.dtos.PerfilDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class ReactivarPerfilInterfaz {
    private JButton reActivarButton;
    private JButton cancelarButton;
    private JPanel panel;
    private JComboBox comboBoxPerfil;


    private ControllerGeneral controllerGeneral;
    private JFrame frame;
    public ReactivarPerfilInterfaz(ControllerGeneral controllerGeneral) {
        this.controllerGeneral = controllerGeneral;
        frame = new JFrame("Reactivar perfil");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 500);


        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerGeneral.mostrarMenuInterfaz(true);
                panel.setVisible(false);
            }
        });
        reActivarButton.addActionListener(new ActionListener() {
            String perfilSeleccionado = (String) comboBoxPerfil.getSelectedItem();
            PerfilController perfilController = new PerfilController(controllerGeneral);
           // Integer idTipoUsuario = perfilController.obtenerPerfilPorNombre(perfilSeleccionado).getIdPerfil();
            @Override
            public void actionPerformed(ActionEvent e) {

                Object selectedItem = comboBoxPerfil.getSelectedItem();

                if (selectedItem == null || selectedItem.toString().isEmpty()) {

                    mostrarMensaje("¡No hay tipos de intervención para activar!");
                    return;
                }

                try {
                    controllerGeneral.getPerfilController().activarPerfil(perfilController.obtenerPerfilPorNombre((String) comboBoxPerfil.getSelectedItem()).getIdPerfil());
                    actualizarComboBox();
                } catch (ServiciosException | SQLException ex) {
                    throw new RuntimeException(ex);
                }
                if (mensajeConfirmacion()){
                    controllerGeneral.mostrarMenuInterfaz(true);
                    mostrarMensaje("Perfil activado correctamente.");
                    volverPrincipal();
                }else {
                    mostrarMensaje("Se ha cancelado la modificación de perfil con éxito.");
                }
            }
        });
    }
    public void setVisible(boolean visible){
        frame.setVisible(visible);
    }

    public JPanel getPanel(){
        return panel;
    }

    public void volverPrincipal(){
        controllerGeneral.mostrarMenuInterfaz(true);
        panel.setVisible(false);
    }

    public void actualizarComboBox() throws SQLException, ServiciosException {
        DefaultComboBoxModel<String> comboBoxModelPerfil = new DefaultComboBoxModel<>();
        PerfilController perfilController = new PerfilController(controllerGeneral);
        if (!perfilController.obtenerPerfilesEliminados().isEmpty()){
            List<PerfilDTO> perfiles = perfilController.obtenerPerfilesEliminados();
            for(PerfilDTO perfilDTO : perfiles){
                comboBoxModelPerfil.addElement(String.valueOf(perfilDTO.getNomPerfil()));
            }
        }
        comboBoxPerfil.setModel(comboBoxModelPerfil);
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

