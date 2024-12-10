package uy.edu.utec.view.Interfaces.Perfil;

import uy.edu.utec.controller.ControllerGeneral;
import uy.edu.utec.controller.EquipoController;
import uy.edu.utec.controller.PerfilController;
import uy.edu.utec.servers.dtos.EquipoDTO;
import uy.edu.utec.servers.dtos.PerfilDTO;
import uy.edu.utec.servers.enums.EstadoGeneral;
import uy.edu.utec.servers.excepciones.ServiciosException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class BajaPerfilInterfaz {
    private JButton darDeBajaButton;
    private JButton cancelarButton;
    private JPanel panel;
    private JComboBox comboBoxPerfil;
    private ControllerGeneral controllerGeneral;
    private JFrame frame;

    public BajaPerfilInterfaz(ControllerGeneral controllerGeneral){
        this.controllerGeneral = controllerGeneral;
        frame = new JFrame("Baja perfil");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 500);


        PerfilController perfilController = new PerfilController(controllerGeneral);


        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverPrincipal();
            }
        });
        darDeBajaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String perfilSeleccionado = (String) comboBoxPerfil.getSelectedItem();
                Integer idTipoUsuario = controllerGeneral.getPerfilController().obtenerPerfilPorNombre(perfilSeleccionado).getIdPerfil();
                try {
                    if (mensajeConfirmacion()){
                        controllerGeneral.getPerfilController().bajaPerfil(idTipoUsuario);
                        actualizarComboBox();
                        mostrarMensaje("Perfil dado de baja correctamente.");
                        volverPrincipal();
                    }else{
                        mostrarMensaje("Se ha cancelado la baja correctamente.");
                    }

                } catch (ServiciosException ex) {
                    throw new RuntimeException(ex);
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

    public void actualizarComboBox()  {
        PerfilController perfilController = new PerfilController(controllerGeneral);
        DefaultComboBoxModel<String> comboBoxModelPerfil = new DefaultComboBoxModel<>();
        List<PerfilDTO> perfiles = perfilController.obtenerPerfilesActivos();
        for(PerfilDTO perfilDTO : perfiles){
            comboBoxModelPerfil.addElement(String.valueOf(perfilDTO.getNomPerfil()));
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
