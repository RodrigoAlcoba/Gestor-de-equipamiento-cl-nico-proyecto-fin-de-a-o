package uy.edu.utec.view.Interfaces.Principales;


import uy.edu.utec.controller.ControllerGeneral;
import uy.edu.utec.controller.UsuarioController;
import uy.edu.utec.servers.dtos.PerfilDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.view.Interfaces.Equipo.BajaEquipoInterfaz;
import uy.edu.utec.view.Interfaces.Equipo.IngresoEquipo;
import uy.edu.utec.view.Interfaces.Equipo.ModificarEquipoInterfaz;
import uy.edu.utec.view.Interfaces.Equipo.ReporteEquiposInterfaz;
import uy.edu.utec.view.Interfaces.Equipo.ReactivarEquipoInterfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PanelEquipo {
    private JPanel cardPanel;
    private JPanel panel;
    private JButton ingresarEquipoButton;
    private JButton bajaEquipoButton;
    private JButton modificarEquipoButton;
    private JButton reporteEquipoButton;
    private JButton reactivarEquipoButton;
    //
    private CardLayout cardLayout;

    private ControllerGeneral controllerGeneral;

    //Interfaces
    private IngresoEquipo ingresoEquipoInterfaz;

    private BajaEquipoInterfaz bajaEquipoInterfaz;

    private ModificarEquipoInterfaz modificarEquipoInterfaz;
    private ReporteEquiposInterfaz reporteEquiposInterfaz;
    private ReactivarEquipoInterfaz reactivarEquipoInterfaz;

    //Paneles
    private JPanel ingresoEquipo;
    private JPanel bajaEquipo;

    private JPanel modificarEquipo;
    private JPanel panelReporteEquipo;
    private JPanel reactivarEquipo;


    public PanelEquipo(ControllerGeneral controller) throws SQLException, ServiciosException {
        this.controllerGeneral = controller;
        cardLayout = new CardLayout(0,0);
        cardPanel.setLayout(cardLayout);
        UsuarioController usuarioController = new UsuarioController(controllerGeneral);


        //interfaz

        ingresoEquipoInterfaz = new IngresoEquipo(controller);
        bajaEquipoInterfaz = new BajaEquipoInterfaz(controller);
        modificarEquipoInterfaz = new ModificarEquipoInterfaz(controller);
        reporteEquiposInterfaz = new ReporteEquiposInterfaz(controller);
        reactivarEquipoInterfaz = new ReactivarEquipoInterfaz(controller);

        //paneles
        ingresoEquipo = ingresoEquipoInterfaz.getPanel();
        bajaEquipo = bajaEquipoInterfaz.getPanel();
        modificarEquipo = modificarEquipoInterfaz.getPanel();
        panelReporteEquipo = reporteEquiposInterfaz.getPanel();
        reactivarEquipo = reactivarEquipoInterfaz.getPanel();

        //agregamos paneles y sacamos visibilidad

        cardPanel.add(ingresoEquipo, "ingresoEquipo");
        cardPanel.add(bajaEquipo, "bajaEquipo");
        cardPanel.add(modificarEquipo, "modificarEquipo");
        cardPanel.add(panelReporteEquipo, "panelReporteEquipo");
        cardPanel.add(reactivarEquipo, "reactivarEquipo");

        ingresoEquipo.setVisible(false);
        bajaEquipo.setVisible(false);
        modificarEquipo.setVisible(false);
        panelReporteEquipo.setVisible(false);
        reactivarEquipo.setVisible(false);


        ingresarEquipoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "ingresoEquipo");
                try {
                    ingresoEquipoInterfaz.actualizarComboBox();
                    modificarEquipoInterfaz.actualizarComboBox();
                } catch (ServiciosException | SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        bajaEquipoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    bajaEquipoInterfaz.actualizarComboBox();
                } catch (SQLException | ServiciosException ex) {
                    throw new RuntimeException(ex);
                }

                cardLayout.show(cardPanel, "bajaEquipo");
            }
        });
        modificarEquipoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    modificarEquipoInterfaz.actualizarComboBox();
                } catch (SQLException | ServiciosException ex) {
                    throw new RuntimeException(ex);
                }
                cardLayout.show(cardPanel, "modificarEquipo");
            }
        });
        reporteEquipoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelReporteEquipo.setVisible(true);
                cardLayout.show(cardPanel, "reporteEquipo");
                modificarEquipo.setVisible(false);
                panel.setVisible(false);
            }
        });
        reactivarEquipoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    reactivarEquipoInterfaz.actualizarComboBox();
                } catch (SQLException | ServiciosException ex) {
                    throw new RuntimeException(ex);
                }
                cardLayout.show(cardPanel, "reactivarEquipo");
            }
        });
    }

    //Se utiliza en el menuInterfaz
    public JPanel getPanel(){
        return cardPanel;
    }


}
