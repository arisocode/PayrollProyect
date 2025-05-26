package co.edu.unbosque.db2.payroll_proyect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.db2.payroll_proyect.exception.ErrorResponse;
import co.edu.unbosque.db2.payroll_proyect.exception.SuccessResponse;
import co.edu.unbosque.db2.payroll_proyect.model.dto.ContractDTO;
import co.edu.unbosque.db2.payroll_proyect.service.interfaces.IContractService;

@RestController
@RequestMapping("/api/contracts")
public class ContractController {

    @Autowired
    private IContractService contractService;

    @PostMapping
    public ResponseEntity<?> createContract(@RequestBody ContractDTO contractDTO) {
        try {
            ContractDTO saved = contractService.save(contractDTO);
            return ResponseEntity.ok(new SuccessResponse<>("Contrato creado exitosamente.", saved));
        } catch (RuntimeException ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(ex.getMessage()));
        }
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> getContractByCode(@PathVariable String code) {
        ContractDTO found = contractService.findByCode(code);
        if (found == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Contrato no encontrado."));
        }
        return ResponseEntity.ok(found);
    }

    @PutMapping("/otrosi")
    public ResponseEntity<?> otroSiContract(@RequestBody ContractDTO contractDTO) {
        try {
            ContractDTO updated = contractService.createOtroSi(contractDTO);
            return ResponseEntity.ok(new SuccessResponse<>("Contrato modificado exitosamente (otrosí).", updated));
        } catch (RuntimeException ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(ex.getMessage()));
        }
    }


}
