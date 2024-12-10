package uy.edu.utec.servers.dtos;

import jakarta.annotation.Nullable;
import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.validation.constraints.*;
import uy.edu.utec.servers.enums.EstadoUsuario;
import uy.edu.utec.servers.validaciones.anotacionesPersonalizadas.FechaNacMinima;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class UsuarioDTO implements Serializable {

    private Integer ID_Usuario;

    @NotBlank(message = "El campo cédula no puede estar vacío")
    @Size(min = 9, max = 9, message = "El máximo de caracteres en campo cedula es 9")
    @Pattern(regexp = "^\\d{7}-\\d{1}$", message = "Formato de cedula incorrecto, el formato correcto es: 1111111-1")
    private String cedula;

    @NotBlank(message = "El campo email no puede estar vacío")
    @Email(message = "Email incorrecto")
    private String email;

    @NotBlank(message = "El campo primer nombre no puede estar vacío")
    @Size(min = 2, max = 20, message = "El primer nombre debe tener entre 2 y 20 caracteres")
    @Pattern(regexp = "^[A-Z][a-z]*$", message = "Formato del primer nombre incorrecto, debe comenzar en mayúscula, " +
            "no se admiten números ni espacios. (Formato correcto: Nombre)")
    private String nombre;

    /*@Size( max = 20, message = "El segundo nombre debe tener entre 2 y 20 caracteres")
    @Pattern(regexp = "^ *[A-Z][a-zA-Z ] $", message = "Formato del segundo nombre incorrecto, debe comenzar en mayúscula, no se admiten números ni caracteres especiales. (Formato correcto: Nombre)")
    */
    private String nombre2;

    @NotBlank(message = "El campo primer apellido no puede estar vacío")
    @Size(min = 2, max = 21, message = "El primer apellido debe tener entre 2 y 21 caracteres")
    @Pattern(regexp = "^[A-Z][a-z]*$", message = "Formato del primer apellido incorrecto, debe comenzar en mayúscula," +
            " no se admiten números ni espacios. (Formato correcto: Apellido)")
    private String apellido;

    @NotBlank(message = "El campo segundo apellido no puede estar vacío")
    @Size(min = 2, max = 21, message = "El segundo apellido debe tener entre 2 y 21 caracteres")
    @Pattern(regexp = "^[A-Z][a-z]*$", message = "Formato del segundo apellido incorrecto, debe comenzar en " +
            "mayúscula, no se admiten números ni espacios. (Formáto correcto: Apellido)")
    private String apellido2;

    @NotNull(message = "El campo fecha de nacimiento no puede estar vacío")
    @JsonbDateFormat("yyyy-MM-dd")
    @FechaNacMinima(message = "Para registrarte debes ser mayor de 18 años")
    private LocalDate fec_nac;

    @NotBlank(message = "El campo nombre de usuario no puede estar vacío")
    @Size(min = 6, max = 51, message = "El nombre de usuario debe tener entre 6 y 51 caracteres")
    @Pattern(regexp = "^[a-z]+\\.[a-z]+$", message = "Su nombre de usuario debe ser en el siguiente formato (ej: nombre.apellido)")
    private String nom_usuario;

    @NotNull(message = "El estado no puede estar vacío")
    private EstadoUsuario estado;

    @NotNull(message = "El campo perfil no puede estar vacío")
    private Integer perfilId;

    @NotNull(message = "El campo intitución no puede estar vacío")
    private Integer institucionId;

    @NotEmpty(message = "El campo teléfonos no puede estar vacío")
    @Size(min = 1, message = "La lista de teléfonos debe contener al menos un teléfono")
    @Size(min = 5, max = 20, message = "El teléfono debe tener entre 5 y 20 caracteres")
    @Pattern(regexp = "^[0-9]+$", message = "No se admiten letras en teléfono")
    private String telefonos;

    @NotBlank(message = "El campo contraseña no puede estar vacío")
    @Size(min = 8, max = 50, message = "La contraseña debe tener entre 8 y 50 caracteres")
    @Pattern(regexp = "(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{8,50}", message = "La contraseña debe contener mayusculas, minusculas y numeros")
    private String contrasenia;

    // Constructors

    public UsuarioDTO() {
    }

    // Getters and setters

    public Integer getID_Usuario() {
        return ID_Usuario;
    }

    public void setID_Usuario(Integer ID_Usuario) {
        this.ID_Usuario = ID_Usuario;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public LocalDate getFec_nac() {
        return fec_nac;
    }

    public void setFec_nac(LocalDate fec_nac) {
        this.fec_nac = fec_nac;
    }

    public String getNom_usuario() {
        return nom_usuario;
    }

    public void setNom_usuario(String nom_usuario) {
        this.nom_usuario = nom_usuario;
    }

    public EstadoUsuario getEstado() {
        return estado;
    }

    public void setEstado(EstadoUsuario estado) {
        this.estado = estado;
    }

    public Integer getPerfilId() {
        return perfilId;
    }

    public void setPerfilId(Integer perfilId) {
        this.perfilId = perfilId;
    }

    public Integer getInstitucionId() {
        return institucionId;
    }

    public void setInstitucionId(Integer institucionId) {
        this.institucionId = institucionId;
    }

    public String getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "ID_Usuario=" + ID_Usuario +
                ", cedula='" + cedula + '\'' +
                ", email='" + email + '\'' +
                ", nombre='" + nombre + '\'' +
                ", nombre2='" + nombre2 + '\'' +
                ", apellido='" + apellido + '\'' +
                ", apellido2='" + apellido2 + '\'' +
                ", fec_nac=" + fec_nac +
                ", nom_usuario='" + nom_usuario + '\'' +
                ", estado=" + estado +
                ", perfilId=" + perfilId +
                ", institucionId=" + institucionId +
                ", telefonos=" + telefonos +
                ", contrasenia='" + contrasenia + '\'' +
                '}';
    }
}
