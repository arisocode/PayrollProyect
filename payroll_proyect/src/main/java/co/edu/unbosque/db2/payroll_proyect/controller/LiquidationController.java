package co.edu.unbosque.db2.payroll_proyect.controller;


import co.edu.unbosque.db2.payroll_proyect.model.dto.LiquidacionResultadoDto;
import co.edu.unbosque.db2.payroll_proyect.service.services.LiquidacionContrato;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/liquidation")
public class LiquidationController {

    private final LiquidacionContrato liquidacionContrato;

    public LiquidationController(LiquidacionContrato liquidacionContrato) {
        this.liquidacionContrato = liquidacionContrato;
    }

    @GetMapping("/calcular")
    public ResponseEntity<LiquidacionResultadoDto> calcular(
            @RequestParam int idUsuario,
            @RequestParam int diasTrabajados,
            @RequestParam BigDecimal salario,
            @RequestParam int diasFaltantesSueldo,
            @RequestParam int diasVacacionesPendientes
    ) {
        LiquidacionResultadoDto resultado = liquidacionContrato.
                calcularLiquidacion(diasTrabajados, salario, diasFaltantesSueldo, diasVacacionesPendientes);
        return ResponseEntity.ok(resultado);
    }


}
