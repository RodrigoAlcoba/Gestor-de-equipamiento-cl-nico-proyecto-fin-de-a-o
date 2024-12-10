package uy.edu.utec.view.Interfaces.TipoIntervencion;

import uy.edu.utec.controller.ControllerGeneral;
import uy.edu.utec.controller.TipoIntervencionController;
import uy.edu.utec.servers.dtos.TipoIntervencionDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListadoTipoIntervencionInterfaz {

    private JButton buttonCancelar;
    private JButton buttonBuscar;
    private JPanel panel;
    private JTextPane textPaneTipo;
    private JTextField textFieldTipo;
    private JRadioButton cbEstado;
    private JComboBox comboBoxEstado;
    private JRadioButton cbTipo;
    private JComboBox cbTipoIntervencion;
    private JButton limpiarButton;
    private JScrollPane scrollPane;
    private JTable tableTipoIntervencion;
    private JRadioButton radioButtonNombre;
    private ControllerGeneral controllerGeneral;
    private ButtonGroup radioButtons = new ButtonGroup();
    private JFrame frame;
    public ListadoTipoIntervencionInterfaz(ControllerGeneral controllerGeneral) {
        this.controllerGeneral = controllerGeneral;
        frame = new JFrame("Listado Tipo Intervención");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 500);

        DefaultComboBoxModel<String> comboBoxModelTipo = new DefaultComboBoxModel<>();
        TipoIntervencionController tipoIntervencionController = new TipoIntervencionController(controllerGeneral);
        List<TipoIntervencionDTO> tipoIntervencionDTOList = tipoIntervencionController.obtenerTodosLosTiposIntervencion();
        for (TipoIntervencionDTO tipoIntervencionDTO : tipoIntervencionDTOList){
            comboBoxModelTipo.addElement(String.valueOf(tipoIntervencionDTO.getNomTipoIntervencion()));
        }
        cbTipoIntervencion.setModel(comboBoxModelTipo);

        radioButtons.add(cbTipo);
        radioButtons.add(cbEstado);

        //JTable

        DefaultTableModel modeloTabla = new DefaultTableModel(
                new Object[]{"Id", "Nombre", "Estado"}, 0);

        tableTipoIntervencion.setRowHeight(40);
        tableTipoIntervencion.setAutoCreateRowSorter(true);
        tableTipoIntervencion.setRowMargin(5);
        tableTipoIntervencion.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        scrollPane.setViewportView(tableTipoIntervencion);

        tableTipoIntervencion.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        tableTipoIntervencion.setModel(modeloTabla);
        buttonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerGeneral.mostrarMenuInterfaz(true);
                panel.setVisible(false);
            }
        });

        buttonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TipoIntervencionController tipoIntervencionController = new TipoIntervencionController(controllerGeneral);
                List<TipoIntervencionDTO> tipoIntervencionDTOList = new ArrayList<>();
                if (cbEstado.isSelected()){
                    if (comboBoxEstado.getSelectedItem().toString().equals("ACTIVO")){
                        tipoIntervencionDTOList = tipoIntervencionController.obtenerTodosLosTipoIntervencionActivos();
                    }else if (comboBoxEstado.getSelectedItem().toString().equals("ELIMINADO")){
                        tipoIntervencionDTOList = tipoIntervencionController.obtenerTodosLoTipoIntervencionEliminados();
                    }
                }else if (cbTipo.isSelected()) {
                    String tipo = (String) comboBoxModelTipo.getSelectedItem();
                    tipoIntervencionDTOList = tipoIntervencionController.obtenerTodosLosTipoIntervencionPorTipo(tipo);
                }else{
                    tipoIntervencionDTOList = tipoIntervencionController.obtenerTodosLosTiposIntervencion();
                }
                mostrarListadoTipo(tipoIntervencionDTOList);
            }
        });

        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                radioButtons.clearSelection();
                ((DefaultTableModel) tableTipoIntervencion.getModel()).setRowCount(0);
            }
        });
    }

    private void mostrarListadoTipo(List<TipoIntervencionDTO> tipoIntervencionDTOList) {
        ((DefaultTableModel) tableTipoIntervencion.getModel()).setRowCount(0);
        for (TipoIntervencionDTO tipoIntervencionDTO : tipoIntervencionDTOList) {
            Object[] filaTipoIntervencion = {
                    tipoIntervencionDTO.getIdTipointervencion(),
                    tipoIntervencionDTO.getNomTipoIntervencion(),
                    tipoIntervencionDTO.getEstado()

            };
            ((DefaultTableModel)  tableTipoIntervencion.getModel()).addRow(filaTipoIntervencion);
        }

    }
    public void setVisible(boolean visible){
        frame.setVisible(visible);
    }

    public void actualizarComboBox() throws SQLException, ServiciosException {
        DefaultComboBoxModel<String> comboBoxModelTipo = new DefaultComboBoxModel<>();
        TipoIntervencionController tipoIntervencionController = new TipoIntervencionController(controllerGeneral);
        List<TipoIntervencionDTO> tipoIntervencionDTOList = tipoIntervencionController.obtenerTodosLosTipoIntervencionActivos();
        for (TipoIntervencionDTO tipoIntervencionDTO : tipoIntervencionDTOList) {
            comboBoxModelTipo.addElement(String.valueOf(tipoIntervencionDTO.getNomTipoIntervencion()));
        }
        cbTipoIntervencion.setModel(comboBoxModelTipo);
    }

    public JPanel getPanel(){
        return panel;
    }
}

