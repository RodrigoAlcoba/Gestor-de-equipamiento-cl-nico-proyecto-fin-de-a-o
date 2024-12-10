package uy.edu.utec.view.Interfaces.Ubicacion;

import com.toedter.calendar.JDateChooser;
import uy.edu.utec.controller.*;
import uy.edu.utec.servers.dtos.*;
import uy.edu.utec.servers.excepciones.ServiciosException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ListadoMovimientosEquipoInterfaz {
    private JButton buttonCancelar;
    private JButton buttonBuscar;
    private JPanel panel;
    private JComboBox comboBoxEquipo;
    private JTextPane textPaneMovimiento;
    private JButton limpiarButton;
    private JComboBox comboBoxMovEquipo;
    private JComboBox comboBoxInstitucion;
    private JComboBox comboBoxSector;
    private JComboBox comboBoxUsuario;
    private JDateChooser JDateChooserDesde;
    private JDateChooser JDateChooserHasta;
    private JScrollPane scrollPane;
    private JTable tableMovEquipos;
    private JRadioButton cbInstitucion;
    private JRadioButton cbSector;
    private JRadioButton cbUsuario;
    private JRadioButton cbFechaDesde;
    private JRadioButton cbFechaHasta;
    private JRadioButton cbEquipo;
    private JRadioButton cbMovEquipo;

    private ControllerGeneral controllerGeneral;
    private ButtonGroup radioButtons = new ButtonGroup();
    private JFrame frame;
    public ListadoMovimientosEquipoInterfaz(ControllerGeneral controllerGeneral) throws ServiciosException, SQLException {
        this.controllerGeneral = controllerGeneral;
        frame = new JFrame("Listado Tipo Intervención");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 500);

        //cb
        radioButtons.add(cbInstitucion);
        radioButtons.add(cbSector);
        radioButtons.add(cbUsuario);
        radioButtons.add(cbFechaDesde);
        radioButtons.add(cbFechaHasta);
        radioButtons.add(cbEquipo);
        radioButtons.add(cbMovEquipo);

        //Equipos
        EquipoController equipoController = new EquipoController(controllerGeneral);
        List<EquipoDTO> equipoDTOList = equipoController.obtenerTodosLosEquipos();
        DefaultComboBoxModel<String> comboBoxModelNomEquipo = new DefaultComboBoxModel<>();
        for (EquipoDTO equipoDTO : equipoDTOList){
            comboBoxModelNomEquipo.addElement(equipoDTO.getNomEquipo());
        }
        comboBoxEquipo.setModel(comboBoxModelNomEquipo);

        //Identificador MovEquipo
        DefaultComboBoxModel comboBoxModelMovimiento = new DefaultComboBoxModel();
        MovEquipoController movEquipoController = new MovEquipoController(controllerGeneral);
        List<MovEquipoDTO> movEquipoDTOS = movEquipoController.obtenerTodosMovDeEquipo();
        for (MovEquipoDTO movEquipoDTO : movEquipoDTOS){
            comboBoxModelMovimiento.addElement(String.valueOf(movEquipoDTO.getEquipoId()));
        }
        comboBoxMovEquipo.setModel(comboBoxModelMovimiento);

        //Intituciones
        DefaultComboBoxModel<String> comboBoxModelInstitucion = new DefaultComboBoxModel<>();
        InstitucionController institucionController = new InstitucionController(controllerGeneral);
        List<InstitucionDTO> institucionDTOList = institucionController.mostrarTodasInstituciones();
        for(InstitucionDTO institucionDTO: institucionDTOList){
            comboBoxModelInstitucion.addElement(String.valueOf(institucionDTO.getNom_Institucion()));
        }

        comboBoxInstitucion.setModel(comboBoxModelInstitucion);

        //Sectores
        DefaultComboBoxModel<String> comboBoxModelSector = new DefaultComboBoxModel<>();
        SectorController sectorController = new SectorController(controllerGeneral);
        List<SectorDTO> sectorDTOS = sectorController.obtenerTodosLosSectores();
        for (SectorDTO sectorDTO : sectorDTOS){
            comboBoxModelSector.addElement(String.valueOf(sectorDTO.getNombre()));
        }
        comboBoxSector.setModel(comboBoxModelSector);

        //Usuarios
        DefaultComboBoxModel<String> comboBoxModelNombre = new DefaultComboBoxModel<>();
        UsuarioController usuarioController = new UsuarioController(controllerGeneral);
        List<UsuarioDTO> usuarios = usuarioController.obtenerTodosLosUsuarios();
        for(UsuarioDTO usuarioDTO : usuarios){
            comboBoxModelNombre.addElement(String.valueOf(usuarioDTO.getNom_usuario()));
        }

        comboBoxUsuario.setModel(comboBoxModelNombre);

        //JTable
        DefaultTableModel modeloTabla = new DefaultTableModel(
                new Object[]{ "Id-Movimiento","Equipo", "Institución",
                        "Sector", "Usuario", "Fecha desde", "Fecha hasta",
                        "Observaciones"}, 0);

        tableMovEquipos.setRowHeight(40);
        tableMovEquipos.setAutoCreateRowSorter(true);
        tableMovEquipos.setRowMargin(5);
        tableMovEquipos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        scrollPane.setViewportView(tableMovEquipos);

        tableMovEquipos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        tableMovEquipos.setModel(modeloTabla);


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
                MovEquipoController movEquipoController1 = new MovEquipoController(controllerGeneral);
                List<MovEquipoDTO> movEquipoDTOS1 = new ArrayList<>();
                if (cbEquipo.isSelected()){
                    try {
                        movEquipoDTOS1 =
                                movEquipoController1.obtenerTodosMovDeEquipoByEuipoId(controllerGeneral.getEquipoController().obtenerPorNombre((String) comboBoxEquipo.getSelectedItem()).getIdEquipo());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }else if(cbMovEquipo.isSelected()){
                            movEquipoDTOS1.add(movEquipoController1.obtenerMovEquipoPorId(Integer.parseInt(comboBoxMovEquipo.getSelectedItem().toString())));
                }else if(cbInstitucion.isSelected()){
                    movEquipoDTOS1 =
                            movEquipoController1.obtenerMovEquiposPorInstitucion(controllerGeneral.getInstitucionController().obtenerInstitucionPorNombre(comboBoxInstitucion.getSelectedItem().toString()).getId());
                }else if(cbSector.isSelected()){
                    movEquipoDTOS1 =
                            movEquipoController1.obtenerMovEquiposPorSector(controllerGeneral.getSectorController().obtenerSectorPorNombre(comboBoxSector.getSelectedItem().toString()).getId());
                }else if(cbUsuario.isSelected()){
                    movEquipoDTOS1 =
                            movEquipoController1.obtenerMovEquiposPorUsuario(controllerGeneral.getUsuarioController().obtenerPorNombreUsuario(comboBoxUsuario.getSelectedItem().toString()).getID_Usuario());
                }else if(cbFechaDesde.isSelected()){
                    movEquipoDTOS1 = movEquipoController1.obtenerMovEquiposPorFecDesde(JDateChooserDesde.getDate());
                }else if(cbFechaHasta.isSelected()){
                    movEquipoDTOS1 = movEquipoController1.obtenerMovEquiposPorFecHasta(JDateChooserHasta.getDate());
                }else{
                    movEquipoDTOS1 = movEquipoController1.obtenerTodosMovDeEquipo();
                }
                try {
                    mostrarListadoMovEquipos(movEquipoDTOS1);
                } catch (ServiciosException e) {
                    throw new RuntimeException(e);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                radioButtons.clearSelection();
                ((DefaultTableModel) tableMovEquipos.getModel()).setRowCount(0);
            }
        });
    }

    public void mostrarListadoMovEquipos(List<MovEquipoDTO> movEquipoDTOS) throws ServiciosException, SQLException {
        ((DefaultTableModel) tableMovEquipos.getModel()).setRowCount(0);

        for(MovEquipoDTO movEquipoDTO: movEquipoDTOS){
            Object[] filaMovEquipo = {
                    movEquipoDTO.getIdMovEquipo(),
                    controllerGeneral.getEquipoController().buscarPorId(movEquipoDTO.getEquipoId()).getNomEquipo(),
                    controllerGeneral.getInstitucionController().obtenerInstitucionPorId(
                            controllerGeneral.getUbicacionController().obtenerUbicacionPorId(movEquipoDTO.getUbicacionId()).getInstitucionId()).getNom_Institucion(),
                   controllerGeneral.getSectorController().buscarPorId(controllerGeneral.getUbicacionController().obtenerUbicacionPorId(movEquipoDTO.getUbicacionId()).getSectorId()).getNombre(),
                    controllerGeneral.getUsuarioController().buscarPorId(movEquipoDTO.getUsuarioId()).getNom_usuario(),
                    movEquipoDTO.getFecEntrada(),
                    movEquipoDTO.getFecSalida(),
                    movEquipoDTO.getObservaciones()
            };
            ((DefaultTableModel)  tableMovEquipos.getModel()).addRow(filaMovEquipo);

        }

    }
    public void setVisible(boolean visible){
        frame.setVisible(visible);
    }

    public JPanel getPanel(){
        return panel;
    }

    private void createUIComponents() {
        JDateChooserDesde = new JDateChooser();
        JDateChooserDesde.setDateFormatString("dd/MM/yyyy");

        JDateChooserHasta = new JDateChooser();
        JDateChooserHasta.setDateFormatString("dd/MM/yyyy");
    }
}

