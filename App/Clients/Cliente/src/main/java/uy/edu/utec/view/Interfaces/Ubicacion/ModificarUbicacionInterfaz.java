package uy.edu.utec.view.Interfaces.Ubicacion;

import uy.edu.utec.controller.ControllerGeneral;
import uy.edu.utec.controller.SectorController;
import uy.edu.utec.controller.UbicacionController;
import uy.edu.utec.servers.dtos.SectorDTO;
import uy.edu.utec.servers.dtos.UbicacionDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ModificarUbicacionInterfaz {
    private JTextField nombreInput;
    private JTextField numeroInput;
    private JTextField camaInput;
    private JButton buttonIngresar;
    private JButton buttonCancelar;
    private JPanel panel;
    private JComboBox ubicacionesComboBox;
    private JComboBox sectoresComboBox;
    private JComboBox pisoComboBox;

    private ControllerGeneral controllerGeneral;
    private JFrame frame;
    public ModificarUbicacionInterfaz(ControllerGeneral controllerGeneral) throws ServiciosException {
        actualizarComboBox();
        this.controllerGeneral = controllerGeneral;
        frame = new JFrame("Listado Tipo Intervención");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 500);


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
                if (mensajeConfirmacion()) {
                    try {
                        actualizarComboBox();
                        modificarUbicacion();

                    } catch (ServiciosException ex) {
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error al modificar una ubicación: \n" + ex.getMessage());
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

    public void volverPrincipal(){
        controllerGeneral.mostrarMenuInterfaz(true);
        panel.setVisible(false);
    }

    public void mostrarMensaje(String mensaje){

        JOptionPane.showMessageDialog(null, mensaje);
    }
    public void modificarUbicacion() throws ServiciosException {
        UbicacionController ubicacionController = new UbicacionController(controllerGeneral);
        SectorController sectorController = new SectorController(controllerGeneral);
        UbicacionDTO ubicacionDTO = ubicacionController.obtenerUbicacionPorNombre(ubicacionesComboBox.getSelectedItem().toString());

        try{
            ubicacionDTO.setCama(Integer.valueOf(camaInput.getText()));
        }catch (NullPointerException | NumberFormatException e){
            System.out.println();
        }

        if(!camaInput.getText().matches("\\d*")){
            mostrarMensaje("El campo cama solo puede contener números.");
            return;
        }

        if(!numeroInput.getText().matches("\\d*")){
            mostrarMensaje("El campo número solo puede contener números.");
            return;
        }
        ubicacionDTO.setNombre(nombreInput.getText());
        ubicacionDTO.setPiso(pisoComboBox.getSelectedItem().toString());
        if (numeroInput.getText().trim().isEmpty()){
            throw new ServiciosException("No se puede ingresar el número de ubicación vacío.");
        }
        ubicacionDTO.setNumero(Integer.valueOf(numeroInput.getText()));
        ubicacionDTO.setSectorId(sectorController.obtenerSectorPorNombre(sectoresComboBox.getSelectedItem().toString()).getId());

        ubicacionController.modificarUbicacion(ubicacionDTO);

        mostrarMensaje("¡Ubicación modificada con éxito!");
        volverPrincipal();
    }

    public void actualizarComboBox() throws ServiciosException {

        //COMBO BOX UBICACIONES
        DefaultComboBoxModel<String> comboBoxModelUbicacion = new DefaultComboBoxModel<>();
        UbicacionController ubicacionController = new UbicacionController(controllerGeneral);
        List<UbicacionDTO> ubicacionList = ubicacionController.obtenerTodasLasUbicaciones();
        for(UbicacionDTO  ubicacion : ubicacionList){
            comboBoxModelUbicacion.addElement(ubicacion.getNombre());
        }
        ubicacionesComboBox.setModel(comboBoxModelUbicacion);

        //COMBOBOX SECTOR
        DefaultComboBoxModel<String> comboBoxModelSector = new DefaultComboBoxModel<>();
        SectorController sectorController = new SectorController(controllerGeneral);
        List<SectorDTO> sectorDTOList = sectorController.obtenerTodosLosSectores();
        for(SectorDTO  sectores : sectorDTOList){
            comboBoxModelSector.addElement(sectores.getNombre().toString());
        }

        sectoresComboBox.setModel(comboBoxModelSector);

    }

    public Boolean mensajeConfirmacion() {
        Object[] opciones = {"Confirmar", "Cancelar"};
        int respuesta = JOptionPane.showOptionDialog(null, "¿Desea confirmar la acción?", "Confirmación", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        return respuesta == JOptionPane.YES_OPTION;
    }
}



