package uy.edu.utec.view.Interfaces.Ubicacion;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.demo.DateChooserPanel;
import uy.edu.utec.controller.ControllerGeneral;
import uy.edu.utec.controller.InstitucionController;
import uy.edu.utec.controller.UbicacionController;
import uy.edu.utec.controller.UsuarioController;
import uy.edu.utec.servers.dtos.BajaUsuarioUbicacionDTO;
import uy.edu.utec.servers.dtos.InstitucionDTO;
import uy.edu.utec.servers.dtos.UbicacionDTO;
import uy.edu.utec.servers.dtos.UsuarioDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.servers.utils.DateMapper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class BajaUbicacionInterfaz {
    private JTextArea textAreaComentarios;
    private JTextArea textAreaRazon;
    private JButton cancelarButton;
    private JButton bajaButton;
    private JPanel panel;
    private JComboBox institucionComboBox;
    private JDateChooser dataChooserFecBaja;
    private JComboBox comboBoxUsuario;
    private JComboBox comboBoxNombreUbicacion;
    private DateChooserPanel dateChooserPanel;

    private ControllerGeneral controllerGeneral;
    private JFrame frame;
    public BajaUbicacionInterfaz(ControllerGeneral controllerGeneral) throws ServiciosException {
        this.controllerGeneral = controllerGeneral;
            frame = new JFrame("Baja de ubicación.");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 500);
        textAreaComentarios.setLineWrap(true);
        textAreaRazon.setLineWrap(true);
        dataChooserFecBaja.setMaxSelectableDate(new java.util.Date());

        UbicacionController ubicacionController = new UbicacionController(controllerGeneral);



        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerGeneral.mostrarMenuInterfaz(true);
                panel.setVisible(false);
            }
        });
        bajaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mensajeConfirmacion()) {
                    try {
                        darDeBajaUbicacion(ubicacionController.obtenerUbicacionPorNombre(comboBoxNombreUbicacion.getSelectedItem().toString()).getIdUbicacion());
                        actualizarComboBox();
                        mostrarMensaje("¡Ubicación dada de baja correctamente!");
                        volverPrincipal();
                    } catch (ServiciosException ex) {
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error al dar de baja ubicación: \n" + ex.getMessage());
                    }
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

    public void darDeBajaUbicacion(Integer idUbicacion) throws ServiciosException {
        UbicacionController ubicacionController = new UbicacionController(controllerGeneral);
        UsuarioController usuarioController = new UsuarioController(controllerGeneral);
        BajaUsuarioUbicacionDTO bajaUsuarioUbicacion = new BajaUsuarioUbicacionDTO();
        bajaUsuarioUbicacion.setUbicacionId(idUbicacion);
        Date fecha_baja = dataChooserFecBaja.getDate();
        if (fecha_baja != null) {
            bajaUsuarioUbicacion.setFec_baja(DateMapper.convertDateToLocalDate(fecha_baja));
        }

        bajaUsuarioUbicacion.setComentario(textAreaComentarios.getText());
        bajaUsuarioUbicacion.setRazon(textAreaRazon.getText());
        bajaUsuarioUbicacion.setUsuarioId(usuarioController.obtenerPorNombreUsuario(comboBoxUsuario.getSelectedItem().toString()).getID_Usuario());

        ubicacionController.darDeBajaUbicacion(bajaUsuarioUbicacion);

    }

    private void createUIComponents() {
        dataChooserFecBaja = new JDateChooser();
        dataChooserFecBaja.setDateFormatString("dd/MM/yyyy");
    }

    public void volverPrincipal() {
        controllerGeneral.mostrarMenuInterfaz(true);
        panel.setVisible(false);
    }

    public void actualizarComboBox() throws ServiciosException {
        //combobox Instituciones
        DefaultComboBoxModel<String> comboBoxModelInstitucion = new DefaultComboBoxModel<>();
        InstitucionController institucionController = new InstitucionController(controllerGeneral);
        List<InstitucionDTO> institucionDTOList = institucionController.mostrarTodasInstituciones();
        for(InstitucionDTO institucionDTO: institucionDTOList){
            comboBoxModelInstitucion.addElement(String.valueOf(institucionDTO.getNom_Institucion()));
        }

        institucionComboBox.setModel(comboBoxModelInstitucion);

        //combobox Usuarios
        DefaultComboBoxModel<String> comboBoxModelUsuario = new DefaultComboBoxModel<>();
        UsuarioController usuarioController = new UsuarioController(controllerGeneral);
        List<UsuarioDTO> usuarioDTOS = usuarioController.obtenerUsuariosActivos();
        for(UsuarioDTO usuarioDTO: usuarioDTOS){
            comboBoxModelUsuario.addElement(usuarioDTO.getNom_usuario());
        }
        comboBoxUsuario.setModel(comboBoxModelUsuario);

        //combobox Ubicacion
        DefaultComboBoxModel<String> comboBoxModelUbicacion = new DefaultComboBoxModel<>();
        UbicacionController ubicacionController = new UbicacionController(controllerGeneral);
        List<UbicacionDTO> ubicacionDTOS = ubicacionController.obtenerTodasLasUbicacionesActivas();
        for(UbicacionDTO ubicacionDTO: ubicacionDTOS){
            comboBoxModelUbicacion.addElement(ubicacionDTO.getNombre());
        }

        comboBoxNombreUbicacion.setModel(comboBoxModelUbicacion);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public Boolean mensajeConfirmacion() {
        Object[] opciones = {"Confirmar", "Cancelar"};
        int respuesta = JOptionPane.showOptionDialog(null, "¿Desea confirmar la acción?", "Confirmación", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        return respuesta == JOptionPane.YES_OPTION;
    }

}
