package uy.edu.utec.view.Interfaces.TipoIntervencion;

import uy.edu.utec.controller.ControllerGeneral;
import uy.edu.utec.controller.TipoIntervencionController;
import uy.edu.utec.servers.dtos.TipoIntervencionDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BajaTipoIntervencionInterfaz {
    private JTextField NomTipoIntervencionInput;
    private JButton cancelarButton;
    private JButton darDeBajaButton;
    private JPanel panel;
    private JComboBox comboBoxTipoIntervencion;

    private ControllerGeneral controllerGeneral;
    private JFrame frame;
    public BajaTipoIntervencionInterfaz(ControllerGeneral controllerGeneral) {
        this.controllerGeneral = controllerGeneral;
        frame = new JFrame("Baja tipo intervención");
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

        darDeBajaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                Object selectedItem = comboBoxTipoIntervencion.getSelectedItem();


                if (selectedItem == null || selectedItem.toString().isEmpty()) {

                    mostrarMensaje("¡No hay tipos de intervención para dar de baja!");
                    return;
                }

               String nomTipoIntervencion = comboBoxTipoIntervencion.getSelectedItem().toString();
               Integer idTipoIntervencion = controllerGeneral.getTipoIntervencionController().obtenerTodosLosTipoIntervencionPorNombre(nomTipoIntervencion).getIdTipointervencion();
               try {
                   controllerGeneral.getTipoIntervencionController().eliminarTipoIntervencion(idTipoIntervencion);
                   actualizarComboBox();
                   mostrarMensaje("¡Tipo de intervención dada de baja correctamente!");
               }catch (ServiciosException ex){
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

    public void volverPrincipal() {
        controllerGeneral.mostrarMenuInterfaz(true);
        panel.setVisible(false);
    }

    public void actualizarComboBox(){
        DefaultComboBoxModel comboBoxModelTipo = new DefaultComboBoxModel();
        TipoIntervencionController tipoIntervencionController = new TipoIntervencionController(controllerGeneral);
        List<TipoIntervencionDTO> tipoIntervencionDTOS = tipoIntervencionController.obtenerTodosLosTipoIntervencionActivos();
        for (TipoIntervencionDTO tipoIntervencionDTO : tipoIntervencionDTOS){
            comboBoxModelTipo.addElement(String.valueOf(tipoIntervencionDTO.getNomTipoIntervencion()));
        }
        comboBoxTipoIntervencion.setModel(comboBoxModelTipo);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

}
