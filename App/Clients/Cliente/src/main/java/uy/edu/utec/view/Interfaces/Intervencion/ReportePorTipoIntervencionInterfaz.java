package uy.edu.utec.view.Interfaces.Intervencion;

import com.toedter.calendar.JDateChooser;
import uy.edu.utec.controller.ControllerGeneral;
import uy.edu.utec.controller.IntervencionController;
import uy.edu.utec.controller.TipoIntervencionController;
import uy.edu.utec.servers.dtos.IntervencionDTO;
import uy.edu.utec.servers.dtos.TipoIntervencionDTO;
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

public class ReportePorTipoIntervencionInterfaz {
    private JPanel panel;
    private JButton buttonBuscar;
    private JButton buttonCancelar;
    private JComboBox comboBoxTipoIntervencion;
    private JDateChooser JDateChooserHasta;
    private JScrollPane b;
    private JTable tableIntervencionesCantidad;
    private JButton limpiarButton;
    private JTable tableIntervenciones;;
    private JDateChooser JDateChooserDesde;
    private JScrollPane scrollPane;
    private ControllerGeneral controllerGeneral;
    private JFrame frame;

    public ReportePorTipoIntervencionInterfaz(ControllerGeneral controllerGeneral) {
        this.controllerGeneral = controllerGeneral;
        frame = new JFrame("Reporte Intervencion por Tipo.");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 500);

        Calendar currentDate = Calendar.getInstance();
        JDateChooserHasta.setMaxSelectableDate(currentDate.getTime());
        JDateChooserDesde.setMaxSelectableDate(currentDate.getTime());

        DefaultComboBoxModel<String>  comboBoxModelTipo = new DefaultComboBoxModel<>();
        TipoIntervencionController tipoIntervencionController = new TipoIntervencionController(controllerGeneral);
        List<TipoIntervencionDTO> tipoIntervencionDTOS = tipoIntervencionController.obtenerTodosLosTipoIntervencionActivos();
        comboBoxModelTipo.addElement("");
        for (TipoIntervencionDTO tipoIntervencionDTO : tipoIntervencionDTOS){
            comboBoxModelTipo.addElement(String.valueOf(tipoIntervencionDTO.getNomTipoIntervencion()));
        }
        comboBoxTipoIntervencion.setModel(comboBoxModelTipo);

        //JTable
        DefaultTableModel modeloTabla = new DefaultTableModel(
                new Object[]{"id-Intervencion", "Usuario", "Equipo", "Tipo Intervencion", "Motivo",
                        "Comentario", "Fecha"}, 0);

        tableIntervenciones.setRowHeight(40);
        tableIntervenciones.setAutoCreateRowSorter(true);
        tableIntervenciones.setRowMargin(5);
        tableIntervenciones.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);


        tableIntervenciones.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        tableIntervenciones.setModel(modeloTabla);

        //tableIntervencionesCantidad.setVisible(false);
       /* //JTable
        DefaultTableModel modeloTablaCantidad = new DefaultTableModel(
                new Object[]{"Tipo de intervención", "Cantidad"}, 0);

        tableIntervencionesCantidad.setRowHeight(40);
        tableIntervencionesCantidad.setAutoCreateRowSorter(true);
        tableIntervencionesCantidad.setRowMargin(5);

        tableIntervencionesCantidad.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        tableIntervencionesCantidad.setModel(modeloTablaCantidad);
        */
        buttonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    IntervencionController intervencionController = new IntervencionController(controllerGeneral);
                    List<IntervencionDTO> intervencionDTOS = new ArrayList<>();
                    Date fechaDesde = JDateChooserDesde.getDate();
                    Date fechaHasta = JDateChooserHasta.getDate();
                    if (fechaDesde == null) {
                        throw new ServiciosException("Debe ingresar una fecha de fecha Desde.");
                    }
                    if (fechaHasta == null) {
                        throw new ServiciosException("Debe ingresar una fecha de fecha Hasta.");
                    }
                    LocalDate fechaDesdeL = DateMapper.convertDateToLocalDate(fechaDesde);
                    LocalDate fechaHastaL = DateMapper.convertDateToLocalDate(fechaHasta);


                if (fechaDesde.after(fechaHasta)) {
                    JOptionPane.showMessageDialog(null, "La fecha no puede ser nula - La fecha desde no puede ser mayor que la fecha hasta", "Error:", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if(comboBoxTipoIntervencion.getSelectedItem().equals("")){
                    for (IntervencionDTO intervencion : intervencionController.obtenerTodasLasIntervenciones()){
                        intervencionDTOS.add(intervencion);
                        //mostrarCantidadPorTipos(intervencionDTOS);
                    }
                }else{
                    Integer tipoIntervencion =
                            controllerGeneral.getTipoIntervencionController().obtenerTodosLosTipoIntervencionPorNombre(comboBoxTipoIntervencion.getSelectedItem().toString()).getIdTipointervencion();
                    intervencionDTOS = intervencionController.obtenerIntervencionesPorTipoYFechas(tipoIntervencion, fechaDesdeL, fechaHastaL);
                    //mostrarCantidadPorTipo(controllerGeneral.getTipoIntervencionController().buscarPorId
                    // (tipoIntervencion));
                }


                mostrarListadoIntervenciones(intervencionDTOS);
                } catch (ServiciosException | SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error: \n" + ex.getMessage());
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

    /*private void mostrarCantidadPorTipos(List<IntervencionDTO> intervencionDTOS) throws SQLException {
        ((DefaultTableModel) tableIntervencionesCantidad.getModel()).setRowCount(0);
        List<TipoIntervencionDTO> tipoIntervencions =
                controllerGeneral.getTipoIntervencionController().obtenerTodosLosTiposIntervencion();
        for (TipoIntervencionDTO tipoIntervencionDTO : tipoIntervencions) {
            Object[] filaIntervencion = {tipoIntervencionDTO.getNomTipoIntervencion(),
                    controllerGeneral.getIntervencionController().obtenerIntervencionesPorTipo(tipoIntervencionDTO.getNomTipoIntervencion())
            };
            ((DefaultTableModel)  tableIntervencionesCantidad.getModel()).addRow(filaIntervencion);
        }
    }

    private void mostrarCantidadPorTipo(TipoIntervencionDTO tipoIntervencionDTO){
        ((DefaultTableModel) tableIntervencionesCantidad.getModel()).setRowCount(0);
            Object[] filaIntervencion = {tipoIntervencionDTO.getNomTipoIntervencion(),
                    controllerGeneral.getIntervencionController().obtenerIntervencionesPorTipo(tipoIntervencionDTO.getNomTipoIntervencion())
            };
            ((DefaultTableModel)  tableIntervencionesCantidad.getModel()).addRow(filaIntervencion);
        }*/




    public void setVisible(boolean visible){
        frame.setVisible(visible);
    }
    public JPanel getPanel(){
        return panel;
    }

    private void createUIComponents() {
        JDateChooserHasta = new JDateChooser();
        JDateChooserHasta.setDateFormatString("dd/MM/yyyy");
        JDateChooserDesde = new JDateChooser();
        JDateChooserDesde.setDateFormatString("dd/MM/yyyy");
    }
}