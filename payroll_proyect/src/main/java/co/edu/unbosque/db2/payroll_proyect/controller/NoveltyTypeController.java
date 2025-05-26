package co.edu.unbosque.db2.payroll_proyect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.db2.payroll_proyect.model.dto.NoveltyTypeDTO;
import co.edu.unbosque.db2.payroll_proyect.service.interfaces.INoveltyTypeService;

@RestController
@RequestMapping("/api/noveltyTypes")
public class NoveltyTypeController {

    @Autowired
    private INoveltyTypeService noveltyTypeService;

    @PostMapping
    public ResponseEntity<?> createNoveltyType(@RequestBody NoveltyTypeDTO noveltyTypeDTO) {
        try {
            NoveltyTypeDTO saved = noveltyTypeService.save(noveltyTypeDTO);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> detAllNoveltyTypes() {
        return ResponseEntity.ok(noveltyTypeService.findAll());
    }
}
