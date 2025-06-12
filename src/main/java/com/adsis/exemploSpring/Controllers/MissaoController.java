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

import com.adsis.exemploSpring.DTOs.MissaoDTO;
import com.adsis.exemploSpring.Exceptions.NaoEncontradoException;
import com.adsis.exemploSpring.services.MissaoService;

@RestController
@RequestMapping("/missoes")
public class MissaoController {

    @Autowired
    private MissaoService missaoService;

    @GetMapping
    public List<MissaoDTO> listar() {
        return missaoService.listar();
    }

    @PostMapping
    public MissaoDTO criar(@RequestBody MissaoDTO missao) {
        return missaoService.salvar(missao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MissaoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(missaoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MissaoDTO> editar(@PathVariable Long id, @RequestBody MissaoDTO entity) {

        return ResponseEntity.ok(missaoService.salvar(id, entity));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }

}
