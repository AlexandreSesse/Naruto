package com.adsis.exemploSpring.services;

import com.adsis.exemploSpring.models.Vila;
import com.adsis.exemploSpring.repositories.VilaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VilaService {
    private final VilaRepository vilaRepository;

    public VilaService(VilaRepository vilaRepository) {
        this.vilaRepository = vilaRepository;
    }

    public List<Vila> listarTodas() {
        return vilaRepository.findAll();
    }

    public Optional<Vila> buscarPorId(Long id) {
        return vilaRepository.findById(id);
    }

    public List<Vila> buscarPorNome(String nome) {
        return vilaRepository.findByNome(nome);
    }

    public List<Vila> buscarPorNomeParcial(String nome) {
        return vilaRepository.findByNomeContainingIgnoreCase(nome);
    }

    public Vila salvar(Vila vila) {
        return vilaRepository.save(vila);
    }

    public void deletar(Long id) {
        vilaRepository.deleteById(id);
    }
}