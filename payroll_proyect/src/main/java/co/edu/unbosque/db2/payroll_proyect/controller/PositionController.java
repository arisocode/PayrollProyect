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
import co.edu.unbosque.db2.payroll_proyect.model.dto.PositionDTO;
import co.edu.unbosque.db2.payroll_proyect.service.interfaces.IPositionService;


@RestController
@RequestMapping("/api/positions")
public class PositionController {

    @Autowired
    private IPositionService positionService;

    @PostMapping
    public ResponseEntity<?> createPosition(@RequestBody PositionDTO positionDTO) {
        try {
            PositionDTO saved = positionService.save(positionDTO);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> getBankByCode(@PathVariable String code) {
        PositionDTO found = positionService.findByCode(code);
        if (found == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Banco no encontrado."));
        }
        return ResponseEntity.ok(found);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteBankByCode(@PathVariable String code) {
        positionService.deleteByCode(code);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping
    public ResponseEntity<?> getAllPositions() {
        return ResponseEntity.ok(positionService.findAll());
    }
}