package uy.edu.utec.view.Interfaces.Principales;

import uy.edu.utec.controller.ControllerGeneral;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.view.Interfaces.Perfil.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PanelPerfil {
    private JPanel cardPanel;
    private JButton cambiarButton;
    private JPanel panel;
    private JButton listarPerfilButton;
    private JButton bajaPerfilButton;
    private JButton reactivarPerfilButton;
    private JButton modificarPerfilButton;
    private CardLayout cardLayout;
    //
    private ControllerGeneral controllerGeneral;

    //Interfaces
    private IngresarPerfilInterfaz ingresarPerfilInterfaz;
    private ListadoPerfilInterfaz listadoPerfilInterfaz;
    private BajaPerfilInterfaz bajaPerfilInterfaz;

    private ReactivarPerfilInterfaz reactivarPerfilInterfaz;
    //private ModificarPerfilInterfaz modificarPerfilInterfaz;

    //Paneles
    private JPanel ingresarPerfil;
    private JPanel listadoPerfil;
    private JPanel bajaPerfil;
    private JPanel reactivarPerfil;



    public PanelPerfil(ControllerGeneral controllerGeneral) throws ServiciosException {
       // cardPanel.add(panel);
        this.controllerGeneral = controllerGeneral;

        cardLayout = new CardLayout(0,0);
        cardPanel.setLayout(cardLayout);
        //interfaces
        ingresarPerfilInterfaz = new IngresarPerfilInterfaz(controllerGeneral);
        listadoPerfilInterfaz = new ListadoPerfilInterfaz(controllerGeneral);
        bajaPerfilInterfaz = new BajaPerfilInterfaz(controllerGeneral);
        reactivarPerfilInterfaz = new ReactivarPerfilInterfaz(controllerGeneral);
        //modificarPerfilInterfaz = new ModificarPerfilInterfaz(controllerGeneral);



        //Paneles
        ingresarPerfil = ingresarPerfilInterfaz.getPanel();
        listadoPerfil = listadoPerfilInterfaz.getPanel();
        bajaPerfil = bajaPerfilInterfaz.getPanel();
        reactivarPerfil = reactivarPerfilInterfaz.getPanel();


        //agregamos y sacamos visibilidad
        cardPanel.add(ingresarPerfil, "ingresarPerfil");
        ingresarPerfil.setVisible(false); //importante
        cardPanel.add(listadoPerfil, "listadoPerfil");
        listadoPerfil.setVisible(false);
        cardPanel.add(bajaPerfil, "bajaPerfil");
        bajaPerfil.setVisible(false);
        cardPanel.add(reactivarPerfil, "reactivarPerfil");
        reactivarPerfil.setVisible(false);



        //listeners
        cambiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "ingresarPerfil");
            }
        });
        listarPerfilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "listadoPerfil");
            }
        });
        bajaPerfilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    bajaPerfilInterfaz.actualizarComboBox();
                cardLayout.show(cardPanel, "bajaPerfil");
            }
        });
        reactivarPerfilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                reactivarPerfilInterfaz.actualizarComboBox();
            } catch (SQLException | ServiciosException ex) {
                throw new RuntimeException(ex);
            }
                cardLayout.show(cardPanel, "reactivarPerfil");
            }
        });
    }




    public JPanel getPanel(){
        return cardPanel;
    }

}
