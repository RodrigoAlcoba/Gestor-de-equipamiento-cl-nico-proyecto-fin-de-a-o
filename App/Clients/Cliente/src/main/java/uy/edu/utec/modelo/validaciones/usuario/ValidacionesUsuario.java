package uy.edu.utec.modelo.validaciones.usuario;

import jakarta.ejb.EJBTransactionRolledbackException;
import uy.edu.utec.controller.ControllerGeneral;
import javax.swing.*;
import java.util.Date;

public class ValidacionesUsuario {

    static ControllerGeneral controllerGeneral  = ControllerGeneral.getInstance();

    //VALIDACIONES PARA PRIMER NOMBRE
    public static boolean validarPNombre(JTextField PNombreInput){
        if (PNombreInput.getText().length() < 2 || PNombreInput.getText().length() > 20){
            mostrarMensaje("El campo 'Primer Nombre' debe de contener entre 2 y 20 caracteres.");
            return false;
        }
        if (PNombreInput.getText().isEmpty()){
            mostrarMensaje("El campo 'Primer Nombre' no puede estar vacío.");
            return false;
        }
        if (PNombreInput.getText().matches("\\d*")){
            mostrarMensaje("El campo 'Primer Nombre' no puede contener numeros.");
            return false;
        }
        if (PNombreInput.getText().matches("^.*[!@#$%^&*()_+\\-={}\\[\\]:;<>,.?/~].*$")){
            mostrarMensaje("El campo 'Primer Nombre' no puede contener símbolos especiales.");
            return false;
        }
        if (PNombreInput.getText().matches("^[^A-Z].*$")){
            mostrarMensaje("El campo 'Primer Nombre' debe de comenzar con mayúsculas.");
            return false;
        }else {
            System.out.println("Datos válidos en: " + PNombreInput);
            return true;
        }
    }

    //VALIDACIONES PARA SEGUNDO NOMBRE
    public static boolean validarSNombre(JTextField SNombreInput){
        if (!SNombreInput.getText().isEmpty()){
            if (SNombreInput.getText().length() < 2 || SNombreInput.getText().length() > 20){
                mostrarMensaje("El campo 'Segundo Nombre' debe de contener entre 2 y 20 caracteres.");
                return false;
            }
            if (SNombreInput.getText().matches("\\d*")){
                mostrarMensaje("El campo 'Segundo Nombre' no puede contener numeros.");
                return false;
            }
            if (SNombreInput.getText().matches("^.*[!@#$%^&*()_+\\-={}\\[\\]:;<>,.?/~].*$")){
                mostrarMensaje("El campo 'Segundo Nombre' no puede contener símbolos especiales.");
                return false;
            }
            if (SNombreInput.getText().matches("^[^A-Z].*$")){
                mostrarMensaje("El campo 'Segundo Nombre' debe de comenzar con mayúsculas.");
                return false;
            }
            if (SNombreInput.getText().isEmpty()){
                return true;
            }
        }else {
            System.out.println("Datos válidos en: " + SNombreInput);
        }
        return true;
    }

    //VALIDACIONES PARA PRIMER APELLIDO
    public static boolean validarPApellido(JTextField PApellidoInput){
        if (PApellidoInput.getText().length() < 2 || PApellidoInput.getText().length() > 21){
            mostrarMensaje("El campo 'Primer Apellido' debe de contener entre 2 y 21 caracteres.");
            return false;
        }
        if (PApellidoInput.getText().isEmpty()){
            mostrarMensaje("El campo 'Primer Apellido' no puede estar vacío.");
            return false;
        }
        if (PApellidoInput.getText().matches("\\d*")){
            mostrarMensaje("El campo 'Primer Apellido' no puede contener numeros.");
            return false;
        }
        if (PApellidoInput.getText().matches("^.*[!@#$%^&*()_+\\-={}\\[\\]:;<>,.?/~].*$")){
            mostrarMensaje("El campo 'Primer Nombre' no puede contener símbolos especiales.");
            return false;
        }
        if (PApellidoInput.getText().matches("^[^A-Z].*$")){
            mostrarMensaje("El campo 'Primer Nombre' debe de comenzar con mayúsculas.");
            return false;
        }else {
            System.out.println("Datos válidos en: " + PApellidoInput);
            return true;
        }
    }

    //VALIDACIONES PARA SEGUNDO APELLIDO
    public static boolean validarSApellido(JTextField sApellidoInput){
        if (sApellidoInput.getText().length() < 2 || sApellidoInput.getText().length() > 21){
            mostrarMensaje("El campo 'Segundo Apellido' debe de contener entre 2 y 21 caracteres.");
            return false;
        }
        if (sApellidoInput.getText().isEmpty()){
            mostrarMensaje("El campo 'Segundo Apellido' no puede estar vacío.");
            return false;
        }
        if (sApellidoInput.getText().matches("\\d*")){
            mostrarMensaje("El campo 'Segundo Apellido' no puede contener numeros.");
            return false;
        }
        if (sApellidoInput.getText().matches("^.*[!@#$%^&*()_+\\-={}\\[\\]:;<>,.?/~].*$")){
            mostrarMensaje("El campo 'Segundo Nombre' no puede contener símbolos especiales.");
            return false;
        }
        if (sApellidoInput.getText().matches("^[^A-Z].*$")){
            mostrarMensaje("El campo 'Segundo Nombre' debe de comenzar con mayúsculas.");
            return false;
        }else {
            System.out.println("Datos válidos en: " + sApellidoInput);
            return true;
        }
    }

    //VALIDACIONES CAMPO CEDULA
    public static boolean validarCedula(JTextField ciInput){
        if (ciInput.getText().isEmpty()){
            mostrarMensaje("El campo 'Cédula' no puede estar vacío.");
            return false;
        }
        if (ciInput.getText().matches("^[^-]*$\n")){
            mostrarMensaje("El campo 'Cédula' debe de contener un guión (-).");
            return false;
        }
        if (ciInput.getText().matches(".*[a-zA-Z].*")){
            mostrarMensaje("El campo 'Cédula' no debe de contener letras.");
            return false;
        }
        if (ciInput.getText().length() != 9) {
            mostrarMensaje("El campo 'Cédula' debe de contener 9 dígitos.");
            return false;
        }
            try {
                if (controllerGeneral.getUsuarioController().obtenerUsuarioPorCedula(ciInput.getText()) != null) {
                    mostrarMensaje("La cédula ingresada ya se encuentra registrada.");
                    return false;
                } else {
                    System.out.println("Datos válidos en: " + ciInput);
                    return true;
                }
            } catch (NullPointerException | EJBTransactionRolledbackException e) {
                System.out.println();
            }
            return true;
    }

    //VALIDACIONES CAMPO EMAIL
    public static boolean validarEmail(JTextField emailInput){
        if (emailInput.getText().isEmpty()){
            mostrarMensaje("El campo 'Email' no puede estar vacío.");
            return false;
        }
        if (emailInput.getText().matches("^[^@]*$")){
            mostrarMensaje("El campo 'Email' debe de contener un (@).");
            return false;
        }
        if (emailInput.getText().matches("^[^.]*$")){
            mostrarMensaje("El campo 'Email' debe de contener un punto (.).");
            return false;
        }
        if (emailInput.getText().matches("^[a-z]+$")){
            mostrarMensaje("El campo 'Email' debe estar todo en minúsculas.");
            return false;
        }
        if (emailInput.getText().length() < 6 || emailInput.getText().length() > 50){
            mostrarMensaje("El campo 'Email' debe contener entre 6 y 50 caracteres.");
            return false;
        }
        if (!emailInput.getText().matches(".+\\..+")) {
            mostrarMensaje("El campo 'Email' debe contener un dominio.");
            return false;
        }try {
            if (controllerGeneral.getUsuarioController().buscarPorEmail(emailInput.getText()) != null) {
                mostrarMensaje("El email ingresado ya se encuentra registrado.");
                return false;
            } else {
            System.out.println("Datos válidos en: " + emailInput);
            return true;
        }
        } catch (NullPointerException | EJBTransactionRolledbackException e) {
            System.out.println();
        }
        return true;
    }

    //VALIDACIONES CAMPO FEC-NAC
    public static boolean validarFecNac(Date fecha_nac){
        if (fecha_nac == null){
            mostrarMensaje("El campo 'Fecha de Nacimiento' no puede estar vacío.");
            return false;
        }else {
            System.out.println("Datos válidos en: " + fecha_nac);
            return true;
        }
    }

    //VALIDACIONES CAMPO NOM-USUARIO
    public static boolean validarNomUsu(JTextField NomUsuarioInput){
        if (NomUsuarioInput.getText().isEmpty()) {
            mostrarMensaje("El campo 'Nombre de Usuario' no puede estar vacío.");
            return false;
        }
        if (NomUsuarioInput.getText().length() < 6 || NomUsuarioInput.getText().length() > 51){
            mostrarMensaje("El campo 'Nombre de Usuario' debe de tener entre 6 y 51 caracteres.");
            return false;
        }
        if (NomUsuarioInput.getText().matches("^[^.]*$\n")){
            mostrarMensaje("El campo 'Nombre de Usuario' debe contener un (.).");
            return false;
        }
        if (NomUsuarioInput.getText().matches("^(?![A-Z])")){
            mostrarMensaje("El campo 'Nombre de Usuario' debe estar en minúsculas.");
            return false;
        }else {
            System.out.println("Datos válidos en: " + NomUsuarioInput);
            return true;
        }
    }

    //VALIDACIONES CAMPO TEL
    public static boolean validarTel(JTextField telInput){
        if (telInput.getText().isEmpty()){
            mostrarMensaje("El campo 'Teléfono Contacto' no puede estar vacío.");
            return false;
        }
        if (telInput.getText().matches(".*[a-zA-Z].*")){
            mostrarMensaje("El campo 'Teléfono Contacto' no puede contener letras.");
            return false;
        }
        if (telInput.getText().length() < 5 || telInput.getText().length() > 20){
            mostrarMensaje("El campo 'Teléfono Contacto' debe de contener entre 5 y 20 dígitos.");
            return false;
        }
        if (telInput.getText().matches("^.*[!@#$%^&*()_+\\-={}\\[\\]:;<>,.?/~].*$")){
            mostrarMensaje("El campo 'Teléfono Contacto' no puede contener símbolos especiales.");
            return false;
        }else {
            System.out.println("Datos válidos en: " + telInput);
            return true;
        }
    }

    public static void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
}
