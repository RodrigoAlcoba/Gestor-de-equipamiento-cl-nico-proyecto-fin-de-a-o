package uy.edu.utec.servers.validaciones.anotacionesPersonalizadas;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import uy.edu.utec.servers.validaciones.validadores.FechaGarantiaModValidator;

import java.lang.annotation.*;


@Documented
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FechaGarantiaModValidator.class)
public @interface FechaGarantiaValidaMod {

    String message() default "La fecha de garantía incorrecta";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
