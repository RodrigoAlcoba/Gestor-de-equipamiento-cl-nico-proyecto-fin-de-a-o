package uy.edu.utec.servers.dtos.creacionales;

import jakarta.annotation.Nullable;
import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.validation.constraints.*;
import uy.edu.utec.servers.validaciones.anotacionesPersonalizadas.FechaNacMinima;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class CreateUsuarioDTO implements Serializable {

    @NotBlank(message = "El campo cédula no puede estar vacío")
    @Size(min = 9, max = 9, message = "El máximo de caracteres en campo cédula es 9")
    @Pattern(regexp = "^\\d{7}-\\d{1}$", message = "Formato correcto de cédula: 1111111-1")
    private String cedula;

    @NotBlank(message = "El campo email no puede estar vacío")
    @Email(message = "Email incorrecto")
    private String email;

    @NotBlank(message = "El campo primer nombre no puede estar vacío")
    @Size(min = 2, max = 20, message = "El primer nombre debe tener entre 2 y 20 caracteres")
    @Pattern(regexp = "^[A-Z][a-z]*$", message = "Formáto correcto para primer nombre: Nombre")
    private String nombre;

    @Null
    //@Size(min = 2, max = 20, message = "El segundo nombre debe tener entre 2 y 20 caracteres")
    //@Pattern(regexp = "^[A-Z][a-z]*$", message = "Formáto correcto para segundo nombre: Nombre")
    private String nombre2;

    @NotBlank(message = "El campo primer apellido no puede estar vacío")
    @Size(min = 2, max = 21, message = "El primer apellido debe tener entre 2 y 21 caracteres")
    @Pattern(regexp = "^[A-Z][a-z]*$", message = "Formáto correcto del primer apellido: Apellido")
    private String apellido;

    @NotBlank(message = "El campo segundo apellido no puede estar vacío")
    @Size(min = 2, max = 21, message = "El segundo apellido debe tener entre 2 y 21 caracteres")
    @Pattern(regexp = "^[A-Z][a-z]*$", message = "Formáto correcto del segundo apellido: Apellido")
    private String apellido2;

    @NotNull(message = "El campo fecha de nacimiento no puede estar vacío")
    @JsonbDateFormat("yyyy-MM-dd")
    @FechaNacMinima(message = "Para registrarte debes ser mayor de 18 años")
    private LocalDate fec_nac;

    @NotBlank(message = "El campo nombre de usuario no puede estar vacío")
    @Size(min = 6, max = 51, message = "El nombre de usuario debe tener entre 6 y 51 caracteres")
    @Pattern(regexp = "^[a-zA-Z]+\\.[a-zA-Z]+$", message = "Formáto de nombre de usuario: nombre.apellido")
    private String nom_usuario;

    @NotBlank(message = "El campo contraseña no puede estar vacío")
    @Size(min = 8, max = 50, message = "La contraseña debe tener entre 8 y 50 caracteres")
    @Pattern(regexp = "(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])", message = "La contraseña debe contener mayusculas, minusculas y numeros")
    private String contrasenia;

    //todo refactor usando @GroupSequence({Default.class, ValidTelefonos.class})

    @NotEmpty(message = "El campo teléfonos no puede estar vacío")
    @Size(min = 1, message = "La lista de teléfonos debe contener al menos un teléfono")
    private List<@NotBlank(message = "No puede dejar el campo de télefono vacío")
    @Size(min = 5, max = 20, message = "El teléfono debe tener entre 5 y 20 caracteres") String> telefonos;

    @NotNull
    private Integer perfilId;

    @NotNull
    private Integer institucionId;

    // Constructors

    public CreateUsuarioDTO() {
    }

    // Getters and setters

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


    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
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

    public List<String> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<String> telefonos) {
        this.telefonos = telefonos;
    }


}
