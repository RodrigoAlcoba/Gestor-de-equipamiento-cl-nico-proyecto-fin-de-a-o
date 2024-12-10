package uy.edu.utec.view.Interfaces.Intervencion;

import com.toedter.calendar.JDateChooser;
import uy.edu.utec.controller.ControllerGeneral;
import uy.edu.utec.controller.IntervencionController;
import uy.edu.utec.controller.SeguimientoIntervencionController;
import uy.edu.utec.controller.TipoIntervencionController;
import uy.edu.utec.servers.dtos.IntervencionDTO;
import uy.edu.utec.servers.dtos.SeguimientoIntervencionDTO;
import uy.edu.utec.servers.dtos.TipoIntervencionDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.servers.utils.DateMapper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class TrabajarIntervencionInterfaz {
    private JTextField inputTipoIntervencion;
    private JTextArea textAreaMotivo;
    private JTextArea textAreaObservaciones;
    private JButton buttonCancelar;
    private JButton buttonIngresar;
    private JPanel panel;
    private JTextField inputIntervencion;
    private JLabel Intervencion;
    private JDateChooser JDateChooser1;
    private  JComboBox comboBoxTipoIntervencion;
    private JComboBox comboBoxIntervencion;

    //

    private static ControllerGeneral controllerGeneral;
    private JFrame frame;

    public TrabajarIntervencionInterfaz(ControllerGeneral controllerGeneral) {
        this.controllerGeneral = controllerGeneral;
        frame = new JFrame("Agregar seguimiento");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 500);

        Calendar currentDate = Calendar.getInstance();
        JDateChooser1.setMaxSelectableDate(currentDate.getTime());


        DefaultComboBoxModel<String> comboBoxModelTipo = new DefaultComboBoxModel<>();
        TipoIntervencionController tipoIntervencionController = new TipoIntervencionController(controllerGeneral);
        List<TipoIntervencionDTO> tipoIntervencionDTOS = tipoIntervencionController.obtenerTodosLosTipoIntervencionActivos();
        for (TipoIntervencionDTO tipoIntervencionDTO : tipoIntervencionDTOS) {
            comboBoxModelTipo.addElement(String.valueOf(tipoIntervencionDTO.getNomTipoIntervencion()));
        }
        comboBoxTipoIntervencion.setModel(comboBoxModelTipo);

        DefaultComboBoxModel<Integer> comboBoxModelIntervencion = new DefaultComboBoxModel<>();
        IntervencionController intervencionController = new IntervencionController(controllerGeneral);
        List<IntervencionDTO> intervencionDTOS = intervencionController.obtenerTodasLasIntervenciones();
        for (IntervencionDTO intervencionDTO : intervencionDTOS){
            comboBoxModelIntervencion.addElement(intervencionDTO.getIdIntervencion());
        }
        comboBoxIntervencion.setModel(comboBoxModelIntervencion);

        buttonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerGeneral.mostrarMenuInterfaz(true);
                panel.setVisible(false);
            }
        });
        buttonIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    agregarSeguimientoIntervencion();
                    volverPrincipal();
                    mostrarMensaje("¡Seguimiento agregado con éxito!");
                } catch (ServiciosException e) {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al agregar un seguimiento: \n" + e.getMessage());
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

    public void agregarSeguimientoIntervencion() throws ServiciosException{
        SeguimientoIntervencionDTO seguimientoIntervencionDTO = new SeguimientoIntervencionDTO();

        seguimientoIntervencionDTO.setIntervencionId((Integer) comboBoxIntervencion.getSelectedItem());
        seguimientoIntervencionDTO.setTipoIntervencion(String.valueOf(comboBoxTipoIntervencion.getSelectedItem()));
        seguimientoIntervencionDTO.setMotivo(textAreaMotivo.getText());
        seguimientoIntervencionDTO.setObservaciones(textAreaObservaciones.getText());

        SeguimientoIntervencionController seguimiento = new SeguimientoIntervencionController(controllerGeneral);
        Date fecha_int = JDateChooser1.getDate();

        if(fecha_int == null){
            throw new ServiciosException("Debe ingresar una fecha.");
        }
        seguimientoIntervencionDTO.setFechaHora(DateMapper.convertDateToLocalDate(fecha_int));

        seguimientoIntervencionDTO.setFechaHora(DateMapper.convertDateToLocalDate(fecha_int));
        seguimiento.agregarSeguimientoIntervencion(seguimientoIntervencionDTO);
    }

    public  void actualizarComboBox(){
        DefaultComboBoxModel<String> comboBoxModelTipo = new DefaultComboBoxModel<>();
        TipoIntervencionController tipoIntervencionController = new TipoIntervencionController(controllerGeneral);
        List<TipoIntervencionDTO> tipoIntervencionDTOS = tipoIntervencionController.obtenerTodosLosTipoIntervencionActivos();
        for (TipoIntervencionDTO tipoIntervencionDTO : tipoIntervencionDTOS) {
            comboBoxModelTipo.addElement(String.valueOf(tipoIntervencionDTO.getNomTipoIntervencion()));
        }
        comboBoxTipoIntervencion.setModel(comboBoxModelTipo);
    }
}
