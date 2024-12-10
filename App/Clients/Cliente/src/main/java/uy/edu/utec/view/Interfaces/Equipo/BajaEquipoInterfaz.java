package uy.edu.utec.view.Interfaces.Equipo;

import com.toedter.calendar.JDateChooser;
import uy.edu.utec.controller.BajaEquipoController;
import uy.edu.utec.controller.ControllerGeneral;
import uy.edu.utec.controller.EquipoController;
import uy.edu.utec.controller.UsuarioController;
import uy.edu.utec.servers.dtos.BajaUsuarioEquipoDTO;
import uy.edu.utec.servers.dtos.EquipoDTO;
import uy.edu.utec.servers.dtos.UsuarioDTO;
import uy.edu.utec.servers.enums.EstadoGeneral;
import uy.edu.utec.servers.enums.EstadoUsuario;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.servers.utils.DateMapper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class BajaEquipoInterfaz {
    private JTextArea comentariosText;
    private JTextArea razonBajaText;
    private JButton darDeBajaButton;
    private JButton cancelarButton;
    private JPanel panel;
    private JComboBox equiposComboBox;
    private JComboBox usuarioComboBox;
    private JDateChooser bajaJDate;
    private JFrame frame;

    private UsuarioController usuarioController;
    //
    ControllerGeneral controllerGeneral;

    public BajaEquipoInterfaz(ControllerGeneral controllerGeneral) throws SQLException {
        this.controllerGeneral = controllerGeneral;
        frame = new JFrame("Registro intervencion");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 500);
        bajaJDate.setMaxSelectableDate(new java.util.Date());

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerGeneral.mostrarMenuInterfaz(true);
                panel.setVisible(false);
                mostrarMensaje("Se ha cancelado la baja del equipo");
            }
        });


        darDeBajaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mensajeConfirmacion()) {
                    try {
                        EquipoController equipoController = new EquipoController(controllerGeneral);
                        Integer id =equipoController.obtenerPorNombre(equiposComboBox.getSelectedItem().toString()).getIdEquipo();
                        darDeBajaEquipo();
                        equipoController.bajaEquipo(id);
                        mostrarMensaje("Equipo dado de baja con exito");
                        volverPrincipal();
                    }catch (SQLException | ServiciosException ex) {
                        JOptionPane.showMessageDialog(null,
                                "Ha ocurrido un error al dar de baja un equipo: \n" + ex.getMessage());
                    }
                } else {
                    mostrarMensaje("Se ha cancelado la baja del equipo con éxito.");
                }
            }
        });
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
        bajaJDate = new JDateChooser();
        bajaJDate.setDateFormatString("dd/MM/yyyy");
    }

    public void darDeBajaEquipo() throws SQLException, ServiciosException {

        BajaUsuarioEquipoDTO bajaUsuarioEquipoDTO = new BajaUsuarioEquipoDTO();
        BajaEquipoController bajaEquipoController = new BajaEquipoController(controllerGeneral);
        EquipoController equipoController = new EquipoController(controllerGeneral);
          usuarioController  = new UsuarioController(controllerGeneral);
        bajaUsuarioEquipoDTO.setEquipoId(equipoController.obtenerPorNombre(equiposComboBox.getSelectedItem().toString()).getIdEquipo());
        bajaUsuarioEquipoDTO.setComentario(comentariosText.getText());
        bajaUsuarioEquipoDTO.setRazon(razonBajaText.getText());
        bajaUsuarioEquipoDTO.setUsuarioId(usuarioController.obtenerPorNombreUsuario(usuarioComboBox.getSelectedItem().toString()).getID_Usuario());
        Date fecha_int = bajaJDate.getDate();



        // Validate the date
        if (fecha_int == null) {
            throw new ServiciosException("Debe ingresar una fecha.");
        }
        bajaUsuarioEquipoDTO.setFechaBaja(DateMapper.convertDateToLocalDate(fecha_int));
        bajaEquipoController.crearBajaEquipo(bajaUsuarioEquipoDTO);
    }
    public void actualizarComboBox() throws SQLException, ServiciosException {
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
        EquipoController equipoController = new EquipoController(controllerGeneral);
        List<EquipoDTO> equipos = equipoController.obtenerTodosLosEquipos();
        for(EquipoDTO equipoDTO : equipos){
            if (equipoDTO.getEstado() == EstadoGeneral.ACTIVO){
                comboBoxModel.addElement(String.valueOf(equipoDTO.getNomEquipo()));
            }
        }

        equiposComboBox.setModel(comboBoxModel);

        DefaultComboBoxModel<String> comboBoxModelUsuario = new DefaultComboBoxModel<>();
        UsuarioController usuarioController = new UsuarioController(controllerGeneral);
        List<UsuarioDTO> usuarios = usuarioController.obtenerTodosLosUsuarios();
        for (UsuarioDTO usuarioDTO : usuarios){
            if (usuarioDTO.getEstado() == EstadoUsuario.ACTIVO){
                comboBoxModelUsuario.addElement(String.valueOf(usuarioDTO.getNom_usuario()));
            }
        }
        usuarioComboBox.setModel(comboBoxModelUsuario);
    }

    public Boolean mensajeConfirmacion() {
        Object[] opciones = {"Confirmar", "Cancelar"};
        int respuesta = JOptionPane.showOptionDialog(null, "¿Desea confirmar la acción?", "Confirmación", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        return respuesta == JOptionPane.YES_OPTION;
    }
}
