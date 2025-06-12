package com.adsis.exemploSpring.Controllers;

import com.adsis.exemploSpring.models.Vila;
import com.adsis.exemploSpring.services.VilaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vila")
public class VilaController {

    private final VilaService vilaService;

    public VilaController(VilaService vilaService) {
        this.vilaService = vilaService;
    }

    @GetMapping
    public ResponseEntity<List<Vila>> listarTodas() {
        List<Vila> vilas = vilaService.listarTodas();
        if (vilas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(vilas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vila> buscarPorId(@PathVariable Long id) {
        return vilaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Vila>> buscarPorNome(@RequestParam String nome) {
        List<Vila> vilas = vilaService.buscarPorNome(nome);
        if (vilas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(vilas);
    }


    @GetMapping("/buscar-parcial")
    public ResponseEntity<List<Vila>> buscarPorNomeParcial(@RequestParam String nome) {
        List<Vila> vilas = vilaService.buscarPorNomeParcial(nome);
        if (vilas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(vilas);
    }

    @PostMapping
    public ResponseEntity<Vila> salvar(@RequestBody Vila vila) {
        Vila salva = vilaService.salvar(vila);
        return ResponseEntity.ok(salva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        vilaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
