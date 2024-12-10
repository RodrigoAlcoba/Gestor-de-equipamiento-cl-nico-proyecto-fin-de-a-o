package uy.edu.utec.view.Interfaces.Intervencion;

import uy.edu.utec.controller.ControllerGeneral;
import uy.edu.utec.controller.IntervencionController;
import uy.edu.utec.controller.SeguimientoIntervencionController;
import uy.edu.utec.controller.UsuarioController;
import uy.edu.utec.servers.dtos.IntervencionDTO;
import uy.edu.utec.servers.dtos.SeguimientoIntervencionDTO;
import uy.edu.utec.servers.dtos.UsuarioDTO;
import uy.edu.utec.servers.enums.EstadoUsuario;
import uy.edu.utec.servers.excepciones.ServiciosException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReporteSeguimientoPorIDInterfaz {
    private JPanel panel;
    private JTextPane textPaneIntervencion;
    private JButton buttonBuscar;
    private JButton buttonCancelar;
    private JComboBox comboBoxIDIntervencion;
    private JScrollPane scrollPane;
    private JTable tableSeguimientos;
    private JButton limpiarButton;
    private JRadioButton cbIntervencion;
    private ControllerGeneral controllerGeneral;
    private JFrame frame;
    private ButtonGroup radioButtons = new ButtonGroup();


    public ReporteSeguimientoPorIDInterfaz(ControllerGeneral controllerGeneral) {
        this.controllerGeneral = controllerGeneral;
        frame = new JFrame("Reporte Intervencion por Tipo.");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 500);
        radioButtons.add(cbIntervencion);

        DefaultComboBoxModel<String>  comboBoxModelIntervencion = new DefaultComboBoxModel<>();
        IntervencionController intervencionController = new IntervencionController(controllerGeneral);
        List<IntervencionDTO> intervencionDTOS = intervencionController.obtenerTodasLasIntervenciones();
        for (IntervencionDTO intervencionDTO : intervencionDTOS){
            comboBoxModelIntervencion.addElement(String.valueOf(intervencionDTO.getIdIntervencion()));
        }
        comboBoxIDIntervencion.setModel(comboBoxModelIntervencion);

        //JTable

        DefaultTableModel modeloTabla = new DefaultTableModel(
                new Object[]{"id-Seguimiento", "Tipo Intervencion", "id-Intervencion", "Motivo",
                        "Observaciones"}, 0);

        tableSeguimientos.setRowHeight(40);
        tableSeguimientos.setAutoCreateRowSorter(true);
        tableSeguimientos.setRowMargin(5);
        tableSeguimientos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        scrollPane.setViewportView(tableSeguimientos);

        tableSeguimientos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        tableSeguimientos.setModel(modeloTabla);
        buttonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SeguimientoIntervencionController seguimientoIntervencionController = new SeguimientoIntervencionController(controllerGeneral);
                List<SeguimientoIntervencionDTO> seguimientoIntervencionDTOS = new ArrayList<>();
                if(cbIntervencion.isSelected()){
                    IntervencionDTO intervencionDTO = controllerGeneral.getIntervencionController().buscarPorId(Integer.valueOf((String) comboBoxIDIntervencion.getSelectedItem()));
                    Integer intervencion = intervencionDTO.getIdIntervencion();

                    seguimientoIntervencionDTOS = seguimientoIntervencionController.obtenerTodosLosSeguimientosDeUnaIntervencionPorID(intervencion);
                }else{
                    for(IntervencionDTO intervencion : intervencionDTOS){
                        for (SeguimientoIntervencionDTO seguimientoIntervencionDTO :seguimientoIntervencionController.obtenerTodosLosSeguimientosDeUnaIntervencionPorID(intervencion.getIdIntervencion()) )
                        seguimientoIntervencionDTOS.add(seguimientoIntervencionDTO);
                    }
                }

                mostrarListadoSeguimientos(seguimientoIntervencionDTOS);
            }
        });
        buttonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controllerGeneral.mostrarMenuInterfaz(true);
                panel.setVisible(false);
            }
        });
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                radioButtons.clearSelection();
                ((DefaultTableModel) tableSeguimientos.getModel()).setRowCount(0);
            }
        });
        comboBoxIDIntervencion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    actualizarComboBox();
                } catch (SQLException | ServiciosException  ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
        private void mostrarListadoSeguimientos(List<SeguimientoIntervencionDTO> seguimientoIntervencionDTOS){
            ((DefaultTableModel) tableSeguimientos.getModel()).setRowCount(0);
            for (SeguimientoIntervencionDTO seguimientoIntervencionDTO : seguimientoIntervencionDTOS) {
                Object[] filaSeguimiento = {
                        seguimientoIntervencionDTO.getSeguimientoIntervencionId(),
                        seguimientoIntervencionDTO.getTipoIntervencion(),
                        seguimientoIntervencionDTO.getIntervencionId(),
                        seguimientoIntervencionDTO.getMotivo(),
                        seguimientoIntervencionDTO.getObservaciones()

            };
                ((DefaultTableModel)  tableSeguimientos.getModel()).addRow(filaSeguimiento);
            }

        }



    public void setVisible(boolean visible){
        frame.setVisible(visible);
    }
    public JPanel getPanel(){
        return panel;
    }

    public void actualizarComboBox() throws SQLException, ServiciosException {
        DefaultComboBoxModel<String>  comboBoxModelIntervencion = new DefaultComboBoxModel<>();
        IntervencionController intervencionController = new IntervencionController(controllerGeneral);
        List<IntervencionDTO> intervencionDTOS = intervencionController.obtenerTodasLasIntervenciones();
        for (IntervencionDTO intervencionDTO : intervencionDTOS){
            comboBoxModelIntervencion.addElement(String.valueOf(intervencionDTO.getIdIntervencion()));
        }
        comboBoxIDIntervencion.setModel(comboBoxModelIntervencion);
    }
}