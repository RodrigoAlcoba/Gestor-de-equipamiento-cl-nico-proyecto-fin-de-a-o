package uy.edu.utec.servers.validaciones.validadores;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uy.edu.utec.servers.validaciones.anotacionesPersonalizadas.FechaNacMinima;

import java.time.LocalDate;
import java.time.Period;

public class FechaNacMinimaValidator implements ConstraintValidator<FechaNacMinima, LocalDate> {

    @Override
    public void initialize(FechaNacMinima constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate fechaNacimiento, ConstraintValidatorContext context) {
        if (fechaNacimiento == null) {
            return true; // Puede ser nulo, el @NotNull se encarga de validar eso
        }

        // Se obtiene la fecha actual
        LocalDate fechaActual = LocalDate.now();

        // Se calcula la diferencia de años
        int edad = Period.between(fechaNacimiento, fechaActual).getYears();

        // Se valida que la edad sea mayor o igual a 18 años
        return edad >= 18;
    }

}
