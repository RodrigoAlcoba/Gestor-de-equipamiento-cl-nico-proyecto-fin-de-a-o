package uy.edu.utec.view.Interfaces.Ubicacion;

import uy.edu.utec.controller.ControllerGeneral;
import uy.edu.utec.controller.InstitucionController;
import uy.edu.utec.controller.SectorController;
import uy.edu.utec.controller.UbicacionController;
import uy.edu.utec.servers.dtos.InstitucionDTO;
import uy.edu.utec.servers.dtos.SectorDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearUbicacionDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class IngresoUbicacionInterfaz {
    private JTextField camaInput;
    private JTextField pisoInput;
    private JTextField numeroInput;
    private JTextField nombreInput;
    private JButton buttonIngresar;
    private JButton cancelarButton;
    private JPanel panel;
    private JComboBox sectorComboBox;
    private JComboBox institucionComboBox;
    private JComboBox comboBox1;

    private ControllerGeneral controllerGeneral;
    private UbicacionController ubicacionController;
    private JFrame frame;
    public IngresoUbicacionInterfaz(ControllerGeneral controllerGeneral) {
        this.controllerGeneral = controllerGeneral;
        frame = new JFrame("Ingreso de ubicación.");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 500);

        //combobox perfil
        DefaultComboBoxModel<String> comboBoxModelPerfil = new DefaultComboBoxModel<>();
        SectorController sectorController = new SectorController(controllerGeneral);
        List<SectorDTO> sectores = sectorController.obtenerTodosLosSectores();
        for(SectorDTO sectorDTO : sectores){
            comboBoxModelPerfil.addElement(String.valueOf(sectorDTO.getNombre()));
        }

        sectorComboBox.setModel(comboBoxModelPerfil);

        //combobox Instituciones
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
                mostrarMensaje("El ingreso ubicación ha sido cancelado");
                controllerGeneral.mostrarMenuInterfaz(true);
                panel.setVisible(false);
            }
        });
        buttonIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mensajeConfirmacion()) {
                    try {
                        agregarUbicacion();


                    } catch (ServiciosException | NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null,
                                "Ha ocurrido un error al ingresar una ubicación: \n" + ex.getMessage());
                    }
                }
            }

            private void mostrarError(String mensaje) {
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error al ingresar una ubicación: \n" + mensaje);
            }
        });
    }

    public void agregarUbicacion() throws ServiciosException {
        InstitucionController institucionController = new InstitucionController(controllerGeneral);
        SectorController sectorController = new SectorController(controllerGeneral);
        CrearUbicacionDTO crearUbicacionDTO = new CrearUbicacionDTO();
        crearUbicacionDTO.setInstitucionId(institucionController.obtenerInstitucionPorNombre(institucionComboBox.getSelectedItem().toString()).getId());
        crearUbicacionDTO.setNombre(nombreInput.getText().trim().toUpperCase());



        if(numeroInput.getText().matches(".[a-zA-Z].")){
            mostrarMensaje("El campo número no puede contener letras");
            return;
        }

        if (numeroInput.getText().trim().isEmpty()){
            throw new ServiciosException("No se puede ingresar el número de ubicación vacío.");
        }

        if(!camaInput.getText().matches("\\d*")){
            mostrarMensaje("El campo cama solo puede contener números.");
            return;
        }
        crearUbicacionDTO.setNumero(Integer.valueOf(numeroInput.getText()));

        try{
            crearUbicacionDTO.setCama(Integer.valueOf(camaInput.getText()));
        }catch (NullPointerException | NumberFormatException e){
            System.out.println();
        }

        crearUbicacionDTO.setPiso(comboBox1.getSelectedItem().toString());
        crearUbicacionDTO.setSectorId(sectorController.obtenerSectorPorNombre(sectorComboBox.getSelectedItem().toString()).getId());

        controllerGeneral.getUbicacionController().agregarUbicacion(crearUbicacionDTO);

        mostrarMensaje("¡Ubicación agregada con éxito!");
        volverPrincipal();
    }

    public void setVisible(boolean visible){
        frame.setVisible(visible);
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

    public JPanel getPanel(){
        return panel;
    }
}

