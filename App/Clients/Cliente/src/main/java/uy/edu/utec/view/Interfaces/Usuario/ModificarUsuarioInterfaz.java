package uy.edu.utec.view.Interfaces.Usuario;

import com.toedter.calendar.JDateChooser;
import uy.edu.utec.controller.ControllerGeneral;
import uy.edu.utec.controller.InstitucionController;
import uy.edu.utec.controller.PerfilController;
import uy.edu.utec.controller.UsuarioController;
import uy.edu.utec.servers.dtos.InstitucionDTO;
import uy.edu.utec.servers.dtos.PerfilDTO;
import uy.edu.utec.servers.dtos.UsuarioDTO;
import uy.edu.utec.servers.enums.EstadoUsuario;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.servers.utils.DateMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ModificarUsuarioInterfaz {
    private JTextField PrimerNombreInput;
    private JTextField SegundoNombreInput;
    private JTextField PrimerApellidoInput;
    private JTextField SegundoApellidoInput;
    private JTextField CedulaInput;
    private JTextField TelContactoInput;
    private JTextField EmailInput;
    private JTextField EstadoInput;

    private JComboBox comboBoxTipoUsuario;


    private JDateChooser dateChooser;
    private JButton guardarButton;
    private JButton cancelarButton;
    private JPanel panel;
    private JComboBox comboBoxNomUsuario;
    private JComboBox comboBoxEstado;
    private JComboBox institucionComboBox;

    private ControllerGeneral controllerGeneral;
    private JFrame frame;

    List<Component> componentes = new ArrayList<>();

    public ModificarUsuarioInterfaz(ControllerGeneral controllerGeneral) {
        this.controllerGeneral = controllerGeneral;
        frame = new JFrame("Modificar usuario");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 500);

        componentes.add(PrimerNombreInput);
        componentes.add(SegundoNombreInput);
        componentes.add(PrimerApellidoInput);
        componentes.add(SegundoApellidoInput);
        componentes.add(CedulaInput);
        componentes.add(TelContactoInput);
        componentes.add(EmailInput);
        componentes.add(comboBoxEstado);
        componentes.add(comboBoxTipoUsuario);
        componentes.add(dateChooser);

        setEnabled(false);
        //usuarios nombre
        DefaultComboBoxModel<String> comboBoxModelNombre = new DefaultComboBoxModel<>();
        UsuarioController usuarioController = new UsuarioController(controllerGeneral);
        List<UsuarioDTO> usuarios = usuarioController.obtenerTodosLosUsuarios();
        comboBoxModelNombre.addElement("");
        for(UsuarioDTO usuarioDTO : usuarios){
            comboBoxModelNombre.addElement(String.valueOf(usuarioDTO.getNom_usuario()));
        }

        comboBoxNomUsuario.setModel(comboBoxModelNombre);

        //perfiles
        DefaultComboBoxModel<String> comboBoxModelPerfil = new DefaultComboBoxModel<>();
        PerfilController perfilController = new PerfilController(controllerGeneral);
        List<PerfilDTO> perfiles = new ArrayList<>();
        if (!perfilController.obtenerPerfilesActivos().isEmpty()){
            perfiles = perfilController.obtenerPerfilesActivos();
        }
        comboBoxTipoUsuario.setModel(comboBoxModelPerfil);

        for(PerfilDTO perfilDTO : perfiles){
            comboBoxModelPerfil.addElement(String.valueOf(perfilDTO.getNomPerfil()));
        }

        //instituciones
        DefaultComboBoxModel<String> comboBoxModelInstitucion = new DefaultComboBoxModel<>();
        InstitucionController institucionController = new InstitucionController(controllerGeneral);
        List<InstitucionDTO> institucionDTOList = institucionController.mostrarTodasInstituciones();
        for(InstitucionDTO institucionDTO: institucionDTOList){
            comboBoxModelInstitucion.addElement(String.valueOf(institucionDTO.getNom_Institucion()));
        }

        institucionComboBox.setModel(comboBoxModelInstitucion);

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerGeneral.mostrarMenuInterfaz(true);
                panel.setVisible(false);
            }
        });
        comboBoxNomUsuario.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                preRellenarCampos();
            }
        });
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBoxNomUsuario.getSelectedItem() != null && !comboBoxNomUsuario.getSelectedItem().toString().isEmpty()) {
                    try {
                        if (mensajeConfirmacion()){
                            modificarDatos();
                            mostrarMensaje("¡Usuario modificado correctamente!");
                            volverPrincipal();
                        }

                    } catch (ServiciosException ex) {
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error al modificar usuario: \n" + ex.getMessage());
                    }
                }else
                    JOptionPane.showMessageDialog(null, "Seleccione un usuario a modificar.");
            }
        });
    }

    public void preRellenarCampos() {
        UsuarioController usuarioController = new UsuarioController(controllerGeneral);
        if (comboBoxNomUsuario.getSelectedItem() != null && !comboBoxNomUsuario.getSelectedItem().toString().isEmpty()) {
            UsuarioDTO usuarioDTO = usuarioController.obtenerPorNombreUsuario(comboBoxNomUsuario.getSelectedItem().toString());
            PrimerApellidoInput.setText(usuarioDTO.getApellido());
            PrimerNombreInput.setText(usuarioDTO.getNombre());
            SegundoNombreInput.setText(usuarioDTO.getNombre2());
            SegundoApellidoInput.setText(usuarioDTO.getApellido2());
            EmailInput.setText(usuarioDTO.getEmail());
            CedulaInput.setText(usuarioDTO.getCedula());
            dateChooser.setDate(DateMapper.convertLocalDateToDate(usuarioDTO.getFec_nac()));
            if (usuarioDTO.getTelefonos() == null){
                usuarioDTO.setTelefonos(new ArrayList<>());
            }
            TelContactoInput.setText(usuarioDTO.getTelefonos().get(0));

            comboBoxEstado.setSelectedItem(usuarioDTO.getEstado().toString());

            comboBoxEstado.setSelectedItem(usuarioDTO.getEstado().toString());
            institucionComboBox.setSelectedItem(controllerGeneral.getInstitucionController().obtenerInstitucionPorId(usuarioDTO.getInstitucionId()));
            setEnabled(true);
        }
    }

    public void modificarDatos() throws ServiciosException {
        UsuarioController usuarioController = new UsuarioController(controllerGeneral);
        UsuarioDTO usuarioSeleccionado = usuarioController.obtenerPorNombreUsuario(comboBoxNomUsuario.getSelectedItem().toString());
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setID_Usuario(usuarioController.obtenerPorNombreUsuario(comboBoxNomUsuario.getSelectedItem().toString()).getID_Usuario());
        usuarioDTO.setApellido(PrimerApellidoInput.getText());
        usuarioDTO.setNombre(PrimerNombreInput.getText());
        usuarioDTO.setNombre2(SegundoNombreInput.getText());
        usuarioDTO.setApellido2(SegundoApellidoInput.getText());
        usuarioDTO.setEmail(EmailInput.getText());
        usuarioDTO.setCedula(CedulaInput.getText());
        Date fecha_nac =  dateChooser.getDate();
        usuarioDTO.setEstado(EstadoUsuario.valueOf(comboBoxEstado.getSelectedItem().toString()));

        if (fecha_nac != null) {
            usuarioDTO.setFec_nac(DateMapper.convertDateToLocalDate(fecha_nac));
        }

        String perfilSeleccionado = (String) comboBoxTipoUsuario.getSelectedItem();
        Integer idTipoUsuario = controllerGeneral.getPerfilController().obtenerPerfilPorNombre(perfilSeleccionado).getIdPerfil();
        usuarioDTO.setPerfilId(idTipoUsuario);
        if (usuarioDTO.getTelefonos() == null){
            usuarioDTO.setTelefonos(new ArrayList<>());
        }
        usuarioDTO.getTelefonos().add(TelContactoInput.getText());
        usuarioDTO.setInstitucionId(controllerGeneral.getInstitucionController().obtenerInstitucionPorNombre
                (Objects.requireNonNull(institucionComboBox.getSelectedItem()).toString()).getId());
        usuarioDTO.setContrasenia(String.valueOf(usuarioController.obtenerPorNombreUsuario(comboBoxNomUsuario.getSelectedItem().toString()).getContrasenia()));
        System.out.println(usuarioController.getUsuarioActivo().getNom_usuario());
        usuarioDTO.setNom_usuario(usuarioController.getUsuarioActivo().getNom_usuario());
        usuarioDTO.setNom_usuario(usuarioSeleccionado.getNom_usuario());
        System.out.println(usuarioSeleccionado);
        usuarioDTO.setContrasenia(usuarioSeleccionado.getContrasenia());

        if (Objects.requireNonNull(comboBoxEstado.getSelectedItem()).toString().equals("ACTIVO")) {
            usuarioController.activarUsuario(usuarioSeleccionado.getID_Usuario());
        }else if ((comboBoxEstado.getSelectedItem().toString().equals("ELIMINADO"))){
            usuarioController.bajaUsuarioLogica(usuarioSeleccionado.getID_Usuario());
        }
        usuarioController.actualizarUsuario(usuarioDTO);
    }
    public void setVisible(boolean visible){
        frame.setVisible(visible);
    }

    public JPanel getPanel(){
        return panel;
    }
    private void createUIComponents() {
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy");
    }

    public void setEnabled(boolean enable){
        for (Component component : componentes){
            component.setEnabled(enable);
        }
    }

    public void volverPrincipal(){
        controllerGeneral.mostrarMenuInterfaz(true);
        panel.setVisible(false);
    }

    public void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public Boolean mensajeConfirmacion() {
        Object[] opciones = {"Confirmar", "Cancelar"};
        int respuesta = JOptionPane.showOptionDialog(null, "¿Desea confirmar la acción?", "Confirmación", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        return respuesta == JOptionPane.YES_OPTION;
    }
}
