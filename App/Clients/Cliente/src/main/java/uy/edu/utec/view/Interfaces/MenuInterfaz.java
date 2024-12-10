package uy.edu.utec.view.Interfaces;

import uy.edu.utec.controller.ControllerGeneral;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.controller.PerfilController;
import uy.edu.utec.controller.UsuarioController;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.view.Interfaces.Principales.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuInterfaz {
    private JPanel panel;
    private JPanel CardPanel;
    private JButton usuarioButton;
    private JButton perfilButton;
    private JButton equipoButton;
    private JButton ubicacionButton;
    private JButton intervencionButton;
    private JButton tipoIntervencionButton;
    private javax.swing.JPanel jPanel;
    private JButton botonCerrarSesion;
    //
    private JFrame frame;

    private CardLayout cardLayout;
    private ControllerGeneral controllerGeneral;
    private Boolean funcionalidadesSeteadas = false;

    public MenuInterfaz(ControllerGeneral controllerGeneral) throws SQLException, ServiciosException {
        frame = new JFrame("Menu");
        this.controllerGeneral = controllerGeneral;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(panel);


        //card Layout
        cardLayout = new CardLayout();
        CardPanel.setLayout(cardLayout);

        //paneles y los agregamos

        JPanel panelUsuario = new PanelUsuario(controllerGeneral).getPanel();
        CardPanel.add(panelUsuario, "panelUsuario");

        JPanel panelPerfil = new PanelPerfil(controllerGeneral).getPanel();
        CardPanel.add(panelPerfil, "panelPerfil");

        JPanel panelEquipo = new PanelEquipo(controllerGeneral).getPanel();
        CardPanel.add(panelEquipo, "panelEquipo") ;

        JPanel panelTipoIntervencion = new PanelTipoIntervencion(controllerGeneral).getPanel();
        CardPanel.add(panelTipoIntervencion, "panelTipoIntervencion");

        JPanel panelUbicacion = new PanelUbicacion(controllerGeneral).getPanel();
        CardPanel.add(panelUbicacion, "panelUbicacion");

        JPanel panelIntervencion = new PanelIntervencion(controllerGeneral).getPanel();
        CardPanel.add(panelIntervencion, "panelIntervencion");

        //le ponemos setVisible Falso
        panelPerfil.setVisible(false);
        panelUsuario.setVisible(false);
        panelEquipo.setVisible(false);
        panelTipoIntervencion.setVisible(false);
        panelUbicacion.setVisible(false);
        panelTipoIntervencion.setVisible(false);
        frame.pack();

        //listeners

        usuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelUsuario.setVisible(true);
                cardLayout.show(CardPanel, "panelUsurio");
                panelPerfil.setVisible(false);
                panelEquipo.setVisible(false);
                panelTipoIntervencion.setVisible(false);
                panelUbicacion.setVisible(false);
                panelIntervencion.setVisible(false);
            }
        });

        perfilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelPerfil.setVisible(true);
                cardLayout.show(CardPanel, "panelPerfil");
                panelUsuario.setVisible(false);
                panelEquipo.setVisible(false);
                panelTipoIntervencion.setVisible(false);
                panelUbicacion.setVisible(false);
                panelIntervencion.setVisible(false);
            }
        });
        equipoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelEquipo.setVisible(true);
                cardLayout.show(CardPanel, "panelEquipo");
                panelUsuario.setVisible(false);
                panelPerfil.setVisible(false);
                panelTipoIntervencion.setVisible(false);
                panelUbicacion.setVisible(false);
                panelIntervencion.setVisible(false);
            }
        });
        tipoIntervencionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelTipoIntervencion.setVisible(true);
                cardLayout.show(CardPanel, "panelTipoIntervencion");
                panelUsuario.setVisible(false);
                panelPerfil.setVisible(false);
                panelEquipo.setVisible(false);
                panelUbicacion.setVisible(false);
                panelIntervencion.setVisible(false);

            }
        });
        ubicacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelUsuario.setVisible(true);
                cardLayout.show(CardPanel, "panelUbicacion");
                panelUsuario.setVisible(false);
                panelPerfil.setVisible(false);
                panelEquipo.setVisible(false);
                panelTipoIntervencion.setVisible(false);
                panelIntervencion.setVisible(false);
            }
        });
        intervencionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelIntervencion.setVisible(true);
                cardLayout.show(CardPanel, "panelIntervencion");
                panelUsuario.setVisible(false);
                panelPerfil.setVisible(false);
                panelEquipo.setVisible(false);
                panelTipoIntervencion.setVisible(false);
                panelUbicacion.setVisible(false);
            }
        });
        CardPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                try {
                    if (!funcionalidadesSeteadas){
                        funcionalidadesSegunPerfil();
                        funcionalidadesSeteadas = true;
                    }
                } catch (ServiciosException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        jPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                try {
                    if (!funcionalidadesSeteadas){
                        funcionalidadesSegunPerfil();
                        funcionalidadesSeteadas = true;
                    }

                } catch (ServiciosException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        botonCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerGeneral.mostrarLogin(true);
                controllerGeneral.mostrarMenuInterfaz(false);
                controllerGeneral.getUsuarioController().setNombreUsuarioActivo(null);
                funcionalidadesSeteadas = false;
                PanelUsuario.setFuncionalidadesSeteadas(false);
            }
        });
    }

    public void funcionalidadesSegunPerfil() throws ServiciosException {
        UsuarioController usuarioController= new UsuarioController(controllerGeneral);
        PerfilController perfilController = new PerfilController(controllerGeneral);
        String perfil = perfilController.obtenerPerfilPorId(usuarioController.getUsuarioActivo().getPerfilId()).getNomPerfil();
        if(perfil.equals("AUXILIAR ADMINISTRATIVO")){
            usuarioButton.setEnabled(true);
            perfilButton.setEnabled(true);
            equipoButton.setEnabled(true);
            ubicacionButton.setEnabled(true);
            intervencionButton.setEnabled(true);
            perfilButton.setVisible(true);
            tipoIntervencionButton.setEnabled(true);
            tipoIntervencionButton.setVisible(true);

        }else if(perfil.equals("INGENIERO BIOMEDICO") || perfil.equals("TECNOLOGO")|| perfil.equals("TECNICO")){
            usuarioButton.setEnabled(true);
            perfilButton.setVisible(false);
            equipoButton.setEnabled(true);
            ubicacionButton.setEnabled(true);
            intervencionButton.setEnabled(true);
            tipoIntervencionButton.setVisible(false);

        }
    }

    //set visible
    public void setVisible(boolean visible){
        frame.setVisible(visible);
    }

    public void setFuncionalidadesSeteadas(Boolean funcionalidadesSeteadas) {
        this.funcionalidadesSeteadas = funcionalidadesSeteadas;
    }
}
