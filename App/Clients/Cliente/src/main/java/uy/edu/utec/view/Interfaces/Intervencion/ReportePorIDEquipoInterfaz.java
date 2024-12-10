package uy.edu.utec.view.Interfaces.Intervencion;

import com.toedter.calendar.JDateChooser;
import uy.edu.utec.controller.ControllerGeneral;
import uy.edu.utec.controller.EquipoController;
import uy.edu.utec.controller.IntervencionController;
import uy.edu.utec.servers.dtos.EquipoDTO;
import uy.edu.utec.servers.dtos.IntervencionDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.servers.utils.DateMapper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReportePorIDEquipoInterfaz {
    private JPanel panel;
    private JTextPane textPaneIntervencion;
    private JButton buttonBuscar;
    private JButton buttonCancelar;
    private JComboBox<String> comboBoxEquipo;
    private JDateChooser JDateChooserHasta;
    private JDateChooser JDateChooserDesde;
    private JButton limpiarButton;
    private JScrollPane scrollPane;
    private JTable tableIntervenciones;

    private ControllerGeneral controllerGeneral;
    private JFrame frame;

    public ReportePorIDEquipoInterfaz(ControllerGeneral controllerGeneral) throws SQLException, ServiciosException {
        this.controllerGeneral = controllerGeneral;
        frame = new JFrame("Reporte Intervencion por Tipo.");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        comboBoxEquipo.setSelectedItem("");
        frame.setSize(600, 500);

        Calendar currentDate = Calendar.getInstance();
        JDateChooserHasta.setMaxSelectableDate(currentDate.getTime());
        JDateChooserDesde.setMaxSelectableDate(currentDate.getTime());

        DefaultComboBoxModel<String> comboBoxModelEquipo = new DefaultComboBoxModel<>();
        EquipoController equipoController = new EquipoController(controllerGeneral);
        List<EquipoDTO> equipoDTOS = equipoController.obtenerTodosLosEquipos();
        comboBoxModelEquipo.addElement("");
        for (EquipoDTO equipoDTO : equipoDTOS) {
            comboBoxModelEquipo.addElement(equipoDTO.getIdEquipo().toString());
        }

        comboBoxEquipo.setModel(comboBoxModelEquipo);

        //JTable

        DefaultTableModel modeloTabla = new DefaultTableModel(
                new Object[]{"id-Intervencion", "Usuario", "Equipo", "Tipo Intervencion", "Motivo",
                        "Comentario", "Fecha"}, 0);

        tableIntervenciones.setRowHeight(40);
        tableIntervenciones.setAutoCreateRowSorter(true);
        tableIntervenciones.setRowMargin(5);
        tableIntervenciones.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        scrollPane.setViewportView(tableIntervenciones);

        tableIntervenciones.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        tableIntervenciones.setModel(modeloTabla);
        buttonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                IntervencionController intervencionController = new IntervencionController(controllerGeneral);
                List<IntervencionDTO> intervencionDTOS = new ArrayList<>();
                Date fechaDesde = JDateChooserDesde.getDate();
                Date fechaHasta = JDateChooserHasta.getDate();

                try {
                    if (fechaDesde == null || fechaHasta == null) {
                        throw new ServiciosException("Debe ingresar ambas fechas.");
                    }

                    if (fechaDesde.after(fechaHasta)) {
                        JOptionPane.showMessageDialog(null, "La fecha desde no puede ser mayor que la fecha hasta.", "Error:", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    LocalDate fechaDesdeL = DateMapper.convertDateToLocalDate(fechaDesde);
                    LocalDate fechaHastaL = DateMapper.convertDateToLocalDate(fechaHasta);

                    if (comboBoxEquipo.getSelectedItem().toString().isEmpty()){
                        for(EquipoDTO equipoDTO : equipoController.obtenerTodosLosEquipos()){
                            for(IntervencionDTO intervencionDTO : intervencionController.obtenerIntervencionesPorEquipoID(equipoDTO.getIdEquipo(), fechaDesdeL, fechaHastaL))
                            intervencionDTOS.add(intervencionDTO);
                        }
                    }else{
                        Integer equipoId = Integer.parseInt((String) comboBoxEquipo.getSelectedItem());
                        EquipoDTO equipoDTO = controllerGeneral.getEquipoController().buscarPorId(equipoId);
                        intervencionDTOS = intervencionController.obtenerIntervencionesPorEquipoID(equipoDTO.getIdEquipo(), fechaDesdeL, fechaHastaL);
                    }

                    mostrarListadoIntervenciones(intervencionDTOS);
                } catch (SQLException | ServiciosException e) {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error: \n" + e.getMessage());
                }
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
                ((DefaultTableModel) tableIntervenciones.getModel()).setRowCount(0);
            }
        });
    }
    private void mostrarListadoIntervenciones(List<IntervencionDTO> intervencionDTOS) throws SQLException {
        ((DefaultTableModel) tableIntervenciones.getModel()).setRowCount(0);
        for (IntervencionDTO intervencionDTO : intervencionDTOS) {
            Object[] filaIntervencion = {
                    intervencionDTO.getIdIntervencion(),
                    controllerGeneral.getUsuarioController().buscarPorId(intervencionDTO.getUsuarioId()).getNom_usuario(),
                    controllerGeneral.getEquipoController().buscarPorId(intervencionDTO.getEquipoId()).getNomEquipo(),
                    intervencionDTO.getTipoIntervencion().getNomTipoIntervencion(),
                    intervencionDTO.getMotivo(),
                    intervencionDTO.getComentario(),
                    intervencionDTO.getFechaHora()

            };
            ((DefaultTableModel)  tableIntervenciones.getModel()).addRow(filaIntervencion);
        }

    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    public JPanel getPanel() {
        return panel;
    }

    private void createUIComponents() {
        JDateChooserHasta = new JDateChooser();
        JDateChooserHasta.setDateFormatString("dd/MM/yyyy");
        JDateChooserDesde = new JDateChooser();
        JDateChooserDesde.setDateFormatString("dd/MM/yyyy");
    }
}
