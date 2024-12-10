package uy.edu.utec.view.Interfaces.Usuario;

import uy.edu.utec.controller.ControllerGeneral;
import uy.edu.utec.controller.PerfilController;
import uy.edu.utec.controller.UsuarioController;
import uy.edu.utec.servers.dtos.PerfilDTO;
import uy.edu.utec.servers.dtos.UsuarioDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListadoDeUsuariosInterfaz {
    private JPanel panel;
    private JButton buttonCancelar;
    private JButton buttonBuscar;
    private JTextPane textPaneUsuario;
    private JTextField textFieldApellido;
    private JTextField textFieldUsuario;
    private JTextField textFieldEmail;
    private JComboBox comboBoxPerfil;
    private JComboBox comboBoxEstado;
    private JTextField textFieldNombre;
    private JRadioButton cbNombre;
    private JRadioButton cbApellido;
    private JRadioButton cbUsuario;
    private JRadioButton cbEmail;
    private JRadioButton cbTipoUsuario;
    private JRadioButton cbEstado;
    private JTable tableUsuarios;
    private JScrollPane scrollPane;
    private JButton limpiarButton;
    private JTable tablePerfiles;

    private ButtonGroup radioButtons = new ButtonGroup();



    private ControllerGeneral controllerGeneral;
    private JFrame frame;
    public ListadoDeUsuariosInterfaz(ControllerGeneral controllerGeneral) {
        this.controllerGeneral = controllerGeneral;
        frame = new JFrame("Listado de usuario.");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 500);

        //setear combobox tipo perfil

        DefaultComboBoxModel<String> comboBoxModelPerfil = new DefaultComboBoxModel<>();
        PerfilController perfilController = new PerfilController(controllerGeneral);
        List<PerfilDTO> perfiles = perfilController.obtenerPerfilesActivos();
        for(PerfilDTO perfilDTO : perfiles){
            comboBoxModelPerfil.addElement(String.valueOf(perfilDTO.getNomPerfil()));
        }
        comboBoxPerfil.setModel(comboBoxModelPerfil);

        radioButtons.add(cbNombre);
        radioButtons.add(cbApellido);
        radioButtons.add(cbUsuario);
        radioButtons.add(cbEmail);
        radioButtons.add(cbTipoUsuario);
        radioButtons.add(cbEstado);

        //JTable
        DefaultTableModel modeloTabla = new DefaultTableModel(
                new Object[]{"Cedula", "Usuario", "Nombre",
                        "Apellido", "Fec_Nac",
                        "Email", "Teléfono", "Estado", "Perfil", "Institucion"}, 0);

        tableUsuarios.setRowHeight(40);
        tableUsuarios.setRowMargin(5);
        tableUsuarios.setModel(modeloTabla);

        buttonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerGeneral.mostrarMenuInterfaz(true);
                panel.setVisible(false);
            }
        });

        buttonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsuarioController usuarioController = new UsuarioController(controllerGeneral);
                List<UsuarioDTO> usuarios = new ArrayList<>();
                if(cbNombre.isSelected()){
                    usuarios = usuarioController.obtenerTodosLosUsuariosPorNombre1(textFieldNombre.getText().trim());
                }else if (cbApellido.isSelected()){
                    usuarios = usuarioController.obtenerTodosLosUsuariosPorApellido1(textFieldApellido.getText().trim());
                }else if (cbUsuario.isSelected()){
                    usuarios = usuarioController.obtenerTodosLosUsuariosPorNombreUsuario(textFieldUsuario.getText().trim());
                }else if (cbTipoUsuario.isSelected()){
                    usuarios =
                            usuarioController.buscarPorPerfi(controllerGeneral.getPerfilController().obtenerPerfilPorNombre(
                                    Objects.requireNonNull(comboBoxPerfil.getSelectedItem()).toString()).getIdPerfil());
                } else if(cbEstado.isSelected()){
                    if (comboBoxEstado.getSelectedItem().toString().equals("ACTIVO")){
                        usuarios = usuarioController.obtenerUsuariosActivos();
                    }else if(comboBoxEstado.getSelectedItem().toString().equals("ELIMINADO")){
                        usuarios = usuarioController.obtenerUsuariosEliminados();
                    }else if (comboBoxEstado.getSelectedItem().toString().equals("SINVALIDAR")){
                        usuarios = usuarioController.obtenerUsuariosSinValidar();
                    }
                }else if(cbEmail.isSelected()){
                    usuarios = usuarioController.obtenerTodosLosUsuariosPorEmails(textFieldEmail.getText().trim());
                }
                else{
                    usuarios = usuarioController.obtenerTodosLosUsuarios();
                }
                try {
                    mostrarListadoUsuarios(usuarios);
                } catch (ServiciosException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                radioButtons.clearSelection();
                ((DefaultTableModel) tableUsuarios.getModel()).setRowCount(0);
            }
        });
    }
    public void setVisible(boolean visible){
        frame.setVisible(visible);
    }

    public void mostrarListadoUsuarios(List<UsuarioDTO> usuarios) throws ServiciosException {
        ((DefaultTableModel) tableUsuarios.getModel()).setRowCount(0);
        //Para añadir una fila nueva con los datos de un usuario
        for(UsuarioDTO usuarioDTO : usuarios){
            Object[] filaUsuario = {
                usuarioDTO.getCedula(),
                    usuarioDTO.getNom_usuario(),
                    usuarioDTO.getNombre(),
                    usuarioDTO.getApellido(),
                    usuarioDTO.getFec_nac(),
                    usuarioDTO.getEmail(),
                    usuarioDTO.getTelefonos().get(0),
                    usuarioDTO.getEstado(),
                    controllerGeneral.getPerfilController().obtenerPerfilPorId(usuarioDTO.getPerfilId()).getNomPerfil(),
                    controllerGeneral.getInstitucionController().obtenerInstitucionPorId(usuarioDTO.getInstitucionId()).getNom_Institucion()
            };
            ((DefaultTableModel)  tableUsuarios.getModel()).addRow(filaUsuario);

        }

    }

    public JPanel getPanel(){
        return panel;
    }
}
