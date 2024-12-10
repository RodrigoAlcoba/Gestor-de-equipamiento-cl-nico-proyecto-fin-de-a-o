package uy.edu.utec.view.Interfaces.Principales;

import uy.edu.utec.controller.ControllerGeneral;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.view.Interfaces.TipoIntervencion.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PanelTipoIntervencion {
    private JPanel cardPanel;
    private JButton agregarTipoDeIntervenciónButton;
    private JButton listarTipoDeIntervencionesButton;
    private JPanel panel;
    private JButton darDeBajaTipoButton;
    private JButton modificarIntervencionButton;
    private JButton activarTipoIntervencionButton;

    //
    private ControllerGeneral controllerGeneral;
    private CardLayout cardLayout;

    //Interfaces
    private IngresoTipoIntervencionInterfaz ingresoTipoIntervencionInterfaz;
    private BajaTipoIntervencionInterfaz bajaTipoIntervencionInterfaz;
    private ListadoTipoIntervencionInterfaz listadoTipoIntervencionInterfaz;
    private ActivarTipoIntervencionInterfaz activarTipoIntervencionInterfaz;
    private ModificarTipoIntervencionInterfaz modificarTipoIntervencionInterfaz;

    //Paneles
    private JPanel agregarTipoIntervencion;
    private JPanel bajaTipoIntervencion;
    private JPanel listadoTipoIntervencion;
    private JPanel activarTipoIntervencion;
    private JPanel modificarTipoIntervencion;

    public PanelTipoIntervencion(ControllerGeneral controllerGeneral) throws ServiciosException {
        this.controllerGeneral = controllerGeneral;

        cardLayout = new CardLayout(0, 0);
        cardPanel.setLayout(cardLayout);

        //interfaces

        ingresoTipoIntervencionInterfaz = new IngresoTipoIntervencionInterfaz(controllerGeneral);
        bajaTipoIntervencionInterfaz = new BajaTipoIntervencionInterfaz(controllerGeneral);
        listadoTipoIntervencionInterfaz = new ListadoTipoIntervencionInterfaz(controllerGeneral);
        activarTipoIntervencionInterfaz = new ActivarTipoIntervencionInterfaz(controllerGeneral);
        modificarTipoIntervencionInterfaz = new ModificarTipoIntervencionInterfaz(controllerGeneral);

        //Paneles
        agregarTipoIntervencion = ingresoTipoIntervencionInterfaz.getPanel();
        bajaTipoIntervencion = bajaTipoIntervencionInterfaz.getPanel();
        listadoTipoIntervencion = listadoTipoIntervencionInterfaz.getPanel();
        activarTipoIntervencion = activarTipoIntervencionInterfaz.getPanel();
        modificarTipoIntervencion = modificarTipoIntervencionInterfaz.getPanel();

        //agregamos y sacamos visibilidad

        cardPanel.add(agregarTipoIntervencion, "agregarTipoIntervencion");
        cardPanel.add(bajaTipoIntervencion, "bajaTipoIntervencion");
        cardPanel.add(listadoTipoIntervencion, "listadoTipoIntervencion");
        cardPanel.add(activarTipoIntervencion, "activarTipoIntervencion");
        agregarTipoIntervencion.setVisible(false);
        bajaTipoIntervencion.setVisible(false);
        listadoTipoIntervencion.setVisible(false);
        activarTipoIntervencion.setVisible(false);
        cardPanel.add(modificarTipoIntervencion, "modificarTipoIntervencion");
        modificarTipoIntervencion.setVisible(false);
        //listeners


        agregarTipoDeIntervenciónButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "agregarTipoIntervencion");
                try {
                    listadoTipoIntervencionInterfaz.actualizarComboBox();
                } catch (SQLException | ServiciosException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        listarTipoDeIntervencionesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    listadoTipoIntervencionInterfaz.actualizarComboBox();
                } catch (SQLException | ServiciosException ex) {
                    throw new RuntimeException(ex);
                }
                cardLayout.show(cardPanel, "listadoTipoIntervencion");
            }
        });
        darDeBajaTipoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                bajaTipoIntervencionInterfaz.actualizarComboBox();
                cardLayout.show(cardPanel, "bajaTipoIntervencion");
            }
        });
        activarTipoIntervencionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                activarTipoIntervencionInterfaz.actualizarComboBox();
                cardLayout.show(cardPanel, "activarTipoIntervencion");
            }
        });
        modificarIntervencionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    modificarTipoIntervencionInterfaz.actualizarComboBox();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ServiciosException ex) {
                    throw new RuntimeException(ex);
                }
                cardLayout.show(cardPanel, "modificarTipoIntervencion");
            }
        });
    }

    public JPanel getPanel() {
        return cardPanel;
    }

}
