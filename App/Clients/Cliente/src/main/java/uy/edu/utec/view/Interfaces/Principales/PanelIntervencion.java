package uy.edu.utec.view.Interfaces.Principales;

import uy.edu.utec.controller.ControllerGeneral;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.view.Interfaces.Intervencion.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PanelIntervencion {
    private JPanel cardPanel;
    private JPanel panel;
    private JButton registroIntervencion;
    private JButton trabajarIntervencionButton;
    private JButton reporteIDEquipoButton;
    private JButton reportePorTipoButton;
    private JButton reporteTrabajarIntervencion;

    //
    private ControllerGeneral controllerGeneral;
    private CardLayout cardLayout;

    //interfaces
    private InterfazRegistroDeIntervencion interfazRegistroDeIntervencion;
    private TrabajarIntervencionInterfaz trabajarIntervencionInterfaz;
    private ReportePorIDEquipoInterfaz reportePorIDEquipoInterfaz;
    private ReportePorTipoIntervencionInterfaz reportePorTipoIntervencionInterfaz;
    private ReporteSeguimientoPorIDInterfaz reporteSeguimientoPorIDInterfaz;

    //Paneles
    private JPanel panelRegistroIntervancion;
    private JPanel panelTrabajarIntervencion;
    private JPanel panelReporteIntervencionEquipo;
    private JPanel panelReportePorTipo;
    private JPanel panelReporteSeguimiento;


    public PanelIntervencion(ControllerGeneral controllerGeneral) throws SQLException, ServiciosException {
        this.controllerGeneral = controllerGeneral;
        cardLayout = new CardLayout(0,0);
        cardPanel.setLayout(cardLayout);

        //interfaces
        interfazRegistroDeIntervencion = new InterfazRegistroDeIntervencion(controllerGeneral);
        trabajarIntervencionInterfaz = new TrabajarIntervencionInterfaz(controllerGeneral);
        reportePorIDEquipoInterfaz= new ReportePorIDEquipoInterfaz(controllerGeneral);
        reportePorTipoIntervencionInterfaz = new ReportePorTipoIntervencionInterfaz(controllerGeneral);
        reporteSeguimientoPorIDInterfaz = new ReporteSeguimientoPorIDInterfaz(controllerGeneral);
        //paneles
        panelRegistroIntervancion = interfazRegistroDeIntervencion.getPanel();
        panelTrabajarIntervencion = trabajarIntervencionInterfaz.getPanel();
        panelReporteIntervencionEquipo = reportePorIDEquipoInterfaz.getPanel();
        panelReportePorTipo = reportePorTipoIntervencionInterfaz.getPanel();
        panelReporteSeguimiento = reporteSeguimientoPorIDInterfaz.getPanel();
        //agregamos y sacamos visibilidad
        cardPanel.add(panelRegistroIntervancion, "panelRegistroIntervencion");
        panelRegistroIntervancion.setVisible(false);

        cardPanel.add(panelTrabajarIntervencion, "panelTrabajarIntervencion");
        panelTrabajarIntervencion.setVisible(false);

        cardPanel.add(panelReporteIntervencionEquipo, "panelReporteIntervencionEquipo");
        panelReporteIntervencionEquipo.setVisible(false);

        cardPanel.add(panelReportePorTipo, "panelReportePorTipo");
        panelReportePorTipo.setVisible(false);

        cardPanel.add(panelReporteSeguimiento, "panelReporteSeguimiento");
        //actionListeners

        registroIntervencion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    interfazRegistroDeIntervencion.actualizarCombox();
                } catch (ServiciosException | SQLException ex) {
                    throw new RuntimeException(ex);
                }
                cardLayout.show(cardPanel, "panelRegistroIntervencion");
            }
        });
        trabajarIntervencionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "panelTrabajarIntervencion");
                trabajarIntervencionInterfaz.actualizarComboBox();
            }
        });
        reporteIDEquipoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "panelReporteIntervencionEquipo");
            }
        });
        reportePorTipoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "panelReportePorTipo");
            }
        });
        reporteTrabajarIntervencion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    reporteSeguimientoPorIDInterfaz.actualizarComboBox();
                } catch (SQLException | ServiciosException e) {
                    throw new RuntimeException(e);
                }
                cardLayout.show(cardPanel, "panelReporteSeguimiento");
            }
        });
    }

    public JPanel getPanel(){
        return cardPanel;
    }
}
