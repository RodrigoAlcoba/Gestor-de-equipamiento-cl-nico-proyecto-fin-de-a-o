package uy.edu.utec.servers.validaciones.validadores;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uy.edu.utec.servers.dtos.creacionales.CrearEquipoDTO;
import uy.edu.utec.servers.validaciones.anotacionesPersonalizadas.FechaGarantiaValida;

import java.time.LocalDate;

public class FechaGarantiaValidator implements ConstraintValidator<FechaGarantiaValida, CrearEquipoDTO> {

    @Override
    public void initialize(FechaGarantiaValida constraintAnnotation) {
    }

    @Override
    public boolean isValid(CrearEquipoDTO crearEquipoDTO, ConstraintValidatorContext context) {
        if (crearEquipoDTO == null) {
            return true;
        }
        LocalDate garantia = crearEquipoDTO.getGarantia();
        LocalDate adquisicion = crearEquipoDTO.getFecAdquisicion();

        return garantia.isAfter(adquisicion);
    }
}
