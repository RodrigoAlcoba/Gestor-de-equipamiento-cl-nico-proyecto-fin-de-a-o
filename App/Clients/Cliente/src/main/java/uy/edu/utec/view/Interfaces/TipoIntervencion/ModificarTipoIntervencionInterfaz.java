package uy.edu.utec.view.Interfaces.TipoIntervencion;

import uy.edu.utec.controller.ControllerGeneral;
import uy.edu.utec.controller.TipoIntervencionController;
import uy.edu.utec.servers.dtos.TipoIntervencionDTO;
import uy.edu.utec.servers.enums.EstadoGeneral;
import uy.edu.utec.servers.excepciones.ServiciosException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.List;

public class ModificarTipoIntervencionInterfaz {
    private JComboBox comboBoxEstado;
    private JButton guardarButton;
    private JButton cancelarButton;
    private JPanel panel;
    private JComboBox comboTipoIntervencion;

    private ControllerGeneral controllerGeneral;
    private JFrame frame;
    public ModificarTipoIntervencionInterfaz(ControllerGeneral controllerGeneral) {
        this.controllerGeneral = controllerGeneral;
        frame = new JFrame("Modificar Tipo Intervención");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 500);

        //Tipo intervención nombre
        DefaultComboBoxModel<String> comboBoxModelTipoIntervencion = new DefaultComboBoxModel<>();
        TipoIntervencionController tipoIntervencionController = new TipoIntervencionController(controllerGeneral);
        List<TipoIntervencionDTO> tipoIntervencion = tipoIntervencionController.obtenerTodosLosTiposIntervencion();
        comboBoxModelTipoIntervencion.addElement("");
        for (TipoIntervencionDTO tipointervencionDTO : tipoIntervencion) {
            comboBoxModelTipoIntervencion.addElement(String.valueOf(tipointervencionDTO.getNomTipoIntervencion()));
        }

        comboTipoIntervencion.setModel(comboBoxModelTipoIntervencion);

        comboTipoIntervencion.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                preRellenarCampos();
            }
        });

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboTipoIntervencion.getSelectedItem() != null && !comboTipoIntervencion.getSelectedItem().toString().isEmpty()){
                    try {
                        modificarDatos();
                        mostrarMensaje("¡Tipo de intervención modificado correctamente!");
                        volverPrincipal();
                    }catch (ServiciosException ex) {
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error al modificar un tipo de intervención: \n" + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un tipo de intervención al modificar.");
                }
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerGeneral.mostrarMenuInterfaz(true);
                //mostrarMensaje("MODIFICACIÓN CANCELADA");
                panel.setVisible(false);
            }
        });
    }

    public void preRellenarCampos(){
        TipoIntervencionController tipoIntervencionController = new TipoIntervencionController(controllerGeneral);
        if (comboTipoIntervencion.getSelectedItem() != null && !comboTipoIntervencion.getSelectedItem().toString().isEmpty()){
            TipoIntervencionDTO tipoIntervencionDTO = tipoIntervencionController.obtenerTodosLosTipoIntervencionPorNombre(comboTipoIntervencion.getSelectedItem().toString());
            comboBoxEstado.setSelectedItem(tipoIntervencionDTO.getEstado().toString());
        }
    }

    public void modificarDatos() throws ServiciosException {
        TipoIntervencionController tipoIntervencionController = new TipoIntervencionController(controllerGeneral);
        TipoIntervencionDTO tipoIntervencionSeleccionado = tipoIntervencionController.obtenerTodosLosTipoIntervencionPorNombre(comboTipoIntervencion.getSelectedItem().toString());
        TipoIntervencionDTO tipoIntervencionDTO = new TipoIntervencionDTO();

        tipoIntervencionDTO.setIdTipointervencion(tipoIntervencionController.obtenerTodosLosTipoIntervencionPorNombre(comboTipoIntervencion.getSelectedItem().toString()).getIdTipointervencion());
        tipoIntervencionDTO.setEstado(EstadoGeneral.valueOf(comboBoxEstado.getSelectedItem().toString()));
        tipoIntervencionDTO.setNomTipoIntervencion(tipoIntervencionSeleccionado.getNomTipoIntervencion());


        // ACTUALIZA
        if (comboBoxEstado.getSelectedItem().toString().equals("ACTIVO")){
            tipoIntervencionController.activarTipoIntervencion(tipoIntervencionDTO.getIdTipointervencion());
        }else {
            tipoIntervencionController.eliminarTipoIntervencion(tipoIntervencionDTO.getIdTipointervencion());
        }

        if (mensajeConfirmacion()){
            controllerGeneral.mostrarMenuInterfaz(true);
        }else {
            mostrarMensaje("Se ha cancelado la modificación de tipo intervención con éxito.");
        }
    }
    public void setVisible(boolean visible){
        frame.setVisible(visible);
    }

    public JPanel getPanel(){
        return panel;
    }

    public void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public void volverPrincipal(){
        controllerGeneral.mostrarMenuInterfaz(true);
        panel.setVisible(false);
    }

    public void actualizarComboBox() throws SQLException, ServiciosException {
        DefaultComboBoxModel<String> comboBoxModelTipo = new DefaultComboBoxModel<>();
        TipoIntervencionController tipoIntervencionController = new TipoIntervencionController(controllerGeneral);
        List<TipoIntervencionDTO> tipoIntervencionDTOList = tipoIntervencionController.obtenerTodosLosTipoIntervencionActivos();
        for (TipoIntervencionDTO tipoIntervencionDTO : tipoIntervencionDTOList) {
            comboBoxModelTipo.addElement(String.valueOf(tipoIntervencionDTO.getNomTipoIntervencion()));
        }
        comboTipoIntervencion.setModel(comboBoxModelTipo);
    }
    public Boolean mensajeConfirmacion() {
        Object[] opciones = {"Confirmar", "Cancelar"};
        int respuesta = JOptionPane.showOptionDialog(null, "¿Desea confirmar la acción?", "Confirmación", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        return respuesta == JOptionPane.YES_OPTION;
    }
}

