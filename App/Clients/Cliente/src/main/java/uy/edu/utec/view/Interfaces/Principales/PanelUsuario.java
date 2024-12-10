package uy.edu.utec.view.Interfaces.Principales;

import uy.edu.utec.controller.ControllerGeneral;
import uy.edu.utec.controller.PaisController;
import uy.edu.utec.controller.PerfilController;
import uy.edu.utec.controller.UsuarioController;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.view.Interfaces.Equipo.BajaEquipoInterfaz;
import uy.edu.utec.view.Interfaces.Usuario.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;

public class PanelUsuario {
    private JPanel cardPanel;
    private JPanel panel;
    private JButton modificarMisDatosButton;
    private JButton activarUsuarioBoton;
    private JButton bajaUsuarioButton;
    private JButton listadoUsuariosButton;
    private JButton modificarUsuarioButton;
    private static Boolean funcionalidadesSeteadas = false;

    //
    ControllerGeneral controllerGeneral;
    CardLayout cardLayout;

    //Interfaces

    ActivarUsuarioInterfaz activarUsuarioInterfaz;
    ModificarMisDatosInterfaz modificarMisDatosInterfaz;
    BajaUsuarioInterfaz bajaUsuarioInterfaz;
    ListadoDeUsuariosInterfaz listadoDeUsuariosInterfaz;
    ModificarUsuarioInterfaz modificarUsuarioInterfaz;




    //Paneles
    JPanel activarUsuario;
    JPanel modificarMisDatos;
    JPanel bajaUsuario;
    JPanel listadoUsuario;
    JPanel modificarUsuario;


    public PanelUsuario(ControllerGeneral controllerGeneral) throws ServiciosException {
        this.controllerGeneral = controllerGeneral;
        cardLayout = new CardLayout(0,0);
        cardPanel.setLayout(cardLayout);

        //interfaces
        activarUsuarioInterfaz = new ActivarUsuarioInterfaz(controllerGeneral);
        modificarMisDatosInterfaz = new ModificarMisDatosInterfaz(controllerGeneral);
        bajaUsuarioInterfaz = new BajaUsuarioInterfaz(controllerGeneral);
        listadoDeUsuariosInterfaz = new ListadoDeUsuariosInterfaz(controllerGeneral);
        modificarUsuarioInterfaz = new ModificarUsuarioInterfaz(controllerGeneral);
        //Paneles
        activarUsuario = activarUsuarioInterfaz.getPanel();
        modificarMisDatos = modificarMisDatosInterfaz.getPanel();
        bajaUsuario = bajaUsuarioInterfaz.getPanel();
        listadoUsuario = listadoDeUsuariosInterfaz.getPanel();
        modificarUsuario = modificarUsuarioInterfaz.getPanel();


        //se agrega y se saca visibilidad
        cardPanel.add(activarUsuario, "reactivarUsuario");
        cardPanel.add(modificarMisDatos, "modificarMisDatos");
        cardPanel.add(bajaUsuario, "bajaUsuario");
        cardPanel.add(listadoUsuario, "listadoUsuario");
        cardPanel.add(modificarUsuario, "modificarUsuario");

        activarUsuario.setVisible(false);
        modificarMisDatos.setVisible(false);
        bajaUsuario.setVisible(false);
        listadoUsuario.setVisible(false);
        modificarUsuario.setVisible(false);

        //listeners

        modificarMisDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // modificarMisDatos.setVisible(true);
                cardLayout.show(cardPanel, "modificarMisDatos");
                try {
                    modificarMisDatosInterfaz.preRellenarCampos();
                } catch (ServiciosException ex) {
                    throw new RuntimeException(ex);
                }
                activarUsuario.setVisible(false);
                panel.setVisible(false);
            }
        });
        activarUsuarioBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    activarUsuarioInterfaz.actualizarComboBox();
                } catch (SQLException | ServiciosException ex) {
                    throw new RuntimeException(ex);
                }
                activarUsuario.setVisible(true);
                cardLayout.show(cardPanel, "activarUsuario");
                modificarMisDatos.setVisible(false);
                panel.setVisible(false);
            }
        });
        bajaUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    bajaUsuarioInterfaz.actualizarComboBox();
                    cardLayout.show(cardPanel, "bajaUsuario");
                } catch (SQLException | ServiciosException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        modificarUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                cardLayout.show(cardPanel, "modificarUsuario");
            }
        });

        listadoUsuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listadoUsuario.setVisible(true);
                cardLayout.show(cardPanel, "listarUsuario");
                modificarMisDatos.setVisible(false);
                panel.setVisible(false);
            }
        });

        panel.addMouseMotionListener(new MouseMotionAdapter() {
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
    }

    public JPanel getPanel(){
        return cardPanel;
    }

    public void funcionalidadesSegunPerfil() throws ServiciosException {
        UsuarioController usuarioController= new UsuarioController(controllerGeneral);
        PerfilController perfilController = new PerfilController(controllerGeneral);
        String perfil = perfilController.obtenerPerfilPorId(usuarioController.getUsuarioActivo().getPerfilId()).getNomPerfil();
        if(perfil.equals("AUXILIAR ADMINISTRATIVO")){
            modificarMisDatosButton.setEnabled(true);
            activarUsuarioBoton.setEnabled(true);
            bajaUsuarioButton.setEnabled(true);
            listadoUsuariosButton.setEnabled(true);
            modificarUsuarioButton.setEnabled(true);
            modificarMisDatosButton.setVisible(true);
            activarUsuarioBoton.setVisible(true);
            bajaUsuarioButton.setVisible(true);
            listadoUsuariosButton.setVisible(true);
            modificarUsuarioButton.setVisible(true);


        }else if(perfil.equals("INGENIERO BIOMEDICO") || perfil.equals("TECNOLOGO")|| perfil.equals("TECNICO")){
            modificarMisDatosButton.setVisible(true);
            activarUsuarioBoton.setVisible(false);
            bajaUsuarioButton.setVisible(false);
            listadoUsuariosButton.setVisible(false);
            modificarUsuarioButton.setVisible(false);

        }
    }

    public static void setFuncionalidadesSeteadas(Boolean bool ) {
        funcionalidadesSeteadas = bool ;
    }
}
