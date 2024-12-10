package uy.edu.utec.modelo;

public class UsuarioSignIn {

    private String email;

    private String contrasenia;

    public UsuarioSignIn(String email, String contrasenia) {
        this.email = email;
        this.contrasenia = contrasenia;
    }

    public UsuarioSignIn(){};
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
