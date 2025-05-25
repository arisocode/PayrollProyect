package co.edu.unbosque.db2.payroll_proyect.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LiquidacionResultadoDto {

    private BigDecimal cesantias;
    private BigDecimal interesesCesantias;
    private BigDecimal primaServicios;
    private BigDecimal vacaciones;
    private BigDecimal totalLiquidacion;
    private BigDecimal nominaPendiente;

    // Getters y setters
}
