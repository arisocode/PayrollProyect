package co.edu.unbosque.db2.payroll_proyect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.db2.payroll_proyect.model.entity.PayrollLiquidation;
import co.edu.unbosque.db2.payroll_proyect.service.interfaces.IPayrollLiquidationService;

@RestController
@RequestMapping("/api/payroll")
public class PayrollLiquidateController {

    @Autowired
    private IPayrollLiquidationService payrollLiquidationService;

    @PostMapping("/{employeeId}")
    public ResponseEntity<?> liquidarEmpleado(@PathVariable Integer employeeId) {
        try {
            PayrollLiquidation liquidacion = payrollLiquidationService.liquidarEmpleado(employeeId);
            return ResponseEntity.ok(liquidacion);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al liquidar empleado: " + e.getMessage());
        }
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<PayrollLiquidation>> getByEmployeeId(@PathVariable Integer employeeId) {
        return ResponseEntity.ok(payrollLiquidationService.getByEmployeeId(employeeId));
    }
}

