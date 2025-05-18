package co.edu.unbosque.db2.payroll_proyect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.db2.payroll_proyect.model.dto.ContractTypeDTO;
import co.edu.unbosque.db2.payroll_proyect.service.interfaces.IContractTypeService;

@RestController
@RequestMapping("/api/contractTypes")
public class ContractTypeController {

    @Autowired
    private IContractTypeService contractTypeService;

    @PostMapping
    public ResponseEntity<?> createContractType(@RequestBody ContractTypeDTO contractTypeDTO) {
        try {
            ContractTypeDTO saved = contractTypeService.save(contractTypeDTO);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllContractType() {
        return ResponseEntity.ok(contractTypeService.findAll());
    }

}