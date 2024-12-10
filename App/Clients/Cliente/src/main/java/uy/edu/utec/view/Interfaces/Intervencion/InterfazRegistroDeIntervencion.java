package uy.edu.utec.view.Interfaces.Intervencion;

import com.toedter.calendar.JDateChooser;
import uy.edu.utec.controller.*;
import uy.edu.utec.servers.dtos.EquipoDTO;
import uy.edu.utec.servers.dtos.IntervencionDTO;
import uy.edu.utec.servers.dtos.TipoIntervencionDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.servers.utils.DateMapper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class InterfazRegistroDeIntervencion {
    private JTextField motivoInput;
    private JTextArea observacionesTextArea;
    private JButton buttonIngresar;
    private JButton buttonCancelar;
    private JPanel panel;
    private JDateChooser JDateChooser1;
    private JComboBox tipoIntervencionComboBox;
    private JComboBox equipoComboBox;

    //
    private ControllerGeneral controllerGeneral;
    private JFrame frame;

    public InterfazRegistroDeIntervencion(ControllerGeneral controllerGeneral) throws SQLException, ServiciosException {
        this.controllerGeneral = controllerGeneral;
        frame = new JFrame("Registro intervencion");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 500);

        Calendar currentDate = Calendar.getInstance();
        JDateChooser1.setMinSelectableDate(currentDate.getTime());
        JDateChooser1.setMaxSelectableDate(currentDate.getTime());


        DefaultComboBoxModel<String> comboBoxModelIntervencion = new DefaultComboBoxModel<>();
        TipoIntervencionController intervencionController = new TipoIntervencionController(controllerGeneral);
        List<TipoIntervencionDTO> intervenciones = intervencionController.obtenerTodosLosTiposIntervencion();
        for(TipoIntervencionDTO tipoIntervencionDTO : intervenciones){
            comboBoxModelIntervencion.addElement(String.valueOf(tipoIntervencionDTO.getNomTipoIntervencion()));
        }

        tipoIntervencionComboBox.setModel(comboBoxModelIntervencion);

        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
        EquipoController equipoController = new EquipoController(controllerGeneral);
        List<EquipoDTO> equipos = equipoController.obtenerTodosLosEquipos();
        for(EquipoDTO equipoDTO : equipos){
            comboBoxModel.addElement(String.valueOf(equipoDTO.getNomEquipo()));
        }

        equipoComboBox.setModel(comboBoxModel);


        buttonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               controllerGeneral.mostrarMenuInterfaz(true);
               panel.setVisible(false);
            }
        });
        buttonIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    try {
                        agregarIntervencion();
                        mostrarMensaje("Intervencion agregada con éxito");
                        volverPrincipal();
                        limpiarCampos();
                    } catch (SQLException | ServiciosException ex) {
                        JOptionPane.showMessageDialog(null, "Ha ocurrido una intervención al ingresar una intervención.: \n" + ex.getMessage());
                }
            }
        });
    }

    public void setVisible(boolean visible){
        frame.setVisible(visible);
    }
    public JPanel getPanel() {
        return panel;
    }

    public void volverPrincipal(){
        controllerGeneral.mostrarMenuInterfaz(true);
        panel.setVisible(false);
    }

    public void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje);
    }

    private void createUIComponents() {
        JDateChooser1 = new JDateChooser();
        JDateChooser1.setDateFormatString("dd/MM/yyyy");
    }
    public void limpiarCampos(){
        motivoInput.setText("");
        observacionesTextArea.setText("");
        JDateChooser1.setDate(null);
    }

    public void agregarIntervencion() throws ServiciosException, SQLException {
        IntervencionDTO intervencionDTO = new IntervencionDTO();
        IntervencionController intervencion = new IntervencionController(controllerGeneral);
        TipoIntervencionController tipoIntervencion = new TipoIntervencionController(controllerGeneral);
        EquipoController equipoController = new EquipoController(controllerGeneral);

        intervencionDTO.setMotivo(motivoInput.getText());
        intervencionDTO.setComentario(observacionesTextArea.getText());
        intervencionDTO.setEquipoId(equipoController.obtenerPorNombre(equipoComboBox.getSelectedItem().toString()).getIdEquipo());
        intervencionDTO.setTipoIntervencion(tipoIntervencion.obtenerTodosLosTipoIntervencionPorNombre(tipoIntervencionComboBox.getSelectedItem().toString()));
        intervencionDTO.setEmailUsuario(controllerGeneral.getUsuarioController().obtenerPorNombreUsuario(UsuarioController.getnombreUsuarioActivo()).getEmail());

        Date fecha_int = JDateChooser1.getDate();

        // Validate the date
        if (fecha_int == null) {
            throw new ServiciosException("Debe ingresar una fecha");
        }
        System.out.println(fecha_int);

        intervencionDTO.setFechaHora(DateMapper.convertDateToLocalDate(fecha_int));
        intervencion.agregarIntervencion(intervencionDTO);
    }

    public  void actualizarCombox() throws ServiciosException, SQLException {
        DefaultComboBoxModel<String> comboBoxModelIntervencion = new DefaultComboBoxModel<>();
        TipoIntervencionController intervencionController = new TipoIntervencionController(controllerGeneral);
        List<TipoIntervencionDTO> intervenciones = intervencionController.obtenerTodosLosTiposIntervencion();
        for(TipoIntervencionDTO tipoIntervencionDTO : intervenciones){
            comboBoxModelIntervencion.addElement(String.valueOf(tipoIntervencionDTO.getNomTipoIntervencion()));
        }

        tipoIntervencionComboBox.setModel(comboBoxModelIntervencion);

        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
        EquipoController equipoController = new EquipoController(controllerGeneral);
        List<EquipoDTO> equipos = equipoController.obtenerTodosLosEquipos();
        for(EquipoDTO equipoDTO : equipos){
            comboBoxModel.addElement(String.valueOf(equipoDTO.getNomEquipo()));
        }

        equipoComboBox.setModel(comboBoxModel);
    }
}
