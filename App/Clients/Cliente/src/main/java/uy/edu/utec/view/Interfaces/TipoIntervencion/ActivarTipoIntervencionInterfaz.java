package uy.edu.utec.view.Interfaces.TipoIntervencion;

import uy.edu.utec.controller.ControllerGeneral;
import uy.edu.utec.controller.TipoIntervencionController;
import uy.edu.utec.servers.dtos.TipoIntervencionDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ActivarTipoIntervencionInterfaz {
    private JButton guardarButton;
    private JButton cancelarButton;
    private JPanel panel;
    private JComboBox comboBoxTipoIntervencion;

    private ControllerGeneral controllerGeneral;
    private JFrame frame;
    public ActivarTipoIntervencionInterfaz(ControllerGeneral controllerGeneral) {
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
            }
        });
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                Object selectedItem = comboBoxTipoIntervencion.getSelectedItem();


                if (selectedItem == null || selectedItem.toString().isEmpty()) {

                    mostrarMensaje("¡No hay tipos de intervención para activar!");
                    return;
                }

                // El comboBox no está vacío, proceder con la lógica original
                String nomTipoIntervencion = selectedItem.toString();
                Integer idTipoIntervencion = controllerGeneral.getTipoIntervencionController()
                        .obtenerTodosLosTipoIntervencionPorNombre(nomTipoIntervencion).getIdTipointervencion();

                try {
                    controllerGeneral.getTipoIntervencionController().activarTipoIntervencion(idTipoIntervencion);
                    actualizarComboBox();
                    mostrarMensaje("¡Tipo de intervención activada correctamente!");
                } catch (ServiciosException e) {
                    throw new RuntimeException(e);
                }
            }

        });
    }

    public void actualizarComboBox(){

        DefaultComboBoxModel comboBoxModelTipo = new DefaultComboBoxModel();
        TipoIntervencionController tipoIntervencionController = new TipoIntervencionController(controllerGeneral);
        List<TipoIntervencionDTO> tipoIntervencionDTOS = tipoIntervencionController.obtenerTodosLoTipoIntervencionEliminados();
        for (TipoIntervencionDTO tipoIntervencionDTO : tipoIntervencionDTOS){
            comboBoxModelTipo.addElement(String.valueOf(tipoIntervencionDTO.getNomTipoIntervencion()));
        }
        comboBoxTipoIntervencion.setModel(comboBoxModelTipo);
    }

    public void setVisible(boolean visible){
        frame.setVisible(visible);
    }

    public JPanel getPanel(){
        return panel;
    }
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
    public void volverPrincipal() {
        controllerGeneral.mostrarMenuInterfaz(true);
        panel.setVisible(false);
    }
}
