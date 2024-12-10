package uy.edu.utec.view.Interfaces.Usuario;

import com.toedter.calendar.JDateChooser;
import uy.edu.utec.controller.ControllerGeneral;
import uy.edu.utec.controller.InstitucionController;
import uy.edu.utec.controller.PerfilController;
import uy.edu.utec.controller.UsuarioController;
import uy.edu.utec.servers.dtos.InstitucionDTO;
import uy.edu.utec.servers.dtos.PerfilDTO;
import uy.edu.utec.servers.dtos.UsuarioDTO;
import uy.edu.utec.servers.entidades.TelefonoUsuario;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.servers.utils.DateMapper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ModificarMisDatosInterfaz {
    private JTextField PrimerNombreInput;
    private JTextField SegundoNombreInput;
    private JTextField PrimerApellidoInput;
    private JTextField SegundoApellidoInput;
    private JTextField CedulaInput;
    //private JTextField FecNacimientoInput;
    private JTextField EmailInput;
    private JPasswordField passwordInput;
    private JButton cancelarButton;
    private JButton guardarButton;
    private JTextField TelefonoInput;
    private JPanel panel;
    private JComboBox comboBoxPerfil;
    private JDateChooser dateChooser;
    private JComboBox institucionComboBox;
    private JLabel CampoObligatorio;
    private JButton update;

    //
    ControllerGeneral controllerGeneral;

    public ModificarMisDatosInterfaz(ControllerGeneral controllerGeneral) {
        this.controllerGeneral = controllerGeneral;

        //comboBox Perfiles
        DefaultComboBoxModel<String> comboBoxModelPerfil = new DefaultComboBoxModel<>();
        PerfilController perfilController = new PerfilController(controllerGeneral);
        List<PerfilDTO> perfiles = perfilController.obtenerPerfilesActivos();
        for (PerfilDTO perfilDTO : perfiles) {
            comboBoxModelPerfil.addElement(String.valueOf(perfilDTO.getNomPerfil()));
        }
        comboBoxPerfil.setModel(comboBoxModelPerfil);
        //System.out.println(controllerGeneral.getUsuarioController().obtenerUsuarioActivo().getEmail());


        //instituciones
        DefaultComboBoxModel<String> comboBoxModelInstitucion = new DefaultComboBoxModel<>();
        InstitucionController institucionController = new InstitucionController(controllerGeneral);
        List<InstitucionDTO> institucionDTOList = institucionController.mostrarTodasInstituciones();
        for (InstitucionDTO institucionDTO : institucionDTOList) {
            comboBoxModelInstitucion.addElement(String.valueOf(institucionDTO.getNom_Institucion()));
        }

        institucionComboBox.setModel(comboBoxModelInstitucion);

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //mostrarMensaje("Se ha cancelado la modificación de su usuario con éxito.");
                controllerGeneral.mostrarMenuInterfaz(true);
                panel.setVisible(false);
            }
        });
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mensajeConfirmacion()) {
                    try {
                        modificarDatos();
                        mostrarMensaje("¡Usuario modificado correctamente!");
                        volverPrincipal();
                    } catch (ServiciosException ex) {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al modificar usuario: \n" + ex.getMessage());
                    }
                } //else {
//                    mostrarMensaje("Se ha cancelado el ingreso del usuario con éxito.");
//                }
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }

    public void preRellenarCampos() throws ServiciosException {
        UsuarioController usuarioController = new UsuarioController(controllerGeneral);
        if (UsuarioController.getnombreUsuarioActivo() != null) {
            UsuarioDTO usuarioDTO = usuarioController.obtenerPorNombreUsuario(UsuarioController.getnombreUsuarioActivo());
            System.out.println(usuarioDTO.getEmail());
            PrimerApellidoInput.setText(usuarioDTO.getApellido());
            PrimerNombreInput.setText(usuarioDTO.getNombre());
            SegundoNombreInput.setText(usuarioDTO.getNombre2());
            SegundoApellidoInput.setText(usuarioDTO.getApellido2());
            EmailInput.setText(usuarioDTO.getEmail());
            CedulaInput.setText(usuarioDTO.getCedula());
            dateChooser.setDate(DateMapper.convertLocalDateToDate(usuarioDTO.getFec_nac()));
            TelefonoInput.setText(usuarioDTO.getTelefonos().get(0));
            institucionComboBox.setSelectedItem(controllerGeneral.getInstitucionController().obtenerInstitucionPorId(usuarioDTO.getInstitucionId()));
            passwordInput.setText(usuarioDTO.getContrasenia());
            comboBoxPerfil.setSelectedItem(controllerGeneral.getPerfilController().obtenerPerfilPorId(usuarioDTO.getPerfilId()).getNomPerfil());
        } else {
            System.out.println("Es null");
        }
    }

    public void modificarDatos() throws ServiciosException {
        UsuarioController usuarioController = new UsuarioController(controllerGeneral);
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        UsuarioDTO usuarioActivo = usuarioController.getUsuarioActivo();
        usuarioDTO.setID_Usuario(usuarioController.obtenerPorNombreUsuario(UsuarioController.getnombreUsuarioActivo()).getID_Usuario());
        usuarioDTO.setApellido(PrimerApellidoInput.getText());
        usuarioDTO.setNombre(PrimerNombreInput.getText());
        usuarioDTO.setNombre2(SegundoNombreInput.getText());
        usuarioDTO.setApellido2(SegundoApellidoInput.getText());
        usuarioDTO.setEmail(EmailInput.getText());
        usuarioDTO.setCedula(CedulaInput.getText());
        Date fecha_nac = dateChooser.getDate();
        if (fecha_nac != null) {
            usuarioDTO.setFec_nac(DateMapper.convertDateToLocalDate(fecha_nac));
        }

        String perfilSeleccionado = (String) comboBoxPerfil.getSelectedItem();
        Integer idTipoUsuario = controllerGeneral.getPerfilController().obtenerPerfilPorNombre(perfilSeleccionado).getIdPerfil();
        usuarioDTO.setPerfilId(idTipoUsuario);
        if (usuarioDTO.getTelefonos() == null) {
            usuarioDTO.setTelefonos(new ArrayList<>());
        }
        usuarioDTO.getTelefonos().add(TelefonoInput.getText());
        usuarioDTO.setContrasenia(String.valueOf(passwordInput.getPassword()));

        usuarioDTO.setInstitucionId(controllerGeneral.getInstitucionController().obtenerInstitucionPorNombre
                (Objects.requireNonNull(institucionComboBox.getSelectedItem()).toString()).getId());
        usuarioDTO.setNom_usuario(usuarioActivo.getNom_usuario());
        usuarioDTO.setEstado(usuarioActivo.getEstado());
        usuarioController.actualizarUsuario(usuarioDTO);

    }

    public void volverPrincipal() {
        controllerGeneral.mostrarMenuInterfaz(true);
        panel.setVisible(false);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    private void createUIComponents() {
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy");
    }

    public Boolean mensajeConfirmacion() {
        Object[] opciones = {"Confirmar", "Cancelar"};
        int respuesta = JOptionPane.showOptionDialog(null, "¿Desea confirmar la acción?", "Confirmación", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        return respuesta == JOptionPane.YES_OPTION;
    }
}
