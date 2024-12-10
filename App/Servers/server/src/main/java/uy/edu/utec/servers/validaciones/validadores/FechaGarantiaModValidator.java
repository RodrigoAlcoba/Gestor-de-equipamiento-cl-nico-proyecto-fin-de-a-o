package uy.edu.utec.servers.validaciones.validadores;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uy.edu.utec.servers.dtos.EquipoDTO;
import uy.edu.utec.servers.validaciones.anotacionesPersonalizadas.FechaGarantiaValidaMod;

import java.time.LocalDate;

public class FechaGarantiaModValidator implements ConstraintValidator<FechaGarantiaValidaMod, EquipoDTO> {

    @Override
    public void initialize(FechaGarantiaValidaMod constraintAnnotation) {
    }

    @Override
    public boolean isValid(EquipoDTO equipoDTO, ConstraintValidatorContext context) {
        if (equipoDTO == null) {
            return true;
        }
        LocalDate garantia = equipoDTO.getGarantia();
        LocalDate adquisicion = equipoDTO.getFecAdquisicion();

        return garantia.isAfter(adquisicion);
    }
}
