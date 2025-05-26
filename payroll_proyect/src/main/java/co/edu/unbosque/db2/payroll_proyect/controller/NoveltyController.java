package co.edu.unbosque.db2.payroll_proyect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.db2.payroll_proyect.model.dto.NoveltyDTO;
import co.edu.unbosque.db2.payroll_proyect.service.interfaces.INoveltyService;

@RestController
@RequestMapping("/api/novelties")
public class NoveltyController {

    @Autowired
    private INoveltyService noveltyService;

    @PostMapping
    public ResponseEntity<?> createNovelty(@RequestBody NoveltyDTO noveltyDTO) {
        try {
            NoveltyDTO saved = noveltyService.save(noveltyDTO);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> detAllNovelty() {
        return ResponseEntity.ok(noveltyService.findAll());
    }

}
