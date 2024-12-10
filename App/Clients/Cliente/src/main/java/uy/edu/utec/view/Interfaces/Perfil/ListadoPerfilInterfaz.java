package uy.edu.utec.view.Interfaces.Perfil;

import uy.edu.utec.controller.ControllerGeneral;
import uy.edu.utec.servers.dtos.PerfilDTO;
import uy.edu.utec.servers.dtos.UsuarioDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ListadoPerfilInterfaz {

    private JPanel pannel;
    private JComboBox comboBoxEstado;
    private JRadioButton cbNombre;
    private JButton buttonBuscar;
    private JButton buttonCancelar;
    private JTextField textFieldNombre;
    private JScrollPane scrollPane;
    private JTable tablePerfil;
    private JButton limpiarButton;
    private JRadioButton cbEstado;
    private JPanel panel;
    private ButtonGroup radioButtons = new ButtonGroup();

    //
    private List<JLabel> labels = new ArrayList<>();
    ControllerGeneral controllerGeneral;


    public ListadoPerfilInterfaz(ControllerGeneral controllerGeneral) {
        this.controllerGeneral = controllerGeneral;
        radioButtons.add(cbNombre);
        radioButtons.add(cbEstado);

        buttonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerGeneral.mostrarMenuInterfaz(true);
                panel.setVisible(false);
            }
        });

        //JTable
        DefaultTableModel modeloTabla = new DefaultTableModel(
                new Object[]{"Nombre", "Estado"}, 0);
        tablePerfil.setRowHeight(40);
        tablePerfil.setAutoCreateRowSorter(true);
        tablePerfil.setRowMargin(5);
        tablePerfil.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        scrollPane.setViewportView(tablePerfil);
        tablePerfil.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tablePerfil.setModel(modeloTabla);


        buttonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // perfiles = controllerGeneral.getPerfilController().obtenerTodosPerfiles();
                List<PerfilDTO> perfiles = new ArrayList<>();
                if (cbNombre.isSelected()){
                    perfiles =
                            controllerGeneral.getPerfilController().obtenerTodosLosPerfilesPorNombre(textFieldNombre.getText());
                }else if(cbEstado.isSelected()) {
                    if (comboBoxEstado.getSelectedItem().toString().equals("ACTIVO")) {
                        perfiles = controllerGeneral.getPerfilController().obtenerPerfilesActivos();
                    } else if (comboBoxEstado.getSelectedItem().toString().equals("ELIMINADO")) {
                        perfiles = controllerGeneral.getPerfilController().obtenerPerfilesEliminados();
                    }
                }else {
                    perfiles = controllerGeneral.getPerfilController().obtenerTodosPerfiles();
                }

                if(textFieldNombre.getText().length() > 40){
                    mostrarMensaje("El nombre del perfil debe ser menor a 40 caracteres.");
                }else{
                    mostrarListadoPerfiles(perfiles);
                }
            }
        });

        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                radioButtons.clearSelection();
                ((DefaultTableModel) tablePerfil.getModel()).setRowCount(0);
            }
        });

    }

    public void mostrarListadoPerfiles(List<PerfilDTO> perfiles)  {
        ((DefaultTableModel) tablePerfil.getModel()).setRowCount(0);
        //Para añadir una fila nueva con los datos de un perfiles
        for(PerfilDTO perfilDTO : perfiles){
            Object[] filaUsuario = {
                    perfilDTO.getNomPerfil(),
                    perfilDTO.getEstado()
            };
            ((DefaultTableModel)  tablePerfil.getModel()).addRow(filaUsuario);
        }

    }

    //cardlayout
    public JPanel getPanel() {
        return panel;
    }
    public void setVisible(boolean visible) {
        panel.setVisible(visible);
    }
    public void volverPrincipal(){
        controllerGeneral.mostrarMenuInterfaz(true);
        panel.setVisible(false);
    }

    public void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje);
    }


}
