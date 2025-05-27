package co.edu.unbosque.db2.payroll_proyect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.db2.payroll_proyect.exception.ErrorResponse;
import co.edu.unbosque.db2.payroll_proyect.exception.SuccessResponse;
import co.edu.unbosque.db2.payroll_proyect.model.dto.EmployeeDTO;
import co.edu.unbosque.db2.payroll_proyect.service.interfaces.IEmployeeService;


@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    //Llamo al servicio del empleado para las peticiones.
    private IEmployeeService employeeService;

    //Metood post para crear empleado
    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        try {
            EmployeeDTO saved = employeeService.save(employeeDTO);
            return ResponseEntity.ok(new SuccessResponse<>("Empleado creado exitosamente.", saved));
        } catch (RuntimeException ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(ex.getMessage()));
        }
    }

    //metodo get para traer un empleado por Id
    @GetMapping("/{nit}")
    public ResponseEntity<?> getEmployeeByNit(@PathVariable String nit) {
        EmployeeDTO found = employeeService.findByNit(nit);
        if (found == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Empleado no encontrado."));
        }
        return ResponseEntity.ok(found);
    }

    //Metodo para eliminar al empleado
    @DeleteMapping("/{nit}")
    public ResponseEntity<Void> deleteEmployeeByNit(@PathVariable String nit) {
        employeeService.deleteByNiT(nit);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/to-liquidate")
    public ResponseEntity<?> getEmployeeToBeLiquidated() {
        try {
            return ResponseEntity.ok(employeeService.getEmployeesToLiquidate());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Error al buscar empleados a liquidar."));
        }
    }
    
    @GetMapping
    public ResponseEntity<?> getAllEmployees() {
        return ResponseEntity.ok(employeeService.findAll());
    }
}
