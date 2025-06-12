package com.adsis.exemploSpring.Controllers;

import com.adsis.exemploSpring.DTOs.JutsuDTO;
import com.adsis.exemploSpring.models.Jutsu;
import com.adsis.exemploSpring.services.JutsuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jutsus")
public class JutsuController {

    private final JutsuService service;

    @Autowired
    public JutsuController(JutsuService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<JutsuDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JutsuDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<JutsuDTO> criar(@RequestBody Jutsu jutsu) {
        return ResponseEntity.ok(service.salvar(jutsu));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JutsuDTO> atualizar(@PathVariable Long id, @RequestBody Jutsu jutsu) {
        return ResponseEntity.ok(service.atualizar(id, jutsu));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
