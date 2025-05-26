package co.edu.unbosque.db2.payroll_proyect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.db2.payroll_proyect.model.dto.PartyTypeDTO;
import co.edu.unbosque.db2.payroll_proyect.service.interfaces.IPartyTypeService;

@RestController
@RequestMapping("api/types")
public class PartyTypeController {

    @Autowired
    private IPartyTypeService partyTypeService;

    @PostMapping
    public ResponseEntity<?> createPartyType(@RequestBody PartyTypeDTO partyTypeDTO) {
        try {
            PartyTypeDTO saved = partyTypeService.save(partyTypeDTO);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllPartyType() {
        return ResponseEntity.ok(partyTypeService.findAll());
    }

}
