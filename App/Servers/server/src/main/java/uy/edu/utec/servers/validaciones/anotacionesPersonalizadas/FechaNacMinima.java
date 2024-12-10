package uy.edu.utec.servers.validaciones.anotacionesPersonalizadas;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import uy.edu.utec.servers.validaciones.validadores.FechaNacMinimaValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FechaNacMinimaValidator.class)
public @interface FechaNacMinima {

    String message() default "La fecha de nacimiento debe ser al menos 18 años atrás";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
