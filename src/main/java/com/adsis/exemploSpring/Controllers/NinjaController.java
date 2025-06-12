package com.adsis.exemploSpring.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adsis.exemploSpring.DTOs.NinjaDTO;
import com.adsis.exemploSpring.services.NinjaService;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    @Autowired
    NinjaService ninjaService;

    @GetMapping
    public List<NinjaDTO> listar() {
        return ninjaService.listar();
    }

    @PostMapping
    public NinjaDTO criarNinja(@RequestBody NinjaDTO ninja) {
        return ninjaService.salvar(ninja);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NinjaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ninjaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public NinjaDTO editar(@PathVariable Long id, @RequestBody NinjaDTO ninja) {
        return ninjaService.salvar(id, ninja);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable Long id) {
        ninjaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
