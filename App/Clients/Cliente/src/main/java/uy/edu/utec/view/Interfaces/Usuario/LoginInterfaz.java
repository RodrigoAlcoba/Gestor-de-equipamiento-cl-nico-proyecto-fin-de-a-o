package uy.edu.utec.view.Interfaces.Usuario;

import uy.edu.utec.controller.ControllerGeneral;
import uy.edu.utec.controller.UsuarioController;
import uy.edu.utec.servers.dtos.LoginDTO;
import uy.edu.utec.servers.enums.EstadoUsuario;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.view.Interfaces.MenuInterfaz;
import uy.edu.utec.view.Interfaces.Principales.PanelUsuario;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginInterfaz {
    private JLabel TextoBienvenida;
    private JButton crearUsuarioButton;
    private JPasswordField passwordInput;
    private JTextField EmailInput;
    private JButton ingresarButton;
    private JLabel imagen;

    private MenuInterfaz menuInterfaz;

    //
    private JFrame frame;
    private JPanel panel;
    private ControllerGeneral controllerGeneral;

    public LoginInterfaz(ControllerGeneral controllerGeneral) {
        this.controllerGeneral = controllerGeneral;
        frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setSize(900, 500);

        crearUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerGeneral.mostrarCrearUsuario(true);
                controllerGeneral.mostrarLogin(false);
            }
        });
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsuarioController usuarioController = new UsuarioController(controllerGeneral);
                char[] passwordChars = passwordInput.getPassword();
                String password = new String(passwordChars);
                LoginDTO loginDTO = new LoginDTO(EmailInput.getText().trim(), password);
                try {
                    if(usuarioController.buscarPorEmail(loginDTO.getEmail()) == null){
                        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecta");
                    }
                    else if (usuarioController.verificarLogIn(loginDTO) && usuarioController.buscarPorEmail(loginDTO.getEmail()).getEstado() == EstadoUsuario.ACTIVO) {
                        usuarioController.setNombreUsuarioActivo(usuarioController.buscarPorEmail(loginDTO.getEmail()).getNom_usuario());
                        usuarioController.setPerfilUsuarioActivo();
                        controllerGeneral.mostrarMenuInterfaz(true);
                        controllerGeneral.mostrarLogin(false);
                        PanelUsuario panelUsuario = new PanelUsuario(controllerGeneral);
                        panelUsuario.funcionalidadesSegunPerfil();
                        controllerGeneral.getMenuInterfaz().funcionalidadesSegunPerfil();

                    } else if( usuarioController.buscarPorEmail(loginDTO.getEmail()).getEstado() !=
                            EstadoUsuario.ACTIVO){
                        JOptionPane.showMessageDialog(null, "Usuario no permitido");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecta");
                    }
                } catch (ServiciosException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }


    public void setVisible(boolean visible) {
        passwordInput.setText("");
        EmailInput.setText("");
        frame.setVisible(visible);
    }

}
