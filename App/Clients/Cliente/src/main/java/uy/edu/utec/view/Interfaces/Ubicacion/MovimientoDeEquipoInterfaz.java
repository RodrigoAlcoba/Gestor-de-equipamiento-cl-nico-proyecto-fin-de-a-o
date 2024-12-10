package uy.edu.utec.view.Interfaces.Ubicacion;

import com.toedter.calendar.JDateChooser;
import uy.edu.utec.controller.*;
import uy.edu.utec.servers.dtos.*;
import uy.edu.utec.servers.dtos.creacionales.CrearMovEquipoDTO;
import uy.edu.utec.servers.entidades.MovEquipo;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.servers.utils.DateMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovimientoDeEquipoInterfaz {
    private JTextField inputPiso;
    private JTextField inputCama;
    private JTextArea textAreaObservaciones;
    private JButton ingresarButton;
    private JButton cancelarButton;
    private JPanel panel;
    private JComboBox comboBoxInstitucion;
    private JComboBox comboBoxSector;
    private JComboBox comboBoxUsuario;
    private JDateChooser JDateChooser1;
    private JComboBox comboBoxUbicacion;
    private JTextField textIdEquipo;
    private JComboBox comboBoxNomEquipo;
    private JTextField textFieldNum;
    //private JComboBox comboBoxNumero;

    List<Component> componentesNomEquipo = new ArrayList<>();
    List<Component> componentesNumUbi = new ArrayList<>();

    private ControllerGeneral controllerGeneral;
    private JFrame frame;
    public MovimientoDeEquipoInterfaz(ControllerGeneral controllerGeneral) throws SQLException, ServiciosException {
        this.controllerGeneral = controllerGeneral;
        frame = new JFrame("Movimiento equipo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 500);

        comboBoxUbicacion.setEnabled(false);
        textFieldNum.setEnabled(false);

        componentesNomEquipo.add(textIdEquipo);
        setEnabled1(false);

        componentesNumUbi.add(textFieldNum);
        setEnabled2(false);

        JDateChooser1.setMaxSelectableDate(new java.util.Date());

        DefaultComboBoxModel<String> comboBoxModelNomEquipo = new DefaultComboBoxModel<>();
        EquipoController equipoController = new EquipoController(controllerGeneral);
        List<EquipoDTO> equipoDTOList = equipoController.obtenerTodosLosEquipos();
        comboBoxModelNomEquipo.addElement("");
        for (EquipoDTO equipoDTO : equipoDTOList){
            comboBoxModelNomEquipo.addElement(equipoDTO.getNomEquipo());
        }
        comboBoxNomEquipo.setModel(comboBoxModelNomEquipo);

        DefaultComboBoxModel<String> comboBoxModeInstitucion = new DefaultComboBoxModel<>();
        InstitucionController institucionController = new InstitucionController(controllerGeneral);
        List<InstitucionDTO> institucionDTOS = institucionController.mostrarTodasInstituciones();
        for (InstitucionDTO institucionDTO : institucionDTOS){
            comboBoxModeInstitucion.addElement(String.valueOf(institucionDTO.getNom_Institucion()));
        }
        comboBoxInstitucion.setModel(comboBoxModeInstitucion);

        DefaultComboBoxModel<String> comboBoxModelSector = new DefaultComboBoxModel<>();
        SectorController sectorController = new SectorController(controllerGeneral);
        List<SectorDTO> sectorDTOS = sectorController.obtenerTodosLosSectores();
        for (SectorDTO sectorDTO : sectorDTOS){
            comboBoxModelSector.addElement(String.valueOf(sectorDTO.getNombre()));
        }
        comboBoxSector.setModel(comboBoxModelSector);

        DefaultComboBoxModel<String> comboBoxModelUbicacion = new DefaultComboBoxModel<>();
        UbicacionController ubicacionController = new UbicacionController(controllerGeneral);
        List<UbicacionDTO> ubicacionDTOS = ubicacionController.obtenerTodasLasUbicaciones();
        for (UbicacionDTO ubicacionDTO : ubicacionDTOS){
            comboBoxModelUbicacion.addElement(String.valueOf(ubicacionDTO.getNombre()));
        }
        comboBoxUbicacion.setModel(comboBoxModelUbicacion);

        DefaultComboBoxModel<String> comboBoxModelUsuario = new DefaultComboBoxModel<>();
        UsuarioController usuarioController = new UsuarioController(controllerGeneral);
        List<UsuarioDTO> usuarioDTOS = usuarioController.obtenerUsuariosActivos();
        for (UsuarioDTO usuarioDTO : usuarioDTOS){
            comboBoxModelUsuario.addElement(String.valueOf(usuarioDTO.getNom_usuario()));
        }
        comboBoxUsuario.setModel(comboBoxModelUsuario);

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerGeneral.mostrarMenuInterfaz(true);
                panel.setVisible(false);
            }
        });

        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    agregarMovimiento();
                } catch (ServiciosException e) {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al agregra movimiento: \n" + e.getMessage());
                }
            }
        });
        comboBoxNomEquipo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    preRellenarCampos();
                } catch (ServiciosException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        comboBoxUbicacion.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    preRellenarCampos();
                } catch (ServiciosException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    public void agregarMovimiento() throws ServiciosException {
        CrearMovEquipoDTO movEquipoDTO = new CrearMovEquipoDTO();
        MovEquipoController movEquipoController = new MovEquipoController(controllerGeneral);
        try {
            if (inputPiso.getText().length() < 1 || inputPiso.getText().length() > 3 || Integer.parseInt(inputPiso.getText()) == 0) {
                mostrarMensaje("El valor del campo Piso debe ser un número entre 1 y 10.");
                return;
            }
        }catch (NumberFormatException | NullPointerException e){
            mostrarMensaje("El valor del campo Piso debe ser un número");
            return;
        }

        if ("0".equals(inputPiso.getText())){
            mostrarMensaje("El campo piso no puede ser 0.");
            return;
        }

        if(!inputCama.getText().matches("\\d*")){
            mostrarMensaje("El campo cama solo puede contener números.");
            return;
        }
        try {
            if (textFieldNum.getText().isEmpty()|| textFieldNum.getText().length() > 3 || Integer.parseInt(textFieldNum.getText()) == 0) {
                mostrarMensaje("El valor del campo Número debe ser un número entre 1 y 10.");
                return;
            }
        }catch (NumberFormatException | NullPointerException e){
            mostrarMensaje("El valor del campo Número debe ser un número");
            return;
        }


        //ID DE EQUIPO
        movEquipoDTO.setEquipoId(Integer.valueOf((String) textIdEquipo.getText()));

        //OBSERVACIONES
        movEquipoDTO.setObservaciones(textAreaObservaciones.getText());

        //ID DE USUARIO
        String usuario = comboBoxUsuario.getSelectedItem().toString();
        UsuarioController usuarioController = new UsuarioController(controllerGeneral);
        UsuarioDTO usuarioDTOList = usuarioController.obtenerPorNombreUsuario(usuario);
        Integer idUsuario = usuarioDTOList.getID_Usuario();
        movEquipoDTO.setUsuarioId(idUsuario);

        // ID DE UBICACION
        String ubicacion = comboBoxUbicacion.getSelectedItem().toString();
        UbicacionController ubicacionController = new UbicacionController(controllerGeneral);
        Integer idUbicacion = ubicacionController.buscarUbicacionPorNombre(ubicacion).getIdUbicacion();
        movEquipoDTO.setUbicacionId(idUbicacion);
        System.out.println();

        //FECHA DE SALIDA
        Date fechaSalida = JDateChooser1.getDate();
        if (fechaSalida == null){
            throw new ServiciosException("Debe de ingresar una fecha de salida.");
        }
        System.out.println(fechaSalida);
        movEquipoDTO.setFecEntrada(DateMapper.convertDateToLocalDate(fechaSalida));

        movEquipoController.crearMovEquipo(movEquipoDTO);

        mostrarMensaje("¡Movimiento agregado con éxito!");
        volverPrincipal();
    }
    public void setVisible(boolean visible){
        frame.setVisible(visible);
    }

    public void volverPrincipal(){
        controllerGeneral.mostrarMenuInterfaz(true);
        panel.setVisible(false);
    }

    public void preRellenarCampos() throws ServiciosException, SQLException {
        UbicacionController ubicacionController = new UbicacionController(controllerGeneral);
        EquipoController equipoController = new EquipoController(controllerGeneral);

        if(comboBoxNomEquipo.getSelectedItem() != null){
            EquipoDTO equipoDTO = equipoController.obtenerPorNombre(comboBoxNomEquipo.getSelectedItem().toString());
            textIdEquipo.setText(String.valueOf(equipoDTO.getIdEquipo()));
            MovEquipoDTO movEquipoDTO =
                    controllerGeneral.movEquipoController().obtenerUbicacionActualByEquipoId(equipoDTO.getIdEquipo());
            comboBoxSector.setSelectedItem(controllerGeneral.getSectorController().buscarPorId(controllerGeneral.getUbicacionController().obtenerUbicacionPorId(movEquipoDTO.getUbicacionId()).getSectorId()).getNombre());
            setEnabled1(true);
        }
        if(comboBoxUbicacion.getSelectedItem() != null){
            UbicacionDTO ubicacionDTO = ubicacionController.buscarUbicacionPorNombre(comboBoxUbicacion.getSelectedItem().toString());
            textFieldNum.setText(String.valueOf(ubicacionDTO.getNumero()));
            setEnabled2(true);
        }


        comboBoxUbicacion.setEnabled(true);
        textFieldNum.setEnabled(true);
    }

    private void createUIComponents() {
        JDateChooser1 = new JDateChooser();
        JDateChooser1.setDateFormatString("dd/MM/yyyy");
    }

    public void setEnabled1(boolean enable){
        for (Component component : componentesNomEquipo){
            component.setEnabled(enable);
        }
    }

    public void setEnabled2(boolean enable){
        for (Component component : componentesNumUbi){
            component.setEnabled(enable);
        }
    }

    public void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public JPanel getPanel(){
        return panel;
    }

    public void actualizarComboBox() throws ServiciosException, SQLException {
        DefaultComboBoxModel<String> comboBoxModelNomEquipo = new DefaultComboBoxModel<>();
        EquipoController equipoController = new EquipoController(controllerGeneral);
        List<EquipoDTO> equipoDTOList = equipoController.obtenerTodosLosEquipos();
        comboBoxModelNomEquipo.addElement("");
        for (EquipoDTO equipoDTO : equipoDTOList){
            comboBoxModelNomEquipo.addElement(equipoDTO.getNomEquipo());
        }
        comboBoxNomEquipo.setModel(comboBoxModelNomEquipo);

        DefaultComboBoxModel<String> comboBoxModeInstitucion = new DefaultComboBoxModel<>();
        InstitucionController institucionController = new InstitucionController(controllerGeneral);
        List<InstitucionDTO> institucionDTOS = institucionController.mostrarTodasInstituciones();
        for (InstitucionDTO institucionDTO : institucionDTOS){
            comboBoxModeInstitucion.addElement(String.valueOf(institucionDTO.getNom_Institucion()));
        }
        comboBoxInstitucion.setModel(comboBoxModeInstitucion);

        DefaultComboBoxModel<String> comboBoxModelSector = new DefaultComboBoxModel<>();
        SectorController sectorController = new SectorController(controllerGeneral);
        List<SectorDTO> sectorDTOS = sectorController.obtenerTodosLosSectores();
        for (SectorDTO sectorDTO : sectorDTOS){
            comboBoxModelSector.addElement(String.valueOf(sectorDTO.getNombre()));
        }
        comboBoxSector.setModel(comboBoxModelSector);

        DefaultComboBoxModel<String> comboBoxModelUbicacion = new DefaultComboBoxModel<>();
        UbicacionController ubicacionController = new UbicacionController(controllerGeneral);
        List<UbicacionDTO> ubicacionDTOS = ubicacionController.obtenerTodasLasUbicaciones();
        for (UbicacionDTO ubicacionDTO : ubicacionDTOS){
            comboBoxModelUbicacion.addElement(String.valueOf(ubicacionDTO.getNombre()));
        }
        comboBoxUbicacion.setModel(comboBoxModelUbicacion);

        DefaultComboBoxModel<String> comboBoxModelUsuario = new DefaultComboBoxModel<>();
        UsuarioController usuarioController = new UsuarioController(controllerGeneral);
        List<UsuarioDTO> usuarioDTOS = usuarioController.obtenerUsuariosActivos();
        for (UsuarioDTO usuarioDTO : usuarioDTOS){
            comboBoxModelUsuario.addElement(String.valueOf(usuarioDTO.getNom_usuario()));
        }
        comboBoxUsuario.setModel(comboBoxModelUsuario);
    }
}
