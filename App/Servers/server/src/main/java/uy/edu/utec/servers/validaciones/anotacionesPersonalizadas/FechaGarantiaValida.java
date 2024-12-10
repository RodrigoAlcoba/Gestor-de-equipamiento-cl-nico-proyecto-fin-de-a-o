package uy.edu.utec.servers.validaciones.anotacionesPersonalizadas;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import uy.edu.utec.servers.validaciones.validadores.FechaGarantiaValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Documented
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FechaGarantiaValidator.class)
public @interface FechaGarantiaValida {

    String message() default "La fecha de garantía incorrecta";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
