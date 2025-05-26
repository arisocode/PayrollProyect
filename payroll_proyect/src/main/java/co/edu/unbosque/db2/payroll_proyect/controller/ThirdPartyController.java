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
import co.edu.unbosque.db2.payroll_proyect.model.dto.ThirdPartyDTO;
import co.edu.unbosque.db2.payroll_proyect.service.interfaces.IThirdPartyService;

@RestController
@RequestMapping("api/thirdparty")
public class ThirdPartyController {

    @Autowired
    private IThirdPartyService thirdPartyService;

    @PostMapping
    public ResponseEntity<?> createThirdParty(@RequestBody ThirdPartyDTO thirdPartyDTO) {
        try {
            ThirdPartyDTO saved = thirdPartyService.save(thirdPartyDTO);
            return ResponseEntity.ok(new SuccessResponse<>("Tercero creado exitosamente.", saved));
        } catch (RuntimeException ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(ex.getMessage()));
        }
    }

    @GetMapping("/{nit}")
    public ResponseEntity<?> getThirdPartyByNit(@PathVariable String nit) {
        ThirdPartyDTO found = thirdPartyService.findByNit(nit);
        if (found == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Empleado no encontrado."));
        }
        return ResponseEntity.ok(found);
    }

    @DeleteMapping("/{nit}")
    public ResponseEntity<Void> deleteThirdPartyByNit(@PathVariable String nit) {
        thirdPartyService.deleteByNiT(nit);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<?> getAllThirdParty() {
        return ResponseEntity.ok(thirdPartyService.findAll());
    }
}
