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
import co.edu.unbosque.db2.payroll_proyect.model.dto.BankDTO;
import co.edu.unbosque.db2.payroll_proyect.service.interfaces.IBankService;

@RestController
@RequestMapping("/api/banks")
public class BankController {

    @Autowired
    //Llamo al servicio del banco para las peticiones.
    private IBankService bankService;

    //Metood post para crear banco
    @PostMapping
    public ResponseEntity<?> createBank(@RequestBody BankDTO bankDTO) {
        try {
            BankDTO saved = bankService.save(bankDTO);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    //metodo get para traer un banco por codigo
    @GetMapping("/{codigo}")
    public ResponseEntity<?> getBankByCode(@PathVariable String codigo) {
        BankDTO found = bankService.findByCode(codigo);
        if (found == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Banco no encontrado."));
        }
        return ResponseEntity.ok(found);
    }

    //Metodo para eliminar un banco
    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> deleteBankByCode(@PathVariable String codigo) {
        bankService.deleteByCode(codigo);
        return ResponseEntity.noContent().build();
    }

    //Metodo para traer todos los bancos
    @GetMapping
    public ResponseEntity<?> getAllBanks() {
        return ResponseEntity.ok(bankService.findAll());
    }
    
}