package uy.edu.utec.view.Interfaces.Usuario;

import com.toedter.calendar.JDateChooser;
import uy.edu.utec.controller.ControllerGeneral;
import uy.edu.utec.controller.InstitucionController;
import uy.edu.utec.controller.PerfilController;
import uy.edu.utec.modelo.validaciones.usuario.ValidacionesUsuario;
import uy.edu.utec.servers.dtos.InstitucionDTO;
import uy.edu.utec.servers.dtos.PerfilDTO;
import uy.edu.utec.servers.dtos.UsuarioDTO;
import uy.edu.utec.servers.enums.EstadoUsuario;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.servers.utils.DateMapper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class InterfazCrearUsuario {
    private JTextField PNombreInput;
    private JTextField SNombreInput;
    private JTextField PApellidoInput;
    private JTextField sApellidoInput;
    private JTextField ciInput;
    private JTextField telInput;
    private JTextField NomUsuarioInput;
    private JTextField emailInput;
    private JPasswordField passwordInput;
    private JButton ingresarButton;
    private JButton cancelarButton;
    private JComboBox comboBoxTipoUsuario;
    private JPanel panel;
    private JPanel panel1;
    private JDateChooser dateChooser;
    private JComboBox institucionComboBox;
    private JComboBox institucion;
    //
    private ControllerGeneral controllerGeneral;
    private JFrame frame;

    public InterfazCrearUsuario(ControllerGeneral controllerGeneral) {
        this.controllerGeneral = controllerGeneral;
        frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 500);
        //comboBox Perfiles
        DefaultComboBoxModel<String> comboBoxModelPerfil = new DefaultComboBoxModel<>();
        PerfilController perfilController = new PerfilController(controllerGeneral);
        List<PerfilDTO> perfiles = new ArrayList<>();
        if (!perfilController.obtenerPerfilesActivos().isEmpty()) {
            perfiles = perfilController.obtenerPerfilesActivos();
        }

        for (PerfilDTO perfilDTO : perfiles) {
            comboBoxModelPerfil.addElement(String.valueOf(perfilDTO.getNomPerfil()));
        }

        comboBoxTipoUsuario.setModel(comboBoxModelPerfil);

        //combobox Instituciones
        DefaultComboBoxModel<String> comboBoxModelInstitucion = new DefaultComboBoxModel<>();
        InstitucionController institucionController = new InstitucionController(controllerGeneral);
        List<InstitucionDTO> institucionDTOList = institucionController.mostrarTodasInstituciones();
        for (InstitucionDTO institucionDTO : institucionDTOList) {
            comboBoxModelInstitucion.addElement(String.valueOf(institucionDTO.getNom_Institucion()));
        }

        institucionComboBox.setModel(comboBoxModelInstitucion);///

        //Permite que todos los usuarios >18 años puedan ingresar su fec-nac, mientras que los menores no
        Date fechaActual = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual);
        calendar.add(Calendar.YEAR, -18);
        Date fechaValida = calendar.getTime();
        dateChooser.setMaxSelectableDate(fechaValida);

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerGeneral.mostrarLogin(true);
                controllerGeneral.mostrarCrearUsuario(false);
            }
        });
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mensajeConfirmacion()) {
                    try {
                        Date fecha_nac = dateChooser.getDate();
                        System.out.println(fecha_nac);
                        crearUsuario();
                        mostrarMensaje("Su solicitud será revisada antes de estar activa.");
                    } catch (ServiciosException ex) {
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error al crear usuario: \n" + ex.getMessage());
                    }
                } else {
                    mostrarMensaje("Se ha cancelado el ingreso del usuario con éxito.");
                }
            }
        });
    }
    //metodos

    public void crearUsuario() throws ServiciosException {
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setNombre(PNombreInput.getText().trim());
        usuario.setNombre2(SNombreInput.getText().trim());
        usuario.setApellido(PApellidoInput.getText().trim());
        usuario.setApellido2(sApellidoInput.getText().trim());
        usuario.setCedula(ciInput.getText().trim());
        usuario.setNom_usuario(NomUsuarioInput.getText().trim());
        usuario.setEmail(emailInput.getText().trim());
        Date fecha_nac = dateChooser.getDate();
        usuario.setFec_nac(DateMapper.convertDateToLocalDate(fecha_nac));
//        // Validate the date
//        if (fecha_nac != null) {
//            usuario.setFec_nac(DateMapper.convertDateToLocalDate(fecha_nac));
//        }
//        if (!ValidacionesUsuario.validarPNombre(PNombreInput)){
//            return;
//        }
//        if (!ValidacionesUsuario.validarSNombre(SNombreInput)){
//            return;
//        }
//        if (!ValidacionesUsuario.validarPApellido(PApellidoInput)){
//            return;
//        }
//        if (!ValidacionesUsuario.validarSApellido(sApellidoInput)){
//            return;
//        }
//
//        if (!ValidacionesUsuario.validarCedula(ciInput)){
//            return;
//        }
//        if (!ValidacionesUsuario.validarEmail(emailInput)){
//            return;
//        }
//        if (!ValidacionesUsuario.validarFecNac(fecha_nac)){
//            return;
//        }
//        if (!ValidacionesUsuario.validarNomUsu(NomUsuarioInput)){
//            return;
//        }
//        if (!ValidacionesUsuario.validarTel(telInput)){
//            return;
//        }
//
//        //VALIDACIONES CAMPO CONSTRASEÑA
//        char[] passwordChars = passwordInput.getPassword();
//        String passwordString = new String(passwordChars);
//
//        if (passwordChars.length < 8 || passwordChars.length > 50) {
//            mostrarMensaje("El campo 'Contraseña' debe de contener entre 8 y 50 caracteres.");
//            return;
//        }
//        if (!passwordString.matches(".*[A-Z].*")) {
//            mostrarMensaje("El campo 'Contraseña' debe de contener mayúsculas.");
//            return;
//        }
//        if (!passwordString.matches(".*[a-z].*")) {
//            mostrarMensaje("El campo 'Contraseña' debe de contener minúsculas.");
//            return;
//        }
//        if (!passwordString.matches(".*\\d.*")) {
//            mostrarMensaje("El campo 'Contraseña' debe de contener al menos un número.");
//            return;
//        }
//        if (!passwordString.matches(".*[^a-zA-Z0-9].*")) {
//            mostrarMensaje("El campo 'Contraseña' debe de contener al menos un símbolo especial.");
//            return;
//        }

        usuario.setContrasenia(String.valueOf(passwordInput.getPassword()));

        //set perfil
        String perfilSeleccionado = (String) comboBoxTipoUsuario.getSelectedItem();
        Integer idTipoUsuario = controllerGeneral.getPerfilController().obtenerPerfilPorNombre(perfilSeleccionado).getIdPerfil();
        usuario.setPerfilId(idTipoUsuario);
        if (usuario.getTelefonos() == null) {
            usuario.setTelefonos(new ArrayList<>());
        }
        usuario.getTelefonos().add(telInput.getText());
        usuario.setEstado(EstadoUsuario.SINVALIDAR);

        //set institucion
        String institucionSeleccionada = Objects.requireNonNull(institucionComboBox.getSelectedItem()).toString();
        InstitucionController controladorInstitucion = new InstitucionController(controllerGeneral);
        Integer idInstitucion = controladorInstitucion.obtenerInstitucionPorNombre(institucionSeleccionada).getId();
        usuario.setInstitucionId(idInstitucion);


        controllerGeneral.getCrearUsuarioController().crearUsuario(usuario);
        controllerGeneral.mostrarLogin(true);
        controllerGeneral.mostrarCrearUsuario(false);

    }

    //set visible
    public void setVisible(boolean visible)  {
        frame.setVisible(visible);
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
