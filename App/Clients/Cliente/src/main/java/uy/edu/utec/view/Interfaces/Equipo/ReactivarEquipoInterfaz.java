package uy.edu.utec.view.Interfaces.Equipo;

import uy.edu.utec.controller.ControllerGeneral;
import uy.edu.utec.controller.EquipoController;
import uy.edu.utec.servers.dtos.EquipoDTO;
import uy.edu.utec.servers.enums.EstadoGeneral;
import uy.edu.utec.servers.excepciones.ServiciosException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class ReactivarEquipoInterfaz {
    private JComboBox reactivarComboBox;
    private JButton cancelarButton;
    private JButton reActivarButton;
    private JPanel panel;
    private String seleccionada;
    private ControllerGeneral controllerGeneral;
    private JFrame frame;
    EquipoController equipoController;

    public ReactivarEquipoInterfaz(ControllerGeneral controllerGeneral) throws ServiciosException {

        this.controllerGeneral = controllerGeneral;
        frame = new JFrame("Listado Tipo Intervención");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 500);


        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerGeneral.mostrarMenuInterfaz(true);
                panel.setVisible(false);
                mostrarMensaje("Se ha cancelado la activación del equipo");
            }
        });

        equipoController = new EquipoController(controllerGeneral);
        reActivarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mensajeConfirmacion()) {

                    Object selectedItem = reactivarComboBox.getSelectedItem();
                    if (selectedItem == null || selectedItem.toString().isEmpty()) {

                        mostrarMensaje("¡No hay equipo para activar!");
                        return;
                    }

                    seleccionada = (String) reactivarComboBox.getSelectedItem();
                    try {
                        Integer idEquipo =  equipoController.obtenerPorNombre(seleccionada).getIdEquipo();
                        equipoController.reactivarEquipo(idEquipo);
                        mostrarMensaje("Equipo activado correctamente.");
                        actualizarComboBox();
                    } catch (SQLException | ServiciosException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    mostrarMensaje("Se ha cancelado la activación del equipo con éxito.");
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

    public void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public void actualizarComboBox() throws SQLException, ServiciosException {
        DefaultComboBoxModel<String> comboBoxModelIntervencion = new DefaultComboBoxModel<>();
        EquipoController equipoController = new EquipoController(controllerGeneral);
        List<EquipoDTO> equipos = equipoController.obtenerTodosLosEquipos();
        for(EquipoDTO equipoDTO : equipos){
            if (equipoDTO.getEstado() == EstadoGeneral.ELIMINADO){
                comboBoxModelIntervencion.addElement(String.valueOf(equipoDTO.getNomEquipo()));
            }
        }
        reactivarComboBox.setModel(comboBoxModelIntervencion);
    }

    public Boolean mensajeConfirmacion() {
        Object[] opciones = {"Confirmar", "Cancelar"};
        int respuesta = JOptionPane.showOptionDialog(null, "¿Desea confirmar la acción?", "Confirmación", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        return respuesta == JOptionPane.YES_OPTION;
    }
}
