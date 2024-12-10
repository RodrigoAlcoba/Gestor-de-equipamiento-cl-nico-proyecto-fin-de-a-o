package uy.edu.utec.view.Interfaces.Principales;

import uy.edu.utec.controller.ControllerGeneral;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.view.Interfaces.Ubicacion.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PanelUbicacion {
    private JPanel cardPanel;
    private JPanel panel;
    private JButton ingresarUbicacionButton;
    private JButton bajaUbicacionButton;
    private JButton listadoDeMovimientosButton;
    private JButton modificarUbicacionButton;
    private JButton movimientoEquipoButton;

    //
    private ControllerGeneral controllerGeneral;
    private CardLayout cardLayout;

    //Interfaces
    private BajaUbicacionInterfaz bajaUbicacionInterfaz;
    private IngresoUbicacionInterfaz ingresoUbicacionInterfaz;
    private ListadoMovimientosEquipoInterfaz listadoMovimientosEquipoInterfaz;
    private ModificarUbicacionInterfaz modificarUbicacionInterfaz;
    private MovimientoDeEquipoInterfaz movimientoDeEquipoInterfaz;



    //Paneles
    private JPanel bajaUbicacion;
    private JPanel ingresoUbicacion;
    private JPanel listadoMovimientoEquipos;
    private JPanel modificarUbicacion;
    private JPanel movimientoEquipo;

    public PanelUbicacion(ControllerGeneral controllerGeneral) throws ServiciosException, SQLException {
        this.controllerGeneral = controllerGeneral;

        cardLayout = new CardLayout(0,0);
        cardPanel.setLayout(cardLayout);

        //interfaces
        bajaUbicacionInterfaz = new BajaUbicacionInterfaz(controllerGeneral);
        movimientoDeEquipoInterfaz = new MovimientoDeEquipoInterfaz(controllerGeneral);
        modificarUbicacionInterfaz = new ModificarUbicacionInterfaz(controllerGeneral);
        listadoMovimientosEquipoInterfaz = new ListadoMovimientosEquipoInterfaz(controllerGeneral);
        ingresoUbicacionInterfaz = new IngresoUbicacionInterfaz(controllerGeneral);

        //paneles
        ingresoUbicacion = ingresoUbicacionInterfaz.getPanel();
        listadoMovimientoEquipos = listadoMovimientosEquipoInterfaz.getPanel();
        bajaUbicacion = bajaUbicacionInterfaz.getPanel();
        modificarUbicacion = modificarUbicacionInterfaz.getPanel();
        movimientoEquipo = movimientoDeEquipoInterfaz.getPanel();

        //agregamos y sacamos visibilidad

        cardPanel.add(ingresoUbicacion, "ingresoUbicacion");
        ingresoUbicacion.setVisible(false); //importante
        cardPanel.add(listadoMovimientoEquipos, "listadoMovimientoEquipos");
        listadoMovimientoEquipos.setVisible(false);
        cardPanel.add(bajaUbicacion, "bajaUbicacion");
        bajaUbicacion.setVisible(false);
        cardPanel.add(movimientoEquipo, "movimientoEquipo");
        movimientoEquipo.setVisible(false);
        cardPanel.add(modificarUbicacion, "modificarUbicacion");
        modificarUbicacion.setVisible(false);

        //actionListeners


        ingresarUbicacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ingresoUbicacion.setVisible(true);
                cardLayout.show(cardPanel, "ingresarUbicacion");
                panel.setVisible(false);

            }
        });
        bajaUbicacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    bajaUbicacionInterfaz.actualizarComboBox();
                } catch (ServiciosException ex) {
                    throw new RuntimeException(ex);
                }
                bajaUbicacion.setVisible(true);
                cardLayout.show(cardPanel, "bajaUbicacion");
                modificarUbicacion.setVisible(false);
                panel.setVisible(false);


            }
        });
        listadoDeMovimientosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listadoMovimientoEquipos.setVisible(true);
                cardLayout.show(cardPanel, "listadoUbicacion");
                panel.setVisible(false);

            }
        });
        modificarUbicacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    modificarUbicacionInterfaz.actualizarComboBox();
                } catch (ServiciosException ex) {
                    throw new RuntimeException(ex);
                }
                modificarUbicacion.setVisible(true);
                cardLayout.show(cardPanel, "modificarUbicacion");
                panel.setVisible(false);
            }
        });
        movimientoEquipoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    movimientoDeEquipoInterfaz.actualizarComboBox();
                } catch (ServiciosException | SQLException ex) {
                    throw new RuntimeException(ex);
                }
                movimientoEquipo.setVisible(true);
                cardLayout.show(cardPanel, "movimientoUbicacion");
                panel.setVisible(false);

            }
        });
    }

    public JPanel getPanel(){
        return cardPanel;
    }

}
